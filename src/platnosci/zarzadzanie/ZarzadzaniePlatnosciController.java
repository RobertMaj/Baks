/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platnosci.zarzadzanie;

import Model.TO_Invoice;
import Model.TO_InvoiceStatus;
import Model.TO_PaymentCompany;
import baks.adm.AbstractController;
import baks.adm.BaksSessionBean;
import dao.DaoFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import platnosci.DodanieFirmyDialog;
import platnosci.WyszukPlatnosciController;
import static platnosci.WyszukPlatnosciController.ZAKL_1;
import static platnosci.WyszukPlatnosciController.ZAKL_2;

/**
 *
 * @author RobertM
 */
public class ZarzadzaniePlatnosciController extends AbstractController {

    private ZarzadzaniePlatnosciaPanel widok;
    private DodanieFirmyDialog dialog;
    private WyszukPlatnosciController nadrzednyController;

    private TO_Invoice faktura;

    public ZarzadzaniePlatnosciController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    public void initCon(WyszukPlatnosciController nadrzednyController, ZarzadzaniePlatnosciaPanel widok) {
        this.nadrzednyController = nadrzednyController;
        this.widok = widok;
        init();
    }

    private void init() {
        widok.getBtnEdytuj().setVisible(false);
        widok.getRadioNieZaplacone().setSelected(true);
        wypelnijCombo();
        widok.getBtnDodajFirme().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dialog = new DodanieFirmyDialog(null, true);
                dialog.initConnection(getConnection(), getDaoFactory());
                dialog.setAlwaysOnTop(true);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                wypelnijCombo();
            }
        });

        widok.getBtnEdytuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        widok.getBtnZapisz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (nadrzednyController.getAktualnyTryb() == WyszukPlatnosciController.DODANIE) {
                        akcjaZapiszNowy();
                    } else if (nadrzednyController.getAktualnyTryb() == WyszukPlatnosciController.EDYCJA) {
                        akcjaEdytuj();
                    }
                } catch (Exception ex) {
                    return;
                }

                BaksSessionBean.getInstance().fireMessage(widok, "Dodanie faktury", "Poprawnie zapisano fakture");
                nadrzednyController.przejdzDoZakladki(WyszukPlatnosciController.ZAKL_1);
                czyscFormatke();

                akcjaWyjdz();
                nadrzednyController.akcjaSzukaj();
            }
        });

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaWyjdz();
            }
        });

    }

    public List<TO_PaymentCompany> getListaFirm() {
        return getDaoFactory().getDaoInvoice().getListaFirm(getConnection());
    }

    public void wypelnijCombo() {
        widok.getFirmaComboBox().removeAllItems();
        for (TO_PaymentCompany t : getListaFirm()) {
            widok.getFirmaComboBox().addItem(t);
        }
    }

    public void akcjaWyjdz() {
        czyscFormatke();
        nadrzednyController.setEnabledZakl(ZAKL_1, true);
        nadrzednyController.setEnabledZakl(ZAKL_2, false);
        nadrzednyController.przejdzDoZakladki(ZAKL_1);
    }

    public void setSelectedCompanyCombo(TO_PaymentCompany comp) {
        for (int i = 0; i < widok.getFirmaComboBox().getModel().getSize(); i++) {
            if (((TO_PaymentCompany) widok.getFirmaComboBox().getItemAt(i)).getName().equals(comp.getName())) {
                widok.getFirmaComboBox().setSelectedIndex(i);
                return;
            }
        }
    }

    public void akcjaZapiszNowy() {
        czytajFormatke();
        getDaoFactory().getDaoInvoice().dodajFakture(getConnection(), faktura);
    }

    public void akcjaEdytuj() {
        czytajFormatke();
        getDaoFactory().getDaoInvoice().aktualizujFakture(getConnection(), faktura);
    }

    @Override
    public void czytajFormatke() {
        if (nadrzednyController.getAktualnyTryb() == WyszukPlatnosciController.DODANIE) {
            faktura = new TO_Invoice();
        } else if (nadrzednyController.getAktualnyTryb() == WyszukPlatnosciController.EDYCJA) {
            faktura.setDataZaplacenia(widok.getDataZaplatyChooser().getDate());
        }
        faktura.setPaymentCompany(((TO_PaymentCompany) widok.getFirmaComboBox().getSelectedItem()));
        if (faktura.getPaymentCompany() == null || faktura.getPaymentCompany().getId() == null) {
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Błażej wariancie, wybierz firmę!");
            return;
        }
        faktura.setTerminPlatnosc(widok.getTerminPlatnosciDate().getDate());
        faktura.setIdentyfikator(widok.getNrFakturyText().getText());
        try {
            faktura.setKoszt(Double.parseDouble(widok.getKwotaText().getText()));
        } catch (NumberFormatException ex) {
            BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Błażej wariancie, prawdopodobnie wpisałeś kwote z przecinkiem. Użyj kropk!");
            return;
        }
        faktura.setStatus(ustawStatusByRadioBtn());

    }

    public TO_InvoiceStatus ustawStatusByRadioBtn() {
        if (widok.getRadioZaplacone().isSelected()) {
            return TO_InvoiceStatus.ZAPLACONA;
        } else if (widok.getRadioNieZaplacone().isSelected()) {
            return TO_InvoiceStatus.NIE_ZAPLACONA;
        } else if (widok.getRadioAnulowane().isSelected()) {
            return TO_InvoiceStatus.ANULOWANA;
        }
        return null;
    }

    public void setSelectedRadioByStatus(TO_InvoiceStatus status) {
        if (status.equals(TO_InvoiceStatus.ZAPLACONA)) {
            widok.getRadioZaplacone().setSelected(true);
        } else if (status.equals(TO_InvoiceStatus.NIE_ZAPLACONA)) {
            widok.getRadioNieZaplacone().setSelected(true);
        } else if (status.equals(TO_InvoiceStatus.ANULOWANA)) {
            widok.getRadioAnulowane().setSelected(true);
        }
    }

    @Override
    public void wypelnijFormatke() {
        widok.getTerminPlatnosciDate().setDate(nadrzednyController.getWybranaFaktura().getTerminPlatnosc());
        widok.getKwotaText().setText(nadrzednyController.getWybranaFaktura().getKoszt().toString());
        setSelectedRadioByStatus(nadrzednyController.getWybranaFaktura().getStatus());
        widok.getNrFakturyText().setText(nadrzednyController.getWybranaFaktura().getIdentyfikator());
        setSelectedCompanyCombo(nadrzednyController.getWybranaFaktura().getPaymentCompany());
    }

    public void czyscFormatke() {
        widok.getRadioNieZaplacone().setSelected(true);
        widok.getKwotaText().setText(null);
        widok.getTerminPlatnosciDate().setDate(new Date());
        widok.getNrFakturyText().setText(null);
    }

    public ZarzadzaniePlatnosciaPanel getWidok() {
        return widok;
    }

    public void setWidok(ZarzadzaniePlatnosciaPanel widok) {
        this.widok = widok;
    }

    public TO_Invoice getFaktura() {
        return faktura;
    }

    public void setFaktura(TO_Invoice faktura) {
        this.faktura = faktura;
    }

}
