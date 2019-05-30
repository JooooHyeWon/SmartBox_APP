package com.example.userss.gach;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();

    }

    private void startLoading() {
        // 쓰레드 뒷쪽에서 처리를 해줍니다.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                //Intent intent = new Intent(LoadingActivity.this, NaviActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
