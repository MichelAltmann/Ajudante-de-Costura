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

    public ArrayList<Pedido> pedidoCarregaLista() {
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
                ArrayList<Material> listaMateriais = materialDao.materialCarregaListaDePedido(res.getInt("idPedido"));
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

    public int pedidoCadastrar(Pedido pedido) {
        PedidoMaterialDao pedidoMaterialDao = new PedidoMaterialDao();
        PreparedStatement stmt = null;
        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                //SQL statement
                String sql = "insert into pedidos (idPedido, "
                        + "prioridade, "
                        + "titulo, "
                        + "descricao, "
                        + "dataEntrega, "
                        + "dataCriacao, "
                        + "imagem, "
                        + "idPessoa, "
                        + "preco, "
                        + "pescoco, "
                        + "busto, "
                        + "ombro, "
                        + "bico, "
                        + "cintura, "
                        + "quadris, "
                        + "manga, "
                        + "punho, "
                        + "larguraBraco, "
                        + "larguraCoxa, "
                        + "larguraCostas, "
                        + "alturaFrente, "
                        + "alturaCostas, "
                        + "alturaBusto, "
                        + "alturaQuadris, "
                        + "alturaGancho, "
                        + "alturaJoelho, "
                        + "comprimentoCalca, "
                        + "comprimentoBlusa, "
                        + "comprimentoSaia)"
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //Preparando o Statement
                stmt = con.prepareStatement(sql);
                //Inserindo os dados no statement
                stmt.setInt(1, pedido.getIdPedido());
                stmt.setInt(2, pedido.getPrioridade());
                stmt.setString(3, pedido.getTitulo());
                stmt.setString(4, pedido.getDescricao());
                stmt.setDate(5, (Date) pedido.getDataEntrega());
                stmt.setDate(6, (Date) pedido.getDataCriacao());
                stmt.setBytes(7, pedido.getImagem());
                stmt.setInt(8, pedido.getCliente().getIdPessoa());
                stmt.setFloat(9, pedido.CalculaPrecoPedido());
                stmt.setFloat(10, pedido.getMedidas().getPescoco());
                stmt.setFloat(11, pedido.getMedidas().getBusto());
                stmt.setFloat(12, pedido.getMedidas().getOmbro());
                stmt.setFloat(13, pedido.getMedidas().getBico());
                stmt.setFloat(14, pedido.getMedidas().getCintura());
                stmt.setFloat(15, pedido.getMedidas().getQuadris());
                stmt.setFloat(16, pedido.getMedidas().getManga());
                stmt.setFloat(17, pedido.getMedidas().getPunho());
                stmt.setFloat(18, pedido.getMedidas().getLarguraBraco());
                stmt.setFloat(19, pedido.getMedidas().getLarguraCoxa());
                stmt.setFloat(20, pedido.getMedidas().getAlturaFrente());
                stmt.setFloat(21, pedido.getMedidas().getAlturaCostas());
                stmt.setFloat(22, pedido.getMedidas().getAlturaBusto());
                stmt.setFloat(23, pedido.getMedidas().getAlturaQuadris());
                stmt.setFloat(24, pedido.getMedidas().getAlturaGancho());
                stmt.setFloat(25, pedido.getMedidas().getAlturaJoelho());
                stmt.setFloat(26, pedido.getMedidas().getComprimentoCalca());
                stmt.setFloat(27, pedido.getMedidas().getComprimentoBlusa());
                stmt.setFloat(28, pedido.getMedidas().getComprimentoSaia());
                stmt.setFloat(29, pedido.getMedidas().getLarguraCostas());
                //Executando o statement
                stmt.execute();
                //Realizando o commit
                con.commit();
                //Chamando PedidoMaterialDao para realizar a conexão dos dois no banco
                pedidoMaterialDao.pedidoMaterialCadastrar(pedido);
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
                exc.getErrorCode();
            }
        }

    }

}
