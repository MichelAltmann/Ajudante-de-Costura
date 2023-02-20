package com.example.ajudantedecostura.controller;

import android.util.Log;
import android.widget.Toast;

import modelDominio.Cliente;
import modelDominio.Costureira;
import modelDominio.Material;
import modelDominio.Pedido;

import com.example.ajudantedecostura.socket.InformacoesApp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

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

    public ArrayList<Cliente> carregaListaCliente(){
        ArrayList<Cliente> listaCliente = new ArrayList<>();
        String msgRecebida = null;

        try{
            informacoesApp.out.writeObject("ClienteCarregarListaCostureira");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")){
                Log.i("costureira: ", "carregaListaCliente: " + informacoesApp.getCostureiraLogada().toString());
                informacoesApp.out.writeObject(informacoesApp.getCostureiraLogada());
                listaCliente = (ArrayList<Cliente>) informacoesApp.in.readObject();
            } else {
                Log.i("costureira: ", "carregaListaCliente: ");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaCliente;
    }


    public String cadastraPedido(Pedido pedido){
        String msgRecebida = null;
        try {
            informacoesApp.out.writeObject("PedidoCadastrar");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")){
                Log.i("asd", "cadastraPedido: tristeza");
                informacoesApp.out.writeObject(pedido);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msgRecebida;
    }

    public ArrayList<Pedido> carregaListaPedidos(){
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        try {
            informacoesApp.out.writeObject("PedidoCarregarListaCostureira");
            String msgRecebida = (String) informacoesApp.in.readObject();
            informacoesApp.out.writeObject(informacoesApp.getCostureiraLogada());
            listaPedidos = (ArrayList<Pedido>) informacoesApp.in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }

    public ArrayList<Pedido> carregaListaPedidoCliente(Cliente cliente){
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        String msgRecebida;
        try {
            informacoesApp.out.writeObject("PedidoCarregarListaCliente");
            msgRecebida = (String) informacoesApp.in.readObject();
            Log.i("ConexaoSocket", "carregaListaPedidoCliente: " + msgRecebida);
            informacoesApp.out.writeObject(cliente);
            listaPedidos = (ArrayList<Pedido>) informacoesApp.in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }

    public String deletaCliente(String idPessoa){
        String msgRecebida = null;
        try {
            informacoesApp.out.writeObject("ClienteDeletar");
            msgRecebida = (String) informacoesApp.in.readObject();
            if (msgRecebida.equals("Ok")){
                informacoesApp.out.writeObject(idPessoa);
                msgRecebida = (String) informacoesApp.in.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return msgRecebida;
    }

    public Cliente selecionaCliente(String id){
        Cliente cliente;
        try {
            informacoesApp.out.writeObject("ClienteSelecionar");
            informacoesApp.out.writeObject(id);
            cliente = (Cliente) informacoesApp.in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    public void deletaPedidos(ArrayList<Pedido> pedidos) {
        try {
            informacoesApp.out.writeObject("PedidoDeletar");
            informacoesApp.out.writeObject(pedidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Material> carregaMateriaisPedido(String idPedido){
        ArrayList<Material> materiais = new ArrayList<>();
        try {
            informacoesApp.out.writeObject("MaterialCarregarListaPedido");
            informacoesApp.out.writeObject(idPedido);
            materiais = (ArrayList<Material>) informacoesApp.in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return materiais;
    }

    public void deletaMateriais(ArrayList<Material> materiais){

    }
}
