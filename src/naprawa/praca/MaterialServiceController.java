/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Czesc;
import Model.praca.Material;
import dao.DaoFactory;
import java.sql.Connection;

/**
 *
 * @author jmaj
 */
public class MaterialServiceController extends ServiceImpl<Material> {

    public MaterialServiceController(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        super(widok, connection, daoFactory);
    }

    @Override
    public void dodajUsluge() {
        fireMaterialyChangeListener();
    }

    @Override
    public void usunUsluge() {
        fireMaterialyChangeListener();
    }
    
    @Override
    public Material getWybranaUslugaN() {
        if (wybranaUsluga == null) {
            return wybranaUsluga = new Material();
        }
        return wybranaUsluga;
    }
}
