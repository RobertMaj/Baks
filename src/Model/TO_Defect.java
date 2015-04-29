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
    private float koszt;
    private TO_Termin termin;
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

    public Float getKoszt() {
        return koszt;
    }

    public void setKoszt(Float koszt) {
        this.koszt = koszt;
    }

    public TO_Termin getTermin() {
        return termin;
    }

    public void setTermin(TO_Termin termin) {
        this.termin = termin;
    }

    public void setKoszt(float koszt) {
        this.koszt = koszt;
    }

    public TO_Customer getCustomer() {
        return customer;
    }

    public void setCustomer(TO_Customer customer) {
        this.customer = customer;
    }

}
