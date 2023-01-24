package com.example.ajudantedecostura.home.pedido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ajudantedecostura.databinding.ActivityDetalhesPedidoBinding;

public class DetalhesPedidoActivity extends AppCompatActivity {

    private ActivityDetalhesPedidoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}