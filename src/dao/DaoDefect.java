/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.TO_Customer;
import Model.TO_Defect;
import Model.TO_StatusDefects;
import Model.TO_Termin;
import Model.TO_User;
import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import Model.praca.Przelicznik;
import Model.praca.RodzajUslugi;
import Model.praca.Usluga;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert M
 */
public class DaoDefect {

    public List<TO_Defect> getDefectList(Connection connection) {
        List<TO_Defect> list = new ArrayList<>();
        try {

            String sql = "Select * from defects ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Defect defect = new TO_Defect();
                defect.setId(rs.getInt("id"));
                defect.setData(rs.getDate("data"));
                defect.setMarka(rs.getString("marka"));
                defect.setModel(rs.getString("model"));
                defect.setRokProd(rs.getString("rok_prod"));
                defect.setKoszt(rs.getDouble("koszt"));
                defect.setStatus(TO_StatusDefects.getStatusDefectsById(rs.getInt("status")));
                defect.setOpis(rs.getString("opis"));
                defect.setDataOddanie(rs.getDate("data_oddania"));
                defect.setRabat(rs.getDouble("rabat"));
                TO_Customer customer = new TO_Customer();
                customer.setName(rs.getString("imie"));
                customer.setSurname(rs.getString("nazwisko"));
                customer.setPhone(rs.getString("tel"));
                defect.setCustomer(customer);

                list.add(defect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<TO_Defect> getDefectDoneListByUser(Connection connection, TO_User user) {
        List<TO_Defect> list = new ArrayList<>();
        try {

            String sql = "Select * from defects where mail = ? and data_oddania is not null order by data_oddania desc ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, user.getMail());

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Defect defect = new TO_Defect();
                defect.setId(rs.getInt("id"));
                defect.setData(rs.getDate("data"));
                defect.setMarka(rs.getString("marka"));
                defect.setModel(rs.getString("model"));
                defect.setRokProd(rs.getString("rok_prod"));
                defect.setKoszt(rs.getDouble("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setDataOddanie(rs.getDate("data_oddania"));
                defect.setCustomer(null);

                list.add(defect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<TO_Defect> getDefectDoneListByDate(Connection connection, Date date) {
        List<TO_Defect> list = new ArrayList<>();
        try {

            String sql = "Select * from defects where data_oddania = ? order by data_oddania desc ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDate(1, new java.sql.Date(date.getTime()));

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Defect defect = new TO_Defect();
                defect.setId(rs.getInt("id"));
                defect.setData(rs.getDate("data"));
                defect.setMarka(rs.getString("marka"));
                defect.setModel(rs.getString("model"));
                defect.setRokProd(rs.getString("rok_prod"));
                defect.setKoszt(rs.getDouble("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setDataOddanie(rs.getDate("data_oddania"));

                list.add(defect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Map<RodzajUslugi, List> pobierzUslugi(Connection connection, TO_Defect defect) {
        Map<RodzajUslugi, List> mapa = new HashMap<>();
        try {

            String sql = "select * from service where id_defect = ? order by rodzaj  ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, defect.getId());

            ResultSet rs = prepStmt.executeQuery();

            mapa.put(RodzajUslugi.CZESC, new ArrayList());
            mapa.put(RodzajUslugi.MATERIAL, new ArrayList());
            mapa.put(RodzajUslugi.NAPRAWA, new ArrayList());

            while (rs.next()) {
                if (rs.getString("rodzaj").equals(RodzajUslugi.CZESC.getRodzaj())) {
                    Czesc czesc = new Czesc();
                    czesc.setId(rs.getInt("id"));
                    czesc.setIdDefect(defect.getId());
                    czesc.setKoszt(rs.getDouble("koszt"));
                    czesc.setOpis(rs.getString("opis"));
                    czesc.setWybranyPrzelicznik(Przelicznik.getPrzelicznikById(rs.getString("rodzaj_zmiana_cena")));
                    czesc.setPrzelicznik(rs.getString("zmiana_cena"));

                    mapa.get(RodzajUslugi.CZESC).add(czesc);
                } else if (rs.getString("rodzaj").equals(RodzajUslugi.NAPRAWA.getRodzaj())) {
                    Naprawa naprawa = new Naprawa();
                    naprawa.setId(rs.getInt("id"));
                    naprawa.setIdDefect(defect.getId());
                    naprawa.setKoszt(rs.getDouble("koszt"));
                    naprawa.setOpis(rs.getString("opis"));

                    mapa.get(RodzajUslugi.NAPRAWA).add(naprawa);

                } else if (rs.getString("rodzaj").equals(RodzajUslugi.MATERIAL.getRodzaj())) {
                    Material material = new Material();
                    material.setId(rs.getInt("id"));
                    material.setIdDefect(defect.getId());
                    material.setKoszt(rs.getDouble("koszt"));
                    material.setOpis(rs.getString("opis"));

                    mapa.get(RodzajUslugi.MATERIAL).add(material);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;

    }

    public void zapiszUsluge(Connection connection, Usluga usluga) {
        try {
            String query = null;
            if (!(usluga instanceof Czesc)) {
                query = "Insert into service(opis, rodzaj, id_defect, koszt)"
                        + " values (?,?,?,?)";
            } else {
                query = "Insert into service(opis, rodzaj, zmiana_cena, rodzaj_zmiana_cena, id_defect, koszt)"
                        + " values (?,?,?,?,?,?)";
            }
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, usluga.getOpis());
            preparedStatement.setString(i++, usluga.getRodzaj().getRodzaj());
            if (usluga instanceof Czesc) {
                preparedStatement.setString(i++, ((Czesc) usluga).getPrzelicznik());
                preparedStatement.setString(i++, ((Czesc) usluga).getWybranyPrzelicznik().getId());
            }
            preparedStatement.setInt(i++, usluga.getIdDefect());
            preparedStatement.setDouble(i++, usluga.getKoszt());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TO_Defect> getDefectUnDoneListByDate(Connection connection, Date date) {
        List<TO_Defect> list = new ArrayList<>();
        try {

            String sql = "Select * from defects where data = ? and data_oddania is null order by data_oddania desc ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDate(1, new java.sql.Date(date.getTime()));

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_Defect defect = new TO_Defect();
                defect.setId(rs.getInt("id"));
                defect.setData(rs.getDate("data"));
                defect.setMarka(rs.getString("marka"));
                defect.setModel(rs.getString("model"));
                defect.setRokProd(rs.getString("rok_prod"));
                defect.setKoszt(rs.getDouble("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setDataOddanie(rs.getDate("data_oddania"));

                list.add(defect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void updateDefect(Connection connection, TO_Defect defect) {
        try {
            String query = "update defects set status = ?, marka = ?, model = ?, rok_prod = ?, opis = ?, koszt = ?, data = ?, data_oddania = ?,  rabat = ? where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setInt(i++, defect.getStatus().getId());
            preparedStatement.setString(i++, defect.getMarka());
            preparedStatement.setString(i++, defect.getModel());
            preparedStatement.setString(i++, defect.getRokProd());
            preparedStatement.setString(i++, defect.getOpis());
            preparedStatement.setDouble(i++, defect.getKoszt());
            preparedStatement.setDate(i++, new java.sql.Date(defect.getData().getTime()));
            if (defect.getDataOddanie() != null) {
                preparedStatement.setDate(i++, new java.sql.Date(defect.getDataOddanie().getTime()));
            } else {
                preparedStatement.setDate(i++, ((java.sql.Date) null));

            }
            preparedStatement.setDouble(i++, defect.getRabat());
            preparedStatement.setInt(i++, defect.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDefectPraca(Connection connection, TO_Defect defect) {
        try {
            String query = "update defects set data_oddania = ?,  koszt = ? where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
            preparedStatement.setDouble(i++, defect.getKoszt());
            preparedStatement.setInt(i++, defect.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDefect(Connection connection, TO_Defect defect) {
        deleteServiceByDefectId(connection, defect.getId());
        try {
            String query = "delete from defects where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setInt(i++, defect.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteServiceByDefectId(Connection connection, Integer id) {
        try {
            String query = "delete from service where id_defect = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setInt(i++, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteService(Connection connection, Usluga usluga) {
        try {
            String query = "delete from service where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setInt(i, usluga.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TO_Termin> getTerminListByDate(Connection connection, Date date) {
        List<TO_Termin> list = new ArrayList<>();
        try {

            String sql = "Select * from defects where data = ? ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setDate(1, new java.sql.Date(date.getTime()));

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                list.add(TO_Termin.getTerminByLp(rs.getInt("termin_nr")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void saveDefect(Connection connection, TO_Defect defect) {
        try {
            String query = "Insert into defects(data, marka, model, rok_prod, opis, status, imie, nazwisko, tel)"
                    + " values (?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setDate(i++, new java.sql.Date(defect.getData().getTime()));
            preparedStatement.setString(i++, defect.getMarka());
            preparedStatement.setString(i++, defect.getModel());
            preparedStatement.setString(i++, defect.getRokProd());
            preparedStatement.setString(i++, defect.getOpis());
            preparedStatement.setInt(i++, defect.getStatus().getId());
            preparedStatement.setString(i++, defect.getCustomer().getName());
            preparedStatement.setString(i++, defect.getCustomer().getSurname());
            preparedStatement.setString(i++, defect.getCustomer().getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDefectsByMail(Connection connection, String mail) {
        try {
            String query = "delete from defects where mail = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, mail);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
