/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adm.Baks;

import aplikacja.AplikacjaController;
import dao.DaoFactory;
import dao.DaoFactoryImpl;
import java.sql.Connection;

/**
 *
 * @author Robert M
 */
public abstract class AbstractController {

    private Connection connection;
    private DaoFactory daoFactory;

    public AbstractController(Connection connection, DaoFactory daoFactory) {
        this.connection = BaksSessionBean.getConnection();
        this.daoFactory = new DaoFactoryImpl();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public abstract void czytajFormatke();

    public abstract void wypelnijFormatke();

    public void akcjaRezygnuj() {
        if (AplikacjaController.widok.getjPanelMain().getComponentCount() > 0) {
            AplikacjaController.widok.getjPanelMain().remove(0);
            AplikacjaController.widok.getjPanelMain().revalidate();
            AplikacjaController.widok.getjPanelMain().repaint();
        }
    }

}
