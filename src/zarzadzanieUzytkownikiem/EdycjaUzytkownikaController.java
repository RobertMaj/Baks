/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanieUzytkownikiem;

import Model.TO_Permission;
import Model.TO_User;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Robert M
 */
public class EdycjaUzytkownikaController extends AbstractController {

    private TO_User user;
    private EdycjaUzytkownikaPanel widok;
    private List<TO_Permission> listaUpr;

    public EdycjaUzytkownikaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new EdycjaUzytkownikaPanel();
        getListaUpr();
        pobierzUser();

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

        wypelnijCombo();
        wypelnijFormatke();

    }

    public void ustawEditablePol() {
        widok.getMail().setEditable(false);
        widok.getImie().setEditable(false);
        widok.getNazwisko().setEditable(false);
        widok.getNrTel().setEditable(false);
        widok.getUprawnieniaComboBox().setEnabled(false);
        getWidok().revalidate();
    }

    public void akcjaZapisz() {
        czytajFormatke();
        ustawEditablePol();
        getDaoFactory().getDaoUser().updateUser(getConnection(), user);
        BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Poprawnie zapisano użytkownika");
    }

    @Override
    public void czytajFormatke() {
        user.setMail(widok.getMail().getText());
        user.setName(widok.getImie().getText());
        user.setSurname(widok.getNazwisko().getText());
        user.setPhone(widok.getNrTel().getText());
        user.setPermission(((TO_Permission) widok.getUprawnieniaComboBox().getSelectedItem()));
    }

    @Override
    public void wypelnijFormatke() {
        widok.getMail().setText(user.getMail());
        widok.getImie().setText(user.getName());
        widok.getNazwisko().setText(user.getSurname());
        widok.getNrTel().setText(user.getPhone());
        widok.getUprawnieniaComboBox().setSelectedItem(user.getPermission());
    }

    public void getListaUpr() {
        listaUpr = new ArrayList<>(Arrays.asList(TO_Permission.values()));
    }

    public void wypelnijCombo() {
        widok.getUprawnieniaComboBox().removeAllItems();
        for (TO_Permission t : listaUpr) {
            widok.getUprawnieniaComboBox().addItem(t);
        }
    }

    public EdycjaUzytkownikaPanel getWidok() {
        return widok;
    }

    public void pobierzUser() {
        user = getDaoFactory().getDaoUser().getUser(getConnection(), BaksSessionBean.getUser().getMail());
    }

    public TO_User getUser() {
        return user;
    }

    public void setUser(TO_User user) {
        this.user = user;
    }

    public void setWidok(EdycjaUzytkownikaPanel widok) {
        this.widok = widok;
    }

    public void setListaUpr(List<TO_Permission> listaUpr) {
        this.listaUpr = listaUpr;
    }

}
