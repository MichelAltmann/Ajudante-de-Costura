package com.example.ajudantedecostura.home.cliente;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.ActivityCadastroClienteBinding;
import com.example.ajudantedecostura.login.DatePickerFragment;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import modelDominio.Cliente;

public class CadastroClienteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    ActivityCadastroClienteBinding binding;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    InformacoesApp informacoesApp;

    Bitmap selectedImageBitmap;
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



        binding.activityCadastroClienteAdicionaImagem.setOnClickListener(v -> {
            imageChooser();
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

                if (selectedImageBitmap.getByteCount() != 0){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] imagem = stream.toByteArray();
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

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();

                    if (data != null && data.getData() != null){
                        Uri selectedImageUri = data.getData();
                        try {
                            selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImageUri);
                            binding.activityCadastroClienteAdicionaImagem.setImageBitmap(selectedImageBitmap);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
    );

    void imageChooser() {
        Intent i = new Intent();
        i.setType("image/");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        Calendar c = new GregorianCalendar(ano, mes, dia);
        binding.activityCadastroClienteTxtDataNascimento.setText(dataFormatada.format(c.getTime()));
    }
}