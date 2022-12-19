/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import modelDominio.Cliente;
import modelDominio.Costureira;
import modelDominio.Material;
import modelDominio.Medidas;
import modelDominio.Pedido;

/**
 *
 * @author aluno
 */
public class PedidoDao {

    private Connection con;

    public PedidoDao() {
        this.con = Conector.getConnection();
    }

    public ArrayList<Pedido> PedidoCarregaLista() {
        MaterialDao materialDao = new MaterialDao();
        PreparedStatement stmt = null;
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        try {
            //Escrevendo o comando SQL
            String sql = "select * from pedidos  join pessoa on (pedidos.idPessoa = pessoa.idPessoa)";
            //Preparando o statement
            stmt = con.prepareStatement(sql);
            //Pegando os resultados
            ResultSet res = stmt.executeQuery();
            //Navegando nos resultados, criando  o objeto do Pedido e adicionando na lista
            while (res.next()) {
                //Criando Costureira
                Costureira costureira = new Costureira(res.getInt("idCostureira"));
                //Criando Cliente
                Cliente cliente = new Cliente(costureira, res.getInt("idPessoa"),
                        res.getString("cpf"),
                        res.getString("nome"),
                        res.getString("email"),
                        res.getString("telefone"),
                        res.getDate("dataNascimento"),
                        res.getInt("cep"),
                        res.getString("estado"),
                        res.getString("cidade"),
                        res.getString("rua"),
                        res.getInt("numero"));
                //Criando lista de Materiais
                ArrayList<Material> listaMateriais = materialDao.MaterialCarregaListaDePedido(res.getInt("idPedido"));
                //Criando Medida
                Medidas medidas = new Medidas(res.getFloat("pescoco"),
                        res.getFloat("ombro"),
                        res.getFloat("busto"),
                        res.getFloat("bico"),
                        res.getFloat("cintura"),
                        res.getFloat("quadris"),
                        res.getFloat("manga"),
                        res.getFloat("punho"),
                        res.getFloat("larguraBraco"),
                        res.getFloat("larguraCoxa"),
                        res.getFloat("larguraCostas"),
                        res.getFloat("alturaFrente"),
                        res.getFloat("alturaCostas"),
                        res.getFloat("alturaBusto"),
                        res.getFloat("alturaQuadris"),
                        res.getFloat("alturaGancho"),
                        res.getFloat("alturaJoelho"),
                        res.getFloat("comprimentoCalca"),
                        res.getFloat("comprimentoBlusa"),
                        res.getFloat("comprimentoSaia"));
                //Criando Pedido
                Pedido pedido = new Pedido(res.getInt("idPedido"),
                        cliente,
                        res.getInt("prioridade"),
                        res.getString("titulo"),
                        res.getString("descricao"),
                        res.getDate("dataEntrega"),
                        res.getDate("dataCriacao"),
                        listaMateriais,
                        res.getBytes("imagem"),
                        medidas);
                //Adicionando na lista de Pedidos
                listaPedidos.add(pedido);
            }
            //Deu tudo certo retornando a lista
            return listaPedidos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
