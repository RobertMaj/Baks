/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.przegladanie;

import Model.TO_Defect;
import Model.TO_Termin;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
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
        lista = getDaoFactory().getDaoDefect().getDefectListByUser(getConnection(), BaksSessionBean.getUser());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setLista(List<TO_Defect> lista) {
        this.lista = lista;
    }
}
