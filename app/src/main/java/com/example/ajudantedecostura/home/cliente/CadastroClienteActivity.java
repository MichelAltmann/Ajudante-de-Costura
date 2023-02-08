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
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
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

        EditText txtDataNascimento = binding.activityCadastroClienteTxtDataNascimento;
        ImageView addImagem = binding.activityCadastroClienteAdicionaImagem;
        EditText txtNome = binding.activityCadastroClienteTxtNome;
        EditText txtEmail = binding.activityCadastroClienteTxtEmail;
        EditText txtEstado = binding.activityCadastroClienteTxtEstado;
        EditText txtCidade = binding.activityCadastroClienteTxtCidade;
        EditText txtRua = binding.activityCadastroClienteTxtRua;
        EditText txtCpf = binding.activityCadastroClienteTxtCpf;
        EditText txtTelefone = binding.activityCadastroClienteTxtTelefone;
        EditText txtCep = binding.activityCadastroClienteTxtCep;

        txtDataNascimento.setOnClickListener(v -> {
            // date picker dialog
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), "Date dialog");
        });


        addImagem.setOnClickListener(v -> {
            imageChooser();
        });

        binding.activityCadastroClienteBtnCadastrar.setOnClickListener(v -> {
            if (!txtNome.getText().toString().equals("")){

                String nome = binding.activityCadastroClienteTxtNome.getText().toString();
                byte[] imagem = null;
                String email = null,
                        estado = null,
                        cidade = null,
                        rua = null,
                        cpf = null,
                        telefone = null;

                Date dataNascimento = null;

                Integer cep = 0, numero = 0;
                if (!txtEmail.getText().toString().equals("")){
                    email = txtEmail.getText().toString();
                }

                if (!txtEstado.getText().toString().equals("")){
                    estado = txtEstado.getText().toString();
                }

                if (!txtCidade.getText().toString().equals("")){
                    cidade = txtCidade.getText().toString();
                }

                if (!txtRua.getText().toString().equals("")){
                    rua = txtRua.getText().toString();
                }

                if (!txtCpf.getText().toString().equals("")){
                    cpf = txtCpf.getText().toString();
                }

                if (!txtDataNascimento.getText().toString().equals("")){
                    try {
                        dataNascimento = dataFormatada.parse(txtDataNascimento.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                if (!txtTelefone.getText().toString().equals("")){
                    telefone = txtTelefone.getText().toString();
                }

                if (!txtCep.getText().toString().equals("")){
                    cep = Integer.parseInt(txtCep.getText().toString());
                }

                if (!txtTelefone.getText().toString().equals("")){
                    numero = Integer.parseInt(txtTelefone.getText().toString());
                }

                if (binding.activityCadastroClienteAdicionaImagem.getDrawable() != null){
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    selectedImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    imagem = stream.toByteArray();
                } else {
                    imagem = null;
                }

                Cliente cliente = new Cliente(informacoesApp.getCostureiraLogada(), cpf, nome, email, telefone, dataNascimento, imagem, cep, estado, cidade, rua, numero);

                Thread thread = new Thread((Runnable) () -> {
                    ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
                    String msg = conexaoSocket.cadastraCliente(cliente);
                    Log.i("cliente: ", msg);
                    runOnUiThread((Runnable) () -> {
                        Toast.makeText(informacoesApp, "Cliente cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    });
                });
                thread.start();
            } else {
                txtNome.requestFocus();
                txtNome.setError("Erro: Campo obrigatório");
            }
        });

        binding.activityCadastroClienteBtnCancelar.setOnClickListener(v -> {
            finish();
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