package com.example.ajudantedecostura;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.ajudantedecostura.databinding.ActivitySplashScreenBinding;
import com.example.ajudantedecostura.login.LoginActivity;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreenActivity extends AppCompatActivity {

    ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GifImageView gif = binding.splashScreenGif;
        RelativeLayout texto = binding.splashScreenAjudanteCosturaSvg;

        gif.setAlpha(0f);
        texto.setAlpha(0f);
        gif.animate().setDuration(2000).alpha(1f).start();
        texto.animate().setDuration(2000).alpha(1f).withEndAction(new Runnable() {
            @Override
            public void run() {
//                gif.setVisibility(View.GONE);
//                texto.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}