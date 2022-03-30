package com.furminger.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.textViewState)).setText("onCreate()\n");
    }

    @Override
    protected void onStart() {
        super.onStart();
        ((TextView)findViewById(R.id.textViewState)).append("onStart()\n");

    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView)findViewById(R.id.textViewState)).append("onResume()\n");

    }

    @Override
    protected void onPause() {
        super.onPause();
        ((TextView)findViewById(R.id.textViewState)).append("onPause()\n");

    }


    @Override
    protected void onStop() {
        super.onStop();
        ((TextView)findViewById(R.id.textViewState)).append("onStop()\n");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        ((TextView)findViewById(R.id.textViewState)).append("onRestart()\n");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((TextView)findViewById(R.id.textViewState)).append("onDestroy()\n");

    }

}