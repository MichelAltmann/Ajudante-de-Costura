package com.example.ajudantedecostura.home.cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.databinding.FragmentListaClientesBinding;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesFragment extends Fragment {

    FragmentListaClientesBinding binding;
    ArrayList<Cliente> listaClientes;
    ListaClientesAdapter adapter;
    InformacoesApp informacoesApp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaClientesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesApp = (InformacoesApp) getActivity().getApplicationContext();

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);

        Thread thread = new Thread((Runnable) () -> {

            if (conexaoSocket.carregaListaCliente() != null){
                listaClientes = conexaoSocket.carregaListaCliente();
            }

            getActivity().runOnUiThread((Runnable) () -> {
                adapter = new ListaClientesAdapter(listaClientes);
                binding.fragmentListaClientesRecyclerview.setAdapter(adapter);
                Toast.makeText(informacoesApp, ""+listaClientes.size(), Toast.LENGTH_SHORT).show();
            });

        });
//        thread.start();


    }
}