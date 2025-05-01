package com.rzatha.guardianbox.presentation.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzatha.guardianbox.R;
import com.rzatha.guardianbox.databinding.FragmentFolderBinding;
import com.rzatha.guardianbox.databinding.FragmentNoteBinding;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.presentation.view.OnCloseFragmentListener;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FolderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FolderFragment extends Fragment {

    private static final String TAG = NoteFragment.class.getSimpleName();;
    private static final String ARG_NOTE = "note";
    private static final String ARG_OPEN_TYPE = "open_type";
    private FragmentOpenType openType;
    private Folder folder;
    private FragmentFolderBinding binding;
    private OnCloseFragmentListener onCloseFragmentListener;
    private RecordViewModel viewModel;

    public FolderFragment() {
    }

    public static FolderFragment newInstance(Folder folder) {
        FolderFragment fragment = new FolderFragment();
        Bundle args = new Bundle();

        if (folder != null) {
            args.putSerializable(ARG_NOTE, folder);
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.EDIT);
        } else {
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.CREATE);
        }

        fragment.setArguments(args);
        return fragment;
    }

    public static FolderFragment newInstance() {
        return newInstance(null);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        onCloseFragmentListener = (OnCloseFragmentListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            openType = (FragmentOpenType) getArguments().getSerializable(ARG_OPEN_TYPE);

            if (openType == FragmentOpenType.EDIT){
                folder = (Folder) getArguments().getSerializable(ARG_NOTE);
                startEditMode(folder);
            } else if (openType == FragmentOpenType.CREATE) {
                startCreateMode();
            } else {
                Log.d(TAG, "Unable to get open type");
                closeFragment();
            }

        } else {
            openType = FragmentOpenType.CREATE;
        }
        viewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.buttonSave.setOnClickListener(view -> {
            String folderName = binding.etFolderName.getText().toString().trim();
            Folder newFolder = null;

            if (!folderName.isEmpty()) {

                if (openType == FragmentOpenType.CREATE) {
                    newFolder = new Folder(folderName);

                } else if (openType == FragmentOpenType.EDIT) {
                    newFolder = folder;
                    folder.setName(folderName);
                }

                viewModel.insertFolder(newFolder)
                        .subscribeOn(Schedulers.newThread())
                        .subscribe();

                closeFragment();
            }
        });
        binding.buttonRemove.setOnClickListener(view -> {

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFolderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void startEditMode(Folder folder){
        if (folder == null)
            throw new IllegalArgumentException("Unable to start editing: note is null");
        binding.etFolderName.setText(folder.getName());
        binding.tvHeader.setText(folder.getName());

        binding.buttonRemove.setVisibility(View.VISIBLE);
    }

    private void startCreateMode() {
        binding.buttonRemove.setVisibility(View.GONE);
    }

    private void closeFragment(){
        getParentFragmentManager().beginTransaction().remove(this).commit();
        onCloseFragmentListener.onCloseFragment();
    }
}