/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabelDefects;

import Model.TO_Defect;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Robert M
 */
public class TableDefectsModel extends AbstractTableModel {

    private List<TO_Defect> list;

    public TableDefectsModel(List<TO_Defect> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    public TO_Defect getDefectAt(int rowIndex) {
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
                value = "Klient";
                break;
            case 2:
                value = "Model";
                break;
            case 3:
                value = "Data przyjęcia";
                break;
            case 4:
                value = "Do zapłaty";
                break;
            case 5:
                value = "Status";
                break;
        }
        return value;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        TO_Defect defect = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = Integer.toString(rowIndex + 1) + ".";
                break;
            case 1:
                value = defect.getCustomer().getName() + ' ' + defect.getCustomer().getSurname() + ' ' + defect.getCustomer().getPhone();
                break;
            case 2:
                value = defect.getModel();
                break;
            case 3:
                value = defect.getData();
                break;
            case 4:
                value = defect.getKoszt();
                break;
            case 5:
                value = defect.getStatus().getOpis();
                break;
        }
        return value;
    }

    public List<TO_Defect> getList() {
        return list;
    }

    public void setList(List<TO_Defect> list) {
        this.list = list;
    }

}
