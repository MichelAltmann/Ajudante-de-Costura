package com.example.ajudantedecostura.home.pedido;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ajudantedecostura.databinding.ActivityDetalhesPedidoBinding;

public class DetalhesPedidoActivity extends AppCompatActivity {

    ActivityDetalhesPedidoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}