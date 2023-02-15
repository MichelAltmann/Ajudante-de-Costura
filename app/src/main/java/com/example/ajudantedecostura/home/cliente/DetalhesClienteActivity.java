package com.example.ajudantedecostura.home.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ajudantedecostura.SalvaImagem;
import com.example.ajudantedecostura.databinding.ActivityDetalhesClienteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import modelDominio.Cliente;

public class DetalhesClienteActivity extends AppCompatActivity {

    private ActivityDetalhesClienteBinding binding;
    Cliente cliente;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        TextView txtNome = binding.activityDetalhesTxtNome;
        TextView txtTelefone = binding.activityDetalhesTxtTelefone;
        TextView txtCpf = binding.activityDetalhesTxtCpf;
        TextView txtDataNascimento = binding.activityDetalhesTxtDataNascimento;
        TextView txtEmail = binding.activityDetalhesTxtEmail;
        TextView txtCep = binding.activityDetalhesTxtCep;
        TextView txtEstado = binding.activityDetalhesTxtEstado;
        TextView txtCidade = binding.activityDetalhesTxtCidade;
        TextView txtRua = binding.activityDetalhesTxtRua;
        TextView txtNumero = binding.activityDetalhesTxtNumero;

        if (cliente.getNome() != null && !cliente.getNome().equals("")){
            txtNome.setText(cliente.getNome());
        } else {
            txtNome.setText("Nome não informado");
        }

        if (cliente.getTelefone() != null && !cliente.getTelefone().equals("")){
            txtTelefone.setText(cliente.getTelefone());
        } else {
            txtTelefone.setText("Telefone não informado");
        }

        if (cliente.getDataNascimento() != null && cliente.getDataNascimento().getTime() != 0){
            txtDataNascimento.setText(dataFormatada.format(cliente.getDataNascimento()));
        } else {
            txtDataNascimento.setText("Data não Informada");
        }

        if (cliente.getCpf() != null && !cliente.getCpf().equals("")){
            txtCpf.setText(cliente.getCpf());
        } else {
            txtCpf.setText("CPF não informado");
        }

        if (cliente.getEmail() != null && !cliente.getEmail().equals("")){
            txtEmail.setText(cliente.getEmail());
        } else {
            txtEmail.setText("Email não informado");
        }

        if (cliente.getCep() != 0){
            txtCep.setText(String.valueOf(cliente.getCep()));
        } else {
            txtCep.setText("Cep não informado");
        }

        if(cliente.getEstado() != null && !cliente.getEstado().equals("")){
            txtEstado.setText(cliente.getEstado());
        } else {
            txtEstado.setText("Estado não informado");
        }

        if(cliente.getCidade() != null && !cliente.getCidade().equals("")){
            txtCidade.setText(cliente.getCidade());
        } else {
            txtCidade.setText("Cidade não informada");
        }

        if (cliente.getRua() != null && !cliente.getRua().equals("")) {
            txtRua.setText(cliente.getRua());
        } else {
            txtRua.setText("Rua não informada");
        }

        if (cliente.getNumero() != 0){
            txtNumero.setText(String.valueOf(cliente.getNumero()));
        } else {
            txtNumero.setText("Número não informado");
        }

        byte[] imagem = SalvaImagem.imagem;
        Log.i("asd", "onCreate: " + SalvaImagem.imagem);
        if (imagem != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            binding.activityDetalhesImagem.setImageBitmap(bitmap);
        }

        binding.activityHomeFabVoltar.setOnClickListener(v -> {
            finish();
        });

    }
}