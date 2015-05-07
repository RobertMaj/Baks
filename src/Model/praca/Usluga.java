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
    private Integer idDefect;
    private String opis;
    public RodzajUslugi rodzaj;

    public abstract double getCena();

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

    public Integer getIdDefect() {
        return idDefect;
    }

    public void setIdDefect(Integer idDefect) {
        this.idDefect = idDefect;
    }
}
