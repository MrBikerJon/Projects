package com.ebookfrenzy.eventexample;

import androidx.appcompat.app.AppCompatActivity;
import com.ebookfrenzy.eventexample.databinding.ActivityMainBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.myButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        binding.statusText.setText("Long button click");
                        return false;
                    }
                }
        );

        binding.myButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        binding.statusText.setText("Button clicked");
                    }
                }
        );
    }
}