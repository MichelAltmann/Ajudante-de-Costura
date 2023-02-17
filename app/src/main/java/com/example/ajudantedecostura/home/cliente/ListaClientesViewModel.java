package com.example.ajudantedecostura.home.cliente;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Cliente>> listaClientes;

    public MutableLiveData<ArrayList<Cliente>> pegaListaClientes(ConexaoSocketController conexaoSocket) {
        ArrayList<Cliente> pegaLista = conexaoSocket.carregaListaCliente();
        if (!listaClientes.getValue().equals(pegaLista)){
            listaClientes.setValue(pegaLista);
        }
        return listaClientes;
    }
}
