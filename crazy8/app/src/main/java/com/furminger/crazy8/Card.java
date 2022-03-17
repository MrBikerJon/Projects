package com.furminger.crazy8;

import android.graphics.Bitmap;

public class Card {

    private int id;
    private int suit;
    private int rank;
    private Bitmap bmp;
    private int scoreValue;

    public Card(int newId) {
        id = newId;
    }

    public void setBitmap(Bitmap newBitmap) {
        bmp = newBitmap;
    }

    public Bitmap getBitmap() {
        return bmp;
    }

    public int getId() {
        return id;
    }
}
