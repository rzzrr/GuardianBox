package com.rzatha.guardianbox.presentation.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzatha.guardianbox.data.repository.RepositoryImpl;
import com.rzatha.guardianbox.databinding.FragmentRecordBinding;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.presentation.adapter.RecordAdapter;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;


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

        viewModel = new ViewModelProvider(getActivity()).get(RecordViewModel.class);

        binding.fabAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int randomId = new Random().nextInt();
                viewModel.insertLogin(new Login(randomId,"rn", "login", "password"))
                        .subscribeOn(Schedulers.newThread())
                        .subscribe();
                viewModel.insertNote(new Note(randomId, "name note", "text note"))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();;
                viewModel.insertFolder(new Folder(randomId, "folder name"))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();;
            }
        });

        viewModel.ldRecords.observe(getActivity(), new Observer<List<Record>>() {
            @Override
            public void onChanged(List<Record> records) {
                recordAdapter.submitList(records);
            }
        });
    }

    public static RecordFragment newInstance() {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    
}