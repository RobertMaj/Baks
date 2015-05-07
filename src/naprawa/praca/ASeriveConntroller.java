/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.praca.Usluga;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RobertM
 * @param <T>
 * @param <M>
 */
public abstract class ASeriveConntroller<T extends Usluga, M extends AbstractTableModel> extends AbstractController implements MouseListener {

    public M model;
    public PracaZaplataPanel widok;

    public List<ServiceListener> listeners = new ArrayList<>();

    public List<T> listaUslug;

    public T wybranaUsluga;

    public TO_Defect wybranyDefect;

    public ASeriveConntroller(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public ASeriveConntroller(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        this(connection, daoFactory);
        this.widok = widok;
        initConst();
    }

    public void initConst() {
        widok.getTable().addMouseListener(this);

        widok.getBtnDodaj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dodajUsluge();
            }
        });

        widok.getBtnUsun().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                usunUsluge();
            }
        });
    }

    public abstract void init(M model, List<T> listaU, TO_Defect defect);

    public abstract void dodajUsluge();

    @Override
    public void mouseClicked(MouseEvent e) {
        akcjaWybierz();
    }

    public abstract void akcjaWybierz();

    public abstract void usunUsluge();

    public void addFireServiceListener(ServiceListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void fireCzescChangeListener() {
        for (ServiceListener item : listeners) {
            item.fireCzescTableChanged();
        }
    }

    public void fireMaterialyChangeListener() {
        for (ServiceListener item : listeners) {
            item.fireMaterialTableChanged();
        }
    }

    public void fireNaprawaChangeListener() {
        for (ServiceListener item : listeners) {
            item.fireNaprawaTableChanged();
        }
    }

    public List<T> getListaUslug() {
        return listaUslug;
    }

    public void setListaUslug(List listaUslug) {
        this.listaUslug = listaUslug;
    }

    public abstract void fillTable();

    public M getModel() {
        return model;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public PracaZaplataPanel getWidok() {
        return widok;
    }

    public void setWidok(PracaZaplataPanel widok) {
        this.widok = widok;
    }

    public List<ServiceListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ServiceListener> listeners) {
        this.listeners = listeners;
    }

    public TO_Defect getWybranyDefect() {
        return wybranyDefect;
    }

    public void setWybranyDefect(TO_Defect wybranyDefect) {
        this.wybranyDefect = wybranyDefect;
    }

    public abstract T getWybranaUslugaN();

}
