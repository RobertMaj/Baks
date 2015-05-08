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
public class Czesc extends Usluga {

    public Przelicznik wybranyPrzelicznik = Przelicznik.PROCENT;

    private String przelicznik = "0";
    private double cena;

    public Czesc() {
        this.rodzaj = RodzajUslugi.CZESC;
    }

    public Czesc(String opis, double koszt) {
        setKoszt(koszt);
        setOpis(opis);
        this.rodzaj = RodzajUslugi.CZESC;
    }

    @Override
    public double getCena() {
        if (wybranyPrzelicznik.equals(Przelicznik.PROCENT)) {
            return cena = super.getKoszt() + super.getKoszt() * (!(przelicznik.equals("0")) ? getPrzelicznikCena() : 0);
        } else if (wybranyPrzelicznik.equals(Przelicznik.WARTOSC)) {
            return cena = super.getKoszt() + (!(przelicznik.equals("0")) ? getPrzelicznikCena() : 0);
        }
        return 0.0;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Przelicznik getWybranyPrzelicznik() {
        return wybranyPrzelicznik;
    }

    public void setWybranyPrzelicznik(Przelicznik wybranyPrzelicznik) {
        this.wybranyPrzelicznik = wybranyPrzelicznik;
    }

    public String getPrzelicznik() {
        return przelicznik;
    }

    public Double getPrzelicznikCena() {
        if (wybranyPrzelicznik.equals(Przelicznik.PROCENT)) {
            return Double.parseDouble(przelicznik) / 100;
        } else {
            return Double.parseDouble(przelicznik);
        }
    }

    public void setPrzelicznik(String przelicznik) {
        this.przelicznik = przelicznik;
    }

    @Override
    public Czesc clone() throws CloneNotSupportedException {
        Czesc czesc = new Czesc();
        czesc.setCena(this.cena);
        czesc.setIdDefect(this.getIdDefect());
        czesc.setKoszt(this.getKoszt());
        czesc.setPrzelicznik(this.przelicznik);
        czesc.setOpis(this.getOpis());
        return czesc;
    }
}
