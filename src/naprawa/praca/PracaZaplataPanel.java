/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.Czesc;
import Model.praca.Przelicznik;
import adm.Baks.BaksSessionBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author mzuralski
 */
public class PracaZaplataPanel extends javax.swing.JPanel {

    public static Przelicznik wybranyRadio;

    static {
        wybranyRadio = Przelicznik.PROCENT;
    }

    /**
     * Creates new form GeografiaStrukturaPanel
     */
    public PracaZaplataPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnGroupCenaProcent = new javax.swing.ButtonGroup();
        panelWybor = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        opis = new javax.swing.JTextField();
        labelCenaKoszt = new javax.swing.JLabel();
        kosztCenaPodst = new javax.swing.JTextField();
        radioProcent = new javax.swing.JRadioButton();
        radioKwota = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        dodajDoCeny = new javax.swing.JTextField();
        labelPlus = new javax.swing.JLabel();
        labelProcentZl = new javax.swing.JLabel();
        labelRowna = new javax.swing.JLabel();
        suma = new javax.swing.JTextField();
        labelZl = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnDodaj = new javax.swing.JButton();
        btnUsun = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(356, 300));
        setPreferredSize(new java.awt.Dimension(919, 300));
        setLayout(new java.awt.GridBagLayout());

        panelWybor.setBorder(javax.swing.BorderFactory.createTitledBorder("Koszty naprawy"));
        panelWybor.setMinimumSize(new java.awt.Dimension(100, 100));
        panelWybor.setPreferredSize(new java.awt.Dimension(100, 100));
        panelWybor.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Opis");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(jLabel1, gridBagConstraints);

        opis.setPreferredSize(new java.awt.Dimension(300, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(opis, gridBagConstraints);

        labelCenaKoszt.setText("Koszt/Cena");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(labelCenaKoszt, gridBagConstraints);

        kosztCenaPodst.setBackground(new java.awt.Color(204, 255, 255));
        kosztCenaPodst.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        kosztCenaPodst.setText("0");
        kosztCenaPodst.setPreferredSize(new java.awt.Dimension(100, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(kosztCenaPodst, gridBagConstraints);

        radioProcent.setBackground(BaksSessionBean
            .PANEL_BACKGROUND_PANEL_COLOR);
        btnGroupCenaProcent.add(radioProcent);
        radioProcent.setText("Procent");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(radioProcent, gridBagConstraints);

        radioKwota.setBackground(BaksSessionBean
            .PANEL_BACKGROUND_PANEL_COLOR);
        btnGroupCenaProcent.add(radioKwota);
        radioKwota.setText("Kwota");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(radioKwota, gridBagConstraints);

        jLabel3.setText("zł");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(jLabel3, gridBagConstraints);

        dodajDoCeny.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        dodajDoCeny.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(dodajDoCeny, gridBagConstraints);

        labelPlus.setText("+");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(labelPlus, gridBagConstraints);

        labelProcentZl.setText("zł");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(labelProcentZl, gridBagConstraints);

        labelRowna.setText("=");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(labelRowna, gridBagConstraints);

        suma.setBackground(new java.awt.Color(204, 255, 255));
        suma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        suma.setText("0");
        suma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        suma.setPreferredSize(new java.awt.Dimension(2, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(suma, gridBagConstraints);

        labelZl.setText("zł");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        panelWybor.add(labelZl, gridBagConstraints);

        jPanel3.setBackground(BaksSessionBean
            .PANEL_BACKGROUND_PANEL_COLOR);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnDodaj.setText(">");
        btnDodaj.setPreferredSize(new java.awt.Dimension(60, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnDodaj, gridBagConstraints);

        btnUsun.setText("<");
        btnUsun.setPreferredSize(new java.awt.Dimension(60, 23));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        jPanel3.add(btnUsun, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.weighty = 1.0;
        panelWybor.add(jPanel3, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panelWybor, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Opis", "Koszt"
            }
        ));
        jScrollPane1.setViewportView(table);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public void ukryjMarza() {
        radioProcent.setVisible(false);
        radioKwota.setVisible(false);
        dodajDoCeny.setVisible(false);
        suma.setVisible(false);
        labelCenaKoszt.setVisible(false);
        labelPlus.setVisible(false);
        labelProcentZl.setVisible(false);
        labelRowna.setVisible(false);
        labelZl.setVisible(false);
    }

    public Przelicznik getWybranyRadio() {
        if (radioKwota.isSelected()) {
            wybranyRadio = Przelicznik.WARTOSC;
        } else if (radioProcent.isSelected()) {
            wybranyRadio = Przelicznik.PROCENT;
        }
        return wybranyRadio;
    }

    public void setSelectedRadio(Przelicznik radio) {
        wybranyRadio = radio;
        if (wybranyRadio.equals(Przelicznik.PROCENT)) {
            radioProcent.setSelected(true);
        } else if (wybranyRadio.equals(Przelicznik.WARTOSC)) {
            radioKwota.setSelected(true);
        }
    }

    public JButton getBtnDodaj() {
        return btnDodaj;
    }

    public void setBtnDodaj(JButton btnDodaj) {
        this.btnDodaj = btnDodaj;
    }

    public ButtonGroup getBtnGroupCenaProcent() {
        return btnGroupCenaProcent;
    }

    public void setBtnGroupCenaProcent(ButtonGroup btnGroupCenaProcent) {
        this.btnGroupCenaProcent = btnGroupCenaProcent;
    }

    public JRadioButton getBtnKwota() {
        return radioKwota;
    }

    public void setBtnKwota(JRadioButton btnKwota) {
        this.radioKwota = btnKwota;
    }

    public JRadioButton getBtnProcent() {
        return radioProcent;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public void setBtnProcent(JRadioButton btnProcent) {
        this.radioProcent = btnProcent;
    }

    public JButton getBtnUsun() {
        return btnUsun;
    }

    public void setBtnUsun(JButton btnUsun) {
        this.btnUsun = btnUsun;
    }

    public JTextField getDodajDoCeny() {
        return dodajDoCeny;
    }

    public void setDodajDoCeny(JTextField dodajDoCeny) {
        this.dodajDoCeny = dodajDoCeny;
    }

    public JTextField getKosztCenaPodst() {
        return kosztCenaPodst;
    }

    public void setKosztCenaPodst(JTextField kosztCenaPodst) {
        this.kosztCenaPodst = kosztCenaPodst;
    }

    public JLabel getLabelCenaKoszt() {
        return labelCenaKoszt;
    }

    public void setLabelCenaKoszt(JLabel labelCenaKoszt) {
        this.labelCenaKoszt = labelCenaKoszt;
    }

    public JLabel getLabelProcentZl() {
        return labelProcentZl;
    }

    public void setLabelProcentZl(JLabel labelProcentZl) {
        this.labelProcentZl = labelProcentZl;
    }

    public JTextField getOpis() {
        return opis;
    }

    public void setOpis(JTextField opis) {
        this.opis = opis;
    }

    public JPanel getPanelWybor() {
        return panelWybor;
    }

    public void setPanelWybor(JPanel panelWybor) {
        this.panelWybor = panelWybor;
    }

    public JTextField getSuma() {
        return suma;
    }

    public void setSuma(JTextField suma) {
        this.suma = suma;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.ButtonGroup btnGroupCenaProcent;
    private javax.swing.JButton btnUsun;
    private javax.swing.JTextField dodajDoCeny;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kosztCenaPodst;
    private javax.swing.JLabel labelCenaKoszt;
    private javax.swing.JLabel labelPlus;
    private javax.swing.JLabel labelProcentZl;
    private javax.swing.JLabel labelRowna;
    private javax.swing.JLabel labelZl;
    private javax.swing.JTextField opis;
    private javax.swing.JPanel panelWybor;
    private javax.swing.JRadioButton radioKwota;
    private javax.swing.JRadioButton radioProcent;
    private javax.swing.JTextField suma;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

    public JLabel getLabelPlus() {
        return labelPlus;
    }

    public void setLabelPlus(JLabel labelPlus) {
        this.labelPlus = labelPlus;
    }

    public JLabel getLabelRowna() {
        return labelRowna;
    }

    public void setLabelRowna(JLabel labelRowna) {
        this.labelRowna = labelRowna;
    }

    public JLabel getLabelZl() {
        return labelZl;
    }

    public void setLabelZl(JLabel labelZl) {
        this.labelZl = labelZl;
    }

    public JRadioButton getRadioKwota() {
        return radioKwota;
    }

    public void setRadioKwota(JRadioButton radioKwota) {
        this.radioKwota = radioKwota;
    }

    public JRadioButton getRadioProcent() {
        return radioProcent;
    }

    public void setRadioProcent(JRadioButton radioProcent) {
        this.radioProcent = radioProcent;
    }

}
