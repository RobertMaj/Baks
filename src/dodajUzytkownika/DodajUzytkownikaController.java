/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dodajUzytkownika;

import Model.TO_Permission;
import Model.TO_User;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import tabelDefects.TableUsersModel;

/**
 *
 * @author Robert M
 */
public class DodajUzytkownikaController extends AbstractController {

    private TO_User user;
    private DodajUzytkownikaPanel widok;
    private List<TO_Permission> listaUpr;
    private TableUsersModel table;
    private List<TO_User> listaUsers;

    public static final int MODE_VIEW = 1;
    public static final int MODE_EDIT = 2;
    public static final int MODE_SAVE = 3;

    private static int MODE = MODE_VIEW;

    public DodajUzytkownikaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new DodajUzytkownikaPanel();
        getListaUpr();
        pobierzListeUsers();

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

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                user = ((TableUsersModel) widok.getTable().getModel()).getUserAt(widok.getTable().getSelectedRow());
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
        });

        table = new TableUsersModel(listaUsers);
        fillTable();
        wypelnijCombo();

    }

    public void akcjaZapisz() {
        czytajFormatke();

        if (sprawdzPoprawnoscHasla()) {
            getDaoFactory().getDaoUser().saveUser(getConnection(), user);
            updateTable();
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Poprawnie zapisano użytkownika");
            czyscFormatke();
        } else {
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Błędne dane");
        }
    }

    public void updateTable() {
        pobierzListeUsers();
        fillTable();
    }

    private void fillTable() {
        table = new TableUsersModel(listaUsers);
        widok.getTable().setModel(table);
        widok.getTable().revalidate();
        widok.revalidate();
        widok.repaint();
    }

    @Override
    public void czytajFormatke() {
        user = new TO_User();
        user.setMail(widok.getMail().getText());
        user.setName(widok.getImie().getText());
        user.setSurname(widok.getNazwisko().getText());
        user.setPhone(widok.getNrTel().getText());
        user.setPermission(((TO_Permission) widok.getUprawnieniaComboBox().getSelectedItem()));
        user.setPassword(String.valueOf(widok.getHaslo().getPassword()));
        user.setPasswordReap(String.valueOf(widok.getHasloPowt().getPassword()));
    }

    private boolean sprawdzPoprawnoscHasla() {
        if (user != null && user.getPassword() != null) {
            return user.getPassword().equals(user.getPasswordReap());
        }
        return false;
    }

    public void czyscFormatke() {
        widok.getMail().setText("");
        widok.getImie().setText("");
        widok.getNazwisko().setText("");
        widok.getNrTel().setText("");
        widok.getHaslo().setText("");
        widok.getHasloPowt().setText("");
    }

    public void getListaUpr() {
        listaUpr = new ArrayList<>(Arrays.asList(TO_Permission.values()));
    }

    public void pobierzListeUsers() {
        listaUsers = getDaoFactory().getDaoUser().getUserList(getConnection());
    }

    public void wypelnijCombo() {
        widok.getUprawnieniaComboBox().removeAllItems();
        for (TO_Permission t : listaUpr) {
            widok.getUprawnieniaComboBox().addItem(t);
        }
    }

    public DodajUzytkownikaPanel getWidok() {
        return widok;
    }

    public TO_User getUser() {
        return user;
    }

    public void setUser(TO_User user) {
        this.user = user;
    }

    public void setWidok(DodajUzytkownikaPanel widok) {
        this.widok = widok;
    }

    public void setListaUpr(List<TO_Permission> listaUpr) {
        this.listaUpr = listaUpr;
    }

    public TableUsersModel getTable() {
        return table;
    }

    public void setTable(TableUsersModel table) {
        this.table = table;
    }

    public List<TO_User> getListaUsers() {
        return listaUsers;
    }

    public void setListaUsers(List<TO_User> listaUsers) {
        this.listaUsers = listaUsers;
    }

    public static int getMODE() {
        return MODE;
    }

    public static void setMODE(int MODE) {
        DodajUzytkownikaController.MODE = MODE;
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
