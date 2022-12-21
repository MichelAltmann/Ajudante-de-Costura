/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelDominio.Administrador;
import modelDominio.Costureira;

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
}
