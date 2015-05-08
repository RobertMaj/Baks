/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_StatusDefects;
import Model.praca.Czesc;
import Model.praca.Material;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;

/**
 *
 * @author jmaj
 */
public class MaterialServiceController extends ServiceImpl<Material> implements MouseListener {

    public MaterialServiceController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public void dodajUsluge() {
        getWybranaUslugaN();
        czytajFormatke();
        model.getList().add(wybranaUsluga);
        model.fireTableDataChanged();
        fireMaterialyChangeListener(model.getList());
        zapiszUslugeDb(wybranaUsluga);
        if (!wybranyDefect.getStatus().equals(TO_StatusDefects.W_TRAKCIE)) {
            wybranyDefect.setStatus(TO_StatusDefects.W_TRAKCIE);
            fireAktualizujDefect(wybranyDefect);
        }
        wybranaUsluga = new Material();
        wypelnijFormatke();
    }

    @Override
    public void akcjaWybierz() {
        wybranaUsluga = (Material) ((ServiceTableModel) widok.getTable().getModel()).getUslugaAt(widok.getTable().getSelectedRow());
        wypelnijFormatke();
    }

    @Override
    public void usunUsluge() {
        getWybranaUslugaN();
        model.getList().remove(wybranaUsluga);
        model.fireTableDataChanged();
        fireMaterialyChangeListener(model.getList());
        usunUslugeDB(wybranaUsluga);
        wybranaUsluga = new Material();
        wypelnijFormatke();
    }

    @Override
    public Material getWybranaUslugaN() {
        if (wybranaUsluga == null) {
            return wybranaUsluga = new Material();
        }
        return wybranaUsluga;
    }

    @Override
    public void initListeners() {
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

        widok.getTable().addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        akcjaWybierz();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
