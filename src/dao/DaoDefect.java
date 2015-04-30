/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.TO_Defect;
import Model.TO_Termin;
import Model.TO_User;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert M
 */
public class DaoDefect {

    public List<TO_Defect> getDefectListByUser(Connection connection, TO_User user) {
        List<TO_Defect> list = new ArrayList<>();
        try {

            String sql = "Select * from defects where mail = ? and data_oddania is null ";

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
                defect.setKoszt(rs.getFloat("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setTermin(TO_Termin.getTerminByLp(rs.getInt("termin_nr")));
                defect.setDataOddanie(rs.getDate("data_oddania"));
                defect.setCustomer(null);

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
                defect.setKoszt(rs.getFloat("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setTermin(TO_Termin.getTerminByLp(rs.getInt("termin_nr")));
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
                defect.setKoszt(rs.getFloat("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setTermin(TO_Termin.getTerminByLp(rs.getInt("termin_nr")));
                defect.setDataOddanie(rs.getDate("data_oddania"));

                list.add(defect);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
                defect.setKoszt(rs.getFloat("koszt"));
                defect.setOpis(rs.getString("opis"));
                defect.setTermin(TO_Termin.getTerminByLp(rs.getInt("termin_nr")));
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
            String query = "update defects set marka = ?, model = ?, rok_prod = ?, termin_nr = ?, opis = ?, data = ?, koszt = ? where id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, defect.getMarka());
            preparedStatement.setString(i++, defect.getModel());
            preparedStatement.setString(i++, defect.getRokProd());
            preparedStatement.setInt(i++, defect.getTermin().getLp());
            preparedStatement.setString(i++, defect.getOpis());
            preparedStatement.setFloat(i++, defect.getKoszt());
            preparedStatement.setDate(i++, new java.sql.Date(defect.getData().getTime()));
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
            preparedStatement.setFloat(i++, defect.getKoszt());
            preparedStatement.setInt(i++, defect.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteDefect(Connection connection, TO_Defect defect) {
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
