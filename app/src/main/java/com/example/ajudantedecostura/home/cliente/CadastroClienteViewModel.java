package com.example.ajudantedecostura.home.cliente;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.socket.InformacoesApp;

import modelDominio.Cliente;

public class CadastroClienteViewModel extends ViewModel {

    private InformacoesApp informacoesApp;
    public CadastroClienteViewModel(InformacoesApp informacoesApp) {
        this.informacoesApp = informacoesApp;
    }
    private MutableLiveData<String> msg = new MutableLiveData<>();
    public Thread threadCadastro;

    public MutableLiveData<String> getMsg() {
        return msg;
    }

    public void cadastraCliente(Cliente cliente){

        if (threadCadastro != null && threadCadastro.isAlive()){
            threadCadastro.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadCadastro = new Thread((Runnable) () -> {
            String msg = conexaoSocket.cadastraCliente(cliente);
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                this.msg.setValue(msg);
            });
        });
        threadCadastro.start();
    }

}
