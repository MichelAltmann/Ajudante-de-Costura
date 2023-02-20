package com.example.ajudantedecostura.home.cliente;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.home.cliente.adapter.ListaClientesAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;

public class ListaClientesViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Cliente>> listaClientes = new MutableLiveData<>();

    private Thread threadCarregaLista;

    public MutableLiveData<ArrayList<Cliente>> pegaListaClientes() {
        return listaClientes;
    }

    public void carregaLista(InformacoesApp informacoesApp){


        if (threadCarregaLista != null && threadCarregaLista.isAlive()){
            threadCarregaLista.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadCarregaLista = new Thread((Runnable) () -> {
            ArrayList<Cliente> pegaLista = conexaoSocket.carregaListaCliente();
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                listaClientes.setValue(pegaLista);
            });
        });

        threadCarregaLista.start();
    }
}
