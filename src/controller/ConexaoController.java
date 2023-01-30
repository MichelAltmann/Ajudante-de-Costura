/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import modelDominio.Administrador;
import modelDominio.Cliente;
import modelDominio.Costureira;
import modelDominio.Pedido;

/**
 *
 * @author Pedro Müller
 */
public class ConexaoController {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    
    public Administrador administrador;
    public ConexaoController(ObjectOutputStream out, ObjectInputStream in) {
        this.out = out;
        this.in = in;
    }
    
    public Administrador efetuarLogin (Administrador administrador){
        //Implementando a comunicacao com o servidor
        String msg;
        try{
            out.writeObject("AdministradorEfetuarLogin");
            msg = (String) in.readObject();
         
                out.writeObject(administrador);
                administrador = (Administrador) in.readObject();
                return administrador;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Costureira> costureiraCarregaLista(){
        //Implementando a comunicação com o servidor
        String msg;
        try{
            out.writeObject("CostureiraCarregarLista");
            return (ArrayList<Costureira>) in.readObject();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Cliente> clienteCarregaLista(){
        //Implementando a comunicação com o servidor
        String msg;
        try{
            out.writeObject("ClienteCarregarLista");
            return (ArrayList<Cliente>) in.readObject();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Pedido> pedidoCarregaLista(){
        //Implementando a comunicação com o servidor
        String msg;
        try{
            out.writeObject("PedidoCarregarLista");
            return (ArrayList<Pedido>) in.readObject();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int costureiraAlterarAutorizacao(Costureira costureira){
        //Implementando a comunicacao com o servidor
        String msg;
        try{
            out.writeObject("CostureiraAlterarAutorizacao");
            msg = (String) in.readObject();
            if (msg.equalsIgnoreCase("Ok")){
                out.writeObject(costureira);
            } else {
                return 1;
            }
            msg = (String) in.readObject();
            if (msg.equalsIgnoreCase("Nok")){
                return 1;
            }
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
