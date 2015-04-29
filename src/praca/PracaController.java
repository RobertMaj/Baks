/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praca;

import Model.TO_Defect;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import tabelDefects.TableDefectsHistoryModel;

/**
 *
 * @author Robert M
 */
public class PracaController extends AbstractController {

    private TableDefectsHistoryModel modelTable;
    private PracaPanel widok;
    private List<TO_Defect> lista;

    private Date wybranaData = new Date();
    private boolean zakonczone = false;

    private TO_Defect wybranyDefect;

    public PracaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        setWidok(new PracaPanel());
        getListaWybranych();
        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaRezygnuj();
            }
        });

        widok.getBtnSzukaj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaSzukaj();
            }
        });

        widok.getBtnZapisz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZapisz();
            }
        });

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                wybranyDefect = ((TableDefectsHistoryModel) widok.getTable().getModel()).getDefectAt(widok.getTable().getSelectedRow());
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

    public void akcjaSzukaj() {
        czytajFormatke();
        getListaWybranych();
        fillTable();
    }

    public void akcjaZapisz() {
        czytajFormatke();
        if (wybranyDefect == null || wybranyDefect.getKoszt() == null) {
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Wypełnij koszt");
            return;
        }
        wybranyDefect.setData(new Date());
        getDaoFactory().getDaoDefect().updateDefectPraca(getConnection(), wybranyDefect);
        getListaWybranych();
        fillTable();
        BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Poprawnie zapisano");
    }

    private void fillTable() {
        modelTable = new TableDefectsHistoryModel(lista);
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
        wybranaData = widok.getData().getDate();
        if (wybranyDefect != null) {
            if (widok.getKoszt().getText() != null && !widok.getKoszt().getText().equals("")) {
                wybranyDefect.setKoszt(Float.parseFloat(widok.getKoszt().getText()));
            }
            wybranyDefect.setData(new Date());
        }
    }

    @Override
    public void wypelnijFormatke() {

    }

    public void getListaWybranych() {
        czytajFormatke();
        if (widok.getwTrakcie().isSelected()) {
            lista = getDaoFactory().getDaoDefect().getDefectUnDoneListByDate(getConnection(), wybranaData);
        } else if (widok.getZakonczone().isSelected()) {
            lista = getDaoFactory().getDaoDefect().getDefectDoneListByDate(getConnection(), wybranaData);
        }
    }

    public TO_Defect getWybranyDefect() {
        return wybranyDefect;
    }

    public void setWybranyDefect(TO_Defect wybranyDefect) {
        this.wybranyDefect = wybranyDefect;
    }

    public Date getWybranaData() {
        return wybranaData;
    }

    public void setWybranaData(Date wybranaData) {
        this.wybranaData = wybranaData;
    }

    public boolean isZakonczone() {
        return zakonczone;
    }

    public void setZakonczone(boolean zakonczone) {
        this.zakonczone = zakonczone;
    }

}
