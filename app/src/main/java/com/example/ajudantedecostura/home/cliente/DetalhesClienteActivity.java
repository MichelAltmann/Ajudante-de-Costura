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

import modelDominio.Cliente;

public class DetalhesClienteActivity extends AppCompatActivity {

    private ActivityDetalhesClienteBinding binding;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cliente = (Cliente) getIntent().getSerializableExtra("cliente");

        TextView txtNome = binding.activityDetalhesTxtNome;
        TextView txtTelefone = binding.activityDetalhesTxtTelefone;
        TextView txtCpf = binding.activityDetalhesTxtCpf;
        TextView txtEmail = binding.activityDetalhesTxtEmail;
        TextView txtCep = binding.activityDetalhesCep;
        TextView txtEstado = binding.activityDetalhesEstado;
        TextView txtCidade = binding.activityDetalhesCidade;
        TextView txtRua = binding.activityDetalhesRua;
        TextView txtNumero = binding.activityDetalhesNumero;

        if (cliente.getNome() != null && !cliente.getNome().equals("")){
            txtNome.setText("Nome: " + cliente.getNome());
        } else {
            txtNome.setText("Nome não informado");
        }

        if (cliente.getTelefone() != null && !cliente.getTelefone().equals("")){
            txtTelefone.setText("Telefone: " + cliente.getTelefone());
        } else {
            txtTelefone.setText("Telefone não informado");
        }

        if (cliente.getEmail() != null && !cliente.getEmail().equals("")){
            txtEmail.setText("Email: " + cliente.getNome());
        } else {
            txtEmail.setText("Email não informado");
        }

        if (cliente.getCpf() != null && !cliente.getCpf().equals("")){
            txtCpf.setText("CPF: " + cliente.getCpf());
        } else {
            txtCpf.setText("CPF não informado");
        }

        if (cliente.getCep() != 0){
            txtCep.setText("Cep: " + cliente.getCep());
        } else {
            txtCep.setText("Cep não informado");
        }

        if(cliente.getEstado() != null && !cliente.getEstado().equals("")){
            txtEstado.setText("Estado: " + cliente.getEstado());
        } else {
            txtEstado.setText("Estado não informado");
        }

        if(cliente.getCidade() != null && !cliente.getCidade().equals("")){
            txtCidade.setText("Cidade: " + cliente.getCidade());
        } else {
            txtCidade.setText("Cidade não informada");
        }

        if (cliente.getRua() != null && !cliente.getRua().equals("")) {
            txtRua.setText("Rua: " + cliente.getRua());
        } else {
            txtRua.setText("Rua não informada");
        }

        if (cliente.getNumero() != 0){
            txtNumero.setText("Número: " + cliente.getNumero());
        } else {
            txtNumero.setText("Número não informado");
        }

        byte[] imagem = SalvaImagem.imagem;
        Log.i("asd", "onCreate: " + SalvaImagem.imagem);
        if (imagem != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
            binding.activityDetalhesImagem.setImageBitmap(bitmap);
        }

    }
}