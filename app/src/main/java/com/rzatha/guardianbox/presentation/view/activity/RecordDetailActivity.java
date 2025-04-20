package com.rzatha.guardianbox.presentation.view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rzatha.guardianbox.R;
import com.rzatha.guardianbox.databinding.ActivityRecordDetailBinding;

public class RecordDetailActivity extends AppCompatActivity {

    private ActivityRecordDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRecordDetailBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

    }


}