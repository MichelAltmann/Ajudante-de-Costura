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

    public ArrayList<Material> MaterialCarregaListaDePedido(int idPedido) {
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

}
