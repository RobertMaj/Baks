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
public class ZarzadzanieUzytkownikiemController extends AbstractController {

    private TO_User user;
    private ZarzadzanieUzytkownikiemPanel widok;
    private List<TO_Permission> listaUpr;
    private TableUsersModel table;
    private List<TO_User> listaUsers;

    public static final int MODE_VIEW = 1;
    public static final int MODE_EDIT = 2;
    public static final int MODE_SAVE = 3;

    private static int MODE = MODE_VIEW;

    public ZarzadzanieUzytkownikiemController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new ZarzadzanieUzytkownikiemPanel();
        getListaUpr();
        pobierzListeUsers();
        ustawEditablePol();
        
        System.out.println(widok.getTable().getColumnCount());

        widok.getBtnZapisz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZapisz();
            }
        });

        widok.getBtnUsun().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaDeleteUser();
            }
        });

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (MODE == MODE_EDIT) {
                    setMODE(MODE_VIEW);
                    widok.getBtnZapisz().setText("Edytuj");
                    ustawEditablePol();
                } else if (MODE == MODE_VIEW) {
                    akcjaRezygnuj();
                }
            }
        });

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                user = ((TableUsersModel) widok.getTable().getModel()).getUserAt(widok.getTable().getSelectedRow());
                wypelnijFormatke();
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

    public void ustawEditablePol() {
        if (MODE == MODE_VIEW) {
            widok.getMail().setEditable(false);
            widok.getImie().setEditable(false);
            widok.getNazwisko().setEditable(false);
            widok.getNrTel().setEditable(false);
            widok.getUprawnieniaComboBox().setEnabled(false);
        } else if (MODE == MODE_EDIT) {
            widok.getMail().setEditable(true);
            widok.getImie().setEditable(true);
            widok.getNazwisko().setEditable(true);
            widok.getNrTel().setEditable(true);
            widok.getUprawnieniaComboBox().setEnabled(true);
        }
        getWidok().revalidate();
    }

    public void akcjaZapisz() {
        if (MODE == MODE_VIEW) {
            setMODE(MODE_EDIT);
            widok.getBtnZapisz().setText("Zapisz");
            ustawEditablePol();
            updateTable();
        } else if (MODE == MODE_EDIT) {
            setMODE(MODE_VIEW);
            czytajFormatke();
            ustawEditablePol();
            widok.getBtnZapisz().setText("Edytuj");
            getDaoFactory().getDaoUser().updateUser(getConnection(), user);
            updateTable();
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Poprawnie zapisano użytkownika");
        }
    }

    public void akcjaDeleteUser() {
        czytajFormatke();
        getDaoFactory().getDaoDefect().deleteDefectsByMail(getConnection(), user.getMail());
        getDaoFactory().getDaoUser().deleteUser(getConnection(), user);
        updateTable();
    }

    public void updateTable() {
        getListaUsers();
        fillTable();
    }

    private void fillTable() {
        widok.getTable().setModel(table);
        widok.getTable().revalidate();
        widok.revalidate();
        widok.repaint();
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

    public void pobierzListeUsers() {
        listaUsers = getDaoFactory().getDaoUser().getUserList(getConnection());
    }

    public void wypelnijCombo() {
        widok.getUprawnieniaComboBox().removeAllItems();
        for (TO_Permission t : listaUpr) {
            widok.getUprawnieniaComboBox().addItem(t);
        }
    }

    public ZarzadzanieUzytkownikiemPanel getWidok() {
        return widok;
    }

    public TO_User getUser() {
        return user;
    }

    public void setUser(TO_User user) {
        this.user = user;
    }

    public void setWidok(ZarzadzanieUzytkownikiemPanel widok) {
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
        ZarzadzanieUzytkownikiemController.MODE = MODE;
    }
}
