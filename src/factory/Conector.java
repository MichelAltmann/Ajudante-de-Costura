/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

import java.sql.*;

/**
 *
 * @author aluno
 */
public class Conector {

    //Declarar variável que vai armazenar a conexao
    private static Connection con;

    //Método que faz a conexao
    public static Connection getConnection() {

        try {
            String url = "jdbc:mysql://localhost:3306/"; //Caminho do banco
            String banco = "dbcostureira"; //Nome exatamente igual o workbench
            String usuario = "root";
            String senha = "";

            con = DriverManager.getConnection(url + banco, usuario, senha);
            System.out.println("Conectado com sucesso no banco: "+banco);
            return con;
        } catch (Exception e) {
            e.printStackTrace(); //Imprime os erros
            return null;
        }
    }
}
