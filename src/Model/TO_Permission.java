/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author jmaj
 */
public enum TO_Permission implements Serializable {

    ADMINISTRATOR(1, "Administrator"),
    UZYTKOWNIK(2, "Użytkownik"),
    PRACOWNIK(3, "Pracownik");

    private Integer id;
    private String opis;

    private TO_Permission(int id, String opis) {
        this.id = id;
        this.opis = opis;
    }
    
    public static TO_Permission getPermissonById(Integer id) {
        if (id == null) {
            return null;
        }
        for (TO_Permission permission : values()) {
            if (id == permission.getId()) {
                return permission;
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
