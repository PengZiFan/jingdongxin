package com.example.windows.ijingdong;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QiDongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qi_dong);

        new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return false;
            }
        }).sendEmptyMessageDelayed(0,3000);

    }
}
