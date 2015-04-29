/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rejestracja;

import Model.TO_Permission;
import Model.TO_User;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Robert M
 */
public class RejestracjaController extends AbstractController {

    private TO_User user;

    private Rejestracja widok;

    public RejestracjaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new Rejestracja();
        widok.setLocationRelativeTo(null);
        widok.setAlwaysOnTop(true);
        widok.setVisible(true);

        widok.getZarejestrujBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZapisz();
            }
        });

        widok.getWyjdzBtn().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                widok.dispose();
            }
        });
    }

    private void akcjaZapisz() {
        user = new TO_User();
        czytajFormatke();
        wypelnijFormatke();
        if (!sprawdzPoprawnoscHasla()) {
            JOptionPane.showMessageDialog(widok, "Niezgodne hasła", "Rejestracja", JOptionPane.ERROR_MESSAGE);
            return;
        }
        getDaoFactory().getDaoUser().saveUser(getConnection(), user);
        JOptionPane.showMessageDialog(widok, "Poprawnie zapisano użytkownika. Możesz się zalogować.", "Rejestracja", JOptionPane.INFORMATION_MESSAGE);
        widok.dispose();
    }

    @Override
    public void czytajFormatke() {
        user.setMail(widok.getMail().getText());
        user.setName(widok.getImie().getText());
        user.setSurname(widok.getNazwisko().getText());
        user.setPhone(widok.getNrTel().getText());
        user.setPermission(TO_Permission.UZYTKOWNIK);
        user.setPassword(String.valueOf(widok.getHaslo().getPassword()));
        user.setPasswordReap(String.valueOf(widok.getHasloPowt().getPassword()));
    }

    private boolean sprawdzPoprawnoscHasla() {
        if (user != null && user.getPassword() != null) {
            return user.getPassword().equals(user.getPasswordReap());
        }
        return false;
    }

    public TO_User getUser() {
        return user;
    }

    public void setUser(TO_User user) {
        this.user = user;
    }

    public Rejestracja getWidok() {
        return widok;
    }

    public void setWidok(Rejestracja widok) {
        this.widok = widok;
    }

    @Override
    public void wypelnijFormatke() {
        System.out.println("");
    }
}
