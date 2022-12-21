/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import modelDominio.Administrador;

/**
 *
 * @author Pedro Müller
 */
public class AdministradorDao {
    
    private Connection con;

    public AdministradorDao() {
        this.con = Conector.getConnection();
    }
    
    public Administrador administradotEfetuarLogin(Administrador admin){
        PreparedStatement stmt = null;
        Administrador adminLogado = null;
        
        try{
            //Consultado no banco de dados
            String sql = "select * from administrador where login = ? and senha = ?";
            //preparando o statement
            stmt = con.prepareStatement(sql);
            stmt.setString(1, admin.getLogin());
            stmt.setString(2, admin.getSenha());
            
            //Pegando o resultado e executando a query
            ResultSet res = stmt.executeQuery();
            while (res.next()){
                adminLogado = new Administrador(res.getString("login"), 
                        res.getString("senha"));
                System.out.println(adminLogado.toString());
            }
            res.close();
            stmt.close();
            con.close();
            //Deu tudo certo, retornando o adminLogado
            return adminLogado;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
