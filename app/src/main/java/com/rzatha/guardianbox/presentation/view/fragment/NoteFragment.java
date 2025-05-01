package com.rzatha.guardianbox.presentation.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rzatha.guardianbox.databinding.FragmentNoteBinding;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

public class NoteFragment extends Fragment {

    private static final String TAG = NoteFragment.class.getSimpleName();;
    private static final String ARG_NOTE = "note";
    private static final String ARG_OPEN_TYPE = "open_type";
    private FragmentOpenType openType;
    private Note note;
    private FragmentNoteBinding binding;
    private RecordViewModel viewModel;

    public NoteFragment() {
    }

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle args = new Bundle();

        if (note != null) {
            args.putSerializable(ARG_NOTE, note);
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.EDIT);
        } else {
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.CREATE);
        }

        fragment.setArguments(args);
        return fragment;
    }

    public static NoteFragment newInstance() {
        return newInstance(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            openType = (FragmentOpenType) getArguments().getSerializable(ARG_OPEN_TYPE);

            if (openType == FragmentOpenType.EDIT){
                note = (Note) getArguments().getSerializable(ARG_NOTE);
                startEditMode(note);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void startEditMode(Note note){
        if (note == null)
            throw new IllegalArgumentException("Unable to start editing: note is null");
        binding.tvNameNote.setText(note.getName());
        binding.tvNote.setText(note.getText());

        binding.buttonMove.setVisibility(View.VISIBLE);
        binding.buttonRemove.setVisibility(View.VISIBLE);
        binding.buttonShare.setVisibility(View.VISIBLE);
    }

    private void startCreateMode() {
        binding.buttonMove.setVisibility(View.GONE);
        binding.buttonRemove.setVisibility(View.GONE);
        binding.buttonShare.setVisibility(View.GONE);
    }

    private void closeFragment(){
        getParentFragmentManager().beginTransaction().remove(this).commit();
    }
}