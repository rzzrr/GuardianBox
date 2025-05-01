package com.rzatha.guardianbox.presentation.view.fragment;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzatha.guardianbox.databinding.FragmentRecordBinding;
import com.rzatha.guardianbox.databinding.MenuAddRecordBinding;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.presentation.adapter.RecordAdapter;
import com.rzatha.guardianbox.presentation.view.activity.RecordDetailActivity;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RecordFragment extends Fragment {

    private OnRecordClickListener onRecordClickListener;
    private OnAddRecordClickListener onAddRecordClickListener;
    private FragmentRecordBinding binding;
    private RecordViewModel viewModel;
    private final ItemTouchHelper itemTouchHelper =
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
                @Override
                public boolean onMove(
                        @NonNull RecyclerView recyclerView,
                        @NonNull RecyclerView.ViewHolder viewHolder,
                        @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    Record record = recordAdapter.getCurrentList().get(position);
                    viewModel.deleteRecord(record)
                            .subscribeOn(Schedulers.io())
                            .subscribe();
                }
            });
    private final RecordAdapter recordAdapter = new RecordAdapter();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        onRecordClickListener = (OnRecordClickListener) context;
        onAddRecordClickListener = (OnAddRecordClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(RecordViewModel.class);
        binding.rvRecord.setAdapter(recordAdapter);
        itemTouchHelper.attachToRecyclerView(binding.rvRecord);

        binding.fabAddRecord.setOnClickListener(v -> {
            onAddRecordClickListener.onAddRecordClick();
        });

        viewModel.allRecords.observe(
                getActivity(),
                records -> recordAdapter.submitList(records)
        );

        recordAdapter.setOnRecordClickListener(record -> {
            onRecordClickListener.onRecordClick(record);
        });
    }

    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnRecordClickListener {
        void onRecordClick(Record record);
    }

    public interface OnAddRecordClickListener {
        void onAddRecordClick();
    }



}