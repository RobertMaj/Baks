/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.TO_Invoice;
import Model.TO_InvoiceStatus;
import Model.TO_PaymentCompany;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import platnosci.WyszukPlatnosciController;

/**
 *
 * @author RobertM
 */
public class DaoInvoice {

    public List<TO_Invoice> getListaInvoice(Connection connection, TO_Invoice kryt) {
        List<TO_Invoice> lista = new ArrayList<>();
        try {

            String sql = "Select i.id iid, i.*, cp.id cpid, cp.* from invoice i"
                    + " inner join company_payment cp on cp.id = i.company_id"
                    + " where status = ? ";
            if (WyszukPlatnosciController.trybSortowania == WyszukPlatnosciController.TERMIN_SORTOWANIE) {
                sql += " order by termin_platnosci ";
            }
            if (WyszukPlatnosciController.trybSortowania == WyszukPlatnosciController.KWOTA_SORTOWANIE) {
                sql += " order by koszt ";
            }
            if (WyszukPlatnosciController.rosnaco) {
                sql += " asc";
            } else {
                sql += " desc";
            }

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, kryt.getStatus().getId());

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Invoice invoice = new TO_Invoice();
                invoice.setId(rs.getInt("iid"));
                invoice.setIdentyfikator(rs.getString("nr_faktury"));
                invoice.setDataWprowadzenia(rs.getDate("data_wprowadzenia"));
                invoice.setTerminPlatnosc(rs.getDate("termin_platnosci"));
                invoice.setKoszt(rs.getDouble("koszt"));
                invoice.setStatus(TO_InvoiceStatus.getStatusDefectsById(rs.getInt("status")));
                invoice.setDataZaplacenia(rs.getDate("data_zaplacenia"));
                TO_PaymentCompany paymentCompany = new TO_PaymentCompany();
                paymentCompany.setId(rs.getInt("cpid"));
                paymentCompany.setName(rs.getString("name"));
                paymentCompany.setNip(rs.getString("nip"));
                paymentCompany.setPhone(rs.getString("phone"));
                paymentCompany.setAdress(rs.getString("adress"));
                invoice.setPaymentCompany(paymentCompany);

                lista.add(invoice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<TO_Invoice> getListaInvoiceDruk(Connection connection, TO_Invoice kryt) {
        List<TO_Invoice> lista = new ArrayList<>();
        try {

            String sql = "Select i.id iid, i.*, cp.id cpid, cp.* from invoice i"
                    + " inner join company_payment cp on cp.id = i.company_id"
                    + " where status = ? AND data_zaplacenia >= ? AND data_zaplacenia <= ?";

            sql += " order by data_zaplacenia ";

            sql += " asc";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, kryt.getStatus().getId());
            prepStmt.setDate(2, new java.sql.Date(kryt.getDataOd().getTime()));
            prepStmt.setDate(3, new java.sql.Date(kryt.getDataDo().getTime()));

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Invoice invoice = new TO_Invoice();
                invoice.setId(rs.getInt("iid"));
                invoice.setIdentyfikator(rs.getString("nr_faktury"));
                invoice.setDataWprowadzenia(rs.getDate("data_wprowadzenia"));
                invoice.setTerminPlatnosc(rs.getDate("termin_platnosci"));
                invoice.setKoszt(rs.getDouble("koszt"));
                invoice.setStatus(TO_InvoiceStatus.getStatusDefectsById(rs.getInt("status")));
                invoice.setDataZaplacenia(rs.getDate("data_zaplacenia"));
                TO_PaymentCompany paymentCompany = new TO_PaymentCompany();
                paymentCompany.setId(rs.getInt("cpid"));
                paymentCompany.setName(rs.getString("name"));
                paymentCompany.setNip(rs.getString("nip"));
                paymentCompany.setPhone(rs.getString("phone"));
                paymentCompany.setAdress(rs.getString("adress"));
                invoice.setPaymentCompany(paymentCompany);

                lista.add(invoice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void dodajFirme(Connection connection, TO_PaymentCompany company) {
        try {
            String query = "Insert into company_payment(nip, name, adress, phone) values (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;

            preparedStatement.setString(i++, company.getNip());
            preparedStatement.setString(i++, company.getName());
            preparedStatement.setString(i++, company.getAdress());
            preparedStatement.setString(i++, company.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TO_PaymentCompany> getListaFirm(Connection connection) {
        List<TO_PaymentCompany> lista = new ArrayList<>();
        try {
            String sql = "Select * from company_payment"
                    + " order by name asc";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_PaymentCompany company = new TO_PaymentCompany();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setNip(rs.getString("nip"));
                company.setPhone(rs.getString("phone"));
                company.setAdress(rs.getString("adress"));
                lista.add(company);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public void dodajFakture(Connection connection, TO_Invoice faktura) {
        try {
            String query = "Insert into invoice(nr_faktury, data_wprowadzenia, termin_platnosci, koszt, status, company_id, data_zaplacenia) "
                    + "values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;

            preparedStatement.setString(i++, faktura.getIdentyfikator());
            preparedStatement.setDate(i++, new java.sql.Date((new Date()).getTime()));
            preparedStatement.setDate(i++, new java.sql.Date(faktura.getTerminPlatnosc().getTime()));
            preparedStatement.setDouble(i++, faktura.getKoszt());
            preparedStatement.setInt(i++, faktura.getStatus().getId());
            preparedStatement.setInt(i++, faktura.getPaymentCompany().getId());
            if (faktura.getDataZaplacenia() != null) {
                preparedStatement.setDate(i++, new java.sql.Date(faktura.getDataZaplacenia().getTime()));
            } else {
                preparedStatement.setDate(i++, ((java.sql.Date) null));
            }

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void aktualizujFakture(Connection connection, TO_Invoice faktura) {
        try {
            String query = "update invoice set nr_faktury = ?, termin_platnosci = ?, koszt = ?, status = ?, company_id = ?, data_zaplacenia = ? "
                    + " where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;

            preparedStatement.setString(i++, faktura.getIdentyfikator());
            preparedStatement.setDate(i++, new java.sql.Date(faktura.getTerminPlatnosc().getTime()));
            preparedStatement.setDouble(i++, faktura.getKoszt());
            preparedStatement.setInt(i++, faktura.getStatus().getId());
            preparedStatement.setInt(i++, faktura.getPaymentCompany().getId());
            preparedStatement.setDate(i++, new java.sql.Date(faktura.getDataZaplacenia().getTime()));
            preparedStatement.setInt(i++, faktura.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void usunFakture(Connection connection, TO_Invoice wybranaFaktura) {
        try {
            String query = "delete from invoice  "
                    + " where id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;

            preparedStatement.setInt(i++, wybranaFaktura.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
