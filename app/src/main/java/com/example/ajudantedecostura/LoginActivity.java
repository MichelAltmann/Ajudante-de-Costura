package com.example.ajudantedecostura;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ajudantedecostura.databinding.ActivityLoginBinding;
import com.example.ajudantedecostura.home.HomeActivity;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText txtLogin = binding.activityLoginTxtLogin;
        EditText txtSenha = binding.activityLoginTxtSenha;

        binding.activityLoginBtnEntrar.setOnClickListener(v -> {
                if (!txtLogin.getText().toString().equals("")) {
                    if (!txtSenha.getText().toString().equals("")) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256

                            String loginCadastrado = txtLogin.getText().toString();

                            BigInteger senhaCadastrada = new BigInteger(1, md.digest(txtSenha.getText().toString().getBytes()));

                            Toast.makeText(LoginActivity.this, "" + senhaCadastrada, Toast.LENGTH_SHORT).show();
                            if (binding.activityLoginCheckboxManterlogado.isChecked()) {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("nome", loginCadastrado);
                                startActivity(intent);
                                //banco de dados e salvar login para conectar com o servidor
                            }
                        } catch (NoSuchAlgorithmException e) {
                            System.out.println("Erro ao carregar o MessageDigest");
                        }
                    } else {
                        txtSenha.setError("Erro: Informe a senha.");
                        txtSenha.requestFocus();
                    }
                } else {
                    txtLogin.setError("Erro: Informe o Email ou CPF.");
                    txtLogin.requestFocus();
                }
            });

    }

}