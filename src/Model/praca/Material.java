/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.praca;

/**
 *
 * @author jmaj
 */
public class Material extends Usluga {

    public Material() {
        setRodzaj("M");
    }

    public Material(String opis, double koszt) {
        setKoszt(koszt);
        setOpis(opis);
        setRodzaj("M");
    }

    @Override
    double getCena() {
        return getKoszt();
    }
}