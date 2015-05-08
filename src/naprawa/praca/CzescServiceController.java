/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.TO_Invoice;
import Model.TO_StatusDefects;
import Model.praca.Czesc;
import Model.praca.Przelicznik;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author jmaj
 */
public class CzescServiceController extends ASeriveConntroller<Czesc, CzescTableModel> implements MouseListener {

    private ActionListener listener;

    public CzescServiceController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public void initListeners() {
        listener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                wybranaUsluga.setWybranyPrzelicznik(widok.getWybranyRadio());
                czytajFormatke();
                wypelnijFormatke();
            }
        };

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

        widok.getRadioKwota().addActionListener(listener);
        widok.getRadioProcent().addActionListener(listener);

        widok.getKosztCenaPodst().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                czytajFormatke();
                wypelnijFormatke();
            }
        });

        widok.getDodajDoCeny().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                czytajFormatke();
                wypelnijFormatke();
            }
        });
    }

    @Override
    public void czytajFormatke() {
        getWybranaUslugaN();
        wybranaUsluga.setIdDefect(getWybranyDefect().getId());
        wybranaUsluga.setKoszt(Double.parseDouble(widok.getKosztCenaPodst().getText()));
        wybranaUsluga.setPrzelicznik(widok.getDodajDoCeny().getText());
        wybranaUsluga.setOpis(widok.getOpis().getText());
        wybranaUsluga.setWybranyPrzelicznik(widok.getWybranyRadio());
        wybranaUsluga.setCena(Double.parseDouble("0"));
    }

    @Override
    public void wypelnijFormatke() {
        widok.getKosztCenaPodst().setText(TO_Invoice.getWynikSumaKoszt(wybranaUsluga.getKoszt()));
        widok.getDodajDoCeny().setText(wybranaUsluga.getPrzelicznik());
        if (wybranaUsluga.getWybranyPrzelicznik().equals(Przelicznik.PROCENT)) {
            widok.getLabelProcentZl().setText("%");
        } else if (wybranaUsluga.getWybranyPrzelicznik().equals(Przelicznik.WARTOSC)) {
            widok.getLabelProcentZl().setText("zł");
        }
        widok.getSuma().setText(TO_Invoice.getWynikSumaKoszt(wybranaUsluga.getCena()));
        widok.getLabelCenaKoszt().setText("Cena");
        widok.setSelectedRadio(wybranaUsluga.getWybranyPrzelicznik());
        widok.getOpis().setText(wybranaUsluga.getOpis());
    }

    //<editor-fold defaultstate="collapsed" desc="MouseListener">
//</editor-fold>
    @Override
    public void init(CzescTableModel model, List<Czesc> listaU, TO_Defect defect) {
        setListaUslug(listaU);
        this.model = model;
        setWybranyDefect(defect);
        widok.setSelectedRadio(Przelicznik.PROCENT);
        fillTable();
    }

    @Override
    public void akcjaWybierz() {
        wybranaUsluga = ((CzescTableModel) widok.getTable().getModel()).getUslugaAt(widok.getTable().getSelectedRow());
        wypelnijFormatke();
    }

    @Override
    public void fillTable() {
        widok.getTable().setModel(model);
        widok.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMinWidth(30);

        widok.getTable().getColumnModel().getColumn(1).setPreferredWidth(350);
        widok.getTable().getColumnModel().getColumn(1).setWidth(350);
        widok.getTable().getColumnModel().getColumn(1).setMaxWidth(350);
        widok.getTable().getColumnModel().getColumn(1).setMinWidth(350);
        widok.getTable().getColumnModel().getColumn(1).setResizable(false);
        widok.getTable().revalidate();
    }

    @Override
    public void dodajUsluge() {
        Przelicznik przelicznikOst;
        czytajFormatke();
        wypelnijFormatke();
        model.getList().add(wybranaUsluga);
        zapiszUslugeDb(wybranaUsluga);
        model.fireTableDataChanged();
        fireCzescChangeListener(model.getList());
        if (!wybranyDefect.getStatus().equals(TO_StatusDefects.W_TRAKCIE)) {
            wybranyDefect.setStatus(TO_StatusDefects.W_TRAKCIE);
            fireAktualizujDefect(wybranyDefect);
        }
        przelicznikOst = wybranaUsluga.getWybranyPrzelicznik();
        wybranaUsluga = new Czesc();
        wybranaUsluga.setWybranyPrzelicznik(przelicznikOst);
        wypelnijFormatke();
    }

    @Override
    public void usunUsluge() {
        getWybranaUslugaN();
        model.getList().remove(wybranaUsluga);
        model.fireTableDataChanged();
        fireCzescChangeListener(model.getList());
        usunUslugeDB(wybranaUsluga);
        wybranaUsluga = new Czesc();
        wypelnijFormatke();
    }

    @Override
    public Czesc getWybranaUslugaN() {
        if (wybranaUsluga == null) {
            return wybranaUsluga = new Czesc();
        }
        return wybranaUsluga;
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
