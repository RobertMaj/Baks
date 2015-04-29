/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author RobertM
 */
public enum TO_InvoiceStatus {

    ZAPLACONA(1, "Zapłacona"),
    NIE_ZAPLACONA(2, "Nie zapłacona"),
    ANULOWANA(3, "Anulowany");

    private final int id;
    private final String opis;

    private TO_InvoiceStatus(int id, String opis) {
        this.id = id;
        this.opis = opis;
    }

    public static TO_InvoiceStatus getStatusDefectsById(int id) {
        for (TO_InvoiceStatus value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getOpis() {
        return opis;
    }
}
