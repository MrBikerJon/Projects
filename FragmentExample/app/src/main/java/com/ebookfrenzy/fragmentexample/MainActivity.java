package com.ebookfrenzy.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ebookfrenzy.fragmentexample.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity implements ToolbarFragment.ToolbarListener {

    private ActivityMainBinding binding;

    public void onButtonClick(int fontsize, String text) {
        TextFragment textFragment = (TextFragment) getSupportFragmentManager().findFragmentById(R.id.text_fragment);

        textFragment.changeTextProperties(fontsize, text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }


    public void fabClicked(View v) {
        Log.i("TAG", "It worked");
        Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_LONG).show();
    }

}