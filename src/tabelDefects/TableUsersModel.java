/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelDefects;

import Model.TO_User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Robert M
 */
public class TableUsersModel extends AbstractTableModel {

    private List<TO_User> list;

    public TableUsersModel(List<TO_User> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public TO_User getUserAt(int rowIndex) {
        return list.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        String value = "";
        switch (column) {
            case 0:
                value = "E-Mail";
                break;
            case 1:
                value = "Imię";
                break;
            case 2:
                value = "Nazwisko";
                break;
            case 3:
                value = "Uprawnienie";
                break;
        }
        return value;
    }
    
    
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        TO_User user = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = user.getMail();
                break;
            case 1:
                value = user.getName();
                break;
            case 2:
                value = user.getSurname();
                break;
            case 3:
                value = user.getPermission().getOpis();
                break;
        }
        return value;
    }

    public List<TO_User> getList() {
        return list;
    }

    public void setList(List<TO_User> list) {
        this.list = list;
    }
}
