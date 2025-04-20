package com.rzatha.guardianbox.presentation.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rzatha.guardianbox.databinding.FragmentLoginEditBinding;
import com.rzatha.guardianbox.domain.model.Login;


public class LoginEditFragment extends Fragment {

    private static final String TAG = "LoginEditFragment";
    private static final String ARG_LOGIN = "login";
    private static final String ARG_OPEN_TYPE = "login";
    private OPEN_TYPE openType;
    private Login login;
    private FragmentLoginEditBinding binding;

    public LoginEditFragment() {
    }

    public static LoginEditFragment newInstance(Login login) {
        LoginEditFragment fragment = new LoginEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LOGIN, login);
        args.putSerializable(ARG_OPEN_TYPE, OPEN_TYPE.EDIT);
        fragment.setArguments(args);
        return fragment;
    }

    public static LoginEditFragment newInstance() {
        LoginEditFragment fragment = new LoginEditFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_OPEN_TYPE, OPEN_TYPE.CREATE);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            openType = (OPEN_TYPE) getArguments().getSerializable(ARG_OPEN_TYPE);

            if (openType == OPEN_TYPE.EDIT){
                login = (Login) getArguments().getSerializable(ARG_LOGIN);
                startEditMode(login);
            } else if (openType == OPEN_TYPE.CREATE) {
                //
            } else {
                Log.d(TAG, "Unable to get open type");
                closeFragment();
            }

        } else {
            openType = OPEN_TYPE.CREATE;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginEditBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private enum OPEN_TYPE {
        CREATE, EDIT
    }

    private void startEditMode(Login login){
        if (login == null) throw new IllegalArgumentException("Unable to start editing: login is null");
        //binding заполнение значениями логина
    }

    private void closeFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}