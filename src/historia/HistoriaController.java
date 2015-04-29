/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package historia;

import Model.TO_Defect;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import tabelDefects.TableDefectsHistoryModel;

/**
 *
 * @author Robert M
 */
public class HistoriaController extends AbstractController {

    private TableDefectsHistoryModel modelTable;
    private PracaPanel widok;
    private List<TO_Defect> lista;

    public HistoriaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        setWidok(new PracaPanel());
        setLista(getDaoFactory().getDaoDefect().getDefectDoneListByUser(getConnection(), BaksSessionBean.getUser()));

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaRezygnuj();
            }
        });

        modelTable = new TableDefectsHistoryModel(lista);
        fillTable();

    }

    /**
     *
     */
    @Override
    public void akcjaRezygnuj() {
        super.akcjaRezygnuj();

    }

    private void fillTable() {
        widok.getTable().setModel(modelTable);
        widok.getTable().revalidate();
    }

//<editor-fold defaultstate="collapsed" desc="Akcesory">
    public PracaPanel getWidok() {
        return widok;
    }

    public void setWidok(PracaPanel widok) {
        this.widok = widok;
    }

    public TableDefectsHistoryModel getModelTable() {
        return modelTable;
    }

    public void setModelTable(TableDefectsHistoryModel modelTable) {
        this.modelTable = modelTable;
    }

    public List<TO_Defect> getLista() {
        return lista;
    }

    public void setLista(List<TO_Defect> lista) {
        this.lista = lista;
    }
//</editor-fold>

    @Override
    public void czytajFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
