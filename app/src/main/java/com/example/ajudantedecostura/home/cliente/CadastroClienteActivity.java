package com.example.ajudantedecostura.home.cliente;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroClienteBinding;
import com.example.ajudantedecostura.login.DatePickerFragment;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modelDominio.Cliente;
import modelDominio.Costureira;

public class CadastroClienteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ActivityCadastroClienteBinding binding;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        informacoesApp = (InformacoesApp) getApplicationContext();

        binding.activityCadastroClienteTxtDataNascimento.setOnClickListener(v -> {
            // date picker dialog
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });

        binding.activityCadastroClienteBtnCadastrar.setOnClickListener(v -> {
            if (!binding.activityCadastroClienteTxtNome.getText().toString().equals("")){

                String nome = binding.activityCadastroClienteTxtNome.getText().toString();

                String email = null,
                        estado = null,
                        cidade = null,
                        rua = null,
                        cpf = null,
                        telefone = null;

                Date dataNascimento = null;

                Integer cep = 0, numero = 0;
                if (!binding.activityCadastroClienteTxtEmail.getText().toString().equals("")){
                    email = binding.activityCadastroClienteTxtEmail.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtEstado.getText().toString().equals("")){
                    estado = binding.activityCadastroClienteTxtEstado.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtCidade.getText().toString().equals("")){
                    cidade = binding.activityCadastroClienteTxtCidade.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtRua.getText().toString().equals("")){
                    rua = binding.activityCadastroClienteTxtRua.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtCpf.getText().toString().equals("")){
                    cpf = binding.activityCadastroClienteTxtCpf.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtDataNascimento.getText().toString().equals("")){
                    try {
                        dataNascimento = dataFormatada.parse(binding.activityCadastroClienteTxtDataNascimento.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (!binding.activityCadastroClienteTxtTelefone.getText().toString().equals("")){
                    telefone = binding.activityCadastroClienteTxtTelefone.getText().toString();
                }

                if (!binding.activityCadastroClienteTxtCep.getText().toString().equals("")){
                    cep = Integer.parseInt(binding.activityCadastroClienteTxtCep.getText().toString());
                }

                if (!binding.activityCadastroClienteTxtTelefone.getText().toString().equals("")){
                    numero = Integer.parseInt(binding.activityCadastroClienteTxtTelefone.getText().toString());
                }

                Cliente cliente = new Cliente(informacoesApp.getCostureiraLogada(),cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero);

                Thread thread = new Thread((Runnable) () -> {
                    ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                    String msg = conexaoSocket.cadastraCliente(cliente);
                    runOnUiThread((Runnable) () -> {
                        Toast.makeText(informacoesApp, msg, Toast.LENGTH_SHORT).show();
                    });
                });
                thread.start();

            } else {
                binding.activityCadastroClienteTxtNome.requestFocus();
                binding.activityCadastroClienteTxtNome.setError("Erro: Campo obrigatório");
            }
        });
}

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        Calendar c = new GregorianCalendar(ano, mes, dia);
        binding.activityCadastroClienteTxtDataNascimento.setText(dataFormatada.format(c.getTime()));
    }
}