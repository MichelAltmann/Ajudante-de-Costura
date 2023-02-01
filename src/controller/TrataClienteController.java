/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import model.AdministradorDao;
import model.ClienteDao;
import model.CostureiraDao;
import model.MaterialDao;
import model.PedidoDao;
import modelDominio.Administrador;
import modelDominio.Cliente;
import modelDominio.Costureira;
import modelDominio.Material;
import modelDominio.Pedido;

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

//----------------------------------------------ADMINISTRADOR---------------------------------                
//Método que faz o login do Administrador - ADMINISTRADOR
                if (comando.equalsIgnoreCase("AdministradorEfetuarLogin")) {
                    AdministradorDao adminDao = new AdministradorDao();
                    out.writeObject("Ok");
                    Administrador adminLogado = (Administrador) in.readObject();
                    adminLogado = adminDao.administradotEfetuarLogin(adminLogado);
                    out.writeObject(adminLogado);
                } 
//----------------------------------------------CLIENTE---------------------------------
//Comando que retorna a lista de Clientes - ADMINISTRADOR
                else if (comando.equalsIgnoreCase("ClienteCarregarLista")) {
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject(clienteDao.clienteCarregaListaAdministrador());
                }
//Comando que retorna a lista de Clientes - COSTUREIRA/ADMINISTRADOR
                else if (comando.equalsIgnoreCase("ClienteCarregarListaCostureira")) {
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject("Ok");
                    Costureira costureira = (Costureira) in.readObject();
                    out.writeObject(clienteDao.clienteCarregaListaCostureira(costureira));
                } 

//Método que realiza o cadastro de Cliente - COSTUREIRA
                else if (comando.equalsIgnoreCase("ClienteCadastrar")) {
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject("Ok");
                    Cliente clienteCadastrar = (Cliente) in.readObject();

                    if (clienteDao.clienteCadastrar(clienteCadastrar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }

                }
                
//Método que altera um Cliente - COSTUREIRA
                else if (comando.equalsIgnoreCase("ClienteAlterar")) {
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject("Ok");
                    Cliente clienteAlterar = (Cliente) in.readObject();

                    if (clienteDao.clienteAlterar(clienteAlterar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }

                }
                
//Método que deleta um Cliente - COSTUREIRA
                else if (comando.equalsIgnoreCase("ClienteDeletar")) {
                    ClienteDao clienteDao = new ClienteDao();
                    out.writeObject("Ok");
                    ArrayList<Cliente> listaClientesDeletar = (ArrayList<Cliente>) in.readObject();

                    if (clienteDao.clienteDeletar(listaClientesDeletar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }

                }
//----------------------------------------------COSTUREIRA---------------------------------
//Comando que retorna a lista de Costureiras - ADMINISTRADOR
                else if (comando.equalsIgnoreCase("CostureiraCarregarLista")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject(costureiraDao.costureiraCarregaLista());
                } 
//Método que realiza a alteração de costureiras - COSTUREIRA/ADMINISTRADOR
                else if (comando.equalsIgnoreCase("CostureiraAlterar")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    ArrayList<Costureira> listaCostureirasAlterar = (ArrayList<Costureira>) in.readObject();
                    out.writeObject(costureiraDao.costureiraAlterar(listaCostureirasAlterar));
                } 
//Método que faz o login da Costureira - COSTUREIRA
                else if (comando.equalsIgnoreCase("CostureiraEfetuarLogin")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    Costureira costureiraLogada = (Costureira) in.readObject();
                    costureiraLogada = costureiraDao.costureiraEfetuarLogin(costureiraLogada);
                    out.writeObject(costureiraLogada);
                } 
//Método que realiza o cadastro da Costureira - COSTUREIRA
                else if (comando.equalsIgnoreCase("CostureiraCadastrar")) {
                    CostureiraDao costureiraDao = new CostureiraDao();
                    out.writeObject("Ok");
                    Costureira costureiraCadastrar = (Costureira) in.readObject();

                    if (costureiraDao.costureiraCadastrar(costureiraCadastrar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }
                }                 
//----------------------------------------------MATERIAL---------------------------------
//Método que cadastra um material = Costureira
                else if (comando.equalsIgnoreCase("MaterialCadastrar")) {
                    MaterialDao materialDao = new MaterialDao();
                    out.writeObject("Ok");
                    Material materialCadastrar = (Material) in.readObject();
                    out.writeObject(materialDao.materialCadastrar(materialCadastrar));
                }
                
//Método que carrega a lista de materiais - COSTUREIRA
                else if (comando.equalsIgnoreCase("MaterialCarregarLista")) {
                    MaterialDao materialDao = new MaterialDao();
                    out.writeObject(materialDao.materialCarregaLista());
                }
                
//Método que deleta uma lista de materiais - COSTUREIRA
                else if (comando.equalsIgnoreCase("MaterialDeletar")) {
                    MaterialDao materialDao = new MaterialDao();
                    out.writeObject("Ok");
                    ArrayList<Material> listaMateriaisDeletar = (ArrayList<Material>) in.readObject();
                    out.writeObject(materialDao.materialDeletar(listaMateriaisDeletar));
                }
                
//Método que altera um material - COSTUREIRA
                else if (comando.equalsIgnoreCase("MaterialAlterar")) {
                    MaterialDao materialDao = new MaterialDao();
                    out.writeObject("Ok");
                    Material materialAlterar = (Material) in.readObject();
                    out.writeObject(materialDao.materialAlterar(materialAlterar));
                }                
//----------------------------------------------PEDIDO---------------------------------                
//Comando que retorna a lista de Pedidos - COSTUREIRA/ADMINISTRADOR
                else if (comando.equalsIgnoreCase("PedidoCarregarListaCostureira")) {
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject("Ok");
                    Costureira costureira = (Costureira) in.readObject();
                    out.writeObject(pedidoDao.pedidoCarregaListaCostureira(costureira));
                } 
                
//Método que altera um Pedido - COSTUREIRA
                else if (comando.equalsIgnoreCase("PedidoAlterar")) {
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject("Ok");
                    Pedido pedidoAlterar = (Pedido) in.readObject();

                    if (pedidoDao.pedidoAlterar(pedidoAlterar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }
                } 
//Método que deleta um Pedido - COSTUREIRA
                else if (comando.equalsIgnoreCase("PedidoDeletar")) {
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject("Ok");
                    ArrayList<Pedido> listaPedidosDeletar = (ArrayList<Pedido>) in.readObject();

                    if (pedidoDao.pedidoDeletar(listaPedidosDeletar) == -1) {
                        out.writeObject("Ok");
                    } else {
                        out.writeObject("Nok");
                    }
                }
//Método que cadastra um Pedido - COSTUREIRA
                else if (comando.equalsIgnoreCase("PedidoCadastrar")){
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject("Ok");
                    Pedido pedido = (Pedido) in.readObject();
                    out.writeObject(pedidoDao.pedidoCadastrar(pedido));
                }
//Método que carrega a lista de materiais de um pedido - COSTUREIRA
                else if (comando.equalsIgnoreCase("MaterialCarregarListaDePedido")) {
                    MaterialDao materialDao = new MaterialDao();
                    out.writeObject("Ok");
                    Pedido pedido = (Pedido) in.readObject();
                    out.writeObject(materialDao.materialCarregaListaDePedido(pedido.getIdPedido()));
                }
//Comando que retorna a lista de Pedidos - ADMINISTRADOR
                else if (comando.equalsIgnoreCase("PedidoCarregarLista")) {
                    PedidoDao pedidoDao = new PedidoDao();
                    out.writeObject(pedidoDao.pedidoCarregarListaAdministrador());
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
