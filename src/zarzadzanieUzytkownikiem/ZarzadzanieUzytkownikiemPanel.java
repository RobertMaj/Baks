/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zarzadzanieUzytkownikiem;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Robert M
 */
public class ZarzadzanieUzytkownikiemPanel extends javax.swing.JPanel {

    /**
     * Creates new form NaprawaZapisPanel
     */
    public ZarzadzanieUzytkownikiemPanel() {
        initComponents();
    }

    public JButton getBtnRezygnuj() {
        return btnRezygnuj;
    }

    public void setBtnRezygnuj(JButton btnRezygnuj) {
        this.btnRezygnuj = btnRezygnuj;
    }

    public JButton getBtnZapisz() {
        return btnZapisz;
    }

    public void setBtnZapisz(JButton btnZapisz) {
        this.btnZapisz = btnZapisz;
    }

    public JPanel getPanelBtn() {
        return panelBtn;
    }

    public void setPanelBtn(JPanel panelBtn) {
        this.panelBtn = panelBtn;
    }

    public JButton getBtnUsun() {
        return btnUsun;
    }

    public void setBtnUsun(JButton btnUsun) {
        this.btnUsun = btnUsun;
    }

    public JTextField getImie() {
        return imie;
    }

    public void setImie(JTextField imie) {
        this.imie = imie;
    }

    public JTextField getMail() {
        return mail;
    }

    public void setMail(JTextField mail) {
        this.mail = mail;
    }

    public JTextField getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(JTextField nazwisko) {
        this.nazwisko = nazwisko;
    }

    public JTextField getNrTel() {
        return nrTel;
    }

    public void setNrTel(JTextField nrTel) {
        this.nrTel = nrTel;
    }

    public JPanel getPanelEdycji() {
        return panelEdycji;
    }

    public void setPanelEdycji(JPanel panelEdycji) {
        this.panelEdycji = panelEdycji;
    }

    public JTable getTable() {
        return table;
    }

    public JComboBox getUprawnieniaComboBox() {
        return uprawnieniaComboBox;
    }

    public void setUprawnieniaComboBox(JComboBox uprawnieniaComboBox) {
        this.uprawnieniaComboBox = uprawnieniaComboBox;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelBtn = new javax.swing.JPanel();
        btnZapisz = new javax.swing.JButton();
        btnRezygnuj = new javax.swing.JButton();
        btnUsun = new javax.swing.JButton();
        panelEdycji = new javax.swing.JPanel();
        nrTel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nazwisko = new javax.swing.JTextField();
        imie = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        mail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        uprawnieniaComboBox = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Zarządzanie użytkownikami"));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        panelBtn.setBackground(new java.awt.Color(102, 102, 102));

        btnZapisz.setText("Edytuj");

        btnRezygnuj.setText("Rezygnuj");

        btnUsun.setText("Usuń");

        javax.swing.GroupLayout panelBtnLayout = new javax.swing.GroupLayout(panelBtn);
        panelBtn.setLayout(panelBtnLayout);
        panelBtnLayout.setHorizontalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnLayout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(btnZapisz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsun)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRezygnuj)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBtnLayout.setVerticalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnZapisz)
                    .addComponent(btnRezygnuj)
                    .addComponent(btnUsun))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEdycji.setToolTipText("");

        jLabel4.setText("Nr telefonu");

        jLabel3.setText("Nazwisko");

        jLabel2.setText("Imię");

        jLabel9.setText("E- mail");

        jLabel7.setText("Uprawnienie");

        javax.swing.GroupLayout panelEdycjiLayout = new javax.swing.GroupLayout(panelEdycji);
        panelEdycji.setLayout(panelEdycjiLayout);
        panelEdycjiLayout.setHorizontalGroup(
            panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdycjiLayout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mail)
                    .addComponent(nrTel)
                    .addComponent(uprawnieniaComboBox, 0, 197, Short.MAX_VALUE)
                    .addComponent(nazwisko)
                    .addComponent(imie))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelEdycjiLayout.createSequentialGroup()
                    .addGap(407, 407, 407)
                    .addComponent(jLabel9)
                    .addContainerGap(574, Short.MAX_VALUE)))
        );
        panelEdycjiLayout.setVerticalGroup(
            panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEdycjiLayout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nrTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(uprawnieniaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(147, 147, 147))
            .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelEdycjiLayout.createSequentialGroup()
                    .addGap(125, 125, 125)
                    .addComponent(jLabel9)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "E-Mail", "Imię", "Nazwisko", "Uprawnienia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addComponent(panelEdycji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEdycji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRezygnuj;
    private javax.swing.JButton btnUsun;
    private javax.swing.JButton btnZapisz;
    private javax.swing.JTextField imie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField mail;
    private javax.swing.JTextField nazwisko;
    private javax.swing.JTextField nrTel;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelEdycji;
    private javax.swing.JTable table;
    private javax.swing.JComboBox uprawnieniaComboBox;
    // End of variables declaration//GEN-END:variables
}