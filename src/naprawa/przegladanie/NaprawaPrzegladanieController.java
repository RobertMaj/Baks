/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.przegladanie;

import Model.TO_Defect;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.List;
import naprawa.praca.PracaController;
import naprawa.praca.PracaPanel;
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

    public static final int ZAKL_1 = 0;
    public static final int ZAKL_2 = 1;

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
        setLista(getDaoFactory().getDaoDefect().getDefectList(getConnection()));

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

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    akcjaWybierz();
                }
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

        widok.getBtnWybierz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaWybierz();
            }
        });

        modelTable = new TableDefectsModel(lista);
        fillTable();
    }

    public void przejdzDoZakladki(int indx) {
        widok.getTabbedPane().setSelectedIndex(indx);
    }

    public void akcjaWybierz() {
        wybranyDefect = ((TableDefectsModel) widok.getTable().getModel()).getDefectAt(widok.getTable().getSelectedRow());
        wypelnijFormatke();
        PracaController pracaController = new PracaController(getConnection(), getDaoFactory());
        pracaController.setWidok(getPracaPanel());
        pracaController.akcjaOtworzPrace();
        przejdzDoZakladki(ZAKL_2);
    }

    /**
     *
     */
    @Override
    public void akcjaRezygnuj() {
        super.akcjaRezygnuj();
    }

    public void akcjaUsun() {
        czytajFormatke();
        getDaoFactory().getDaoDefect().deleteDefect(getConnection(), wybranyDefect);
        odswiezFormatke();
    }

    private void fillTable() {
        widok.getTable().setModel(modelTable);
        widok.getTable().revalidate();
    }

    public void odswiezFormatke() {
        lista = getDaoFactory().getDaoDefect().getDefectList(getConnection());
        modelTable = new TableDefectsModel(lista);
        fillTable();
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

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
    }

    public void setLista(List<TO_Defect> lista) {
        this.lista = lista;
    }

    public PracaPanel getPracaPanel() {
        return getWidok().getPracaPanel();
    }
}
