package jonathan.furminger.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public int playerTurn = 0;
    public String[][] playingField = new String[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setValue(View v) {

        Button b = (Button) v;

        if(playerTurn == 0) {
            b.setText("O");
            playerTurn = 1;
            Log.d("success", "Set box to 'O'");
        }
        else {
            b.setText("X");
            playerTurn = 0;
            Log.d("success", "Set box to 'X'");
        }

        b.setEnabled(false);
    }

    public void checkIfGameOver() {

    }

    public void getInputAndOutputToText(View v) {

        String s = ((TextView) findViewById(R.id.editTextTextPersonName)).getText().toString();
        ((TextView) findViewById(R.id.textView2)).setText(s);

    }


}