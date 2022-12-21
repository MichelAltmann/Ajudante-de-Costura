/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import factory.Conector;
import java.sql.*;
import modelDominio.Costureira;

/**
 *
 * @author aluno
 */
public class CostureiraDao {

    private Connection con;

    public CostureiraDao() {
        this.con = Conector.getConnection();
    }

    public int costureiraCadastrar(Costureira costureira) {
        PreparedStatement stmt = null;
        try {
            try {
                //Desligando o autocommit
                con.setAutoCommit(false);
                String sql = "insert into pessoa (cpf, nome, email, telefone, dataNascimento, cep, estado, cidade, rua, numero, tipo, senha, imagem) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, costureira.getCpf());
                stmt.setString(2, costureira.getNome());
                stmt.setString(3, costureira.getEmail());
                stmt.setString(4, costureira.getTelefone());
                stmt.setDate(5, (Date) costureira.getDataNascimento());
                stmt.setInt(6, costureira.getCep());
                stmt.setString(7, costureira.getEstado());
                stmt.setString(8, costureira.getCidade());
                stmt.setString(9, costureira.getRua());
                stmt.setInt(10, costureira.getNumero());
                stmt.setInt(11, 0);
                stmt.setString(12, costureira.getSenha());
                stmt.setBytes(13, costureira.getImagem());
                //Executar o statemente
                stmt.execute();
                //Executar o commit
                con.commit();
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

    public Costureira costureiraEfetuarLogin(Costureira costureira) {
        PreparedStatement stmt = null;
        Costureira costureiraLogada = null;

        try {
            String sql = "select * from costureira"
                    + " where (email = ? or cpf = ?) and senha = ?";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, costureira.getEmail());
            stmt.setString(2, costureira.getCpf());
            stmt.setString(3, costureira.getSenha());

            ResultSet res = stmt.executeQuery();

            //Se tem resultado
            if (res.next()) {
                costureiraLogada = new Costureira(res.getString("senha"),
                        res.getBytes("imagem"),
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

    public int costureiraAlterar(Costureira costureira) {
        PreparedStatement stmt = null;

        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                String sql = "update pessoa set cpf = ?, nome = ?, email = ?, telefone = ?, dataNascimento = ?, cep = ?, estado = ?, cidade = ?, rua = ?, numero = ?, senha = ?, imagem = ? where idPessoa = ? ";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, costureira.getCpf());
                stmt.setString(2, costureira.getNome());
                stmt.setString(3, costureira.getEmail());
                stmt.setString(4, costureira.getTelefone());
                stmt.setDate(5, (Date) costureira.getDataNascimento());
                stmt.setInt(6, costureira.getCep());
                stmt.setString(7, costureira.getEstado());
                stmt.setString(8, costureira.getCidade());
                stmt.setString(9, costureira.getRua());
                stmt.setInt(10, costureira.getNumero());
                stmt.setString(11, costureira.getSenha());
                stmt.setBytes(12, costureira.getImagem());
                stmt.setInt(13, costureira.getIdPessoa());
                //Executar o comando SQL
                stmt.execute();
                //Efetivar o commit
                con.commit();

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

    public int costureiraAlterarAutorizacao(Costureira costureira) {
        PreparedStatement stmt = null;
        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                String sql = "update pessoa set autorizacao = ? where idPessoa = ?";
                //Prepara o Statement
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, costureira.getAutorizacao());
                stmt.setInt(2, costureira.getIdPessoa());

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

}
