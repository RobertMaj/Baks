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
public abstract class Usluga {

    private double koszt;
    private String opis;
    public RodzajUslugi rodzaj;

    abstract double getCena();

    public String getOpis() {
        return opis;
    }

    public RodzajUslugi getRodzaj() {
        return rodzaj;
    }

    public double getKoszt() {
        return koszt;
    }

    public void setKoszt(double koszt) {
        this.koszt = koszt;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setRodzaj(RodzajUslugi rodzaj) {
        this.rodzaj = rodzaj;
    }

}
