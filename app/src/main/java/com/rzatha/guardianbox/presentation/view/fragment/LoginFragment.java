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

import com.rzatha.guardianbox.databinding.FragmentLoginBinding;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.presentation.viewmodel.RecordViewModel;

import io.reactivex.rxjava3.schedulers.Schedulers;


public class LoginFragment extends Fragment {

    private static final String TAG = LoginFragment.class.getSimpleName();
    private static final String ARG_LOGIN = "login";
    private static final String ARG_OPEN_TYPE = "open_type";
    private FragmentOpenType openType;
    private Login login;
    private FragmentLoginBinding binding;
    private RecordViewModel viewModel;

    public LoginFragment() {
    }

    public static LoginFragment newInstance(Login login) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        if (login != null) {
            args.putSerializable(ARG_LOGIN, login);
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.EDIT);
        } else {
            args.putSerializable(ARG_OPEN_TYPE, FragmentOpenType.CREATE);
        }

        fragment.setArguments(args);
        return fragment;
    }

    public static LoginFragment newInstance() {
        return newInstance(null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {

            openType = (FragmentOpenType) getArguments().getSerializable(ARG_OPEN_TYPE);

            if (openType == FragmentOpenType.EDIT) {
                login = (Login) getArguments().getSerializable(ARG_LOGIN);
                startEditMode(login);
            } else if (openType == FragmentOpenType.CREATE) {
                startCreateMode();
            } else {
                Log.d(TAG, "Unable to get open type");
                closeFragment();
            }

        } else {
            openType = FragmentOpenType.CREATE;
        }

        viewModel = new ViewModelProvider(getActivity()).get(RecordViewModel.class);

        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.buttonSave.setOnClickListener(view -> {

            String resourceName = binding.etResourceName.getText().toString();
            String loginValue = binding.etLoginValue.getText().toString();
            String passwordValue = binding.etPasswordValue.getText().toString();

            Login newLogin = null;
            if (openType == FragmentOpenType.CREATE) {
                newLogin = new Login(resourceName, loginValue, passwordValue);

            } else if (openType == FragmentOpenType.EDIT) {
                newLogin = login;
                login.setResourceName(resourceName);
                login.setLogin(loginValue);
                login.setPassword(passwordValue);
            }

            viewModel.insertLogin(newLogin)
                    .subscribeOn(Schedulers.newThread())
                    .subscribe();

            closeFragment();
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void startEditMode(Login login) {
        if (login == null)
            throw new IllegalArgumentException("Unable to start editing: login is null");
        binding.tvHeader.setText(login.getResourceName());
        binding.etResourceName.setText(login.getResourceName());
        binding.etLoginValue.setText(login.getLogin());
        binding.etPasswordValue.setText(login.getPassword());

        binding.buttonMove.setVisibility(View.VISIBLE);
        binding.buttonRemove.setVisibility(View.VISIBLE);
        binding.buttonShare.setVisibility(View.VISIBLE);
    }

    private void startCreateMode() {
        binding.buttonMove.setVisibility(View.GONE);
        binding.buttonRemove.setVisibility(View.GONE);
        binding.buttonShare.setVisibility(View.GONE);
    }

    private void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}