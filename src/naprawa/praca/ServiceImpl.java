/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.TO_Invoice;
import Model.praca.Usluga;
import dao.DaoFactory;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.List;

public abstract class ServiceImpl<T extends Usluga> extends ASeriveConntroller<T, ServiceTableModel> {

    public ServiceImpl(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public ServiceImpl(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        super(widok, connection, daoFactory);
        initListeners();
    }

    @Override
    public void czytajFormatke() {
        wybranaUsluga.setIdDefect(getWybranyDefect().getId());
        wybranaUsluga.setKoszt(Double.parseDouble(widok.getKosztCenaPodst().getText()));
        wybranaUsluga.setOpis(widok.getOpis().getText());
    }

    @Override
    public void wypelnijFormatke() {
        if (wybranaUsluga != null) {
            widok.getKosztCenaPodst().setText(TO_Invoice.getWynikSumaKoszt(wybranaUsluga.getKoszt()));
            widok.getOpis().setText(wybranaUsluga.getOpis());
        }
    }

    //<editor-fold defaultstate="collapsed" desc="MouseListener">
//</editor-fold>
    @Override
    public void init(ServiceTableModel model, List listaU, TO_Defect defect) {
        setListaUslug(listaU);
        setWybranyDefect(defect);
        this.model = model;
        widok.ukryjMarza();
        fillTable();
        wypelnijFormatke();
    }

    @Override
    public void fillTable() {
        widok.getTable().setModel(model);
        widok.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMinWidth(30);

        widok.getTable().getColumnModel().getColumn(1).setPreferredWidth(450);
        widok.getTable().getColumnModel().getColumn(1).setWidth(450);
        widok.getTable().getColumnModel().getColumn(1).setMaxWidth(450);
        widok.getTable().getColumnModel().getColumn(1).setMinWidth(450);
        widok.getTable().getColumnModel().getColumn(1).setResizable(false);
        widok.getTable().revalidate();
    }

}
