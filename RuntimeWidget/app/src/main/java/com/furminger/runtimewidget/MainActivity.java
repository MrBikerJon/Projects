package com.furminger.runtimewidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout layout = findViewById(R.id.layout);

        DatePicker datePicker = new DatePicker(this);
        layout.addView(datePicker);
    }
}