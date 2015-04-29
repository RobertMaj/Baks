/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platnosci;

import Model.TO_Invoice;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RobertM
 */
public class TablePaymentsModel extends AbstractTableModel {

    private List<TO_Invoice> list;

    public TablePaymentsModel(List<TO_Invoice> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    public TO_Invoice getInvoiceAt(int rowIndex) {
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
                value = "Firma";
                break;
            case 2:
                value = "Nr faktury";
                break;
            case 3:
                value = "Kwota";
                break;
            case 4:
                value = "Termin płatności";
                break;
            case 5:
                value = "Termin opłacenia";
                break;
            case 6:
                value = "Status";
                break;
        }
        return value;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = "??";
        TO_Invoice defect = list.get(rowIndex);

        switch (columnIndex) {
            case 0:
                value = Integer.toString(rowIndex + 1) + ".";
                break;
            case 1:
                value = defect.getPaymentCompany().getName();
                break;
            case 2:
                value = defect.getIdentyfikator();
                break;
            case 3:
                value = TO_Invoice.getWynikSumaKoszt(defect.getKoszt());
                break;
            case 4:
                value = defect.getTerminPlatnosc();
                break;
            case 5:
                value = defect.getDataZaplacenia();
                break;
            case 6:
                value = defect.getStatus().getOpis();
                break;
        }
        return value;
    }

    public List<TO_Invoice> getList() {
        return list;
    }

    public void setList(List<TO_Invoice> list) {
        this.list = list;
    }
}
