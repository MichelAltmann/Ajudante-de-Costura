package com.example.ajudantedecostura.home.cliente;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ajudantedecostura.controller.ConexaoSocketController;
import com.example.ajudantedecostura.home.cliente.adapter.ListaPedidosClienteAdapter;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.util.ArrayList;

import modelDominio.Cliente;
import modelDominio.Pedido;

public class DetalhesClienteViewModel extends ViewModel {

    private InformacoesApp informacoesApp;
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private MutableLiveData<ArrayList<Pedido>> pedidos = new MutableLiveData<>();
    private Cliente cliente;
    public DetalhesClienteViewModel(InformacoesApp informacoesApp, Cliente cliente) {
        this.informacoesApp = informacoesApp;
        this.cliente = cliente;
    }

    public MutableLiveData<ArrayList<Pedido>> pegaPedidos(){
        return pedidos;
    }

    ArrayList<Pedido> deletaPedidos = new ArrayList<>();

    private Thread threadDeletar;

    public void deletaClientes(){

        if (threadDeletar != null && threadDeletar.isAlive()){
            threadDeletar.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadDeletar = new Thread((Runnable) () -> {
            clientes.add(cliente);
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                deletaPedidos = pedidos.getValue();
            });
//            conexaoSocket.deletaPedidos(deletaPedidos);
            conexaoSocket.deletaCliente(cliente.getIdPessoa());
//            clientes = conexaoSocket.carregaListaCliente();
            Log.i("teste", "deletaClientes: " + clientes);
//                Toast.makeText(informacoesApp, "" + cliente.getNome(), Toast.LENGTH_SHORT).show();
//                runOnUiThread((Runnable) this::finish);
        });
        threadDeletar.start();
    }

    private Thread threadPedidos;
    public void carregaPedidosCliente(){
        if (threadPedidos != null && threadPedidos.isAlive()){
            threadPedidos.interrupt();
        }

        ConexaoSocketController conexaoSocket = new ConexaoSocketController(informacoesApp);
        threadPedidos = new Thread((Runnable) () -> {
            ArrayList<Pedido> pegaPedidos = conexaoSocket.carregaListaPedidoCliente(cliente);
            new Handler(Looper.getMainLooper()).post((Runnable) () -> {
                pedidos.setValue(pegaPedidos);
            });
        });
        threadPedidos.start();
    }


}
