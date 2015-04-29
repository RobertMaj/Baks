/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Robert M
 */
public enum TO_Termin implements Serializable {

    TERMIN_1(1, "8:00 - 8:30"),
    TERMIN_2(2, "8:30 - 9:00"),
    TERMIN_3(3, "9:00 - 9:30"),
    TERMIN_4(4, "9:30 - 10:00"),
    TERMIN_5(5, "10:00 - 10:30"),
    TERMIN_6(6, "10:30 - 11:00"),
    TERMIN_7(7, "11:00 - 11:30"),
    TERMIN_8(8, "11:30 - 12:00"),
    TERMIN_9(9, "12:00 - 12:30"),
    TERMIN_10(10, "12:30 - 13:00"),
    TERMIN_11(11, "13:00 - 13:30"),
    TERMIN_12(12, "13:30 - 14:00"),
    TERMIN_13(13, "14:00 - 14:30"),
    TERMIN_14(14, "14:30 - 15:00"),
    TERMIN_15(15, "15:00 - 15:30"),
    TERMIN_16(16, "15:30 - 16:00"),
    TERMIN_17(17, "16:00 - 16:30"),
    TERMIN_18(18, "16:30 - 17:00");

    private final int lp;
    private final String opis;

    private TO_Termin(int lp, String opis) {
        this.lp = lp;
        this.opis = opis;
    }

    public int getLp() {
        return lp;
    }

    public String getOpis() {
        return opis;
    }

    public static TO_Termin getTerminByLp(int lp) {
        for (TO_Termin t : values()) {
            if (t.getLp() == lp) {
                return t;
            }
        }
        return null;
    }

    /**
     * dostaje na wejsciu zajete zwraca dostepne
     *
     * @param termin
     * @return
     */
    public static List<TO_Termin> getDostepneTerminy(List<TO_Termin> termin) {
        List<TO_Termin> lista = new ArrayList<>(Arrays.asList(values()));
        for (TO_Termin t : termin) {
            if (lista.contains(t)) {
                lista.remove(t);
            }
        }
        return lista;
    }

    public static List<TO_Termin> getDostepneTerminyEdit(List<TO_Termin> termin, TO_Termin wybranyTermin) {
        List<TO_Termin> lista = new ArrayList<>(Arrays.asList(values()));
        for (TO_Termin t : termin) {
            if (wybranyTermin != null && t.equals(wybranyTermin)) {
                continue;
            }
            if (lista.contains(t)) {
                lista.remove(t);
            }
        }
        return lista;
    }

    @Override
    public String toString() {
        return opis;
    }

}
