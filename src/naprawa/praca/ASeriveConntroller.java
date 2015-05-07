/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Usluga;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RobertM
 */
public abstract class ASeriveConntroller extends AbstractController {

    public AbstractTableModel model;
    public PracaZaplataPanel widok;

    public List<ServiceListener> listeners = new ArrayList<>();

    public List<Usluga> listaUslug;

    public ASeriveConntroller(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public ASeriveConntroller(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        this(connection, daoFactory);
        this.widok = widok;
        wypelnijFormatke();
    }

    public void dodajUsluge() {
        fireServiceChangeListener();
        model.fireTableDataChanged();
    }

    public void usunUsluge() {
        fireServiceChangeListener();
        model.fireTableDataChanged();
    }

    public void addFireServiceListener(ServiceListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void fireServiceChangeListener() {
        for (ServiceListener item : listeners) {
            item.fireChangeSerive();
        }
    }

    public List<Usluga> getListaUslug() {
        return listaUslug;
    }

    public void setListaUslug(List<Usluga> listaUslug) {
        this.listaUslug = listaUslug;
    }
}
