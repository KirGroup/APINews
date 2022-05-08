package com.example.newsapi23.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.newsapi23.MainActivity;
import com.example.newsapi23.R;

public class LogoActivity extends Activity {

    private Animation logoAnim;
    private ImageView logoImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        logoAnim = AnimationUtils.loadAnimation(this, R.anim.logo_anim);
        logoImage = findViewById(R.id.id_image_start);
        logoImage.startAnimation(logoAnim);
        startMainActivity();
    }

    public void startMainActivity() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}