package com.example.ajudantedecostura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityMainBinding;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EditText txtLogin = binding.txtLogin;
        EditText txtSenha = binding.txtSenha;

        binding.btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtLogin.getText().toString().equals("")) {
                    if (!txtSenha.getText().toString().equals("")) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5"); // MD5, SHA-1, SHA-256

                            String loginCadastrado = txtLogin.getText().toString();

                            BigInteger senhaCadastrada = new BigInteger(1, md.digest(txtSenha.getText().toString().getBytes()));

                            Toast.makeText(MainActivity.this, "" + senhaCadastrada, Toast.LENGTH_SHORT).show();
                            if (binding.cbManterLogado.isChecked()) {
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
            }
        });

    }

}