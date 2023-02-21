package com.example.ajudantedecostura.home.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.FragmentListaClientesBinding;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesFragment extends Fragment {

    FragmentListaClientesBinding binding;
    ArrayList<Cliente> listaClientes = new ArrayList<>();
    ListaClientesAdapter adapter;
    InformacoesApp informacoesApp;
    ConexaoSocketController conexaoSocket;
    ListaClientesAdapter.ClientesOnClickListener onClickListener;
    ListaClientesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaClientesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ListaClientesViewModel.class);

        informacoesApp = (InformacoesApp) getActivity().getApplicationContext();

        conexaoSocket = new ConexaoSocketController(informacoesApp);

        onClickListener = (iview, position) -> {
            Intent intent = new Intent(getContext(), DetalhesClienteActivity.class);
            intent.putExtra("posicao", position);
            startActivity(intent);
        };

        setObservador();
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.carregaLista(conexaoSocket);
    }

    private void setObservador(){
        final Observer<ArrayList<Cliente>> clientesObserver = clientes -> {
            informacoesApp.setClientes(clientes);
            this.listaClientes = clientes;
            adapter = new ListaClientesAdapter(clientes, onClickListener);
            binding.fragmentListaClientesRecyclerview.setAdapter(adapter);
        };
        viewModel.pegaListaClientes().observe(getActivity(), clientesObserver);
    }

}