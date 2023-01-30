/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Cliente;

/**
 *
 * @author Pedro Müller
 */
public class ClienteTableModel extends AbstractTableModel {

    private ArrayList<Cliente> listaClientes;

    public ClienteTableModel(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
        //Colocar mais um depois para a foto da costureira
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = listaClientes.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cliente.getNome();
            case 1:
                return cliente.getCostureira().getNome();
            case 2:
                return "10";
            case 3:
                return "1000";
            default:
                return "";
        }
        //Colocar mais um depois para a foto da costureira
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "Cliente";
            case 1:
                return "Costureira";
            case 2:
                return "Número de Pedidos";
            case 3:
                return "Valor dos pedidos";
            default:
                return "";
        }
        //Colocar mais um depois para a foto da costureira
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

}
