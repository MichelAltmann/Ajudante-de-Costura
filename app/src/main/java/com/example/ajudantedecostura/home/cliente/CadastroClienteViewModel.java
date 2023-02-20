package com.example.ajudantedecostura.home.cliente;

import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.socket.InformacoesApp;

import modelDominio.Cliente;

public class CadastroClienteViewModel extends ViewModel {

    private InformacoesApp informacoesApp;
    public CadastroClienteViewModel(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }

    private Thread threadCadastro;
    public void cadastraCliente(Cliente cliente){

        if (threadCadastro != null && threadCadastro.isAlive()){
            threadCadastro.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadCadastro = new Thread((Runnable) () -> {
            conexaoSocket.cadastraCliente(cliente);
        });
        threadCadastro.start();
    }

}
