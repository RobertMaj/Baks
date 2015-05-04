/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikacja;

import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import dao.DaoFactory;
import dodajUzytkownika.DodajUzytkownikaController;
import historia.HistoriaController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import logowanie.LogowanieController;
import naprawa.przegladanie.NaprawaPrzegladanieController;
import naprawa.zapisanie.NaprawaZapisController;
import platnosci.WyszukPlatnosciController;
import praca.PracaController;
import zarzadzanieUzytkownikiem.EdycjaUzytkownikaController;
import zarzadzanieUzytkownikiem.ZarzadzanieUzytkownikiemController;
import zmianaHasla.ZmianaHaslaController;

/**
 *
 * @author Robert M
 */
public class AplikacjaController extends AbstractController {

    public static Okno widok;

    public AplikacjaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        widok = new Okno();
        widok.setAlwaysOnTop(true);
        widok.setLocationRelativeTo(null);
        widok.setResizable(false);

        if (!BaksSessionBean.isAdministrator()) {
            widok.getUzytkownicyAdm().setVisible(false);
        }

        if (BaksSessionBean.isPracownik()) {
            widok.getHistoriaMenu().setVisible(false);
        }

        if (BaksSessionBean.isUzytkownik()) {
            widok.getPracaMenu().setVisible(false);
        }

        if (BaksSessionBean.isPracownik()) {
            widok.getNaprawaMenu().setVisible(false);
        }

        widok.getZapiszMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                zapiszNapraweInit();
            }
        });

        widok.getPrzegladajMenuItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                przegladajNaprawyInit();
            }
        });

        widok.getWylogujItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    getConnection().close();
                } catch (SQLException ex) {
                    Logger.getLogger(LogowanieController.class.getName()).log(Level.SEVERE, null, ex);
                }
                widok.dispose();
            }
        });

        widok.getDaneUzytkownikowAdmItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initZarzadzajUzytkownikami();
            }
        });

        widok.getZmianaHaslaItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initZmianaHasla();
            }
        });

        widok.getEdytujDaneItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initEdycjaUser();
            }
        });

        widok.getPrzegladajHistItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initHistoria();
            }
        });

        widok.getDodajUzytkownikaItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initDodajUzytk();
            }
        });

        widok.getPracaItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initPraca();
            }
        });

        widok.getFakturaItem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fakturaPrzegladajInit();
            }
        });

        widok.setVisible(true);
    }

    private void fakturaPrzegladajInit() {
        akcjaRezygnuj();
        WyszukPlatnosciController controller = new WyszukPlatnosciController(getConnection(), getDaoFactory());
        initWidok(controller.getPane());
    }

    private void przegladajNaprawyInit() {
        akcjaRezygnuj();
        NaprawaPrzegladanieController controller = new NaprawaPrzegladanieController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void zapiszNapraweInit() {
        akcjaRezygnuj();
        NaprawaZapisController controller = new NaprawaZapisController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initZarzadzajUzytkownikami() {
        akcjaRezygnuj();
        ZarzadzanieUzytkownikiemController controller = new ZarzadzanieUzytkownikiemController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initZmianaHasla() {
        akcjaRezygnuj();
        ZmianaHaslaController controller = new ZmianaHaslaController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initEdycjaUser() {
        akcjaRezygnuj();
        EdycjaUzytkownikaController controller = new EdycjaUzytkownikaController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initWidok(JPanel panel) {
        panel.setSize(1050, 645);
        widok.getjPanelMain().add(panel);
        widok.getjPanelMain().revalidate();
        panel.revalidate();
    }

    private void initWidok(JTabbedPane panel) {
        panel.setSize(1050, 645);
        widok.getjPanelMain().add(panel);
        widok.getjPanelMain().revalidate();
        panel.revalidate();
    }

    private void initDodajUzytk() {
        akcjaRezygnuj();
        DodajUzytkownikaController controller = new DodajUzytkownikaController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initHistoria() {
        akcjaRezygnuj();
        HistoriaController controller = new HistoriaController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    private void initPraca() {
        akcjaRezygnuj();
        PracaController controller = new PracaController(getConnection(), getDaoFactory());
        initWidok(controller.getWidok());
    }

    @Override
    public void czytajFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void wypelnijFormatke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
