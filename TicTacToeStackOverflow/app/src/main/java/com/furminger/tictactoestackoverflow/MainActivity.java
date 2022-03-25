package com.furminger.tictactoestackoverflow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public int counter = 0;
    Toast msg;
    ImageView img, img2, img3, img4, img5, img6, img7, img8, img9;

//    int o = 1;
//    int x = 2;

    List<Integer> winner = new ArrayList<>(9);

    boolean owin, xwin = false;
    boolean gstate = true;

    //oOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoO

    public void isOm() {
        if (counter % 2 != 0 && img5.getDrawable() != null) {
            winner.set(4, 1);
        } else {
        }
    }

    public void isOmr() {
        if (counter % 2 != 0 && img6.getDrawable() != null) {
            winner.set(5, 1);
        } else {
        }
    }

    public void isOml() {
        if (counter % 2 != 0 && img4.getDrawable() != null) {
            winner.set(3, 1);
        } else {
        }
    }

    public void isOru() {
        if (counter % 2 != 0 && img3.getDrawable() != null) {
            winner.set(2, 1);
        } else {
        }
    }

    public void isOmu() {
        if (counter % 2 != 0 && img2.getDrawable() != null) {
            winner.set(1, 1);
        } else {
        }
    }


    public void isOlu() {
        if (counter % 2 != 0 && img.getDrawable() != null) {
            winner.set(0, 1);
        } else {
        }
    }

    public void isOdr() {
        if (counter % 2 != 0 && img9.getDrawable() != null) {
            winner.set(8, 1);
        } else {
        }
    }

    public void isOmd() {
        if (counter % 2 != 0 && img8.getDrawable() != null) {
            winner.set(7, 1);
        } else {
        }
    }

    public void isOdl() {
        if (counter % 2 != 0 && img7.getDrawable() != null) {
            winner.set(6, 1);
        } else {
        }
    }

    //oOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoOoO

    //xXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxX

    public void isXm() {
        if (counter % 2 == 0 && img5.getDrawable() != null) {
            winner.set(4, 2);
        } else {
        }
    }

    public void isXmr() {
        if (counter % 2 == 0 && img6.getDrawable() != null) {
            winner.set(5, 2);
        } else {
        }
    }

    public void isXml() {
        if (counter % 2 == 0 && img4.getDrawable() != null) {
            winner.set(3, 2);
        } else {
        }
    }

    public void isXru() {
        if (counter % 2 == 0 && img3.getDrawable() != null) {
            winner.set(2, 2);
        } else {
        }
    }

    public void isXmu() {
        if (counter % 2 == 0 && img2.getDrawable() != null) {
            winner.set(1, 2);
        } else {
        }
    }

    public void isXlu() {
        if (counter % 2 == 0 && img.getDrawable() != null) {
            winner.set(0, 2);
        } else {
        }
    }

    public void isXdr() {
        if (counter % 2 == 0 && img9.getDrawable() != null) {
            winner.set(8, 2);
        } else {
        }
    }

    public void isXmd() {
        if (counter % 2 == 0 && img8.getDrawable() != null) {
            winner.set(7, 2);
        } else {
        }
    }

    public void isXdl() {
        if (counter % 2 == 0 && img7.getDrawable() != null) {
            winner.set(6, 2);
        } else {
        }
    }


    //xXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxXxX

    public boolean oWin() {
        try {
            Log.i("xxx", "winner 0, 1, 2 is " + winner.get(0) + " " + winner.get(1) + " " + winner.get(2));
            if(((winner.get(0) == 1) && (winner.get(1) == 1) && (winner.get(2) == 1)) || ((img4.getDrawable() != null) && (img5.getDrawable() != null) && (img6.getDrawable() != null) && ((int)winner.get(3) == 1) && ((int)winner.get(4) == 1) && ((int)winner.get(5) == 1)) || ((img7.getDrawable() != null) && (img8.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(6) == 1) && ((int)winner.get(7) == 1) && ((int)winner.get(8) == 1)) || ((img.getDrawable() != null) && (img4.getDrawable() != null) && (img7.getDrawable() != null) && ((int)winner.get(0) == 1) && ((int)winner.get(3) == 1) && ((int)winner.get(6) == 1)) || ((img2.getDrawable() != null) && (img5.getDrawable() != null) && (img8.getDrawable() != null) && ((int)winner.get(1) == 1) && ((int)winner.get(4) == 1) && ((int)winner.get(7) == 1)) || ((img3.getDrawable() != null) && (img6.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(2) == 1) && ((int)winner.get(5) == 1) && ((int)winner.get(8) == 1)) || ((img.getDrawable() != null) && (img5.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(0) == 1) && ((int)winner.get(4) == 1) && ((int)winner.get(8) == 1)) || ((img3.getDrawable() != null) && (img5.getDrawable() != null) && (img7.getDrawable() != null) && ((int)winner.get(2) == 1) && ((int)winner.get(4) == 1) && ((int)winner.get(6) == 1))){
                owin = true;
            }
            return owin;
        }
        catch(Exception e) {
            //Toast.makeText(this,"An image is not here",Toast.LENGTH_SHORT).show();
            return owin;
        }
    }

    public boolean xWin() {
        try {
            if (((img.getDrawable() != null) && (img2.getDrawable() != null) && (img3.getDrawable() != null) && ((int)winner.get(0) == 2) && ((int)winner.get(1) == 2) && ((int)winner.get(2) == 2)) || ((img4.getDrawable() != null) && (img5.getDrawable() != null) && (img6.getDrawable() != null) && ((int)winner.get(3) == 2) && ((int)winner.get(4) == 2) && ((int)winner.get(5) == 2)) || ((img7.getDrawable() != null) && (img8.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(6) == 2) && ((int)winner.get(7) == 2) && ((int)winner.get(8) == 2)) || ((img.getDrawable() != null) && (img4.getDrawable() != null) && (img7.getDrawable() != null) && ((int)winner.get(0) == 2) && ((int)winner.get(3) == 2) && ((int)winner.get(6) == 2)) || ((img2.getDrawable() != null) && (img5.getDrawable() != null) && (img8.getDrawable() != null) && ((int)winner.get(1) == 2) && ((int)winner.get(4) == 2) && ((int)winner.get(7) == 2)) || ((img3.getDrawable() != null) && (img6.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(2) == 2) && ((int)winner.get(5) == 2) && ((int)winner.get(8) == 2)) || ((img.getDrawable() != null) && (img5.getDrawable() != null) && (img9.getDrawable() != null) && ((int)winner.get(0) == 2) && ((int)winner.get(4) == 2) && ((int)winner.get(8) == 2)) || ((img3.getDrawable() != null) && (img5.getDrawable() != null) && (img7.getDrawable() != null) && ((int)winner.get(2) == 2) && ((int)winner.get(4) == 2) && ((int)winner.get(6) == 2))){
                xwin = true;
            }
            return xwin;
        }
        catch(Exception e) {
            //Toast.makeText(this,"An image is not here",Toast.LENGTH_SHORT).show();
            return xwin;
        }
    }

    public void greset(View view) {
        counter = 0;
        //img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(0);
        //img2 = (ImageView) findViewById(R.id.imageView2);
        img2.setImageResource(0);
        //img3 = (ImageView) findViewById(R.id.imageView3);
        img3.setImageResource(0);
        //img4 = (ImageView) findViewById(R.id.imageView4);
        img4.setImageResource(0);
        //img5 = (ImageView) findViewById(R.id.imageView5);
        img5.setImageResource(0);
        //img6 = (ImageView) findViewById(R.id.imageView6);
        img6.setImageResource(0);
        //img7 = (ImageView) findViewById(R.id.imageView7);
        img7.setImageResource(0);
        //img8 = (ImageView) findViewById(R.id.imageView8);
        img8.setImageResource(0);
        //img9 = (ImageView) findViewById(R.id.imageView9);
        img9.setImageResource(0);
        gstate = true;
        owin = false; // added
        xwin = false; // added
    }

    public void pgame(View view) {
        ImageView image = (ImageView) view;

        img = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        img3 = (ImageView) findViewById(R.id.imageView3);
        img4 = (ImageView) findViewById(R.id.imageView4);
        img5 = (ImageView) findViewById(R.id.imageView5);
        img6 = (ImageView) findViewById(R.id.imageView6);
        img7 = (ImageView) findViewById(R.id.imageView7);
        img8 = (ImageView) findViewById(R.id.imageView8);
        img9 = (ImageView) findViewById(R.id.imageView9);

        if (image.getDrawable() != null) {
            msg = Toast.makeText(this, "Please select another space", Toast.LENGTH_SHORT);
            msg.show();
        } // moved the closing bracket to here

            if((ImageView)view == img){
                winner.set(0, 1);
                Log.i("xxx", "winner 0 = " + winner.get(0));
            }
            else if((ImageView)view == img2){
                winner.set(1, 1);
                Log.i("xxx", "winner 1 = " + winner.get(1));

            }
            else if((ImageView)view == img3){
                winner.set(2, 1);
                Log.i("xxx", "winner 2 = " + winner.get(2));

            }
            else if((ImageView)view == img4){
                winner.set(3, 1);
            }
            else if((ImageView)view == img5){
                winner.set(4, 1);
            }
            else if((ImageView)view == img6){
                winner.set(5, 1);
            }
            else if((ImageView)view == img7){
                winner.set(6, 1);
            }
            else if((ImageView)view == img8){
                winner.set(7, 1);
            }
            else if((ImageView)view == img9){
                winner.set(8, 1);
            }
            else if((ImageView)view == img){
                winner.set(0, 2);
                Log.i("xxx", "winner 0 = " + winner.get(2));

            }
            else if((ImageView)view == img2){
                winner.set(1, 2);
                Log.i("xxx", "winner 1 = " + winner.get(2));

            }
            else if((ImageView)view == img3){
                winner.set(2, 2);
                Log.i("xxx", "winner 2 = " + winner.get(2));

            }
            else if((ImageView)view == img4){
                winner.set(3, 2);
            }
            else if((ImageView)view == img5){
                winner.set(4, 2);
            }
            else if((ImageView)view == img6){
                winner.set(5, 2);
            }
            else if((ImageView)view == img7){
                winner.set(6, 2);
            }
            else if((ImageView)view == img8){
                winner.set(7, 2);
            }
            else if((ImageView)view == img9){
                winner.set(8, 2);
            }

//            if (counter > 5 && counter < 9){ commented out the if check to just check for a winner every move
                Log.i("xxx", "checking to call xWin");
                boolean xwin = xWin(); // changed code here from this.xWin();
                boolean owin = oWin();  // changed code here from this.xWin();
                Log.i("xxx", "owin = " + owin);
                Log.i("xxx", "xwin = " + xwin);

//            }

            if (owin) {
                msg = Toast.makeText(this, "\'O\' is the winner, press the reset button", Toast.LENGTH_SHORT);
                msg.show();
                xwin = false;
                gstate = false;
            }

            if (xwin) {
                msg = Toast.makeText(this, "\'X\' is the winner, press the reset button", Toast.LENGTH_SHORT);
                msg.show();
                xwin = false;
                gstate = false;
            }



        if (image.getDrawable() == null) {
            if (counter % 2 == 0) {
                image.setImageResource(R.drawable.o);
                Log.i("xxx", "counter = " + counter);
                counter += 1;

            } else {
                image.setImageResource(R.drawable.x);
                Log.i("xxx", "counter = " + counter);
                counter += 1;
            }

            image.setTranslationY(-1500);
            image.animate().translationYBy(1500).setDuration(500);

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);
        winner.add(0);


//        img = (ImageView) findViewById(R.id.imageView);
//        img2 = (ImageView) findViewById(R.id.imageView2);
//        img3 = (ImageView) findViewById(R.id.imageView3);
//        img4 = (ImageView) findViewById(R.id.imageView4);
//        img5 = (ImageView) findViewById(R.id.imageView5);
//        img6 = (ImageView) findViewById(R.id.imageView6);
//        img7 = (ImageView) findViewById(R.id.imageView7);
//        img8 = (ImageView) findViewById(R.id.imageView8);
//        img9 = (ImageView) findViewById(R.id.imageView9);

//        img.setImageResource(0);
//        //img2 = (ImageView) findViewById(R.id.imageView2);
//        img2.setImageResource(0);
//        //img3 = (ImageView) findViewById(R.id.imageView3);
//        img3.setImageResource(0);
//        //img4 = (ImageView) findViewById(R.id.imageView4);
//        img4.setImageResource(0);
//        //img5 = (ImageView) findViewById(R.id.imageView5);
//        img5.setImageResource(0);
//        //img6 = (ImageView) findViewById(R.id.imageView6);
//        img6.setImageResource(0);
//        //img7 = (ImageView) findViewById(R.id.imageView7);
//        img7.setImageResource(0);
//        //img8 = (ImageView) findViewById(R.id.imageView8);
//        img8.setImageResource(0);
//        //img9 = (ImageView) findViewById(R.id.imageView9);
//        img9.setImageResource(0);

    }
}