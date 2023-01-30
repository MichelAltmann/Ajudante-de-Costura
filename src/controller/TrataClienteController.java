/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.AdministradorDao;
import model.ClienteDao;
import model.CostureiraDao;
import model.PedidoDao;
import modelDominio.Administrador;
import modelDominio.Costureira;

/**
 *
 * @author Pedro Müller
 */
public class TrataClienteController extends Thread {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket s;
    private int idUnico;

    public TrataClienteController(ObjectInputStream in, ObjectOutputStream out, Socket s, int idUnico) {
        this.in = in;
        this.out = out;
        this.s = s;
        this.idUnico = idUnico;
    }

    @Override
    public void run() {
        //Cliente se conectou
        String comando;
        System.out.println("Esperando comandos do Cliente: " + idUnico);
        try {
            //Lendo comandos do cliente
            comando = (String) in.readObject();
            while (!comando.equalsIgnoreCase("fim")) {
                //Tratando comandos do cliente
                System.out.println("Cliente: " + idUnico + " enviou o comando: " + comando);
                
                //Método que faz o login do Administrador
                if (comando.equalsIgnoreCase("AdministradorEfetuarLogin")){
                    AdministradorDao adminDao = new AdministradorDao();
                    out.writeObject("Ok");
                    Administrador adminLogado = (Administrador) in.readObject();
                    adminLogado = adminDao.administradotEfetuarLogin(adminLogado);
                    out.writeObject(adminLogado);
                }
                
                //Método que faz o login da Costureira
                else if (comando.equalsIgnoreCase("CostureiraEfetuarLogin")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    Costureira costureiraLogada = (Costureira) in.readObject();
                    costureiraLogada = costureiraDao.costureiraEfetuarLogin(costureiraLogada);
                    out.writeObject(costureiraLogada);
                }
                
                //Método que realiza o cadastro da Costureira
                else if (comando.equalsIgnoreCase("CostureiraCadastrar")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    Costureira costureiraCadastrar = (Costureira) in.readObject();

                    if (costureiraDao.costureiraCadastrar(costureiraCadastrar) == -1){
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }

                }
                
                //Método que altera a autorização da Costureira
                else if (comando.equalsIgnoreCase("CostureiraAlterarAutorizacao")){
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    Costureira costureiraAtivar = (Costureira) in.readObject();
                    if (costureiraDao.costureiraAlterarAutorizacao(costureiraAtivar) == -1){
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }
                }
                
                //Comando que retorna a lista de Costureiras
                else if (comando.equalsIgnoreCase("CostureiraCarregarLista")){
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject(costureiraDao.costureiraCarregaLista());
                }
                
                //Comando que retorna a lista de Clientes
                else if (comando.equalsIgnoreCase("ClienteCarregarLista")){
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject(clienteDao.clienteCarregaLista());
                }
                
                //Comando que retorna a lista de Pedidos
                else if (comando.equalsIgnoreCase("PedidoCarregarLista")){
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject(pedidoDao.pedidoCarregaLista());
                }
                
                comando = (String) in.readObject();
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }

        //veio fim
        try {
            System.out.println("Cliente: " + idUnico + " se desconectou");
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
