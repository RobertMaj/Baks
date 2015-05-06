/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adm.Baks;

import Model.TO_Permission;
import Model.TO_User;
import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import dao.DaoFactory;
import dao.DaoFactoryImpl;
import db.DatabaseConn;
import java.awt.Color;
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
    
    public static Color BUTTON_PANEL_COLOR;
    public static Color MAIN_BACKGROUND_PANEL_COLOR;
    public static Color PANEL_BACKGROUND_PANEL_COLOR;
    public static Color BORDER_COLOR;
    public static Color BROWN_CIEM_COLOR;
    public static Color BROWN_COLOR;
    public static Color GREEN_CIEM_COLOR;
    public static Color GREEN_COLOR;
    

    private static boolean isIDE = false;

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

            input = new FileInputStream("BinSrc/Baks.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            dbName = prop.getProperty("dbName");
            BUTTON_PANEL_COLOR = Color.decode(prop.getProperty("panelBtnColor"));
            MAIN_BACKGROUND_PANEL_COLOR = Color.decode(prop.getProperty("mainBackgroundColor"));
            PANEL_BACKGROUND_PANEL_COLOR = Color.decode(prop.getProperty("panelBackgroundColor"));
            BORDER_COLOR = Color.decode(prop.getProperty("tabPaneBorderColor"));
            BROWN_CIEM_COLOR = Color.decode(prop.getProperty("brownCiemny"));
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

    public static boolean isIsIDE() {
        return isIDE;
    }

    public static void setIsIDE(boolean isIDE) {
        BaksSessionBean.isIDE = isIDE;
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
