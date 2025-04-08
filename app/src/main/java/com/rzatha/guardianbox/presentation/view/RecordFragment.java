package com.rzatha.guardianbox.presentation.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.databinding.FragmentRecordBinding;
import com.rzatha.guardianbox.presentation.adapter.RecordAdapter;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;


public class RecordFragment extends Fragment {

    private FragmentRecordBinding binding;
    private RecordViewModel viewModel;
    private RecordAdapter recordAdapter = new RecordAdapter();

    //Удалить
    private RepositoryImpl repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRecordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repository = new RepositoryImpl(getActivity().getApplication());

        binding.rvRecord.setAdapter(recordAdapter);

        recordAdapter.submitList(repository.testList);
    }

    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    
}