/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logowanie;

import aplikacja.AplikacjaController;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import rejestracja.RejestracjaController;

/**
 *
 * @author Robert M
 */
public class LogowanieController extends AbstractController {

    private Logowanie widok;

    private String mail;
    private String password;

    public LogowanieController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new Logowanie();
        widok.setLocationRelativeTo(null);
        widok.setVisible(true);

        widok.getRejestracjaButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RejestracjaController rejestracjaController = new RejestracjaController(getConnection(), getDaoFactory());
            }
        });

        widok.getBtnWyjdz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(LogowanieController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        });

        widok.getBtnZaloguj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZaloguj();
            }
        });

    }

    private void akcjaZaloguj() {

        czytajFormatke();
        if (getConnection() == null) {
            BaksSessionBean.getInstance().fireMessage(null, "Aplikacja", "Brak połączenia z internetem");
            return;
        }

        String passDb = getDaoFactory().getDaoUser().getPasswordByEmail(getConnection(), mail);

        if (passDb == null) {
            JOptionPane.showMessageDialog(widok, "Niepoprawne dane", "Logowanie", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            if (password.equals(passDb)) {
                poprawneLogowanie();
            } else {
                JOptionPane.showMessageDialog(widok, "Niepoprawne dane", "Logowanie", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }

    private void poprawneLogowanie() {
        widok.setVisible(false);
        BaksSessionBean.setUser(getDaoFactory().getDaoUser().getUser(getConnection(), widok.getEmailText().getText()));
        AplikacjaController controller = new AplikacjaController(getConnection(), getDaoFactory());
    }

    @Override
    public void czytajFormatke() {
        mail = widok.getEmailText().getText();
        password = String.valueOf(widok.getPasswordText().getPassword());
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
