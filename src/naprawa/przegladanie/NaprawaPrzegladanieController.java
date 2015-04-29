/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.przegladanie;

import Model.TO_Defect;
import Model.TO_Termin;
import baks.adm.AbstractController;
import baks.adm.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.util.List;
import tabelDefects.TableDefectsModel;

/**
 *
 * @author Robert M
 */
public class NaprawaPrzegladanieController extends AbstractController {

    private TableDefectsModel modelTable;
    private NaprawaPrzegladaniePanel widok;
    private List<TO_Defect> lista;

    private TO_Defect wybranyDefect;

    private List<TO_Termin> listaDostTerminow;

    public static final int MODE_EDIT = 1;
    public static final int MODE_VIEW = 2;

    private static int MODE;

    public NaprawaPrzegladanieController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        setWidok(new NaprawaPrzegladaniePanel());
        wybranyDefect = new TO_Defect();
        setLista(getDaoFactory().getDaoDefect().getDefectListByUser(getConnection(), BaksSessionBean.getUser()));
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

        widok.getBtnUsun().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaUsun();
            }
        });

        widok.getDateChooser().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                zmianaDaty();
            }
        });

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                wybranyDefect = ((TableDefectsModel) widok.getTable().getModel()).getDefectAt(widok.getTable().getSelectedRow());
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

        modelTable = new TableDefectsModel(lista);
        fillTable();
        wypelnijCombo();

    }

    /**
     *
     */
    @Override
    public void akcjaRezygnuj() {
        if (MODE == MODE_EDIT) {
            setMODE(MODE_VIEW);
            widok.getBtnZapisz().setText("Edytuj");
            ustawEditablePol();
        } else if (MODE == MODE_VIEW) {
            super.akcjaRezygnuj();
        }
    }

    public void akcjaUsun() {
        czytajFormatke();
        getDaoFactory().getDaoDefect().deleteDefect(getConnection(), wybranyDefect);
        odswiezFormatke();
    }

    public void czytajFormatke() {
        getWybranyDefect().setMarka(getWidok().getMarka().getText());
        getWybranyDefect().setModel(getWidok().getModel().getText());
        getWybranyDefect().setOpis(getWidok().getOpis().getText());
        getWybranyDefect().setTermin((TO_Termin) getWidok().getWolnyComboBox().getSelectedItem());
        getWybranyDefect().setRokProd(getWidok().getRokProd().getText());
        getWybranyDefect().setData(getWidok().getDateChooser().getDate());
        getWidok().getPanelEdycji().revalidate();
    }

    public void wypelnijFormatke() {
        wypelnijCombo();
        getWidok().getMarka().setText(getWybranyDefect().getMarka());
        getWidok().getModel().setText(getWybranyDefect().getModel());
        getWidok().getOpis().setText(getWybranyDefect().getOpis());
        getWidok().getWolnyComboBox().setSelectedItem(getWybranyDefect().getTermin());
        getWidok().getRokProd().setText(getWybranyDefect().getRokProd());
        getWidok().getDateChooser().setDate(getWybranyDefect().getData());
    }

    public void akcjaZapisz() {
        if (MODE == MODE_VIEW) {
            getWidok().getBtnZapisz().setText("Zapisz");
            setMODE(MODE_EDIT);
            getListaDostepnychTerm();
            wypelnijCombo();
            ustawEditablePol();
            wypelnijFormatke();
        } else if (MODE == MODE_EDIT) {
            getWidok().getBtnZapisz().setText("Edytuj");
            setMODE(MODE_VIEW);
            ustawEditablePol();
            czytajFormatke();
            getDaoFactory().getDaoDefect().updateDefect(getConnection(), wybranyDefect);
            odswiezFormatke();
            BaksSessionBean.getInstance().fireMessage(widok, "Usterka", "Poprawnie zapisano");
        }
    }

    public void odswiezFormatke() {
        lista = getDaoFactory().getDaoDefect().getDefectListByUser(getConnection(), BaksSessionBean.getUser());
        modelTable = new TableDefectsModel(lista);
        fillTable();
        wypelnijCombo();
    }

    public void ustawEditablePol() {
        if (MODE == MODE_VIEW) {
            getWidok().getDateChooser().getSpinner().setEnabled(false);
            getWidok().getMarka().setEnabled(false);
            getWidok().getModel().setEnabled(false);
            getWidok().getWolnyComboBox().setEnabled(false);
            getWidok().getOpis().setEnabled(false);
            getWidok().getRokProd().setEnabled(false);
        } else if (MODE == MODE_EDIT) {
            getWidok().getDateChooser().getSpinner().setEnabled(true);
            getWidok().getMarka().setEnabled(true);
            getWidok().getModel().setEnabled(true);
            getWidok().getWolnyComboBox().setEnabled(true);
            getWidok().getOpis().setEnabled(true);
            getWidok().getRokProd().setEnabled(true);
        }
        getWidok().revalidate();
    }

    private void fillTable() {
        widok.getTable().setModel(modelTable);
        widok.getTable().revalidate();
    }

    public void wypelnijCombo() {
        widok.getWolnyComboBox().removeAllItems();
        for (TO_Termin t : getListaDostepnychTerm()) {
            widok.getWolnyComboBox().addItem(t);
        }
    }

    public void zmianaDaty() {
        wypelnijCombo();
    }

    public List<TO_Termin> getListaDostepnychTerm() {
        List<TO_Termin> listaTerm;
        listaTerm = getDaoFactory().getDaoDefect().getTerminListByDate(getConnection(), widok.getDateChooser().getDate());
        return listaDostTerminow = TO_Termin.getDostepneTerminyEdit(listaTerm, wybranyDefect.getTermin());
    }
//<editor-fold defaultstate="collapsed" desc="Akcesory">

    public NaprawaPrzegladaniePanel getWidok() {
        return widok;
    }

    public void setWidok(NaprawaPrzegladaniePanel widok) {
        this.widok = widok;
    }

    public TableDefectsModel getModelTable() {
        return modelTable;
    }

    public void setModelTable(TableDefectsModel modelTable) {
        this.modelTable = modelTable;
    }

    public TO_Defect getWybranyDefect() {
        return wybranyDefect;
    }

    public void setWybranyDefect(TO_Defect wybranyDefect) {
        this.wybranyDefect = wybranyDefect;
    }

    public static int getMODE() {
        return MODE;
    }

    public static void setMODE(int MODE) {
        NaprawaPrzegladanieController.MODE = MODE;
    }

    public List<TO_Defect> getLista() {
        return lista;
    }

    public void setLista(List<TO_Defect> lista) {
        this.lista = lista;
    }
//</editor-fold>

}
