/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;

/**
 *
 * @author RobertM
 */
public class TO_Invoice implements Serializable {

    private Integer id;
    private String nrFaktury;
    private Date dataWprowadzenia;
    private Date terminPlatnosc;
    private Double koszt;
    private TO_InvoiceStatus status;
    private TO_PaymentCompany paymentCompany;
    private Date dataZaplacenia;

    private Date dataOd;
    private Date dataDo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentyfikator() {
        return nrFaktury;
    }

    public void setIdentyfikator(String identyfikator) {
        this.nrFaktury = identyfikator;
    }

    public Date getDataWprowadzenia() {
        return dataWprowadzenia;
    }

    public void setDataWprowadzenia(Date dataWprowadzenia) {
        this.dataWprowadzenia = dataWprowadzenia;
    }

    public Date getTerminPlatnosc() {
        return terminPlatnosc;
    }

    public void setTerminPlatnosc(Date terminPlatnosc) {
        this.terminPlatnosc = terminPlatnosc;
    }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) {
        this.koszt = koszt;
    }

    public TO_InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(TO_InvoiceStatus status) {
        this.status = status;
    }

    public TO_PaymentCompany getPaymentCompany() {
        return paymentCompany;
    }

    public Date getDataOd() {
        return dataOd;
    }

    public void setDataOd(Date dataOd) {
        this.dataOd = dataOd;
    }

    public Date getDataDo() {
        return dataDo;
    }

    public void setDataDo(Date dataDo) {
        this.dataDo = dataDo;
    }

    public void setPaymentCompany(TO_PaymentCompany paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public String getNrFaktury() {
        return nrFaktury;
    }

    public void setNrFaktury(String nrFaktury) {
        this.nrFaktury = nrFaktury;
    }

    public Date getDataZaplacenia() {
        return dataZaplacenia;
    }

    public void setDataZaplacenia(Date dataZaplacenia) {
        this.dataZaplacenia = dataZaplacenia;
    }

    public static String getWynikSumaKoszt(Double kwota) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(kwota);
    }

}
