/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Czesc;
import Model.praca.Przelicznik;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RobertM
 */
public class CzescTableModel extends AbstractTableModel {

    private List<Czesc> list;

    public CzescTableModel(List<Czesc> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public Czesc getUslugaAt(int rowIndex) {
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
                value = "Cena";
                break;
            case 3:
                value = "Marża";
                break;
            case 4:
                value = "Razem";
                break;
        }
        return value;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        Czesc usluga = list.get(rowIndex);

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
            case 3:
                value = usluga.getPrzelicznik() + (usluga.getWybranyPrzelicznik().equals(Przelicznik.PROCENT) ? " %" : " zł");
                break;
            case 4:
                value = usluga.getCena();
                break;
        }
        return value;
    }

    public List<Czesc> getList() {
        return list;
    }

    public void setList(List<Czesc> list) {
        this.list = list;
    }
}
