/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import modelDominio.Costureira;

/**
 *
 * @author Pedro Müller
 */
public class CostureiraTableModel extends AbstractTableModel {

    private ArrayList<Costureira> listaCostureiras;

    public CostureiraTableModel(ArrayList<Costureira> listaCostureiras) {
        this.listaCostureiras = listaCostureiras;
    }

    @Override
    public int getRowCount() {
        return listaCostureiras.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
        
//        Depois adicionar +4 clunas para ;
//        imagem, 
//        checkbox, e 
//        botões para acessar pedidos e clientes só dessa costureira
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Costureira costureira = listaCostureiras.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return costureira.getNome();
            case 1:
                return costureira.getAutorizacao();
            case 2:
                return "sete";
            case 3:
                return costureira.getEmail();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Status";
            case 2:
                return "Número de pedidos";
            case 3:
                return "Email";
            default:
                return "";
        }
//        Depois adicionar +4 cases para ;
//        imagem, 
//        checkbox, e 
//        botões para acessar pedidos e clientes só dessa costureira
    }

    public ArrayList<Costureira> getListaCostureiras() {
        return listaCostureiras;
    }

    public void setListaCostureiras(ArrayList<Costureira> listaCostureiras) {
        this.listaCostureiras = listaCostureiras;
    }
}
