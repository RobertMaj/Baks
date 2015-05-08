/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.TO_Invoice;
import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author RobertM
 */
public class PodsumowanieController extends AbstractController implements ServiceListener {

    private PodsumowaniePanel widok;

    private TO_Defect wybranyDefect;

    private double marza = 0.0;
    private double cena = 0.0;

    private CzescTableModel czescModel;
    private ServiceTableModel materialTableModel;
    private ServiceTableModel naprawaTableModel;

    public PodsumowanieController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public PodsumowanieController(PodsumowaniePanel panel, Connection connection, DaoFactory daoFactory) {
        this(connection, daoFactory);
    }

    public void initListener() {
        widok.getBtnRabat().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dodajRabat();
            }
        });
    }

    private void dodajRabat() {
        if (!widok.getRabat().isEditable()) {
            widok.getRabat().setEditable(true);
            widok.getBtnRabat().setText("Zapisz");
        } else {
            wybranyDefect.setRabat(Double.parseDouble(widok.getRabat().getText()));
            obliczKosztPoRabacie();
            widok.getPodsumowanieKoszt().setText(wybranyDefect.getKosztSumaS());
            widok.getLabelDoZaplaty().setText("DO ZAPŁATY: " + wybranyDefect.getKosztSumaS() + " zł");
            widok.getRabat().setEditable(false);
            widok.getBtnRabat().setText("Rabat");
            fireAktualizujDefect(wybranyDefect);
        }
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
        widok.getNaprawaKoszt().setText(wybranyDefect.getKosztNaprawyS());
        widok.getCzesciKoszt().setText(wybranyDefect.getKosztCzesciS());
        widok.getMaterialyKoszt().setText(wybranyDefect.getKosztMaterialyS());
        widok.getPodsumowanieKoszt().setText(wybranyDefect.getKosztSumaS());

        widok.getRabat().setText(TO_Invoice.getWynikSumaKoszt(wybranyDefect.getRabat()));

        widok.getCenaCzesc().setText(TO_Invoice.getWynikSumaKoszt(cena));
        widok.getMarzaCzesc().setText(TO_Invoice.getWynikSumaKoszt(marza));

        widok.getPoRabacieRazem().setText(TO_Invoice.getWynikSumaKoszt(wybranyDefect.getKosztCzesci() + wybranyDefect.getKosztMaterialy() + wybranyDefect.getKosztNaprawy()));

        widok.getLabelDoZaplaty().setText("DO ZAPŁATY: " + wybranyDefect.getKosztSumaS() + " zł");
    }

    public void obliczPodsumowanie() {
        wybranyDefect.setKoszt(0.0);
        obliczKosztCzesci();
        obliczKosztMaterial();
        obliczKosztNaprawy();
        obliczKosztPoRabacie();
        wypelnijFormatke();
    }

    public void obliczKosztPoRabacie() {
        wybranyDefect.obliczRabat();
    }

    public void obliczKosztCzesci() {
        double koszt = 0.0;
        marza = 0.0;
        cena = 0.0;
        for (Czesc item : czescModel.getList()) {
            koszt += item.getCena();
            cena += item.getKoszt();
            marza += item.getCena() - item.getKoszt();
        }
        wybranyDefect.setKosztCzesci(koszt);
        wybranyDefect.setKoszt(wybranyDefect.getKoszt() + wybranyDefect.getKosztCzesci());
    }

    public void obliczKosztNaprawy() {
        double koszt = 0.0;
        for (Object item : naprawaTableModel.getList()) {
            koszt += ((Naprawa) item).getKoszt();
        }
        wybranyDefect.setKosztNaprawy(koszt);
        wybranyDefect.setKoszt(wybranyDefect.getKoszt() + wybranyDefect.getKosztNaprawy());
    }

    public void obliczKosztMaterial() {
        double koszt = 0.0;
        for (Object item : materialTableModel.getList()) {
            koszt += ((Material) item).getKoszt();
        }
        wybranyDefect.setKosztMaterialy(koszt);
        wybranyDefect.setKoszt(wybranyDefect.getKoszt() + wybranyDefect.getKosztMaterialy());
    }

    public PodsumowaniePanel getWidok() {
        return widok;
    }

    public void setWidok(PodsumowaniePanel widok) {
        this.widok = widok;
    }

    @Override
    public void fireCzescTableChanged(List<Czesc> lista) {
        czescModel.setList(lista);
        czescModel.fireTableDataChanged();
        obliczPodsumowanie();
//        wypelnijFormatke();
    }

    @Override
    public void fireNaprawaTableChanged(List<Naprawa> lista) {
        naprawaTableModel.setList(lista);
        naprawaTableModel.fireTableDataChanged();
        obliczPodsumowanie();
    }

    @Override
    public void fireMaterialTableChanged(List<Material> lista) {
        materialTableModel.setList(lista);
        materialTableModel.fireTableDataChanged();
        obliczPodsumowanie();
    }

    public void initTableCzesci(List<Czesc> listaCzesci) {
        czescModel = new CzescTableModel(listaCzesci);
        widok.getTableCzesci().setModel(czescModel);
        widok.getTableCzesci().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTableCzesci().getColumnModel().getColumn(0).setWidth(30);
        widok.getTableCzesci().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTableCzesci().getColumnModel().getColumn(0).setMinWidth(30);

        widok.getTableCzesci().getColumnModel().getColumn(1).setPreferredWidth(350);
        widok.getTableCzesci().getColumnModel().getColumn(1).setWidth(350);
        widok.getTableCzesci().getColumnModel().getColumn(1).setMaxWidth(350);
        widok.getTableCzesci().getColumnModel().getColumn(1).setMinWidth(350);
        widok.getTableCzesci().getColumnModel().getColumn(1).setResizable(false);
        widok.getTableCzesci().revalidate();
    }

    public void initTableNaprawa(List<Naprawa> listaNaprawa) {
        naprawaTableModel = new ServiceTableModel<>(listaNaprawa);
        widok.getTableNaprawa().setModel(naprawaTableModel);
        widok.getTableNaprawa().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTableNaprawa().getColumnModel().getColumn(0).setWidth(30);
        widok.getTableNaprawa().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTableNaprawa().getColumnModel().getColumn(0).setMinWidth(30);

        widok.getTableNaprawa().getColumnModel().getColumn(1).setPreferredWidth(450);
        widok.getTableNaprawa().getColumnModel().getColumn(1).setWidth(450);
        widok.getTableNaprawa().getColumnModel().getColumn(1).setMaxWidth(450);
        widok.getTableNaprawa().getColumnModel().getColumn(1).setMinWidth(450);
        widok.getTableNaprawa().getColumnModel().getColumn(1).setResizable(false);
        widok.getTableNaprawa().revalidate();
    }

    public void initTableMaterialy(List<Material> listaMaterial) {
        materialTableModel = new ServiceTableModel<>(listaMaterial);
        widok.getTableMaterialy().setModel(materialTableModel);
        widok.getTableMaterialy().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTableMaterialy().getColumnModel().getColumn(0).setWidth(30);
        widok.getTableMaterialy().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTableMaterialy().getColumnModel().getColumn(0).setMinWidth(30);

        widok.getTableMaterialy().getColumnModel().getColumn(1).setPreferredWidth(450);
        widok.getTableMaterialy().getColumnModel().getColumn(1).setWidth(450);
        widok.getTableMaterialy().getColumnModel().getColumn(1).setMaxWidth(450);
        widok.getTableMaterialy().getColumnModel().getColumn(1).setMinWidth(450);
        widok.getTableMaterialy().getColumnModel().getColumn(1).setResizable(false);
        widok.getTableMaterialy().revalidate();
    }

    public TO_Defect getWybranyDefect() {
        return wybranyDefect;
    }

    public void setWybranyDefect(TO_Defect wybranyDefect) {
        this.wybranyDefect = wybranyDefect;
    }

    @Override
    public void fireAktualizujDefect(TO_Defect defect) {
        getDaoFactory().getDaoDefect().updateDefect(getConnection(), defect);
    }

}
