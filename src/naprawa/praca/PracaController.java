/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
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

    private TO_Defect wybranyDefect;

    private ASeriveConntroller czesciController;
    private ASeriveConntroller materialyController;
    private ASeriveConntroller naprawaController;
    private ServiceListener podsumowanieController;

    private Map<RodzajUslugi, List> mapaUslug;

    UITogglePanel.TogglePanelOpenCloseListener listener = new UITogglePanel.TogglePanelOpenCloseListener() {

        @Override
        public void openCloseActionPerformed(UITogglePanel.OpenCloseActionEvent evt) {
            widok.getjScrollPane1().scrollRectToVisible(evt.getTogglePanel().getInternalPanel().getBounds());
        }
    };

//    private List<Czesc> listCzesci = new ArrayList<>();
//    private List<Material> listaMaterialow = new ArrayList<>();
//    private List<Naprawa> listaNapraw = new ArrayList<>();
    public PracaController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public void initMapa() {
        if (mapaUslug == null) {
            mapaUslug = new HashMap<>();
            mapaUslug.put(RodzajUslugi.CZESC, new ArrayList<Czesc>());
            mapaUslug.put(RodzajUslugi.MATERIAL, new ArrayList<Material>());
            mapaUslug.put(RodzajUslugi.NAPRAWA, new ArrayList<Naprawa>());
        } else {
            if (mapaUslug.containsKey(RodzajUslugi.CZESC)) {
                mapaUslug.put(RodzajUslugi.CZESC, new ArrayList<Czesc>());
            } else if (mapaUslug.containsKey(RodzajUslugi.MATERIAL)) {
                mapaUslug.put(RodzajUslugi.MATERIAL, new ArrayList<Material>());
            } else if (mapaUslug.containsKey(RodzajUslugi.NAPRAWA)) {
                mapaUslug.put(RodzajUslugi.NAPRAWA, new ArrayList<Naprawa>());
            }
        }
    }

    public void akcjaOtworzPrace() {
        initMapa();
        ustawRozwiniecieZakladek();

        podsumowanieController = new PodsumowanieController(((PodsumowaniePanel) widok.getTglPodsumowanie().getMainPanel()), getConnection(), getDaoFactory());
        ((PodsumowanieController) podsumowanieController).setListaUslug(mapaUslug);
        
        czesciController = new CzescServiceController(((PracaZaplataPanel) widok.getTglCzesci().getMainPanel()), getConnection(), getDaoFactory());
        czesciController.init(new CzescTableModel(mapaUslug.get(RodzajUslugi.CZESC)), mapaUslug.get(RodzajUslugi.CZESC), wybranyDefect);
        czesciController.addFireServiceListener(podsumowanieController);

        materialyController = new MaterialServiceController(((PracaZaplataPanel) widok.getTglMaterialy().getMainPanel()), getConnection(), getDaoFactory());
        materialyController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.MATERIAL)), mapaUslug.get(RodzajUslugi.MATERIAL), wybranyDefect);
        materialyController.addFireServiceListener(podsumowanieController);

        naprawaController = new NaprawaServiceController(((PracaZaplataPanel) widok.getTglKosztNaprawy().getMainPanel()), getConnection(), getDaoFactory());
        naprawaController.init(new ServiceTableModel(mapaUslug.get(RodzajUslugi.NAPRAWA)), mapaUslug.get(RodzajUslugi.NAPRAWA), wybranyDefect);
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

//    public List<Czesc> getListCzesci() {
//        return listCzesci;
//    }
//
//    public void setListCzesci(List<Czesc> listCzesci) {
//        this.listCzesci = listCzesci;
//    }
//
//    public List<Material> getListaMaterialow() {
//        return listaMaterialow;
//    }
//
//    public void setListaMaterialow(List<Material> listaMaterialow) {
//        this.listaMaterialow = listaMaterialow;
//    }
//
//    public List<Naprawa> getListaNapraw() {
//        return listaNapraw;
//    }
//
//    public void setListaNapraw(List<Naprawa> listaNapraw) {
//        this.listaNapraw = listaNapraw;
//    }
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

}
