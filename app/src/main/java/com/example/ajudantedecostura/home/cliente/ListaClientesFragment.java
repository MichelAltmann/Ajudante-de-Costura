package com.example.ajudantedecostura.home.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentListaClientesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        informacoesApp = (InformacoesApp) getActivity().getApplicationContext();

        conexaoSocket = new ConexaoSocketController(informacoesApp);

        onClickListener = (iview, position) -> {
            Intent intent = new Intent(getContext(), DetalhesClienteActivity.class);
            Cliente cliente = listaClientes.get(position);
            SalvaImagem.imagem = cliente.getImagem();
            cliente.setImagem(null);
            intent.putExtra("cliente", cliente);
            startActivity(intent);
        };

    }

    @Override
    public void onResume() {
        super.onResume();
        carregaLista(conexaoSocket);
    }

    private void carregaLista(ConexaoSocketController conexaoSocket){
        Thread thread = new Thread((Runnable) () -> {
            listaClientes = conexaoSocket.carregaListaCliente();
            informacoesApp.setClientes(listaClientes);
            getActivity().runOnUiThread((Runnable) () -> {
                adapter = new ListaClientesAdapter(listaClientes, onClickListener);
                binding.fragmentListaClientesRecyclerview.setAdapter(adapter);
                Toast.makeText(informacoesApp, ""+listaClientes.size(), Toast.LENGTH_SHORT).show();
            });

        });
        thread.start();
    }
}