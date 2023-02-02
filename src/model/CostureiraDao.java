/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import java.util.ArrayList;
import modelDominio.Costureira;

/**
 *
 * @author Pedro Müller
 */
public class CostureiraDao {

    private Connection con;

    public CostureiraDao() {
        this.con = Conector.getConnection();
    }

    //Função que cadastra costureiras - COSTUREIRA
    public int costureiraCadastrar(Costureira costureira) {
        PreparedStatement stmt = null;
        try {
            try {
                //Desligando o autocommit
                con.setAutoCommit(false);
                //Fazendo o comando SQL
                String sql = "insert into pessoa (cpf,"
                        + " nome,"
                        + " email,"
                        + " telefone,"
                        + " dataNascimento,"
                        + " cep,"
                        + " estado,"
                        + " cidade,"
                        + " rua,"
                        + " numero,"
                        + " tipo,"
                        + " senha,"
                        + " imagem) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //Preparando o statement
                stmt = con.prepareStatement(sql);
                //Inserindo os dados no statement
                stmt.setString(1, costureira.getCpf());
                stmt.setString(2, costureira.getNome());
                stmt.setString(3, costureira.getEmail());
                stmt.setString(4, costureira.getTelefone());
                stmt.setDate(5, new java.sql.Date(costureira.getDataNascimento().getTime()));
                stmt.setInt(6, costureira.getCep());
                stmt.setString(7, costureira.getEstado());
                stmt.setString(8, costureira.getCidade());
                stmt.setString(9, costureira.getRua());
                stmt.setInt(10, costureira.getNumero());
                stmt.setInt(11, 0);
                stmt.setString(12, costureira.getSenha());
                stmt.setBytes(13, costureira.getImagem());
                //Executando o statemente
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

    //Função que realiza o login de costureiras - COSTUREIRA
    public Costureira costureiraEfetuarLogin(Costureira costureira) {
        PreparedStatement stmt = null;
        Costureira costureiraLogada = null;

        try {
            //Statement SQL
            String sql = "select * from pessoa"
                    + " where (email = ? or cpf = ?) and senha = ?";
            //Preparando o statement
            stmt = con.prepareStatement(sql);
            //Inserindo os dados no statement
            stmt.setString(1, costureira.getEmail());
            stmt.setString(2, costureira.getEmail()); //Pegamos com o email pois é do mesmo edittext
            stmt.setString(3, costureira.getSenha());
            //Navegando pelos resultados
            ResultSet res = stmt.executeQuery();

            //Se tem resultado
            if (res.next()) {
                costureiraLogada = new Costureira(res.getString("senha"),
                        res.getBytes("imagem"),
                        res.getInt("idPessoa"),
                        res.getString("cpf"),
                        res.getString("nome"),
                        res.getString("email"),
                        res.getString("telefone"),
                        res.getDate("dataNascimento"),
                        res.getInt("cep"),
                        res.getString("estado"),
                        res.getString("cidade"),
                        res.getString("rua"),
                        res.getInt("numero"),
                        res.getInt("autorizacao"));

                System.out.println(costureiraLogada.toString());

            }
            //Fechar a conexão e stmt
            res.close();
            stmt.close();
            con.close();
            return costureiraLogada;

        } catch (Exception e) {
            e.printStackTrace();
            return costureiraLogada;
        }
    }

    //Função que altera os dados de costureiras (INCLUI AUTORIZAÇÃO) - COSTUREIRA/ADMINISTRADOR - ARRAY
    public int costureiraAlterar(ArrayList<Costureira> listaCostureiras) {
        PreparedStatement stmt = null;

        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                for (int x = 0; x < listaCostureiras.size(); x++){
                Costureira costureira = listaCostureiras.get(x);
                //Escrevendo o comando SQL
                String sql = "update pessoa set cpf = ?,"
                        + " nome = ?,"
                        + " email = ?,"
                        + " telefone = ?,"
                        + " dataNascimento = ?,"
                        + " cep = ?,"
                        + " estado = ?,"
                        + " cidade = ?,"
                        + " rua = ?,"
                        + " numero = ?,"
                        + " senha = ?,"
                        + " imagem = ?,"
                        + " autorizacao = ? where idPessoa = ?";
                //Preparando o Statement
                stmt = con.prepareStatement(sql);
                //Inserindo os dados no statement
                stmt.setString(1, costureira.getCpf());
                stmt.setString(2, costureira.getNome());
                stmt.setString(3, costureira.getEmail());
                stmt.setString(4, costureira.getTelefone());
                stmt.setDate(5, new java.sql.Date(costureira.getDataNascimento().getTime()));
                stmt.setInt(6, costureira.getCep());
                stmt.setString(7, costureira.getEstado());
                stmt.setString(8, costureira.getCidade());
                stmt.setString(9, costureira.getRua());
                stmt.setInt(10, costureira.getNumero());
                stmt.setString(11, costureira.getSenha());
                stmt.setBytes(12, costureira.getImagem());
                stmt.setInt(13, costureira.getAutorizacao());
                stmt.setInt(14, costureira.getIdPessoa());
                //Executando o comando SQL
                stmt.execute();
                //Executando o commit
                con.commit();
                }
                
                return -1;
            } catch (SQLException e) {
                try {
                    con.rollback();
                    return e.getErrorCode();
                } catch (SQLException ex) {
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

    //Função que retorna a lista de costureiras - ADMINISTRADOR
    public ArrayList<Costureira> costureiraCarregaLista() {
        PreparedStatement stmt = null;
        ArrayList<Costureira> listaCostureiras = new ArrayList<>();
        try {
            //Escrevendo o comando SQL
            String sql = "select * from pessoa where autorizacao is not null";
            //preparando o statement
            stmt = con.prepareStatement(sql);
            //pegando o resultado
            ResultSet res = stmt.executeQuery();
            //Navegando no resultados, criando o objeto da costureira e adicionando na lista
            while (res.next()) {
                //Criando a costureira
                Costureira costureira = new Costureira(res.getString("senha"),
                        res.getBytes("imagem"),
                        res.getInt("idPessoa"),
                        res.getString("cpf"),
                        res.getString("nome"),
                        res.getString("email"),
                        res.getString("telefone"),
                        res.getDate("dataNascimento"),
                        res.getInt("cep"),
                        res.getString("estado"),
                        res.getString("cidade"),
                        res.getString("rua"),
                        res.getInt("numero"),
                        res.getInt("autorizacao"));
                //Adicionando a costureira na lista
                listaCostureiras.add(costureira);
            }
            //Deu tudo certo retornando a lista
            return listaCostureiras;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Função que retorna uma costureira com base no ID - ADMINISTRADOR
    public Costureira costureiraCarregaComID(int id) {
        PreparedStatement stmt = null;
        Costureira costureira = null;
        try {
            //Escrevendo o comando SQL
            String sql = "select * from pessoa where idPessoa = ?";
            //preparando o statement
            stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            //pegando o resultado
            ResultSet res = stmt.executeQuery();
            //Navegando no resultados, criando o objeto da costureira
            while (res.next()) {
                //Criando a costureira
                costureira = new Costureira(res.getString("senha"),
                        res.getBytes("imagem"),
                        res.getInt("idPessoa"),
                        res.getString("cpf"),
                        res.getString("nome"),
                        res.getString("email"),
                        res.getString("telefone"),
                        res.getDate("dataNascimento"),
                        res.getInt("cep"),
                        res.getString("estado"),
                        res.getString("cidade"),
                        res.getString("rua"),
                        res.getInt("numero"),
                        res.getInt("autorizacao"));
            }
            //Deu tudo certo retornando a costureira
            return costureira;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
