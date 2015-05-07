/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Material;
import Model.praca.Naprawa;
import dao.DaoFactory;
import java.sql.Connection;

/**
 *
 * @author jmaj
 */
public class NaprawaServiceController extends ServiceImpl<Naprawa> {

    public NaprawaServiceController(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        super(widok, connection, daoFactory);
    }

    @Override
    public void dodajUsluge() {
        fireNaprawaChangeListener();
    }

    @Override
    public void usunUsluge() {
        fireNaprawaChangeListener();
    }

    @Override
    public Naprawa getWybranaUslugaN() {
        if (wybranaUsluga == null) {
            return wybranaUsluga = new Naprawa();
        }
        return wybranaUsluga;
    }
}
