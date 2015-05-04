/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.zapisanie;

import Model.TO_Customer;
import Model.TO_Defect;
import Model.TO_StatusDefects;
import Model.TO_Termin;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
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
    private TO_Defect defect;

    public NaprawaZapisController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new NaprawaZapisPanel();

        widok.getDateChooser().setDate(new java.util.Date());

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
        czytajFormatke();
        getDaoFactory().getDaoDefect().saveDefect(getConnection(), defect);
        BaksSessionBean.getInstance().fireMessage(widok, "Naprawa", "Poprawnie zapisano na naprawę");
        akcjaRezygnuj();
    }

    public NaprawaZapisPanel getWidok() {
        return widok;
    }

    public void setWidok(NaprawaZapisPanel widok) {
        this.widok = widok;
    }

    @Override
    public void czytajFormatke() {
        defect = new TO_Defect();
        defect.setData(widok.getDateChooser().getDate());
        defect.setMarka(widok.getMarka().getText());
        defect.setModel(widok.getModel().getText());
        defect.setRokProd(widok.getRokProd().getText());
        defect.setOpis(widok.getOpis().getText());
        defect.setStatus(TO_StatusDefects.ZAPLANOWANY);
        TO_Customer customer = new TO_Customer();
        customer.setName(widok.getImie().getText());
        customer.setSurname(widok.getNazwisko().getText());
        customer.setPhone(widok.getTel().getText());
        defect.setCustomer(customer);
    }

    @Override
    public void wypelnijFormatke() {
    }

}
