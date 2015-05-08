/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adm.Baks;

import logowanie.LogowanieController;

/**
 *
 * @author jmaj
 */
public class Baks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(BaksSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Dimension screenSize = env.getMaximumWindowBounds().getSize();
//        Okno widok;
//        widok = new Okno();
//        widok.setPreferredSize(screenSize);
//        widok.setSize(screenSize);
//        widok.setAlwaysOnTop(false);
//        widok.setLocationRelativeTo(null);
//        widok.setResizable(true);
//        widok.getjPanelMain().setLayout(new GridBagLayout());
//        NaprawaPrzegladanieController naprawaConn = new NaprawaPrzegladanieController();
//        naprawaConn.initTest();
//        GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
//        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
//        gridBagConstraints.weightx = 1.0;
//        gridBagConstraints.weighty = 1.0;
//        widok.getjPanelMain().add(naprawaConn.getWidok(), gridBagConstraints);
//        widok.getjPanelMain().revalidate();
//        naprawaConn.getWidok().revalidate();
//
//        widok.setVisible(true);

        BaksSessionBean.getInstance();
        if (args.length > 0 && args[0].equals("-IDE")) {
            BaksSessionBean.setIsIDE(true);
        }
        if (args.length > 1 && args[1].equals("-NODB")) {
            BaksSessionBean.setNO_DB(true);
        }

//            List<TO_User> lista  = session.getBaksFactory().getDaoUser().getUserList(BaksSessionBean.getConnection());
        try {
            LogowanieController con = new LogowanieController(BaksSessionBean.getConnection(), BaksSessionBean.getBaksFactory());
        } catch (Exception ex) {
            BaksSessionBean.getInstance().fireMessage(null, "Aplikacja", "Brak połączenia z internetem");
            System.exit(0);
        }
    }

}
