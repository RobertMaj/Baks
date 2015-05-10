/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.TO_Invoice;
import Model.TO_StatusDefects;
import Model.praca.Czesc;
import Model.praca.RodzajUslugi;
import Model.praca.Usluga;
import adm.Baks.AbstractController;
import adm.Baks.BaksSessionBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import static com.itextpdf.text.pdf.PdfName.T;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dao.DaoFactory;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import naprawa.przegladanie.NaprawaPrzegladanieController;
import platnosci.WyszukPlatnosciController;
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

    public void akcjaDrukuj() {
        mapaUslug = getDaoFactory().getDaoDefect().pobierzUslugi(getConnection(), wybranyDefect);
        FileOutputStream file = null;
        File druk = null;
        try {
            Document document = new Document(PageSize.A5, 0, 0, 0, 0);
            String userPath = System.getProperty("user.home");
            druk = new File(userPath + "/Baks wydruki");
            if (!druk.exists()) {
                druk.mkdirs();
            }

            File wydruk = new File(druk + "/" + wybranyDefect.getInfoNaprawa() + ".pdf");
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

                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                table.setSpacingBefore(0f);
                table.setSpacingAfter(0f);

                table.addCell(getNewCell("BAK's Machine\ninż. Błażej Krzciuk\n26-800 Białobrzegi ul. Brzechwy 31\ntel. 509-281-487"));
                PdfPCell cell1 = getNewCell("Białobrzegi, dn. " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell1);

                table.addCell(addTableCzesc());
                table.addCell(addTableCzescService("Materiały", RodzajUslugi.MATERIAL));
                table.addCell(addTableCzescService("Naprawa", RodzajUslugi.NAPRAWA));

                PdfPTable tablePodsumowanie = new PdfPTable(3);
                tablePodsumowanie.setWidthPercentage(100);
                int[] width = {8, 77, 15};
                tablePodsumowanie.setWidths(width);
                PdfPCell cell = getNewCell("Podsumowanie");
                cell.setColspan(10);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablePodsumowanie.addCell(cell);

                tablePodsumowanie.addCell(getNewCell("1."));
                tablePodsumowanie.addCell(getNewCell("Części"));
                tablePodsumowanie.addCell(getNewCell(wybranyDefect.getKosztCzesciS() + " zł"));

                tablePodsumowanie.addCell(getNewCell("2."));
                tablePodsumowanie.addCell(getNewCell("Materiały"));
                tablePodsumowanie.addCell(getNewCell(wybranyDefect.getKosztMaterialyS() + " zł"));

                tablePodsumowanie.addCell(getNewCell("3."));
                tablePodsumowanie.addCell(getNewCell("Naprawa"));
                tablePodsumowanie.addCell(getNewCell(wybranyDefect.getKosztNaprawyS() + " zł"));

                tablePodsumowanie.addCell(getNewCell(""));
                tablePodsumowanie.addCell(getNewCell("RAZEM"));
                tablePodsumowanie.addCell(getNewCell(wybranyDefect.getKosztSumaS() + " zł"));

                table.addCell(tablePodsumowanie);

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

    public PdfPTable addTableCzesc() {
        PdfPTable tableCzesc = new PdfPTable(3);
        try {
            tableCzesc.setWidthPercentage(100);
            int[] width = {8, 77, 15};
            tableCzesc.setWidths(width);
            PdfPCell cell = getNewCell("Części");
            cell.setColspan(10);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCzesc.addCell(cell);

            int i = 1;
            for (Object item : mapaUslug.get(RodzajUslugi.CZESC)) {
                Czesc czesc = ((Czesc) item);
                tableCzesc.addCell(getNewCell(i + "."));
                tableCzesc.addCell(getNewCell(czesc.getOpis()));
                tableCzesc.addCell(getNewCell(TO_Invoice.getWynikSumaKoszt(czesc.getCena()) + " zł"));
                i++;
            }
        } catch (DocumentException ex) {
            Logger.getLogger(PracaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableCzesc;
    }

    public PdfPTable addTableCzescService(String rodzaj, RodzajUslugi usluga) {
        PdfPTable tableCzesc = new PdfPTable(3);
        try {
            tableCzesc.setWidthPercentage(100);
            int[] width = {8, 77, 15};
            tableCzesc.setWidths(width);
            PdfPCell cell = getNewCell(rodzaj);
            cell.setColspan(10);
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tableCzesc.addCell(cell);

            int i = 1;
            for (Object item : mapaUslug.get(usluga)) {
                Usluga czesc = ((Usluga) item);
                tableCzesc.addCell(getNewCell(i + "."));
                tableCzesc.addCell(getNewCell(czesc.getOpis()));
                tableCzesc.addCell(getNewCell(TO_Invoice.getWynikSumaKoszt(czesc.getKoszt()) + " zł"));
                i++;
            }
        } catch (DocumentException ex) {
            Logger.getLogger(PracaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tableCzesc;
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
        PdfPCell cell = new PdfPCell(new Paragraph(nazwa, new com.itextpdf.text.Font(bf, 10)));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

}
