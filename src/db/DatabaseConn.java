/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import adm.Baks.BaksSessionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert M
 */
public class DatabaseConn {

    private static DatabaseConn instance;
    private static Connection conn;

    private DatabaseConn() {

    }

    public static DatabaseConn getInstance() {
        if (instance == null) {
            try {
                String dbName = BaksSessionBean.getInstance().getDbName();
                instance = new DatabaseConn();
                Class.forName("org.postgresql.Driver");
                try {
                    conn = DriverManager.getConnection("jdbc:postgresql://" + dbName + "&password=j8XgZjA_v8Syhh6uO98x-IAJGB&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
                    BaksSessionBean.getInstance().fireMessage(null, "Aplikacja", "Brak połączenia z internetem");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
                BaksSessionBean.getInstance().fireMessage(null, "Aplikacja", "Brak połączenia z internetem");
            }
        }
        return instance;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void setConn(Connection conn) {
        DatabaseConn.conn = conn;
    }

}
