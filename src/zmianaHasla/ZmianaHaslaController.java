/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zmianaHasla;

import Model.TO_User;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author Robert M
 */
public class ZmianaHaslaController extends AbstractController {

    private TO_User user;
    private ZmianaHaslaPanel widok;

    public ZmianaHaslaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new ZmianaHaslaPanel();

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
        if (!isRozneOdBazy() && isPoprawne()) {
            getDaoFactory().getDaoUser().updateHaslo(getConnection(), user);
            BaksSessionBean.getInstance().fireMessage(widok, "Zmiana hasła", "Poprawnie zmieniono hasło.");
            ustawEditablePol();
        } else {
            BaksSessionBean.getInstance().fireMessage(widok, "Zmiana hasła", "Błędne dane");
        }
    }

    public boolean isRozneOdBazy() {
        if (widok.getStare().getPassword().length == 0
                || widok.getNowePow().getPassword().length == 0
                || widok.getNowe().getPassword().length == 0) {
            return false;
        }
        return widok.getStare().getPassword().equals(getDaoFactory().getDaoUser().getPasswordByEmail(getConnection(), user.getMail()));
    }

    public boolean isPoprawne() {
        return widok.getNowe().getPassword().equals(widok.getNowePow().getPassword());
    }

    public void ustawEditablePol() {
        widok.getStare().setEnabled(false);
        widok.getNowe().setEnabled(false);
        widok.getNowePow().setEnabled(false);
        getWidok().revalidate();
    }

    @Override
    public void czytajFormatke() {
        user.setPassword(String.valueOf(widok.getStare().getPassword()));
        user.setPasswordReap(String.valueOf(widok.getNowe().getPassword()));
    }

    @Override
    public void wypelnijFormatke() {
    }

    public ZmianaHaslaPanel getWidok() {
        return widok;
    }

    public TO_User getUser() {
        return user;
    }

    public void setUser(TO_User user) {
        this.user = user;
    }

    public void setWidok(ZmianaHaslaPanel widok) {
        this.widok = widok;
    }

}
