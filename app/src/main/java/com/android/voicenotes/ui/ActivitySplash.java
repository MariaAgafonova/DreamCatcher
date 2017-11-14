package com.android.voicenotes.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.android.voicenotes.R;

/**
 * Created by maria on 04.11.17.
 */

public class ActivitySplash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = getLayoutInflater();
        final View mainView = inflater.inflate(R.layout.activity_splash, null);
        setContentView(mainView);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startActivity(MainActivity.class);
            }
        }, 3000);
    }

    public synchronized void startActivity(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}
