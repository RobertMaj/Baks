/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platnosci;

import Model.TO_Invoice;
import Model.TO_InvoiceStatus;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.DaoFactory;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import javax.swing.table.TableColumn;
import platnosci.zarzadzanie.Drukowanie;
import platnosci.zarzadzanie.ZarzadzaniePlatnosciController;
import platnosci.zarzadzanie.ZarzadzaniePlatnosciaPanel;

/**
 *
 * @author Robert M
 */
public class WyszukPlatnosciController extends AbstractController {

    private TablePaymentsModel modelTable;
    private PrzegladaniePlatnosciPanel widok;
    private ZarzadzaniePlatnosciaPanel widokZarz;
    private List<TO_Invoice> lista = new ArrayList<>();

    private ZarzadzaniePlatnosciController controllerZarzadzanie;

    private TO_Invoice kryteriaWyszuk = new TO_Invoice();

    private TO_Invoice wybranaFaktura;

    public static final int PRZEGLADANIE = 0;
    public static final int EDYCJA = 1;
    public static final int DODANIE = 2;

    public static final int ZAKL_1 = 0;
    public static final int ZAKL_2 = 1;

    public static boolean rosnaco = true;

    public static final int TERMIN_SORTOWANIE = 0;
    public static final int KWOTA_SORTOWANIE = 1;

    public static int trybSortowania = TERMIN_SORTOWANIE;
    private JTabbedPane pane;

    private int aktualnyTryb = PRZEGLADANIE;

    private ActionListener radioButtonListener;

    private ActionListener radioSortowanieListner;

    private ActionListener radioRosnacoMalejacoListner;

    public WyszukPlatnosciController(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
        init();
    }

    private void init() {
        setWidok(new PrzegladaniePlatnosciPanel());
        widokZarz = new ZarzadzaniePlatnosciaPanel();
        pane = new JTabbedPane();
        pane.add("Wyszukiwanie", widok);
        pane.add("Zarządzanie płatnością", widokZarz);
        setEnabledZakl(ZAKL_2, false);
        widok.getRadioNieZaplacone().setSelected(true);
        widok.getRadioTerminSortowanie().setSelected(true);
        widok.getRadioRosnacoTermin().setSelected(true);
        widok.getRadioKwotaRosnaco().setSelected(true);
        fillTable();
        setAktualnyTryb(PRZEGLADANIE);
        ustawEnableSortowanie();
        kryteriaWyszuk.setStatus(TO_InvoiceStatus.NIE_ZAPLACONA);
        initListener();
        widok.getBtnDrukuj().setVisible(false);

        widok.getBtnDrukuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaDrukuj();
            }
        });

        widok.getTable().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                wybranaFaktura = ((TablePaymentsModel) widok.getTable().getModel()).getInvoiceAt(widok.getTable().getSelectedRow());
                wypelnijFormatke();
                if (e.getClickCount() == 2) {
                    akcjaWybierz();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        widok.getBtnSzukaj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaSzukaj();
            }
        });

        widok.getBtnWybierz().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaWybierz();
            }
        });

        widok.getBtnUsun().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaUsun();
            }
        });

        widok.getBtnRezygnuj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaRezygnuj();
            }
        });

        widok.getBtnDodaj().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                akcjaDodajNowy();
            }
        });

        widok.getRadioAnulowane().addActionListener(radioButtonListener);
        widok.getRadioNieZaplacone().addActionListener(radioButtonListener);
        widok.getRadioZaplacone().addActionListener(radioButtonListener);

        widok.getRadioTerminSortowanie().addActionListener(radioSortowanieListner);
        widok.getRadioKwotaSortowanie().addActionListener(radioSortowanieListner);

        widok.getRadioKwotaMalejaco().addActionListener(radioRosnacoMalejacoListner);
        widok.getRadioKwotaRosnaco().addActionListener(radioRosnacoMalejacoListner);

        widok.getRadioMalejacoTermin().addActionListener(radioRosnacoMalejacoListner);
        widok.getRadioRosnacoTermin().addActionListener(radioRosnacoMalejacoListner);
    }

    private void initListener() {
        radioButtonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (widok.getRadioAnulowane().isSelected()) {
                    kryteriaWyszuk.setStatus(TO_InvoiceStatus.ANULOWANA);
                } else if (widok.getRadioNieZaplacone().isSelected()) {
                    kryteriaWyszuk.setStatus(TO_InvoiceStatus.NIE_ZAPLACONA);
                } else if (widok.getRadioZaplacone().isSelected()) {
                    kryteriaWyszuk.setStatus(TO_InvoiceStatus.ZAPLACONA);
                }
            }
        };

        radioSortowanieListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (widok.getRadioTerminSortowanie().isSelected()) {
                    trybSortowania = TERMIN_SORTOWANIE;
                } else if (widok.getRadioKwotaSortowanie().isSelected()) {
                    trybSortowania = KWOTA_SORTOWANIE;
                }
                ustawEnableSortowanie();
                ustawRadioSzukaj();
            }
        };

        radioRosnacoMalejacoListner = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ustawRadioSzukaj();
            }
        };
    }

    public void ustawRadioSzukaj() {
        if (widok.getRadioKwotaMalejaco().isSelected()
                || widok.getRadioMalejacoTermin().isSelected()) {
            rosnaco = false;
        } else if (widok.getRadioKwotaRosnaco().isSelected()
                || widok.getRadioRosnacoTermin().isSelected()) {
            rosnaco = true;
        }
        akcjaSzukaj();
    }

    public void ustawEnableSortowanie() {
        if (trybSortowania == TERMIN_SORTOWANIE) {
            widok.getRadioKwotaMalejaco().setEnabled(false);
            widok.getRadioKwotaRosnaco().setEnabled(false);
            widok.getRadioMalejacoTermin().setEnabled(true);
            widok.getRadioRosnacoTermin().setEnabled(true);
        } else if (trybSortowania == KWOTA_SORTOWANIE) {
            widok.getRadioKwotaMalejaco().setEnabled(true);
            widok.getRadioKwotaRosnaco().setEnabled(true);
            widok.getRadioMalejacoTermin().setEnabled(false);
            widok.getRadioRosnacoTermin().setEnabled(false);
        }
    }

    public void akcjaWybierz() {
        try {
            wybranaFaktura = ((TablePaymentsModel) widok.getTable().getModel()).getInvoiceAt(widok.getTable().getSelectedRow());
        } catch (ArrayIndexOutOfBoundsException ex) {
            BaksSessionBean.getInstance().fireMessage(widok, "Edycja", "Brak faktur do edycji");
            return;
        }
        wypelnijFormatke();
        if (wybranaFaktura == null) {
            BaksSessionBean.getInstance().fireMessage(widok, "Edycja", "Wybierz fakture do edycji");
            return;
        }
        wypelnijFormatke();
        setAktualnyTryb(EDYCJA);
        setEnabledZakl(ZAKL_1, false);
        setEnabledZakl(ZAKL_2, true);
        if (controllerZarzadzanie == null) {
            controllerZarzadzanie = new ZarzadzaniePlatnosciController(getConnection(), getDaoFactory());
            controllerZarzadzanie.initCon(this, widokZarz);
        }
        controllerZarzadzanie.setFaktura(wybranaFaktura);
        controllerZarzadzanie.ustawWidocznoscKontrolek();
        przejdzDoZakladki(ZAKL_2);
    }

    public void akcjaUsun() {
        try {
            wybranaFaktura = ((TablePaymentsModel) widok.getTable().getModel()).getInvoiceAt(widok.getTable().getSelectedRow());
        } catch (ArrayIndexOutOfBoundsException ex) {
            BaksSessionBean.getInstance().fireMessage(widok, "Edycja", "Brak faktur do usunięcia.");
            return;
        }
        if (wybranaFaktura == null) {
            BaksSessionBean.getInstance().fireMessage(widok, "Usuwanie", "Wybierz fakture do usuniecia");
            return;
        }
        try {
            getDaoFactory().getDaoInvoice().usunFakture(getConnection(), wybranaFaktura);
        } catch (Exception ex) {
            BaksSessionBean.getInstance().fireMessage(widok, "Usuwanie", "Nie udało się usunąć faktury");
            return;
        }
        BaksSessionBean.getInstance().fireMessage(widok, "Usuwanie", "Usunięto fakturę");
        wybranaFaktura = null;
        akcjaSzukaj();
    }

    public void akcjaSzukaj() {
        lista = getDaoFactory().getDaoInvoice().getListaInvoice(getConnection(), kryteriaWyszuk);
        if (lista == null || lista.isEmpty()) {
            BaksSessionBean.getInstance().fireMessage(widok, "Wyszukiwanie", "Nie znaleziono faktur o danych parametrach.");
        }
        fillTable();
        ustawDoZaplaty();
    }

    public void ustawDoZaplaty() {
        if (kryteriaWyszuk != null) {
            if (kryteriaWyszuk.getStatus().equals(TO_InvoiceStatus.ZAPLACONA)) {
                widok.getLabelDoZaplaty().setText("Suma zapłaconych");
                widok.getBtnDrukuj().setVisible(true);
            } else if (kryteriaWyszuk.getStatus().equals(TO_InvoiceStatus.NIE_ZAPLACONA)) {
                widok.getLabelDoZaplaty().setText("Suma do zapłaty");
                widok.getBtnDrukuj().setVisible(false);
            } else {
                widok.getLabelDoZaplaty().setVisible(false);
                widok.getDoZaplatyText().setVisible(false);
                widok.getZlLabel().setVisible(false);
                return;
            }
        }
        widok.getLabelDoZaplaty().setVisible(true);
        widok.getDoZaplatyText().setVisible(true);
        widok.getZlLabel().setVisible(true);
        Double suma = 0.0;
        if (lista != null && !lista.isEmpty()) {
            for (TO_Invoice item : lista) {
                suma += item.getKoszt();
            }
        }
        widok.getDoZaplatyText().setText(TO_Invoice.getWynikSumaKoszt(suma));
    }

    public void akcjaDodajNowy() {
        setAktualnyTryb(DODANIE);
        setEnabledZakl(ZAKL_1, false);
        setEnabledZakl(ZAKL_2, true);
        if (controllerZarzadzanie == null) {
            controllerZarzadzanie = new ZarzadzaniePlatnosciController(getConnection(), getDaoFactory());
            controllerZarzadzanie.initCon(this, widokZarz);
        }
        controllerZarzadzanie.ustawWidocznoscKontrolek();
        przejdzDoZakladki(ZAKL_2);
    }

    public void przejdzDoZakladki(int indx) {
        pane.setSelectedIndex(indx);
    }

    public void setEnabledZakl(int indx, boolean flag) {
        pane.setEnabledAt(indx, flag);
    }

    private void fillTable() {
        modelTable = new TablePaymentsModel(lista);
        widok.getTable().setModel(modelTable);
        widok.getTable().getColumnModel().getColumn(0).setPreferredWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMaxWidth(30);
        widok.getTable().getColumnModel().getColumn(0).setMinWidth(30);
        TableColumn columnDataOplaty = widok.getTable().getColumnModel().getColumn(5);
        if (kryteriaWyszuk.getStatus() == null || kryteriaWyszuk.getStatus().equals(TO_InvoiceStatus.NIE_ZAPLACONA)) {
            widok.getTable().getColumnModel().removeColumn(columnDataOplaty);
        }
        widok.getTable().revalidate();
    }

    public void akcjaDrukuj() {
        Drukowanie drukPanel = new Drukowanie(null, true);
        drukPanel.setLocationRelativeTo(null);
        drukPanel.setVisible(true);
        if (!drukPanel.isDruk()) {
            return;
        }

        FileOutputStream file = null;
        File druk = null;
        try {
            Document document = new Document();
            String userPath = System.getProperty("user.home");
            druk = new File(userPath + "/Baks wydruki");
            if (!druk.exists()) {
                druk.mkdirs();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String strdate = "";
            Date calendardate = new Date();
            strdate = sdf.format(calendardate.getTime());

            File wydruk = new File(druk + "/Platnosci - " + strdate + ".pdf");
            if (!wydruk.exists()) {
                try {
                    wydruk.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            file = new FileOutputStream(wydruk);
            try {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(wydruk));
                document.open();

                PdfPTable table = new PdfPTable(4); // 3 columns.
                table.setWidthPercentage(100); //Width 100%
                table.setSpacingBefore(10f); //Space before table
                table.setSpacingAfter(10f); //Space after table

                //Set Column widths
                float[] columnWidths = {1f, 1f, 1f, 1f};
                table.setWidths(columnWidths);
                BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

                PdfPCell cell1 = new PdfPCell(new Paragraph("Lp", new com.itextpdf.text.Font(bf, 16)));
                cell1.setPaddingLeft(10);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell1.setVerticalAlignment(Element.ALIGN_LEFT);

                PdfPCell cell2 = new PdfPCell(new Paragraph("Firma", new com.itextpdf.text.Font(bf, 16)));
                cell2.setPaddingLeft(10);
                cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell2.setVerticalAlignment(Element.ALIGN_LEFT);

                PdfPCell cell3 = new PdfPCell(new Paragraph("Data płatności", new com.itextpdf.text.Font(bf, 16)));
                cell3.setPaddingLeft(10);
                cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell3.setVerticalAlignment(Element.ALIGN_LEFT);

                PdfPCell cell4 = new PdfPCell(new Paragraph("Kwota", new com.itextpdf.text.Font(bf, 16)));
                cell4.setPaddingLeft(10);
                cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell4.setVerticalAlignment(Element.ALIGN_LEFT);

                //To avoid having the cell border and the content overlap, if you are having thick cell borders
                cell1.setBorder(PdfPCell.NO_BORDER);
                cell2.setBorder(PdfPCell.NO_BORDER);
                cell3.setBorder(PdfPCell.NO_BORDER);
                cell4.setBorder(PdfPCell.NO_BORDER);
                table.addCell(cell1);
                table.addCell(cell2);
                table.addCell(cell3);
                table.addCell(cell4);

                TO_Invoice kryt = new TO_Invoice();
                kryt.setDataDo(drukPanel.getDateDo());
                kryt.setDataOd(drukPanel.getDateOd());
                kryt.setStatus(TO_InvoiceStatus.ZAPLACONA);
                List<TO_Invoice> listaFaktur = getDaoFactory().getDaoInvoice().getListaInvoiceDruk(getConnection(), kryt);

                Integer i = 1;
                for (TO_Invoice item : listaFaktur) {
                    table.addCell(getNewCell(i.toString() + "."));
                    table.addCell(getNewCell(item.getPaymentCompany().getName()));
                    table.addCell(getNewCell(item.getDataZaplacenia().toString()));
                    table.addCell(getNewCell(TO_Invoice.getWynikSumaKoszt(item.getKoszt()) + " zł"));
                    i++;
                }

                document.add(table);

                document.close();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
            BaksSessionBean.getInstance().fireMessage(widok, "Wydruk", "Pdf do którego chcesz zapisać wynik jest otwarty!\n Zamknij i spróbuj jeszcze raz.");
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
            try {
                desktop.open(druk);
            } catch (IOException ex) {
                Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        BaksSessionBean.getInstance().fireMessage(widok, "Zapis", "Wydruk zapisany w folderze: " + System.getProperty("user.home") + "/Baks wydruki");
    }

    public PdfPCell getNewCell(String nazwa) {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException ex) {
            Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WyszukPlatnosciController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfPCell cell = new PdfPCell(new Paragraph(nazwa, new com.itextpdf.text.Font(bf, 12)));
        cell.setPaddingLeft(10);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        return cell;
    }

    @Override
    public void czytajFormatke() {
    }

    @Override
    public void wypelnijFormatke() {
        if (controllerZarzadzanie == null) {
            controllerZarzadzanie = new ZarzadzaniePlatnosciController(getConnection(), getDaoFactory());
            controllerZarzadzanie.initCon(this, widokZarz);
        }
        controllerZarzadzanie.wypelnijFormatke();
    }

    //<editor-fold defaultstate="collapsed" desc="Akcesory">
    public TablePaymentsModel getModelTable() {
        return modelTable;
    }

    public void setModelTable(TablePaymentsModel modelTable) {
        this.modelTable = modelTable;
    }

    public PrzegladaniePlatnosciPanel getWidok() {
        return widok;
    }

    public JTabbedPane getPane() {
        return pane;
    }

    public void setPane(JTabbedPane pane) {
        this.pane = pane;
    }

    public ActionListener getRadioButtonListener() {
        return radioButtonListener;
    }

    public void setRadioButtonListener(ActionListener radioButtonListener) {
        this.radioButtonListener = radioButtonListener;
    }

    public void setWidok(PrzegladaniePlatnosciPanel widok) {
        this.widok = widok;
    }

    public List<TO_Invoice> getLista() {
        return lista;
    }

    public void setLista(List<TO_Invoice> lista) {
        this.lista = lista;
    }

    public TO_Invoice getKryteriaWyszuk() {
        return kryteriaWyszuk;
    }

    public void setKryteriaWyszuk(TO_Invoice kryteriaWyszuk) {
        this.kryteriaWyszuk = kryteriaWyszuk;
    }

    public TO_Invoice getWybranaFaktura() {
        return wybranaFaktura;
    }

    public void setWybranaFaktura(TO_Invoice wybranaFaktura) {
        this.wybranaFaktura = wybranaFaktura;
    }

    public int getAktualnyTryb() {
        return aktualnyTryb;
    }

    public void setAktualnyTryb(int aktualnyTryb) {
        this.aktualnyTryb = aktualnyTryb;
    }
//</editor-fold>
}
