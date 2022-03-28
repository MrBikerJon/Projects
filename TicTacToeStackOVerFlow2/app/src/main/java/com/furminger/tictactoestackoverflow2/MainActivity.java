package com.furminger.tictactoestackoverflow2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int board[][] = new int[3][3];
    int turn = 1;

    public void flip(ImageView i) {
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        if (turn == 1) {
            i.setImageResource(R.drawable.opic);
            turn = 2;
            turnpic.setImageResource(R.drawable.xturn);
        }
        // call a new method to play Android's turn
        androidsTurn();
        turn = 1;
        turnpic.setImageResource(R.drawable.oturn);
    }

    public void win() {
        int winner = 0;
        if (board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != 0)
            winner = board[1][0];
        else if (board[2][0] == board[2][1] && board[2][0] == board[1][2] && board[2][0] != 0)
            winner = board[2][0];
        else if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != 0)
            winner = board[0][2];
        else if (board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != 0)
            winner = board[0][0];
        else if (board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != 0)
            winner = board[0][1];
        else if (board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != 0)
            winner = board[0][2];
        else if (board[0][0] != 0 && board[0][1] != 0 && board[0][2] != 0 &&
                board[1][0] != 0 && board[1][1] != 0 && board[1][2] != 0 &&
                board[2][0] != 0 && board[2][1] != 0 && board[2][2] != 0)
            winner = 3;
        if (winner == 1) {
            Toast.makeText(getApplicationContext(), "Yellow Duck Wins", Toast.LENGTH_SHORT).show();
        } else if (winner == 2) {
            Toast.makeText(getApplicationContext(), "Scissors Wins", Toast.LENGTH_SHORT).show();
        } else if (winner == 3) {
            Toast.makeText(getApplicationContext(), "Cat's game", Toast.LENGTH_SHORT).show();
        }
    }

    public void aClick(View view) {
        if (board[0][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.a);
            board[0][0] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void bClick(View view) {
        if (board[0][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.b);
            board[0][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void cClick(View view) {
        if (board[0][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.c);
            board[0][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void dClick(View view) {
        if (board[1][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.d);
            board[1][0] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void eClick(View view) {
        if (board[1][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.e);
            board[1][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void fClick(View view) {
        if (board[1][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.f);
            board[1][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void gClick(View view) {
        if (board[2][0] == 0) {
            ImageView i = (ImageView) findViewById(R.id.g);
            board[2][0] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void hClick(View view) {
        if (board[2][1] == 0) {
            ImageView i = (ImageView) findViewById(R.id.h);
            board[2][1] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void iClick(View view) {
        if (board[2][2] == 0) {
            ImageView i = (ImageView) findViewById(R.id.i);
            board[2][2] = turn;
            flip(i);
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    public void androidsTurn() {
        boolean foundASpaceToPlay = false;
        ImageView i = null;

        // (1) First we need to check whether android needs to block player from winning.
        // (1)(a) Look for two player's tokens together in row, and a blank.
        // Start by checking the first row...

        if(!foundASpaceToPlay && board[0][0] == 0 && board[0][1] == 1 && board[0][2] == 1) {
            foundASpaceToPlay = true;
            board[0][0] = 2;
            i = (ImageView) findViewById(R.id.a);
        }

        if(!foundASpaceToPlay && board[0][0] == 1 && board[0][1] == 0 && board[0][2] == 1) {
            foundASpaceToPlay = true;
            board[0][1] = 2;
            i = (ImageView) findViewById(R.id.b);
        }

        if(!foundASpaceToPlay && board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 0) {
            foundASpaceToPlay = true;
            board[0][2] = 2;
            i = (ImageView) findViewById(R.id.c);
        }

        // check the next row

        if(!foundASpaceToPlay && board[1][0] == 0 && board[1][1] == 1 && board[1][2] == 1) {
            foundASpaceToPlay = true;
            board[1][0] = 2;
            i = (ImageView) findViewById(R.id.d);
        }

        if(!foundASpaceToPlay && board[1][0] == 1 && board[1][1] == 0 && board[1][2] == 1) {
            foundASpaceToPlay = true;
            board[1][1] = 2;
            i = (ImageView) findViewById(R.id.e);
        }

        if(!foundASpaceToPlay && board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 0) {
            foundASpaceToPlay = true;
            board[1][2] = 2;
            i = (ImageView) findViewById(R.id.f);
        }

        // check the final row

        if(!foundASpaceToPlay && board[2][0] == 0 && board[2][1] == 1 && board[2][2] == 1) {
            foundASpaceToPlay = true;
            board[2][0] = 2;
            i = (ImageView) findViewById(R.id.g);
        }

        if(!foundASpaceToPlay && board[2][0] == 1 && board[2][1] == 0 && board[2][2] == 1) {
            foundASpaceToPlay = true;
            board[2][1] = 2;
            i = (ImageView) findViewById(R.id.h);
        }

        if(!foundASpaceToPlay && board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 0) {
            foundASpaceToPlay = true;
            board[2][2] = 2;
            i = (ImageView) findViewById(R.id.i);
        }

        // (1)(b)then look for two opponent's together in a column and a blank

        if(!foundASpaceToPlay && board[0][0] == 0 && board[1][0] == 1 && board[2][0] == 1) {
            foundASpaceToPlay = true;
            board[0][0] = 2;
            i = (ImageView) findViewById(R.id.a);
        }

        if(!foundASpaceToPlay && board[0][0] == 1 && board[1][0] == 0 && board[2][0] == 1) {
            foundASpaceToPlay = true;
            board[1][0] = 2;
            i = (ImageView) findViewById(R.id.d);
        }

        if(!foundASpaceToPlay && board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 0) {
            foundASpaceToPlay = true;
            board[2][0] = 2;
            i = (ImageView) findViewById(R.id.g);
        }

        // check the next column

        if(!foundASpaceToPlay && board[0][1] == 0 && board[1][1] == 1 && board[2][1] == 1) {
            foundASpaceToPlay = true;
            board[0][1] = 2;
            i = (ImageView) findViewById(R.id.b);
        }

        if(!foundASpaceToPlay && board[0][1] == 1 && board[1][1] == 0 && board[2][1] == 1) {
            foundASpaceToPlay = true;
            board[1][1] = 2;
            i = (ImageView) findViewById(R.id.e);
        }

        if(!foundASpaceToPlay && board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 0) {
            foundASpaceToPlay = true;
            board[2][1] = 2;
            i = (ImageView) findViewById(R.id.h);
        }

        // check the final column

        if(!foundASpaceToPlay && board[0][2] == 0 && board[1][2] == 1 && board[2][2] == 1) {
            foundASpaceToPlay = true;
            board[0][2] = 2;
            i = (ImageView) findViewById(R.id.c);
        }

        if(!foundASpaceToPlay && board[0][2] == 1 && board[1][2] == 0 && board[2][2] == 1) {
            foundASpaceToPlay = true;
            board[1][2] = 2;
            i = (ImageView) findViewById(R.id.f);
        }

        if(!foundASpaceToPlay && board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 0) {
            foundASpaceToPlay = true;
            board[2][2] = 2;
            i = (ImageView) findViewById(R.id.i);
        }

        // (1)(c) Then look for two opponent's together in a diagonal and a blank
            if(!foundASpaceToPlay && board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 0) {
                foundASpaceToPlay = true;
                board[2][2] = 2;
                i = (ImageView) findViewById(R.id.i);
            }
        // You need to repeat the above code for the other 5 possible combinations


        // (2) Now look for opportunities to win
        // this code is similar to the code above, but now we look for value "2" instead of "1"
        // (2)(a) Look for two android's together in row and a blank:

        if(!foundASpaceToPlay && board[0][0] == 0 && board[0][1] == 2 && board[0][2] == 2) {
            foundASpaceToPlay = true;
            board[0][0] = 2;
            i = (ImageView) findViewById(R.id.a);
        }

        if(!foundASpaceToPlay && board[0][0] == 2 && board[0][1] == 0 && board[0][2] == 2) {
            foundASpaceToPlay = true;
            board[0][1] = 2;
            i = (ImageView) findViewById(R.id.b);
        }

        if(!foundASpaceToPlay && board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 0) {
            foundASpaceToPlay = true;
            board[0][2] = 2;
            i = (ImageView) findViewById(R.id.c);
        }

        // check the next row

        if(!foundASpaceToPlay && board[1][0] == 0 && board[1][1] == 2 && board[1][2] == 2) {
            foundASpaceToPlay = true;
            board[1][0] = 2;
            i = (ImageView) findViewById(R.id.d);
        }

        if(!foundASpaceToPlay && board[1][0] == 2 && board[1][1] == 0 && board[1][2] == 2) {
            foundASpaceToPlay = true;
            board[1][1] = 2;
            i = (ImageView) findViewById(R.id.e);
        }

        if(!foundASpaceToPlay && board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 0) {
            foundASpaceToPlay = true;
            board[1][2] = 2;
            i = (ImageView) findViewById(R.id.f);
        }

        // check the last row

        if(!foundASpaceToPlay && board[2][0] == 0 && board[2][1] == 2 && board[2][2] == 2) {
            foundASpaceToPlay = true;
            board[2][0] = 2;
            i = (ImageView) findViewById(R.id.g);
        }

        if(!foundASpaceToPlay && board[2][0] == 2 && board[2][1] == 0 && board[2][2] == 2) {
            foundASpaceToPlay = true;
            board[2][1] = 2;
            i = (ImageView) findViewById(R.id.h);
        }

        if(!foundASpaceToPlay && board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 0) {
            foundASpaceToPlay = true;
            board[2][2] = 2;
            i = (ImageView) findViewById(R.id.i);
        }

        // (2)(b) Then look for two android's together in a column and a blank.

        if(!foundASpaceToPlay && board[0][0] == 0 && board[1][0] == 2 && board[2][0] == 2) {
            foundASpaceToPlay = true;
            board[0][0] = 2;
            i = (ImageView) findViewById(R.id.a);
        }

        if(!foundASpaceToPlay && board[0][0] == 2 && board[1][0] == 0 && board[2][0] == 2) {
            foundASpaceToPlay = true;
            board[1][0] = 2;
            i = (ImageView) findViewById(R.id.d);
        }

        if(!foundASpaceToPlay && board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 0) {
            foundASpaceToPlay = true;
            board[2][0] = 2;
            i = (ImageView) findViewById(R.id.g);
        }

        // check the next column

        if(!foundASpaceToPlay && board[0][1] == 0 && board[1][1] == 2 && board[2][1] == 2) {
            foundASpaceToPlay = true;
            board[0][1] = 2;
            i = (ImageView) findViewById(R.id.b);
        }

        if(!foundASpaceToPlay && board[0][1] == 2 && board[1][1] == 0 && board[2][1] == 2) {
            foundASpaceToPlay = true;
            board[1][1] = 2;
            i = (ImageView) findViewById(R.id.e);
        }

        if(!foundASpaceToPlay && board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 0) {
            foundASpaceToPlay = true;
            board[2][1] = 2;
            i = (ImageView) findViewById(R.id.h);
        }

        // check the final column

        if(!foundASpaceToPlay && board[0][2] == 0 && board[1][2] == 2 && board[2][2] == 2) {
            foundASpaceToPlay = true;
            board[0][2] = 2;
            i = (ImageView) findViewById(R.id.c);
        }

        if(!foundASpaceToPlay && board[0][2] == 2 && board[1][2] == 0 && board[2][2] == 2) {
            foundASpaceToPlay = true;
            board[1][2] = 2;
            i = (ImageView) findViewById(R.id.f);
        }

        if(!foundASpaceToPlay && board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 0) {
            foundASpaceToPlay = true;
            board[2][2] = 2;
            i = (ImageView) findViewById(R.id.i);
        }

        // (2)(c) Then look for two android's together in a diagonal and a blank
        // Again, reuse the code above

        // (3) Now look for opportunities to set up a win in the next turn
        // (3)(a) Look for one android in a row and a two blanks

        // (3)(b) Then look for one android in a column and two blanks

        // (3)(c) Then look for one android in a diagonal and two blanks


        // (4) If all else fails, choose one blank space to play.
        // Search through all cells looking for a blank space to play
        // the first blank space is played
        if(!foundASpaceToPlay) {
            for(int row = 0; row <3 && !foundASpaceToPlay; row++ ) {
                for(int col = 0; col <3 && !foundASpaceToPlay; col++) {
                    if(board[row][col] == 0) {
                        board[row][col] = 2;
                        foundASpaceToPlay = true;
                        // Found a bank space, now to set the correct ImageView
                        switch(row) {
                            case 0:
                                switch(col) {
                                    case 0:
                                        i = (ImageView) findViewById(R.id.a);
                                        break;
                                    case 1:
                                        i = (ImageView) findViewById(R.id.b);
                                        break;
                                    case 2:
                                        i = (ImageView) findViewById(R.id.c);
                                        break;
                                }
                                break;
                            case 1:
                                switch(col) {
                                    case 0:
                                        i = (ImageView) findViewById(R.id.d);
                                        break;
                                    case 1:
                                        i = (ImageView) findViewById(R.id.e);
                                        break;
                                    case 2:
                                        i = (ImageView) findViewById(R.id.f);
                                        break;
                                }
                                break;
                            case 2:
                                switch(col) {
                                    case 0:
                                        i = (ImageView) findViewById(R.id.g);
                                        break;
                                    case 1:
                                        i = (ImageView) findViewById(R.id.h);
                                        break;
                                    case 2:
                                        i = (ImageView) findViewById(R.id.i);
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
        }

        if(i != null) {
            i.setImageResource(R.drawable.xpic);
        }
    }

    public void reset(View view){
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.blank);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.blank);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.blank);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.blank);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.blank);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.blank);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.blank);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.blank);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.blank);
        for(int k=0; k<3; k++){
            for(int j=0; j<3; j++){
                board[k][j]=0;
            }
        }
    }

    public void gerbilClick(View view){
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.gerbil);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.gerbil);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.gerbil);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.gerbil);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.gerbil);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.gerbil);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.gerbil);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.gerbil);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.gerbil);
        for(int k=0; k<3; k++){
            for(int j=0; j<3; j++){
                board[k][j]=0;
            }
        }
    }


}

