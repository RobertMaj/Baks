/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.praca.RodzajUslugi;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RobertM
 */
public class PodsumowanieController extends AbstractController implements ServiceListener {

    private PodsumowaniePanel widok;
    private Map<RodzajUslugi, List> listaUslug;

    public PodsumowanieController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public PodsumowanieController(PodsumowaniePanel panel, Connection connection, DaoFactory daoFactory) {
        this(connection, daoFactory);
        this.widok = panel;
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
        widok.getNaprawaKoszt().setText(TO_Defect.getKosztNaprawy());
        widok.getCzesciKoszt().setText(TO_Defect.getKosztCzesci());
        widok.getMaterialyKoszt().setText(TO_Defect.getKosztMaterialy());
        widok.getPodsumowanieKoszt().setText(TO_Defect.getKosztSuma());

        widok.getLabelDoZaplaty().setText("DO ZAPŁATY: " + TO_Defect.getKosztSuma() + " zł");
    }

    public void obliczPodsumowanie() {
        TO_Defect.aktualizujKoszt(listaUslug);
    }

    public PodsumowaniePanel getWidok() {
        return widok;
    }

    public void setWidok(PodsumowaniePanel widok) {
        this.widok = widok;
    }

    @Override
    public void fireCzescTableChanged() {
        obliczPodsumowanie();
        wypelnijFormatke();
    }

    @Override
    public void fireNaprawaTableChanged() {
        obliczPodsumowanie();
        wypelnijFormatke();
    }

    @Override
    public void fireMaterialTableChanged() {
        obliczPodsumowanie();
        wypelnijFormatke();
    }

    public Map<RodzajUslugi, List> getListaUslug() {
        return listaUslug;
    }

    void setListaUslug(Map<RodzajUslugi, List> listaUslug) {
        this.listaUslug = listaUslug;
    }

}
