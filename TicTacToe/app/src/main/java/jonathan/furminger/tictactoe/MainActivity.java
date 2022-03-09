package jonathan.furminger.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

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

    /**
     * This method sets the value in a box, if the player clicks a box the
     * text value of the box (myButton object) is set to "X" and the status is
     * set to show that the player clicked the box. The method also currently
     * checks if the game is over and handles the result.
     * It is currently called by the "onClick" property of the button
     * @param v
     */



    public void setValue(View v) {

        MyButton b = (MyButton) v;

        // Disable the button so it can't be clicked on again
        b.setEnabled(false);

        // Player's turn, set button to X
        b.setText("X");
        b.setStatus(PLAYER);

        // check if still needed
        playerTurn = ANDROID;

        numberOfTurns++;
        result = isGameOver();
        if(result.getGameOver()) {
                Snackbar.make(binding.layoutMain, "Game Over. The winner was: " + checkWinningPlayer(),
                        Snackbar.LENGTH_LONG).setAction("OK", null).show();
                resetGame();
        }

        // update status
        binding.textView.setText("My turn");


        // make it look like Android is thinking
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        androidsTurn();

        numberOfTurns++;
        result = isGameOver();
        if(result.getGameOver()) {
            Snackbar.make(binding.layoutMain, "Game Over. The winner was: " + checkWinningPlayer(),
                    Snackbar.LENGTH_LONG).setAction("OK", null).show();
            resetGame();
        }

        // update status
        binding.textView.setText("Your turn");

    }

    private String checkWinningPlayer() {
        String gameResult;
        switch(result.getWinningPlayer()) {
            case PLAYER:
                gameResult = "You";
                break;
            case ANDROID:
                gameResult = "Me";
                break;
            default:
                gameResult = "No-one. It was a draw";
                break;
        }

        return gameResult;

    }

    private void androidsTurn() {



        // Pick the first empty button
        boolean foundAnEmptyBox = false;
        Log.d("GameOver", "looking for an empty box");



        for(int row = 0; row < 3 && !foundAnEmptyBox; row ++) {
            for(int col = 0; col < 3 && !foundAnEmptyBox; col++) {
                if(playingField[row][col].getStatus() == 0) {
                    Log.d("GameOver", "found an empty box at " + row + ", " + col);
                    playingField[row][col].setText("O");
                    playingField[row][col].setEnabled(false);
                    playingField[row][col].setStatus(200);

                    // check if still needed
                    playerTurn = PLAYER;

                    foundAnEmptyBox = true;
                }
            }
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

     static class Result {

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