/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import java.util.ArrayList;
import java.util.List;
import swing.UITogglePanel;

/**
 *
 * @author jmaj
 */
public class PracaController {

    private PracaPanel widok;

    UITogglePanel.TogglePanelOpenCloseListener listener = new UITogglePanel.TogglePanelOpenCloseListener() {

        @Override
        public void openCloseActionPerformed(UITogglePanel.OpenCloseActionEvent evt) {
            widok.getjScrollPane1().scrollRectToVisible(evt.getTogglePanel().getInternalPanel().getBounds());
        }
    };

    private List<Czesc> listCzesci = new ArrayList<>();
    private List<Material> listaMaterialow = new ArrayList<>();
    private List<Naprawa> listaNapraw = new ArrayList<>();

    public PracaController() {
        init();
        for (UITogglePanel item : widok.getListaTglPanel()) {
            item.addTogglePanelOpenCloseListener(listener);
        }
    }

    private void init() {
        widok = new PracaPanel();
    }

    public void akcjaOtworzPrace() {
        ustawRozwiniecieZakladek();
    }

    public PracaPanel getWidok() {
        return widok;
    }

    public void setWidok(PracaPanel widok) {
        this.widok = widok;
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

}
