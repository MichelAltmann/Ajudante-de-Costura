package com.example.ajudantedecostura.home.pedido;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.FragmentListaPedidosBinding;
import com.example.ajudantedecostura.home.cliente.ListaClientesViewModel;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.home.pedido.adapter.ListaPedidosAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;
import modelDominio.Pedido;

public class ListaPedidosFragment extends Fragment {

    private FragmentListaPedidosBinding binding;
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private ListaPedidosAdapter adapter;
    InformacoesApp informacoesApp;
    ConexaoSocketController conexaoSocket;

    ListaPedidoViewModel viewModel;

    ListaPedidosAdapter.PedidosOnClickListener onPedidoClick = new ListaPedidosAdapter.PedidosOnClickListener() {
        @Override
        public void onClickPedido(View view, int position) {
            Intent intent = new Intent(getContext(), CadastroPedidoActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaPedidosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        informacoesApp = (InformacoesApp) getActivity().getApplicationContext();
        conexaoSocket = new ConexaoSocketController(informacoesApp);
        viewModel = new ViewModelProvider(this).get(ListaPedidoViewModel.class);
        setObservador();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.carregaPedidos(conexaoSocket);
    }

    private void setObservador(){
        final Observer<ArrayList<Pedido>> pedidosObserver = pedidos -> {
            adapter = new ListaPedidosAdapter(pedidos, onPedidoClick, getContext());
            binding.fragmentListaPedidosRecyclerview.setAdapter(adapter);
        };
        viewModel.getPedidos().observe(getActivity(), pedidosObserver);
    }

}