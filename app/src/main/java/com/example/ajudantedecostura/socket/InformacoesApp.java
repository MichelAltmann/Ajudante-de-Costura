package com.example.ajudantedecostura.socket;

import android.app.Application;

import modelDominio.Costureira;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class InformacoesApp extends Application {
    public Socket socket;
    public ObjectOutputStream out;
    public ObjectInputStream in;

    private Costureira costureiraLogada;

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
}
