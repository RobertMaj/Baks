/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import Model.praca.RodzajUslugi;
import Model.praca.Usluga;
import adm.Baks.AbstractController;
import dao.DaoFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import swing.UITogglePanel;

/**
 *
 * @author jmaj
 */
public class PracaController extends AbstractController {

    private PracaPanel widok;

    private ASeriveConntroller czesciController;
    private ASeriveConntroller materialyController;
    private ASeriveConntroller naprawaController;
    private ServiceListener podsumowanieController;

    private Map<RodzajUslugi, List<Usluga>> mapaUslug = null;

    UITogglePanel.TogglePanelOpenCloseListener listener = new UITogglePanel.TogglePanelOpenCloseListener() {

        @Override
        public void openCloseActionPerformed(UITogglePanel.OpenCloseActionEvent evt) {
            widok.getjScrollPane1().scrollRectToVisible(evt.getTogglePanel().getInternalPanel().getBounds());
        }
    };

    private List<Czesc> listCzesci = new ArrayList<>();
    private List<Material> listaMaterialow = new ArrayList<>();
    private List<Naprawa> listaNapraw = new ArrayList<>();

    public PracaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        this.mapaUslug = new HashMap<>();
    }

    public void akcjaOtworzPrace() {
        ustawRozwiniecieZakladek();

        podsumowanieController = new PodsumowanieConntroller(((PodsumowaniePanel) widok.getTglPodsumowanie().getMainPanel()), getConnection(), getDaoFactory());

        czesciController = new ASeriveConntrollerImpl(((PracaZaplataPanel) widok.getTglCzesci().getMainPanel()), getConnection(), getDaoFactory());
        czesciController.setListaUslug(mapaUslug.get(RodzajUslugi.CZESC));
        czesciController.addFireServiceListener(podsumowanieController);

        materialyController = new ASeriveConntrollerImpl(((PracaZaplataPanel) widok.getTglMaterialy().getMainPanel()), getConnection(), getDaoFactory());
        materialyController.setListaUslug(mapaUslug.get(RodzajUslugi.MATERIAL));
        materialyController.addFireServiceListener(podsumowanieController);

        naprawaController = new ASeriveConntrollerImpl(((PracaZaplataPanel) widok.getTglKosztNaprawy().getMainPanel()), getConnection(), getDaoFactory());
        naprawaController.setListaUslug(mapaUslug.get(RodzajUslugi.NAPRAWA));
        naprawaController.addFireServiceListener(podsumowanieController);
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
    }

    public List<Czesc> getListCzesci() {
        return listCzesci;
    }

    public void setListCzesci(List<Czesc> listCzesci) {
        this.listCzesci = listCzesci;
    }

    public List<Material> getListaMaterialow() {
        return listaMaterialow;
    }

    public void setListaMaterialow(List<Material> listaMaterialow) {
        this.listaMaterialow = listaMaterialow;
    }

    public List<Naprawa> getListaNapraw() {
        return listaNapraw;
    }

    public void setListaNapraw(List<Naprawa> listaNapraw) {
        this.listaNapraw = listaNapraw;
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
    }

}
