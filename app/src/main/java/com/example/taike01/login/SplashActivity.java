package com.example.taike01.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.taike01.R;
import com.example.taike01.activity.MainActivity;
import com.example.taike01.login.login_Activity;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        postDelayed();
    }

    public void postDelayed(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,login_Activity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                overridePendingTransition(0,0);
            }
        },2000);

    }

}
