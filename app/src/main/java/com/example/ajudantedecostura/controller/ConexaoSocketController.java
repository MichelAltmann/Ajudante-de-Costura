package com.example.ajudantedecostura.controller;

import android.util.Log;

import modelDominio.Cliente;
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
            informacoesApp.socket = new Socket("10.0.2.2", 12345);
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
            Log.i("teste", "autenticaCostureira: " + msgRecebida);
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

    public String cadastraCliente(Cliente cliente){
        String msgRecebida = null;
        try{
            informacoesApp.out.writeObject("ClienteCadastrar");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")){
                informacoesApp.out.writeObject(cliente);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msgRecebida;
    }

}
