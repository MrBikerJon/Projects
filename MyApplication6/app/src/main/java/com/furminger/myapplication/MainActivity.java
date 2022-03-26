package com.furminger.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

        ImageView background;
        public int imgCount;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            background = (ImageView)findViewById(R.id.imageView);
            imgCount = 1;
            runThread();
        }

        private void runThread() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // set thread to run on a continuous loop
                    while (true) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        imgCount++;
                        if (imgCount > 3) imgCount = 1;

                        // UI must be updated on the UI thread
                        background.post(new Runnable() {
                            public void run() {

                                switch (imgCount) {
                                    case 1:
                                        background.setImageResource(R.drawable.main_background1);
                                        break;
                                    case 2:
                                        background.setImageResource(R.drawable.main_background2);
                                        break;
                                    case 3:
                                        background.setImageResource(R.drawable.main_background3);
                                        break;
                                }
                            }
                        });
                    }
                }
            }).start();
        }
}