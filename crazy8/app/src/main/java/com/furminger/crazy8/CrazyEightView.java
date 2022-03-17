package com.furminger.crazy8;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

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
    private boolean myTurn;
    private ComputerPlayer computerPlayer = new ComputerPlayer();
    private int validRank = 8;
    private int validSuit = 0;
    private int movingIdx = -1;
    private int movingX;
    private int movingY;


    public CrazyEightView(Context context) {

        super(context);
        ctx = context;
        scale = ctx.getResources().getDisplayMetrics().density;
        paint = new Paint();
    }

    protected void onDraw(Canvas canvas) {


        for(int i = 0; i < computerHand.size(); i++) {
            canvas.drawBitmap(cardBack,
                    i*(scale * 5),
                    paint.getTextSize()+(50*scale),
                    null);
        }
        float cbackLeft = (scrW/2) - cardBack.getWidth() - 10;
        float cbackTop = (scrH/2) - (cardBack.getHeight() / 2);

        canvas.drawBitmap(cardBack, cbackLeft, cbackTop, null);

        if(!discardPile.isEmpty()) {
            canvas.drawBitmap(discardPile.get(0).getBitmap(),
                    (scrW / 2) + 10,
                    (scrH / 2) - (cardBack.getHeight() / 2),
                    null);
        }


        for (int i = 0; i < playerHand.size(); i++) {
            if(i == movingIdx) {
                canvas.drawBitmap(playerHand.get(i).getBitmap(),
                        movingX,
                        movingY,
                        null);
            } else {
                if (i < 7) {
                    canvas.drawBitmap(playerHand.get(i).getBitmap(),
                            i*(scaledCW +5),
                            scrH - scaledCH - paint.getTextSize()-(50*scale),
                            null);
                }
            }
        }
        invalidate();
        setToFullScreen();
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
        super.onSizeChanged(w, h, oldw, oldh);
        scrW = w;
        scrH = h;
        Bitmap tempBitmap = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.card_back);
        scaledCW = (int) (scrW /8);
        scaledCH = (int) (scaledCW * 1.28);
        cardBack = Bitmap.createScaledBitmap(tempBitmap, scaledCW, scaledCH, false);

        myTurn = new Random().nextBoolean();
        if(!myTurn) {
            computerPlay();
        }
    }

    private void computerPlay() {
        int tempPlay = 0;
        while(tempPlay == 0) {
            tempPlay = computerPlayer.playCard(computerHand, validSuit, validRank);
            if(tempPlay == 0) {
                drawCard(computerHand);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();

        switch (eventaction) {

            case MotionEvent.ACTION_DOWN:
                if(myTurn) {
                    for(int i = 0; i < 7; i++) {
                        if(X > i*(scaledCW +5) && X < i*(scaledCW +5) + scaledCW &&
                                Y > scrH - scaledCH - paint.getTextSize()-(50*scale)) {
                            movingIdx = i;
                            movingX = X-(int)(30*scale);
                            movingY = Y-(int)(70*scale);
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE :
                movingX = X-(int)(30*scale);
                movingY = Y-(int)(70*scale);
                break;

            case MotionEvent.ACTION_UP:
                movingIdx = -1;
                break;
        }
        invalidate();
        return true;
    }

    private void setToFullScreen() {
        setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        Log.d(getClass().getName(), "setToFullScreen called");
    }

    private void changeSuit() {
        final Dialog changeSuitDlg = new Dialog(ctx);
        changeSuitDlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}

