/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.TO_StatusDefects;
import Model.praca.RodzajUslugi;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import aplikacja.AplikacjaController;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import naprawa.przegladanie.NaprawaPrzegladanieController;
import swing.UITogglePanel;

/**
 *
 * @author jmaj
 */
public class PracaController extends AbstractController {

    private PracaPanel widok;

    private NaprawaPrzegladanieController nadrzednyController;

    private TO_Defect wybranyDefect;

    private ASeriveConntroller czesciController;
    private ASeriveConntroller materialyController;
    private ASeriveConntroller naprawaController;
    private ServiceListener podsumowanieController;

    private Map<RodzajUslugi, List> mapaUslug;

    UITogglePanel.TogglePanelOpenCloseListener listener = new UITogglePanel.TogglePanelOpenCloseListener() {

        @Override
        public void openCloseActionPerformed(UITogglePanel.OpenCloseActionEvent evt) {
        }
    };

    public PracaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public void initListners() {
        widok.getBtnZakonczPrace().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaZakonczPrace();
            }

            private void akcjaZakonczPrace() {
                wybranyDefect.setDataOddanie(new Date());
                wybranyDefect.setStatus(TO_StatusDefects.ZAKONCZONY);
                getDaoFactory().getDaoDefect().updateDefect(getConnection(), wybranyDefect);
                BaksSessionBean.getInstance().fireMessage(widok, "Praca", "Zakończono prace");
                akcjaWyjdz();
            }
        });

        widok.getBtnDrukuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaDrukuj();
            }

            private void akcjaDrukuj() {
            }
        });

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaWyjdz();
            }
        });
    }

    public void akcjaWyjdz() {
        nadrzednyController.przejdzDoZakladki(NaprawaPrzegladanieController.ZAKL_1);
    }

    public void initMapa() {
        mapaUslug = getDaoFactory().getDaoDefect().pobierzUslugi(getConnection(), wybranyDefect);
    }

    public void akcjaOtworzPrace() {
        initMapa();
        ustawRozwiniecieZakladek();

        podsumowanieController = new PodsumowanieController(getConnection(), getDaoFactory());
        ((PodsumowanieController) podsumowanieController).setWidok(((PodsumowaniePanel) widok.getTglPodsumowanie().getMainPanel()));
        ((PodsumowanieController) podsumowanieController).setWybranyDefect(wybranyDefect);

        czesciController = new CzescServiceController(getConnection(), getDaoFactory());
        czesciController.setWidok(((PracaZaplataPanel) widok.getTglCzesci().getMainPanel()));
        czesciController.initListeners();
        czesciController.init(new CzescTableModel(mapaUslug.get(RodzajUslugi.CZESC)), mapaUslug.get(RodzajUslugi.CZESC), wybranyDefect);
        czesciController.addFireServiceListener(podsumowanieController);

        materialyController = new MaterialServiceController(getConnection(), getDaoFactory());
        materialyController.setWidok(((PracaZaplataPanel) widok.getTglMaterialy().getMainPanel()));
        materialyController.initListeners();
        materialyController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.MATERIAL)), mapaUslug.get(RodzajUslugi.MATERIAL), wybranyDefect);
        materialyController.addFireServiceListener(podsumowanieController);

        naprawaController = new NaprawaServiceController(getConnection(), getDaoFactory());
        naprawaController.setWidok(((PracaZaplataPanel) widok.getTglKosztNaprawy().getMainPanel()));
        naprawaController.initListeners();
        naprawaController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.NAPRAWA)), mapaUslug.get(RodzajUslugi.NAPRAWA), wybranyDefect);
        naprawaController.addFireServiceListener(podsumowanieController);

        ((PodsumowanieController) podsumowanieController).initTableCzesci(((CzescTableModel) czesciController.getModel()).getList());
        ((PodsumowanieController) podsumowanieController).initTableNaprawa(((ServiceTableModel) naprawaController.getModel()).getList());
        ((PodsumowanieController) podsumowanieController).initTableMaterialy(((ServiceTableModel) materialyController.getModel()).getList());
        ((PodsumowanieController) podsumowanieController).obliczPodsumowanie();
        ((PodsumowanieController) podsumowanieController).initListener();
        wypelnijInfoOSpisie();

//        podsumowanieController = new PodsumowanieController();
//        ((PodsumowanieController) podsumowanieController).setWidok(((PodsumowaniePanel) widok.getTglPodsumowanie().getMainPanel()));
//        ((PodsumowanieController) podsumowanieController).setWybranyDefect(wybranyDefect);
//
//        czesciController = new CzescServiceController();
//        czesciController.setWidok(((PracaZaplataPanel) widok.getTglCzesci().getMainPanel()));
//        czesciController.initListeners();
//        czesciController.init(new CzescTableModel(mapaUslug.get(RodzajUslugi.CZESC)), mapaUslug.get(RodzajUslugi.CZESC), wybranyDefect);
//        czesciController.addFireServiceListener(podsumowanieController);
//
//        materialyController = new MaterialServiceController();
//        materialyController.setWidok(((PracaZaplataPanel) widok.getTglMaterialy().getMainPanel()));
//        materialyController.initListeners();
//        materialyController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.MATERIAL)), mapaUslug.get(RodzajUslugi.MATERIAL), wybranyDefect);
//        materialyController.addFireServiceListener(podsumowanieController);
//
//        naprawaController = new NaprawaServiceController();
//        naprawaController.setWidok(((PracaZaplataPanel) widok.getTglKosztNaprawy().getMainPanel()));
//        naprawaController.initListeners();
//        naprawaController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.NAPRAWA)), mapaUslug.get(RodzajUslugi.NAPRAWA), wybranyDefect);
//        naprawaController.addFireServiceListener(podsumowanieController);
//
//        ((PodsumowanieController) podsumowanieController).initTableCzesci(((CzescTableModel) czesciController.getModel()).getList());
//        ((PodsumowanieController) podsumowanieController).initTableNaprawa(((ServiceTableModel) naprawaController.getModel()).getList());
//        ((PodsumowanieController) podsumowanieController).initTableMaterialy(((ServiceTableModel) materialyController.getModel()).getList());
//        ((PodsumowanieController) podsumowanieController).obliczPodsumowanie();
//        ((PodsumowanieController) podsumowanieController).initListener();
//        wypelnijInfoOSpisie();
    }

    public PracaPanel getWidok() {
        return widok;
    }

    public void setWidok(PracaPanel widok) {
        this.widok = widok;
        for (UITogglePanel item : widok.getListaTglPanel()) {
            item.addTogglePanelOpenCloseListener(listener);
        }
    }

    public void ustawRozwiniecieZakladek() {
        widok.getTglCzesci().setOpen(false);
        widok.getTglKosztNaprawy().setOpen(false);
        widok.getTglMaterialy().setOpen(false);
        widok.getTglPodsumowanie().setOpen(false);
        widok.getTglInfoNaprawa().setOpen(false);
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
    }

    public TO_Defect getWybranyDefect() {
        return wybranyDefect;
    }

    public void setWybranyDefect(TO_Defect wybranyDefect) {
        this.wybranyDefect = wybranyDefect;
    }

    public Map<RodzajUslugi, List> getMapaUslug() {
        return mapaUslug;
    }

    public void setMapaUslug(Map<RodzajUslugi, List> mapaUslug) {
        this.mapaUslug = mapaUslug;
    }

    public void wypelnijInfoOSpisie() {
        widok.getTglInfoNaprawa().setTytul("INFORMACJE O NAPRAWIE - " + wybranyDefect.getInfoNaprawa());
        getInfoPracaPanel().getMarka().setText(wybranyDefect.getMarka());
        getInfoPracaPanel().getModel().setText(wybranyDefect.getModel());
        getInfoPracaPanel().getImie().setText(wybranyDefect.getCustomer().getName());
        getInfoPracaPanel().getNazwisko().setText(wybranyDefect.getCustomer().getSurname());
        getInfoPracaPanel().getNrTel().setText(wybranyDefect.getCustomer().getPhone());
        getInfoPracaPanel().getOpis().setText(wybranyDefect.getOpis());
    }

    public PracaInfoPanel getInfoPracaPanel() {
        return ((PracaInfoPanel) widok.getTglInfoNaprawa().getMainPanel());
    }

    public NaprawaPrzegladanieController getNadrzednyController() {
        return nadrzednyController;
    }

    public void setNadrzednyController(NaprawaPrzegladanieController nadrzednyController) {
        this.nadrzednyController = nadrzednyController;
    }

}
