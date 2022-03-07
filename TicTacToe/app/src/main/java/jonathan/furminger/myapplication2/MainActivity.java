package jonathan.furminger.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import jonathan.furminger.myapplication2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public int playerTurn = 1;
    public int numberOfTurns = 0;
    public MyButton[][] playingField = new MyButton[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        playingField[0][0] = binding.button1;
        playingField[0][1] = binding.button2;
        playingField[0][2] = binding.button3;
        playingField[1][0] = binding.button4;
        playingField[1][1] = binding.button5;
        playingField[1][2] = binding.button6;
        playingField[2][0] = binding.button7;
        playingField[2][1] = binding.button8;
        playingField[2][2] = binding.button9;
    }

    public void setValue(View v) {

        MyButton b = (MyButton) v;

        b.setEnabled(false);

        if(playerTurn == 1) {
            b.setText("O");
            b.setStatus(1);
            playerTurn = 2;
        }
        else {
            b.setText("X");
            b.setStatus(2);
            playerTurn = 1;
        }

        numberOfTurns++;
        if(isGameOver()) {
            resetGame();
        }
    }

    public boolean isGameOver() {
        if(numberOfTurns == 9) return true;

        boolean isGameOver = false;

        for(int row = 0; row < 3; row++) {
            int check = 0;

            for(int col = 0; col < 3; col++) {
                check += playingField[row][col].getStatus();
            }

            if(check == 3 || check == 6) {
                isGameOver = true;
                Snackbar.make(binding.layoutMain, "Winner player " + (check / 3) + " in row " + row, Snackbar.LENGTH_LONG).setAction("OK", null).show();
            }
        }

        return isGameOver;
    }

    public void resetGame() {
        numberOfTurns = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                playingField[row][col].setEnabled(true);
                playingField[row][col].setText("Button");
                playingField[row][col].setStatus(0);
            }
        }
    }

}