package com.ebookfrenzy.serviceexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import static androidx.core.app.JobIntentService.enqueueWork;

import com.ebookfrenzy.serviceexample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static final int SERVICE_ID = 1001;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void buttonClick(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
}