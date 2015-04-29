/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.TO_Permission;
import Model.TO_User;
import java.sql.Connection;
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
public class DaoUser {

    public List<TO_User> getUserList(Connection connection) {
        List<TO_User> list = new ArrayList<>();
        try {

            String sql = "Select * from users ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                TO_User user = new TO_User();
                user.setMail(rs.getString("mail"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("passwordUser"));
                user.setPermission(TO_Permission.getPermissonById(rs.getInt("id_permission")));

                list.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public TO_User getUser(Connection connection, String mail) {
        TO_User user = new TO_User();
        try {

            String sql = "Select * from users where mail = ? ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, mail);

            ResultSet rs = prepStmt.executeQuery();

            while (rs.next()) {
                user.setMail(rs.getString("mail"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPhone(rs.getString("phone"));
                user.setPassword(rs.getString("passwordUser"));
                user.setPermission(TO_Permission.getPermissonById(rs.getInt("id_permission")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void updateHaslo(Connection connection, TO_User user) {
        try {
            String query = "update users set passwordUser = ? where mail = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, user.getPasswordReap());
            preparedStatement.setString(i++, user.getMail());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void updateUser(Connection connection, TO_User user) {
        try {
            String query = "update users set mail = ?, name = ?, surname = ?, phone = ?, passwordUser = ? where mail = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, user.getMail());
            preparedStatement.setString(i++, user.getName());
            preparedStatement.setString(i++, user.getSurname());
            preparedStatement.setString(i++, user.getPhone());
            preparedStatement.setString(i++, user.getPassword());
            preparedStatement.setString(i++, user.getMail());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saveUser(Connection connection, TO_User user) {
        try {
            String query = "insert into users values (?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, user.getMail());
            preparedStatement.setString(i++, user.getName());
            preparedStatement.setString(i++, user.getSurname());
            preparedStatement.setString(i++, user.getPhone());
            preparedStatement.setInt(i++, user.getPermission().getId());
            preparedStatement.setString(i++, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUser(Connection connection, TO_User user) {
        try {
            String query = "delete from users where mail = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int i = 1;
            preparedStatement.setString(i++, user.getMail());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPasswordByEmail(Connection connection, String email) {
        try {
            String query = "select passworduser from users where mail = ?";
            ResultSet resultSet = null;
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TO_User deleteUser(Connection connection, String mail) {
        TO_User user = new TO_User();
        try {

            String sql = "delete from users where mail = ? ";

            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, mail);

            prepStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}
