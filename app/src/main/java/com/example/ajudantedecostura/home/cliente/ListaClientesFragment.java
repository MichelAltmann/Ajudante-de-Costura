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
import android.widget.Toast;

import com.example.ajudantedecostura.SalvaImagem;
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

        atualizaLista();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void atualizaLista(){
        final Observer<ArrayList<Cliente>> clientesObserver = new Observer<ArrayList<Cliente>>() {
            @Override
            public void onChanged(ArrayList<Cliente> clientes) {
                adapter = new ListaClientesAdapter(listaClientes, onClickListener);
                binding.fragmentListaClientesRecyclerview.setAdapter(adapter);
            }

        };
        Thread thread = new Thread((Runnable) () -> {
            viewModel.pegaListaClientes(conexaoSocket).observe(getActivity(), clientesObserver);
        });
        thread.start();
    }

}