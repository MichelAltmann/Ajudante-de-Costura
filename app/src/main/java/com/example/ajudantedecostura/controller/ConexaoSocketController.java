package com.example.ajudantedecostura.controller;

import modelDominio.Costureira;
import com.example.ajudantedecostura.socket.InformacoesApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConexaoSocketController {
    InformacoesApp informacoesApp;

    public ConexaoSocketController(InformacoesApp informacoesApp){
        this.informacoesApp = informacoesApp;
    }

    public boolean criaConexao(){
        boolean resultado;
        try {
            informacoesApp.socket = new Socket("192.168.31.101", 12345);
            informacoesApp.out = new ObjectOutputStream(informacoesApp.socket.getOutputStream());
            informacoesApp.in = new ObjectInputStream(informacoesApp.socket.getInputStream());

            resultado = true;
        } catch (IOException ioe){
            ioe.printStackTrace();
            resultado = false;
        }
        return resultado;
    }

    public Costureira autenticaCostureira(Costureira costureira){
        Costureira costureiraLogada = null;
        try {
            informacoesApp.out.writeObject("CostureiraEfetuarLogin");
            String msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")) {
                informacoesApp.out.writeObject(costureira);
                costureiraLogada = (Costureira) informacoesApp.in.readObject();
            }
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        return costureiraLogada;
    }

    public String cadastraCostureira(Costureira costureira){
        String msgRecebida = null;
        try {
            informacoesApp.out.writeObject("CostureiraCadastrar");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")) {
                informacoesApp.out.writeObject(costureira);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msgRecebida;
    }

}
