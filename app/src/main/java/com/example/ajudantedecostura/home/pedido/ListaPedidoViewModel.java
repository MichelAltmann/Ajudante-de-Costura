package com.example.ajudantedecostura.home.pedido;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.home.pedido.adapter.ListaPedidosAdapter;

import java.util.ArrayList;

import modelDominio.Pedido;

public class ListaPedidoViewModel extends ViewModel {

    MutableLiveData<ArrayList<Pedido>> pedidos = new MutableLiveData<>();

    Thread threadCarregaPedidos;

    public MutableLiveData<ArrayList<Pedido>> getPedidos() {
        return pedidos;
    }

    public void carregaPedidos(ConexaoSocketController conexaoSocket){
        threadCarregaPedidos = new Thread((Runnable) () -> {
            ArrayList<Pedido> pedidos = conexaoSocket.carregaListaPedidos();
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                this.pedidos.setValue(pedidos);
            });

        });
        threadCarregaPedidos.start();

    }

}
