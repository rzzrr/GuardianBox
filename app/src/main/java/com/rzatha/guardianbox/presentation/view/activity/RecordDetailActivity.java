package com.rzatha.guardianbox.presentation.view.activity;

import static com.rzatha.guardianbox.presentation.view.RecordFragmentHelper.getFragmentType;
import static com.rzatha.guardianbox.presentation.view.RecordFragmentHelper.getRightFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.databinding.ActivityRecordDetailBinding;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.presentation.view.fragment.FragmentType;
import com.rzatha.guardianbox.presentation.view.fragment.LoginFragment;

public class RecordDetailActivity extends AppCompatActivity {

    private static final String EXTRA_RECORD = "record";
    private ActivityRecordDetailBinding binding;
    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            record = getIntent().getSerializableExtra(EXTRA_RECORD, Record.class);
        }

        FragmentType fragmentType = getFragmentType(record);

        Fragment fragment = getRightFragment(fragmentType, record);
        launchFragment(binding.getRoot(), fragment);
    }

    public static Intent newIntent(Record record, Context context) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra(EXTRA_RECORD, record);
        return intent;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, RecordDetailActivity.class);
    }

    private void launchFragment(Record record) {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(
                        binding.getRoot().getId(),
                        LoginFragment.newInstance()
                )
                .commit();
    }

    private void launchFragment(View fragmentContainer, Fragment fragment) {

        //getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }


}