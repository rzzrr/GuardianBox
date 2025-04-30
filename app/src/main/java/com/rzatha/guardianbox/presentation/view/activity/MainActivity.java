package com.rzatha.guardianbox.presentation.view.activity;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static com.rzatha.guardianbox.presentation.view.RecordFragmentHelper.getRightFragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.databinding.ActivityMainBinding;
import com.rzatha.guardianbox.databinding.MenuAddRecordBinding;
import com.rzatha.guardianbox.domain.model.Folder;
import com.rzatha.guardianbox.domain.model.Login;
import com.rzatha.guardianbox.domain.model.Note;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.presentation.view.fragment.FragmentType;
import com.rzatha.guardianbox.presentation.view.fragment.RecordFragment;

public class MainActivity extends AppCompatActivity
        implements RecordFragment.OnRecordClickListener, RecordFragment.OnAddRecordClickListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        launchFragment(binding.fragmentContainerViewList, RecordFragment.newInstance());
    }

    @Override
    public void onRecordClick(Record record) {
        if (record instanceof Login) {
            launchDependsOfOrientation(record, FragmentType.LOGIN_FRAGMENT);
        } else if (record instanceof Note) {
            launchDependsOfOrientation(record, FragmentType.NOTE_FRAGMENT);
        } else if (record instanceof Folder) {
            launchDependsOfOrientation(record, FragmentType.FOLDER_FRAGMENT);
        }

    }

    private void launchFragment(View fragmentContainer, Fragment fragment) {
        //getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }

    private void launchDependsOfOrientation(Record record, FragmentType fragmentType) {
        if (isLandscape()) {
            Fragment fragment = getRightFragment(fragmentType, record);
            launchFragment(binding.fragmentContainerViewEdit, fragment);
        } else {
            launchActivity(record);
        }
    }

    private void launchDependsOfOrientation(FragmentType fragmentType) {
        launchDependsOfOrientation(null, fragmentType);
    }

    private void launchActivity(Record record) {
        Intent intent = RecordDetailActivity.newIntent(record, this);
        startActivity(intent);
    }

    private Boolean isLandscape() {
        return getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onAddRecordClick() {
        showMenu();
    }

    private void showMenu() {
        MenuAddRecordBinding menuBinding = MenuAddRecordBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
        dialog.setContentView(menuBinding.getRoot());
        dialog.setCancelable(true);

        menuBinding.buttonNewLogin.setOnClickListener(view -> {
            launchDependsOfOrientation(FragmentType.LOGIN_FRAGMENT);
            dialog.dismiss();
        });
        menuBinding.buttonNewNote.setOnClickListener(view -> {
            launchDependsOfOrientation(FragmentType.NOTE_FRAGMENT);
            dialog.dismiss();
        });
        menuBinding.buttonNewFolder.setOnClickListener(view -> {
            launchDependsOfOrientation(FragmentType.FOLDER_FRAGMENT);
            dialog.dismiss();
        });

        dialog.show();
    }

}