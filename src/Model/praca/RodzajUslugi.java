/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.praca;

/**
 *
 * @author RobertM
 */
public enum RodzajUslugi {

    CZESC("C"),
    MATERIAL("M"),
    NAPRAWA("N");

    private RodzajUslugi(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    private String rodzaj;

    public String getRodzaj() {
        return rodzaj;
    }
}
