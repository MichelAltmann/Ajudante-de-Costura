/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import modelDominio.Material;
import modelDominio.Pedido;

/**
 *
 * @author Pedro Müller
 */
public class PedidoMaterialDao {
    
    Connection con;

    public PedidoMaterialDao() {
        this.con = Conector.getConnection();
    }
    
    public int pedidoMaterialCadastrar(Pedido pedido){
        PreparedStatement stmt = null;
        
        try {
            try{
                //Desliga o autocommit
                con.setAutoCommit(false);
                for (int x = 0; x > pedido.getListaMateriais().size(); x++){
                    Material material = pedido.getListaMateriais().get(x);
                    //SQL statemente
                    String sql = "insert into pedidoMaterial (idPedido, "
                            + "idMaterial, "
                            + "custo) "
                            + "values(?,?,?)";
                    //Preparando o statement
                    stmt = con.prepareStatement(sql);
                    //Inserindo os dados no statement
                    stmt.setInt(1, pedido.getIdPedido());
                    stmt.setInt(2, material.getIdMaterial());
                    stmt.setFloat(3, material.CalculaPrecoTotal());
                    //Executando o statement
                    stmt.execute();
                    //Realizando o commit
                    con.commit();
                }
                
                //Terminou a lista e deu tudo certo, retornando -1
                return -1;
            } catch (SQLException e){
                try {
                    con.rollback();
                    e.printStackTrace();
                    return e.getErrorCode();
                } catch (SQLException ex){
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }
        } finally{
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException exc){
                exc.printStackTrace();
                exc.getErrorCode();
            }
        }
    }
     
}
