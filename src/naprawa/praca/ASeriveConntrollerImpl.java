/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import dao.DaoFactory;
import java.sql.Connection;

public class ASeriveConntrollerImpl extends ASeriveConntroller {

    public ASeriveConntrollerImpl(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public ASeriveConntrollerImpl(PracaZaplataPanel widok, Connection connection, DaoFactory daoFactory) {
        super(widok, connection, daoFactory);
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
    }

}
