package com.example.ajudantedecostura.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.ajudantedecostura.databinding.ActivityPerfilCostureiraBinding;
import com.example.ajudantedecostura.login.LoginActivity;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import modelDominio.Costureira;

public class PerfilCostureiraActivity extends AppCompatActivity {

    ActivityPerfilCostureiraBinding binding;

    InformacoesApp informacoesApp;

    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilCostureiraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        informacoesApp = (InformacoesApp) getApplicationContext();

        Costureira costureira = informacoesApp.getCostureiraLogada();

        binding.activityPerfilCostureiraImageView.setImageBitmap(BitmapFactory.decodeByteArray(costureira.getImagem(),0,costureira.getImagem().length));

        binding.activityPerfilCostureiraTxtNome.setText(costureira.getNome());
        binding.activityPerfilCostureiraTxtEmail.setText(costureira.getEmail());
        binding.activityPerfilCostureiraTxtTelefone.setText(costureira.getTelefone());
        binding.activityPerfilCostureiraTxtDataNascimento.setText(dataFormatada.format(costureira.getDataNascimento()));
        binding.activityPerfilCostureiraTxtCpf.setText(costureira.getCpf());
        binding.activityPerfilCostureiraTxtCep.setText(String.valueOf(costureira.getCep()));
        binding.activityPerfilCostureiraTxtEstado.setText(costureira.getEstado());
        binding.activityPerfilCostureiraTxtCidade.setText(costureira.getCidade());
        binding.activityPerfilCostureiraTxtRua.setText(costureira.getRua());
        binding.activityPerfilCostureiraTxtNumero.setText(String.valueOf(costureira.getNumero()));

        binding.activityPerfilCostureiraFabVoltar.setOnClickListener(v -> {
            finish();
        });

        binding.activityPerfilCostureiraFabLogout.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }
}