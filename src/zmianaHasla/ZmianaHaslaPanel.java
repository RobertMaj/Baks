/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zmianaHasla;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Robert M
 */
public class ZmianaHaslaPanel extends javax.swing.JPanel {

    /**
     * Creates new form NaprawaZapisPanel
     */
    public ZmianaHaslaPanel() {
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

    public JPasswordField getNowe() {
        return nowe;
    }

    public void setNowe(JPasswordField nowe) {
        this.nowe = nowe;
    }

    public JPasswordField getNowePow() {
        return nowePow;
    }

    public void setNowePow(JPasswordField nowePow) {
        this.nowePow = nowePow;
    }

    public JPasswordField getStare() {
        return stare;
    }

    public void setStare(JPasswordField stare) {
        this.stare = stare;
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
        panelEdycji = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        stare = new javax.swing.JPasswordField();
        nowe = new javax.swing.JPasswordField();
        nowePow = new javax.swing.JPasswordField();

        setBackground(new java.awt.Color(204, 204, 255));
        setBorder(javax.swing.BorderFactory.createTitledBorder("Zmiana hasła"));
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setPreferredSize(new java.awt.Dimension(1000, 600));

        panelBtn.setBackground(new java.awt.Color(102, 102, 102));

        btnZapisz.setText("Zapisz");

        btnRezygnuj.setText("Rezygnuj");

        javax.swing.GroupLayout panelBtnLayout = new javax.swing.GroupLayout(panelBtn);
        panelBtn.setLayout(panelBtnLayout);
        panelBtnLayout.setHorizontalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnLayout.createSequentialGroup()
                .addGap(472, 472, 472)
                .addComponent(btnZapisz)
                .addGap(18, 18, 18)
                .addComponent(btnRezygnuj)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        panelBtnLayout.setVerticalGroup(
            panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBtnLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelBtnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnZapisz)
                    .addComponent(btnRezygnuj))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelEdycji.setToolTipText("");

        jLabel3.setText("Powtórz drugie hasło");

        jLabel2.setText("Nowe hasło");

        jLabel9.setText("Stare hasło");

        javax.swing.GroupLayout panelEdycjiLayout = new javax.swing.GroupLayout(panelEdycji);
        panelEdycji.setLayout(panelEdycjiLayout);
        panelEdycjiLayout.setHorizontalGroup(
            panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdycjiLayout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stare)
                    .addComponent(nowe)
                    .addComponent(nowePow, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEdycjiLayout.setVerticalGroup(
            panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEdycjiLayout.createSequentialGroup()
                .addContainerGap(124, Short.MAX_VALUE)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(stare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nowe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelEdycjiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nowePow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(150, 150, 150))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelEdycji, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEdycji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(panelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRezygnuj;
    private javax.swing.JButton btnZapisz;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField nowe;
    private javax.swing.JPasswordField nowePow;
    private javax.swing.JPanel panelBtn;
    private javax.swing.JPanel panelEdycji;
    private javax.swing.JPasswordField stare;
    // End of variables declaration//GEN-END:variables
}
