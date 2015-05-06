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
public class Naprawa extends Usluga{
    
    public Naprawa() {
        setRodzaj("N");
    }

    public Naprawa(String opis, double koszt) {
        setKoszt(koszt);
        setOpis(opis);
        setRodzaj("N");
    }

    @Override
    double getCena() {
        return getKoszt();
    }
}
