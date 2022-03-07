package jonathan.furminger.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import jonathan.furminger.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final int PLAYER = 1;
    private final int ANDROID = 2;

    private ActivityMainBinding binding;
    private Result result;

    public int playerTurn = PLAYER;
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

        if(playerTurn == PLAYER) {
            b.setText("X");
            b.setStatus(PLAYER);
            playerTurn = ANDROID;
        }
        else {
            b.setText("O");
            b.setStatus(200);
            playerTurn = PLAYER;
        }

        numberOfTurns++;
        result = isGameOver();
        if(result.getGameOver()) {
            String gameResult;
            switch(result.getWinningPlayer()) {
                case PLAYER :
                    gameResult = "You";
                    break;
                case ANDROID :
                    gameResult = "Android";
                    break;
                default :
                    gameResult = "No-one. It was a draw";

            }
            Snackbar.make(binding.layoutMain, "Game Over. The winner was: " + gameResult,
                    Snackbar.LENGTH_LONG).setAction("OK", null).show();
            resetGame();
        }
    }

    public Result isGameOver() {

        // 0 in winningPlayer field indicates no winner
        Result result = new Result(false, 0);

        // if 9 turns have been played, the result is a draw
        if(numberOfTurns == 9) {
            result.setResult(true, 0);
        }

        // check rows for complete line.  600 means player 2 won, 3 means player 1 won
        for(int row = 0; row < 3; row++) {
            int check = 0;

            for(int col = 0; col < 3; col++) {
                check += playingField[row][col].getStatus();
            }

            if(check == 3) {
                result.setResult(true, PLAYER);
            }

            if(check == 600) {
                result.setResult(true, ANDROID);
            }
        }

        // check columns for complete line.  600 means player 2 won, 3 means player 1 won
        for(int col = 0; col < 3; col++) {
            int check = 0;

            for(int row = 0; row < 3; row++) {
                check += playingField[row][col].getStatus();
            }

            if(check == 3) {
                result.setResult(true, PLAYER);
            }
            if(check == 600) {
                result.setResult(true, ANDROID);
            }
        }

        // check diagonals
        int check = playingField[0][0].getStatus() + playingField[1][1].getStatus() + playingField[2][2].getStatus();

        if(check == 3) {
            result.setResult(true, PLAYER);
        }
        else if(check == 600) {
            result.setResult(true, ANDROID);
        }

        check = playingField[0][2].getStatus() + playingField[1][1].getStatus() + playingField[2][0].getStatus();

        if(check == 3) {
            result.setResult(true, PLAYER);
        }
        else if(check == 600) {
            result.setResult(true, ANDROID);
        }

        return result;
    }

    public void resetGame() {
        playerTurn = PLAYER;
        numberOfTurns = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                playingField[row][col].setEnabled(true);
                playingField[row][col].setText("Button");
                playingField[row][col].setStatus(0);
            }
        }
    }

     class Result {

        private boolean isGameOver;
        private int winningPlayer;

        public Result(boolean isGameOver, int winningPlayer) {
            this.isGameOver = isGameOver;
            this.winningPlayer = winningPlayer;
        }

        public boolean getGameOver() {
            return isGameOver;
        }

        public int getWinningPlayer() {
            return winningPlayer;
        }

        public void setResult(boolean gameOver, int winningPlayer) {
            isGameOver = gameOver;
            this.winningPlayer = winningPlayer;
        }

    }

}