/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import factory.Conector;
import java.io.IOException;
import java.net.ServerSocket;
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
            //Continuar na próxima aula aqui
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
