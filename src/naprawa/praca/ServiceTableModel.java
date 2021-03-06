/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Usluga;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RobertM
 * @param <T>
 */
public class ServiceTableModel<T extends Usluga> extends AbstractTableModel {

    private List<T> list;

    public ServiceTableModel(List<T> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    public T getUslugaAt(int rowIndex) {
        return list.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        String value = "";
        switch (column) {
            case 0:
                value = "Lp.";
                break;
            case 1:
                value = "Opis";
                break;
            case 2:
                value = "Koszt";
                break;
        }
        return value;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        T usluga = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = Integer.toString(rowIndex + 1) + ".";
                break;
            case 1:
                value = usluga.getOpis();
                break;
            case 2:
                value = usluga.getKoszt();
                break;
        }
        return value;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
