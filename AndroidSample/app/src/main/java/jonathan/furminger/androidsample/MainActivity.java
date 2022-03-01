package jonathan.furminger.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
//import android.widget.EditText;
//import android.widget.TextView;
import jonathan.furminger.androidsample.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    public void convertCurrency(View view) {

//        EditText dollarText = findViewById(R.id.dollarText);
//        TextView textView = findViewById(R.id.textView);

//        if(!dollarText.getText().toString().equals("")) {
        if(!binding.dollarText.getText().toString().equals("")) {

//            float dollarValue = Float.parseFloat((dollarText.getText().toString()));
            float dollarValue = Float.valueOf((binding.dollarText.getText().toString()));

            float euroValue = dollarValue * 0.85F;
//            textView.setText((String.format(Locale.ENGLISH, "%f", euroValue)));
            binding.textView.setText((String.format(Locale.ENGLISH, "%f", euroValue)));

        } else {
            binding.textView.setText(R.string.no_value_string);
//            textView.setText(R.string.no_value_string);
        }
    }
}