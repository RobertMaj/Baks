/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adm.Baks;

import Model.TO_Permission;
import Model.TO_User;
import dao.DaoFactory;
import dao.DaoFactoryImpl;
import db.DatabaseConn;
import java.awt.Component;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jmaj
 */
public class BaksSessionBean {

    private static BaksSessionBean instance;
    private static TO_User user;
    private static DaoFactory baksFactory;
    private static String dbName;

    private BaksSessionBean() {
        init();
    }

    public static BaksSessionBean getInstance() {
        if (instance == null) {
            instance = new BaksSessionBean();
        }
        return instance;
    }

    private void init() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        readProperties();
    }

    public void readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/adm/Baks/Baks.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            dbName = prop.getProperty("dbName");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static DaoFactory getBaksFactory() {
        if (baksFactory == null) {
            baksFactory = new DaoFactoryImpl();
        }
        return baksFactory;
    }

    public static Connection getConnection() {
        return DatabaseConn.getInstance().getConn();
    }

    public static TO_User getUser() {
        return user;
    }

    public static void setUser(TO_User user) {
        BaksSessionBean.user = user;
    }

    public void fireMessage(Component cmp, String title, String message) {
        JOptionPane.showMessageDialog(cmp, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean isAdministrator() {
        return BaksSessionBean.user.getPermission().equals(TO_Permission.ADMINISTRATOR);
    }

    public static boolean isPracownik() {
        return BaksSessionBean.user.getPermission().equals(TO_Permission.PRACOWNIK);
    }

    public static boolean isUzytkownik() {
        return BaksSessionBean.user.getPermission().equals(TO_Permission.UZYTKOWNIK);
    }

    public static String getDbName() {
        return dbName;
    }

    public static void setDbName(String dbName) {
        BaksSessionBean.dbName = dbName;
    }
}
