package com.example.ajudantedecostura.home.pedido;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.socket.InformacoesApp;

import modelDominio.Pedido;

public class DetalhesPedidoViewModel extends ViewModel {

    private MutableLiveData<String> msg = new MutableLiveData<>();

    private Thread deletaThread = null;
    private Pedido pedido;
    private InformacoesApp informacoesApp;

    public DetalhesPedidoViewModel(InformacoesApp informacoesApp, Pedido pedido) {
        this.informacoesApp = informacoesApp;
        this.pedido = pedido;
    }

    public MutableLiveData<String> getMsg() {
        return msg;
    }

    public void deletaPedido() {

        if (deletaThread != null && deletaThread.isAlive()) {
            deletaThread.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        deletaThread = new Thread((Runnable) () -> {
            String msg = conexaoSocket.deletaPedido(pedido.getIdPedido());
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                this.msg.setValue(msg);
            });
        });
        deletaThread.start();
    }

}
