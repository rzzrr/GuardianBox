package com.rzatha.guardianbox.presentation.view.activity;

import static com.rzatha.guardianbox.presentation.view.RecordFragmentHelper.getFragmentType;
import static com.rzatha.guardianbox.presentation.view.RecordFragmentHelper.getRightFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rzatha.guardianbox.databinding.ActivityRecordDetailBinding;
import com.rzatha.guardianbox.domain.model.Record;
import com.rzatha.guardianbox.presentation.view.OnCloseFragmentListener;
import com.rzatha.guardianbox.presentation.view.fragment.FragmentType;

public class RecordDetailActivity extends AppCompatActivity implements OnCloseFragmentListener {

    private static final String EXTRA_RECORD = "record";
    private static final String EXTRA_FRAGMENT_TYPE = "fragment_type";
    private ActivityRecordDetailBinding binding;
    private Record record;
    private FragmentType fragmentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            record = getIntent().getSerializableExtra(EXTRA_RECORD, Record.class);
            fragmentType = getIntent().getSerializableExtra(EXTRA_FRAGMENT_TYPE, FragmentType.class);
        }

        if (record != null){
            FragmentType fragmentType = getFragmentType(record);
            Fragment fragment = getRightFragment(fragmentType, record);
            launchFragment(binding.getRoot(), fragment);
        } else {

            launchFragment(binding.getRoot(), getRightFragment(fragmentType));
        }

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    public static Intent newIntent(Record record, Context context) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra(EXTRA_RECORD, record);
        return intent;
    }
    public static Intent newIntent(FragmentType fragmentType, Context context) {
        Intent intent = new Intent(context, RecordDetailActivity.class);
        intent.putExtra(EXTRA_FRAGMENT_TYPE, fragmentType);
        return intent;
    }

    private void launchFragment(View fragmentContainer, Fragment fragment) {

        //getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(fragmentContainer.getId(), fragment)
                .addToBackStack(null)
                .commit();
    }
    @Override
    public void onCloseFragment() {
        finish();
    }


}