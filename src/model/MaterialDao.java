/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import modelDominio.Material;

/**
 *
 * @author aluno
 */
public class MaterialDao {

    private Connection con;

    public MaterialDao() {
        this.con = Conector.getConnection();
    }

    //Função que carrega a lista de materiais de um pedido em específico
    public ArrayList<Material> materialCarregaListaDePedido(int idPedido) {
        PreparedStatement stmt = null;
        ArrayList<Material> listaMateriais = new ArrayList<>();

        try {
            //Escrevendo o comando SQL
            String sql = "select * from material join pedidoMaterial on (material.idMaterial = pedidoMaterial.idMaterial) where pedidoMaterial.idPedido = ?";
            //Preparando o statement
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idPedido);
            //Pegando o resultado
            ResultSet res = stmt.executeQuery();
            //Navegando nos resultados, criando o material e adicionando na lista
            while (res.next()) {
                Material material = new Material(res.getInt("idMaterial"),
                        res.getString("tipo"),
                        res.getFloat("comprimento"),
                        res.getFloat("largura"),
                        res.getFloat("preco"));
                listaMateriais.add(material);
            }
            //Deu tudo certo retornando a lista
            return listaMateriais;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Função que cadastra novos materiais
    public int materialCadastrar(Material material) {
        PreparedStatement stmt = null;

        try {
            try {
                //Desligando o autocommit
                con.setAutoCommit(false);
                //Fazendo o comando SQL
                String sql = "insert into material (comprimento, largura, preco)"
                        + "values (?,?,?)";
                //Preparando o statement
                stmt = con.prepareStatement(sql);
                //Inserindo os dados no statement
                stmt.setFloat(1, material.getComprimento());
                stmt.setFloat(2, material.getLargura());
                stmt.setFloat(3, material.getPreco());
                //Executando o statement
                stmt.execute();
                //Executando o commit
                con.commit();
                //Deu tudo certo retornando -1
                return -1;
            } catch (SQLException e) {
                try {
                    con.rollback();
                    e.printStackTrace();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }
        } finally {
            try {
                stmt.close();
            } catch (SQLException exc) {
                exc.printStackTrace();
                exc.getErrorCode();
            }
        }

    }

    //Função que carrega a lista de materiais sem um pedido em específico
    public ArrayList<Material> materialCarregaLista() {
        PreparedStatement stmt = null;
        ArrayList<Material> listaMateriais = new ArrayList<>();

        try {
            //Escrevendo o comando SQL
            String sql = "select * from material";
            //Preparando o statement
            stmt = con.prepareStatement(sql);
            //Pegando o resultado
            ResultSet res = stmt.executeQuery();
            //Navegando nos resultados, criando o material e adicionando na lista
            while (res.next()) {
                Material material = new Material(res.getInt("idMaterial"),
                        res.getString("tipo"),
                        res.getFloat("comprimento"),
                        res.getFloat("largura"),
                        res.getFloat("preco"));
                listaMateriais.add(material);
            }
            //Deu tudo certo retornando a lista
            return listaMateriais;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Função que deleta um material
    public int materialDeletar(Material material) {
        PreparedStatement stmt = null;

        try {
            try {
                //Desligando o autocommit
                con.setAutoCommit(false);
                //Escrevendo o comando SQL
                String sql = "delete from material where idMaterial = ?";
                //Preparando o statement
                stmt = con.prepareStatement(sql);
                //Inserindo os dados no statement
                stmt.setInt(1, material.getIdMaterial());
                //Executando o statement
                stmt.execute();
                //Executando o commit
                con.commit();
                //Deu tudo certo retornando -1
                return -1;
            } catch (SQLException e) {
                try {
                    con.rollback();
                    e.printStackTrace();
                    return e.getErrorCode();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return ex.getErrorCode();
                }
            }
        } finally {
            try {
                stmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
    }
}
