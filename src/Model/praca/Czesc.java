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

    public static final String PROCENT = "P";
    public static final String WARTOŚĆ = "W";
    public static final String BRAK = "B";

    public static String wybranyPrzelicznik = BRAK;

    private String przelicznik;

    public Czesc() {
        setRodzaj("C");
    }

    public Czesc(String opis, double koszt) {
        setKoszt(koszt);
        setOpis(opis);
        setRodzaj("C");
    }

    @Override
    double getCena() {
        if (!wybranyPrzelicznik.equals(BRAK)) {
            return super.getKoszt() * (Double.parseDouble(przelicznik) != 0 ? Double.parseDouble(przelicznik) : 1);
        }
        return super.getKoszt();
    }

    public static String getWybranyPrzelicznik() {
        return wybranyPrzelicznik;
    }

    public static void setWybranyPrzelicznik(String wybranyPrzelicznik) {
        Czesc.wybranyPrzelicznik = wybranyPrzelicznik;
    }

    public String getPrzelicznik() {
        return przelicznik;
    }

    public void setPrzelicznik(String przelicznik) {
        this.przelicznik = przelicznik;
    }

}
