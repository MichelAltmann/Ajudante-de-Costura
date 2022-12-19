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

/**
 *
 * @author aluno
 */
public class ClienteDao {

    private Connection con;

    public ClienteDao() {
        this.con = Conector.getConnection();
    }

    public int ClienteCadastrar(Cliente cliente) {
        PreparedStatement stmt = null;

        try {
            try {
                //Desliga o autocommit
                con.setAutoCommit(false);
                //SQL statement
                String sql = "insert into pessoa (cpf, "
                        + "nome, "
                        + "email, "
                        + "telefone, "
                        + "dataNascimento, "
                        + "cep, "
                        + "estado, "
                        + "cidade, "
                        + "rua, "
                        + "numero, "
                        + "tipo, "
                        + "idCostureira) "
                        + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
                //Prepara o Statement
                stmt = con.prepareStatement(sql);
                //Insere os dados no Statement
                stmt.setString(1, cliente.getCpf());
                stmt.setString(2, cliente.getNome());
                stmt.setString(3, cliente.getEmail());
                stmt.setString(4, cliente.getTelefone());
                stmt.setDate(5, (Date) cliente.getDataNascimento());
                stmt.setInt(6, cliente.getCep());
                stmt.setString(7, cliente.getEstado());
                stmt.setString(8, cliente.getCidade());
                stmt.setString(9, cliente.getRua());
                stmt.setInt(10, cliente.getNumero());
                stmt.setInt(11, 1);
                stmt.setInt(12, cliente.getCostureira().getIdPessoa());
                //Executa o Statement
                stmt.execute();
                //Realiza o commit
                con.commit();
                //Deu tudo certo retorna -1
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
                con.setAutoCommit(false);
                con.close();
            } catch (SQLException exc) {
                exc.printStackTrace();
                exc.getErrorCode();
            }
        }
    }

//     public int ClienteAlterar (Cliente cliente){;
//         
//     }
    public ArrayList<Cliente> ClienteCarregaLista() {
        PreparedStatement stmt = null;
        ArrayList<Cliente> listaClientes = new ArrayList<>();

        try {
            //Escrevendo o comando SQL
            String sql = "select * from pessoa join pedido on (pessoa.idPessoa = pedido.idPessoa)";
            //Preparando o Statement
            stmt = con.prepareStatement(sql);
            //Pegando o resultado
            ResultSet res = stmt.executeQuery();

            //Navegando nos resultados, criando  o objeto do Cliente e adicionando na lista
            while (res.next()) {
                Costureira costureira = new Costureira(res.getInt("idCostureira"));
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
                listaClientes.add(cliente);
            }
            //Deu tudo certo retornando a lista
            return listaClientes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
