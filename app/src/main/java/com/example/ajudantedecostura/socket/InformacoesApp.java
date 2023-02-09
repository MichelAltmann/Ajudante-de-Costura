package com.example.ajudantedecostura.socket;

import android.app.Application;

import modelDominio.Cliente;
import modelDominio.Costureira;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Costureira costureiraLogada;
    private ArrayList<Cliente> clientes;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public Costureira getCostureiraLogada() {
        return costureiraLogada;
    }

    public void setCostureiraLogada(Costureira costureiraLogada){
        this.costureiraLogada = costureiraLogada;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
}
