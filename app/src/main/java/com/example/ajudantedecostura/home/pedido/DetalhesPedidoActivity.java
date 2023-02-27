package com.example.ajudantedecostura.home.pedido;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ajudantedecostura.R;
import com.example.ajudantedecostura.databinding.ActivityDetalhesPedidoBinding;
import com.example.ajudantedecostura.home.cliente.DetalhesClienteViewModel;
import com.example.ajudantedecostura.home.pedido.adapter.MateriaisPedidoAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.MedidasPedidoAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import modelDominio.Pedido;

public class DetalhesPedidoActivity extends AppCompatActivity {

    private ActivityDetalhesPedidoBinding binding;

    private Pedido pedido;

    private TextView txtTitulo, txtCliente, txtDataCriacao, txtDataEntrega, txtPrioridade, txtPreco, txtDescricao;
    private ImageView imagem;

    private DetalhesPedidoViewModel viewModel;
    private DetalhesClienteViewModel viewModelCliente;
    private InformacoesApp informacoesApp;
    private MedidasPedidoAdapter adapterMedida;
    private MateriaisPedidoAdapter adapterMaterial;
    final DateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalhesPedidoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        informacoesApp = (InformacoesApp) getApplicationContext();



        txtTitulo = binding.activityDetalhesPedidoTxtTitulo;
        txtCliente = binding.activityDetalhesPedidoTxtCliente;
        txtDataCriacao = binding.activityDetalhesPedidoTxtDataCriacao;
        txtDataEntrega = binding.activityDetalhesPedidoTxtDataEntrega;
        txtPrioridade = binding.activityDetalhesPedidoTxtPrioridade;
        txtPreco = binding.activityDetalhesPedidoTxtPreco;
        txtDescricao = binding.activityDetalhesPedidoTxtDescricao;
        imagem = binding.activityDetalhesPedidoImagem;

        MateriaisPedidoAdapter.OnMateriaisItemClickListener onMateriaisItemClick = (view, position) -> {
        };

        MedidasPedidoAdapter.OnMedidaItemClickListener onMedidasItemClick = (view, position) -> {
        };

        if (SalvaPedido.pedido != null) {
            this.pedido = SalvaPedido.pedido;
            viewModel = new DetalhesPedidoViewModel(informacoesApp, pedido);
            viewModelCliente = new DetalhesClienteViewModel(informacoesApp, pedido.getCliente());
            Bitmap bmp = null;
            if (pedido.getImagem() != null) {
                 bmp = BitmapFactory.decodeByteArray(pedido.getImagem(), 0, pedido.getImagem().length);
                 imagem.setImageBitmap(bmp);
            }

            if (pedido.getTitulo() != null && !pedido.getTitulo().equals("")) {
                txtTitulo.setText(pedido.getTitulo());
            }

            if (pedido.getCliente() != null) {
                txtCliente.setText(pedido.getCliente().getNome());
            }

            if (pedido.getDataCriacao() != null) {
                txtDataCriacao.setText(dataFormatada.format(pedido.getDataCriacao()));
            }

            if (pedido.getDataEntrega() != null) {
                txtDataEntrega.setText(dataFormatada.format(pedido.getDataEntrega()));
            }

            if (pedido.getDescricao() != null && !pedido.getDescricao().equals("")) {
                txtDescricao.setText(pedido.getDescricao());
            }

            txtPrioridade.setText(pedido.prioridadeToString());

            if (pedido.getPreco() != null && !pedido.getPreco().toString().equals("")) {
                txtPreco.setText(pedido.getPreco().toString());
            }

            adapterMaterial = new MateriaisPedidoAdapter(pedido.getListaMateriais(), onMateriaisItemClick);
            binding.activityDetalhesPedidoRecyclerMateriais.setAdapter(adapterMaterial);

            adapterMedida = new MedidasPedidoAdapter(TransformaEmMedidas.transformaEmLista(pedido.getMedidas()), onMedidasItemClick);
            binding.activityDetalhesPedidoRecyclerMedidas.setAdapter(adapterMedida);

            binding.activityDetalhesPedidoFabVoltar.setOnClickListener(v -> {
                finish();
            });

            binding.activityDetalhesPedidoBtnDeletar.setOnClickListener(v -> {
                deletaPedido();
                Toast.makeText(informacoesApp, "Pedido deletado com sucesso!", Toast.LENGTH_SHORT).show();
            });

            binding.activityDetalhesPedidoBtnFinalizar.setOnClickListener(v -> {
                deletaPedido();
                Toast.makeText(informacoesApp, "Pedido finalizado com sucesso!", Toast.LENGTH_SHORT).show();
            });

        }

    }

    private void deletaPedido() {
        viewModel.deletaPedido();
        viewModel.getMsg().observe(this, msg -> {
            finish();
        });
    }
}