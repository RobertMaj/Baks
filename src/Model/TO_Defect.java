/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

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
    private Double koszt;
    private Double kosztCzesci;
    private Double kosztNaprawy;
    private Double kosztMaterialy;
    private double rabat = 0.0;
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

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }

    public TO_Customer getCustomer() {
        return customer;
    }

    public void setCustomer(TO_Customer customer) {
        this.customer = customer;
    }

//    public static void aktualizujKoszt(Map<RodzajUslugi, List> map) {
//        koszt = 0.0;
//        kosztCzesci = 0.0;
//        kosztMaterialy = 0.0;
//        kosztNaprawy = 0.0;
//        for (Object item : map.get(RodzajUslugi.CZESC)) {
//            kosztCzesci += ((Czesc) item).getCena();
//        }
//        for (Object item : map.get(RodzajUslugi.NAPRAWA)) {
//            kosztNaprawy += ((Naprawa) item).getKoszt();
//        }
//        for (Object item : map.get(RodzajUslugi.MATERIAL)) {
//            kosztMaterialy += ((Material) item).getKoszt();
//        }
//        koszt = kosztCzesci + kosztMaterialy + kosztNaprawy;
//    }
    public String getKosztCzesciS() {
        return TO_Invoice.getWynikSumaKoszt(kosztCzesci);
    }

    public String getKosztMaterialyS() {
        return TO_Invoice.getWynikSumaKoszt(kosztMaterialy);
    }

    public String getKosztNaprawyS() {
        return TO_Invoice.getWynikSumaKoszt(kosztNaprawy);
    }

    public String getKosztSumaS() {
        return TO_Invoice.getWynikSumaKoszt(koszt);
    }

    public Double getKosztCzesci() {
        return kosztCzesci;
    }

    public void setKosztCzesci(Double kosztCzesci) {
        this.kosztCzesci = kosztCzesci;
    }

    public Double getKosztNaprawy() {
        return kosztNaprawy;
    }

    public void setKosztNaprawy(Double kosztNaprawy) {
        this.kosztNaprawy = kosztNaprawy;
    }

    public Double getKosztMaterialy() {
        return kosztMaterialy;
    }

    public void setKosztMaterialy(Double kosztMaterialy) {
        this.kosztMaterialy = kosztMaterialy;
    }

    public String getInfoNaprawa() {
        return (this.getCustomer().getNameS() + ' ' + this.getCustomer().getSurnameS() + ' ' + this.getCustomer().getPhoneS()
                + "      " + this.getMarkaS() + ' ' + this.getModelS()).toUpperCase();

    }

    private String getModelS() {
        if (this.model == null) {
            return "";
        }
        return model;
    }

    private String getMarkaS() {
        if (this.marka == null) {
            return "";
        }
        return marka;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public void obliczRabat() {
        this.koszt = koszt - (koszt * (rabat == 0.0 ? 0 : (rabat / 100)));
    }
}
