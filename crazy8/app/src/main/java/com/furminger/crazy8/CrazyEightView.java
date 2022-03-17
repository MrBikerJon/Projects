package com.furminger.crazy8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CrazyEightView extends View {

    private int scrW;
    private int scrH;
    private Context ctx;
    private List<Card> deck = new ArrayList<Card>();
    private int scaledCW;
    private int scaledCH;
    private List<Card> playerHand = new ArrayList<>();
    private List<Card> computerHand = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();
    private float scale;
    private Paint paint;
    private Bitmap cardBack;

    public CrazyEightView(Context context) {

        super(context);
        scale = ctx.getResources().getDisplayMetrics().density;
        paint = new Paint();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0; i < computerHand.size(); i++) {
            canvas.drawBitmap(cardBack, i * (scale * 5),
                    paint.getTextSize() + (50 * scale),
                    null);
        }

        for(int i = 0; i < playerHand.size(); i++) {
            canvas.drawBitmap(playerHand.get(i).getBitmap(), i * (scaledCW + 5),
                    scrH - scaledCH - paint.getTextSize()-(50*scale),
                    null);
        }
    }

    private void initializeDeck() {
        for(int i = 0; i < 4; i++) {
            for(int j = 102; j < 115; j++) {
                int tempId = j + (i*100);
                Card tempCard = new Card(tempId);
                int resourceId = getResources().getIdentifier("card" + tempId,
                        "drawable", ctx.getPackageName());
                Bitmap tempBitmap = BitmapFactory.decodeResource(ctx.getResources(), resourceId);
                scaledCW = (int) (scrW / 8);
                scaledCH = (int) (scaledCW * 1.28);
                Bitmap scaledBitMap = Bitmap.createScaledBitmap(tempBitmap, scaledCW, scaledCH, false);
                tempCard.setBitmap(scaledBitMap);
                deck.add(tempCard);
            }
        }
    }

    private void dealCards() {
        Collections.shuffle(deck, new Random());
        for(int i = 0; i < 7; i++) {
            drawCard(playerHand);
            drawCard(computerHand);
        }
    }

    private void drawCard(List<Card> hand) {
        hand.add(0, deck.get(0));
        deck.remove(0);
        if(deck.isEmpty()) {
            for(int i = discardPile.size()-1; i > 0; i--) {
                deck.add(discardPile.get(i));
                discardPile.remove(i);
                Collections.shuffle(deck, new Random());
            }
        }
    }

    public void onSizeChanged (int w, int h, int oldw, int oldh) {

        // other statements

        scaledCW = (int) (scrW /8);
        scaledCH = (int) (scaledCW * 1.28);

        Bitmap tempBitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.card_back);
        cardBack = Bitmap.createScaledBitmap(tempBitmap, scaledCW, scaledCH, false);
    }
}
