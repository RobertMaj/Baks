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
public enum TO_StatusDefects {

    ZAPLANOWANY(1, "OCZEKUJĄCY"),
    W_TRAKCIE(2, "W NAPRAWIE"),
    ZAKONCZONY(3, "ZAKOŃCZONE");

    private int id;
    private String opis;

    private TO_StatusDefects(int id, String opis) {
        this.id = id;
        this.opis = opis;
    }

    public static TO_StatusDefects getStatusDefectsById(int id) {
        for (TO_StatusDefects value : values()) {
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
