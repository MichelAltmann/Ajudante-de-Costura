package com.example.ajudantedecostura.home.pedido;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.home.cliente.CadastroClienteViewModel;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;
import modelDominio.Pedido;

public class CadastroPedidoViewModel extends ViewModel {


    private MutableLiveData<Cliente> cliente = new MutableLiveData<>();

    private InformacoesApp informacoesApp;
    public CadastroPedidoViewModel(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    private Thread threadCadastroPedido;
    private Thread threadSelecionaCliente;

    public void cadastroPedido(Pedido pedido){

        if (threadCadastroPedido != null && threadCadastroPedido.isAlive()){
            threadCadastroPedido.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadCadastroPedido = new Thread((Runnable) () -> {
            conexaoSocket.cadastraPedido(pedido);
        });
        threadCadastroPedido.start();
    }

}
