package com.example.ajudantedecostura.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.ActivityCadastroBinding;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityCadastroBtnCancelar.setOnClickListener(view -> {
            finish();
        });


    }
}