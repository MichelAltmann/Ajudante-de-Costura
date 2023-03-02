package com.example.ajudantedecostura.home.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajudantedecostura.databinding.ActivityDetalhesClienteBinding;
import com.example.ajudantedecostura.home.cliente.adapter.ListaPedidosClienteAdapter;
import com.example.ajudantedecostura.home.pedido.DetalhesPedidoActivity;
import com.example.ajudantedecostura.home.pedido.SalvaPedido;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Timer;

import modelDominio.Cliente;
import modelDominio.Pedido;

public class DetalhesClienteActivity extends AppCompatActivity {

    private ActivityDetalhesClienteBinding binding;
    private Cliente cliente;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");
    private ListaPedidosClienteAdapter adapter;
    private ListaPedidosClienteAdapter.OnPedidoItemClickListener pedidoClickListener;
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
    private InformacoesApp informacoesApp;

    private DetalhesClienteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        informacoesApp = (InformacoesApp) getApplicationContext();

        pedidoClickListener = (view, position) -> {
            Intent intent = new Intent(this, DetalhesPedidoActivity.class);
            SalvaPedido.pedido = pedidos.get(position);
            startActivity(intent);
        };

        int posicao = (Integer) getIntent().getIntExtra("posicao", 0);
        cliente = informacoesApp.getClientes().get(posicao);
        viewModel = new DetalhesClienteViewModel(informacoesApp, cliente);
        if (cliente != null) {


            TextView txtNome = binding.activityDetalhesClienteTxtNome;
            TextView txtTelefone = binding.activityDetalhesClienteTxtTelefone;
            TextView txtCpf = binding.activityDetalhesClienteTxtCpf;
            TextView txtDataNascimento = binding.activityDetalhesClienteTxtDataNascimento;
            TextView txtEmail = binding.activityDetalhesClienteTxtEmail;
            TextView txtCep = binding.activityDetalhesClienteTxtCep;
            TextView txtEstado = binding.activityDetalhesClienteTxtEstado;
            TextView txtCidade = binding.activityDetalhesClienteTxtCidade;
            TextView txtRua = binding.activityDetalhesClienteTxtRua;
            TextView txtNumero = binding.activityDetalhesClienteTxtNumero;

            if (cliente.getNome() != null && !cliente.getNome().equals("")) {
                txtNome.setText(cliente.getNome());
            } else {
                txtNome.setText("Nome não informado");
            }

            if (cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {
                txtTelefone.setText(cliente.getTelefone());
            } else {
                txtTelefone.setText("Telefone não informado");
            }

            if (cliente.getDataNascimento() != null && cliente.getDataNascimento().getTime() != 0) {
                txtDataNascimento.setText(dataFormatada.format(cliente.getDataNascimento()));
            } else {
                txtDataNascimento.setText("Data não Informada");
            }

            if (cliente.getCpf() != null && !cliente.getCpf().equals("")) {
                txtCpf.setText(cliente.getCpf());
            } else {
                txtCpf.setText("CPF não informado");
            }

            if (cliente.getEmail() != null && !cliente.getEmail().equals("")) {
                txtEmail.setText(cliente.getEmail());
            } else {
                txtEmail.setText("Email não informado");
            }

            if (cliente.getCep() != 0) {
                txtCep.setText(String.valueOf(cliente.getCep()));
            } else {
                txtCep.setText("Cep não informado");
            }

            if (cliente.getEstado() != null && !cliente.getEstado().equals("")) {
                txtEstado.setText(cliente.getEstado());
            } else {
                txtEstado.setText("Estado não informado");
            }

            if (cliente.getCidade() != null && !cliente.getCidade().equals("")) {
                txtCidade.setText(cliente.getCidade());
            } else {
                txtCidade.setText("Cidade não informada");
            }

            if (cliente.getRua() != null && !cliente.getRua().equals("")) {
                txtRua.setText(cliente.getRua());
            } else {
                txtRua.setText("Rua não informada");
            }

            if (cliente.getNumero() != 0) {
                txtNumero.setText(String.valueOf(cliente.getNumero()));
            } else {
                txtNumero.setText("Número não informado");
            }

            byte[] imagem = cliente.getImagem();
            Log.i("asd", "onCreate: " + imagem);
            if (imagem != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
                binding.activityDetalhesClienteImagem.setImageBitmap(bitmap);
            }
        }

        atualizaLista();


        binding.activityDetalhesClienteFabVoltar.setOnClickListener(v -> {
            finish();
        });

        binding.activityDetalhesClienteBtnDeletar.setOnClickListener(v -> {
            viewModel.deletaClientes();
            viewModel.getDeletaMsg().observe(this, msg -> {
                finish();
            });
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.carregaPedidosCliente();
    }

    private void atualizaLista() {
        final Observer<ArrayList<Pedido>> pedidosObserver = pedidos -> {
            this.pedidos = pedidos;
            adapter = new ListaPedidosClienteAdapter(pedidos, pedidoClickListener);
            binding.activityDetalhesClienteRecyclerPedido.setAdapter(adapter);
        };

        viewModel.pegaPedidos().observe(this, pedidosObserver);
    }
}