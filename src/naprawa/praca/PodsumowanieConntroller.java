/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.sql.Connection;

/**
 *
 * @author RobertM
 */
public class PodsumowanieConntroller extends AbstractController implements ServiceListener {

    private PodsumowaniePanel widok;

    public PodsumowanieConntroller(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public PodsumowanieConntroller(PodsumowaniePanel panel, Connection connection, DaoFactory daoFactory) {
        this(connection, daoFactory);
        this.widok = panel;
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
    }

    @Override
    public void fireChangeSerive() {
        obliczPodsumowanie();
        wypelnijFormatke();
    }

    public void obliczPodsumowanie() {

    }

    public PodsumowaniePanel getWidok() {
        return widok;
    }

    public void setWidok(PodsumowaniePanel widok) {
        this.widok = widok;
    }
}
