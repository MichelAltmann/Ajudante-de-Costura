/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Pedido;

/**
 *
 * @author Pedro Müller
 */
public class PedidoTableModel extends AbstractTableModel {

    private ArrayList<Pedido> listaPedidos;

    public PedidoTableModel(ArrayList<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @Override
    public int getRowCount() {
        return listaPedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
        //Depois adicionar mais uma coluna para a foto da costureira
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = listaPedidos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return pedido.getCliente().getCostureira().getNome();
            case 1:
                return pedido.getPrioridade();
            case 2:
                return pedido.getTitulo();
            case 3:
                return pedido.getDataCriacao();
            case 4:
                return pedido.getCliente().getNome();
            case 5:
                return "1000";
            default:
                return "";
        }
        //Depois adicionar mais um case para a foto da costureira
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Costureira";
            case 1:
                return "Prioridade";
            case 2:
                return "Titulo";
            case 3:
                return "Data de Criação";
            case 4:
                return "Cliente";
            case 5:
                return "Valor de Venda";
            default:
                return "";
        }
        //Depois adicionar mais um case para a foto da costureira
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

}
