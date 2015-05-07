/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import Model.praca.RodzajUslugi;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Robert M
 */
public class TO_Defect implements Serializable {

    private int id;
    private Date data;
    private String marka;
    private String model;
    private String rokProd;
    private String opis;
    private static Double koszt;
    private static Double kosztCzesci;
    private static Double kosztNaprawy;
    private static Double kosztMaterialy;
    private Date dataOddanie;
    private TO_Customer customer;
    private TO_StatusDefects status;

    public Date getDataOddanie() {
        return dataOddanie;
    }

    public void setDataOddanie(Date dataOddanie) {
        this.dataOddanie = dataOddanie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TO_StatusDefects getStatus() {
        return status;
    }

    public void setStatus(TO_StatusDefects status) {
        this.status = status;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRokProd() {
        return rokProd;
    }

    public void setRokProd(String rokProd) {
        this.rokProd = rokProd;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getKoszt() {
        return koszt;
    }

    public static void setKoszt(Double koszt) {
        koszt = koszt;
    }

    public TO_Customer getCustomer() {
        return customer;
    }

    public void setCustomer(TO_Customer customer) {
        this.customer = customer;
    }

    public static void aktualizujKoszt(Map<RodzajUslugi, List> map) {
        koszt = 0.0;
        kosztCzesci = 0.0;
        kosztMaterialy = 0.0;
        kosztNaprawy = 0.0;
        for (Object item : map.get(RodzajUslugi.CZESC)) {
            kosztCzesci += ((Czesc) item).getCena();
        }
        for (Object item : map.get(RodzajUslugi.NAPRAWA)) {
            kosztNaprawy += ((Naprawa) item).getKoszt();
        }
        for (Object item : map.get(RodzajUslugi.MATERIAL)) {
            kosztMaterialy += ((Material) item).getKoszt();
        }
        koszt = kosztCzesci + kosztMaterialy + kosztNaprawy;
    }

    public static String getKosztCzesci() {
        return TO_Invoice.getWynikSumaKoszt(kosztCzesci);
    }

    public static String getKosztMaterialy() {
        return TO_Invoice.getWynikSumaKoszt(kosztMaterialy);
    }

    public static String getKosztNaprawy() {
        return TO_Invoice.getWynikSumaKoszt(kosztNaprawy);
    }

    public static String getKosztSuma() {
        return TO_Invoice.getWynikSumaKoszt(koszt);
    }

}
