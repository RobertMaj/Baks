/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.zapisanie;

import Model.TO_Defect;
import Model.TO_Termin;
import baks.adm.AbstractController;
import baks.adm.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Robert M
 */
public class NaprawaZapisController extends AbstractController {

    private NaprawaZapisPanel widok;
    private List<TO_Termin> listaDostTerminow;
    private TO_Defect defect;

    public NaprawaZapisController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new NaprawaZapisPanel();

        wypelnijCombo();

        widok.getDateChooser().setDate(new java.util.Date());

        widok.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                zmianaDaty();
            }
        });

        widok.getBtnZapisz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZapisz();
            }
        });

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaRezygnuj();
            }
        });

    }

    public void akcjaZapisz() {
        pobierzDaneFormatka();
        getDaoFactory().getDaoDefect().saveDefect(getConnection(), defect);
        wypelnijCombo();
        BaksSessionBean.getInstance().fireMessage(widok, "Naprawa", "Poprawnie zapisano na naprawę");
        akcjaRezygnuj();
    }

    public void pobierzDaneFormatka() {
        defect = new TO_Defect();
        defect.setData(widok.getDateChooser().getDate());
        defect.setMarka(widok.getMarka().getText());
        defect.setModel(widok.getModel().getText());
        defect.setRokProd(widok.getRokProd().getText());
        defect.setOpis(widok.getOpis().getText());
//        defect.setUser(BaksSessionBean.getInstance().getUser());
        defect.setTermin((TO_Termin) widok.getWolnyComboBox().getSelectedItem());
    }

    public NaprawaZapisPanel getWidok() {
        return widok;
    }

    public void setWidok(NaprawaZapisPanel widok) {
        this.widok = widok;
    }

    public void zmianaDaty() {
        wypelnijCombo();
    }

    public List<TO_Termin> getListaDostepnychTerm() {
        List<TO_Termin> lista = getDaoFactory().getDaoDefect().getTerminListByDate(getConnection(), widok.getDateChooser().getDate());
        return listaDostTerminow = TO_Termin.getDostepneTerminy(lista);
    }

    public List<TO_Termin> getListaDostTerminow() {
        return listaDostTerminow;
    }

    public void setListaDostTerminow(List<TO_Termin> listaDostTerminow) {
        this.listaDostTerminow = listaDostTerminow;
    }

    public void wypelnijCombo() {
        widok.getWolnyComboBox().removeAllItems();
        for (TO_Termin t : getListaDostepnychTerm()) {
            widok.getWolnyComboBox().addItem(t);
        }
    }

    @Override
    public void czytajFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
