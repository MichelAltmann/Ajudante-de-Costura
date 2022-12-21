/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.TrataClienteController;
import factory.Conector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
/**
 *
 * @author aluno
 */
public class Principal {
    //Criando o método main que vai executar o servidor
    public static void main(String[] args) throws IOException {
        //Fazendo a conexao com o banco
        Connection con;
        con = Conector.getConnection();
        
        try {
            //Criar o servidor Socket
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor inicializado, Aguardando conexoes...");
            
            ConectaServidor s1 = new ConectaServidor(servidor, con);
            //iniciando thread
            s1.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ConectaServidor extends Thread{
    private ServerSocket servidor;
    private int idUnico = 0;
    private Connection con;

    public ConectaServidor(ServerSocket servidor, Connection con) {
        this.servidor = servidor;
        this.con = con;
    }

    @Override
    public void run() {
        try {
            while(true){
                Socket cliente = this.servidor.accept();
                System.out.println("um novo cliente conectou "+cliente);
                
                //criando entrada e saida
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
                idUnico++;
                System.out.println("Inicializando uma thread para o cliente: "+idUnico);
                TrataClienteController trataCliente = new TrataClienteController(in, out, cliente, idUnico);
                trataCliente.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }  
}
