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

        BaksSessionBean session = BaksSessionBean.getInstance();

//            List<TO_User> lista  = session.getBaksFactory().getDaoUser().getUserList(BaksSessionBean.getConnection());
        try {
            LogowanieController con = new LogowanieController(BaksSessionBean.getConnection(), BaksSessionBean.getBaksFactory());
        } catch (Exception ex) {
            BaksSessionBean.getInstance().fireMessage(null, "Aplikacja", "Brak połączenia z internetem");
        }
    }

}
