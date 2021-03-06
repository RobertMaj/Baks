package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

/**
 *
 * @author Marcin Zuralski
 */
public class UITogglePanel<T extends JPanel> extends javax.swing.JPanel {

    private T mainPanel;

    public List<TogglePanelOpenCloseListener> togglePanelOpenCloseListeners = new ArrayList<>();

    /**
     * Creates new form UITogglePanel
     */
    public UITogglePanel() {
        initComponents();
        pokazUkryjToggleBtn.setSelected(true);
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

        naglowekPanel = new javax.swing.JPanel();
        pokazUkryjToggleBtn = new javax.swing.JToggleButton();
        tytulLabel = new javax.swing.JLabel();
        internalPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        naglowekPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        naglowekPanel.setMinimumSize(new java.awt.Dimension(0, 30));
        naglowekPanel.setPreferredSize(new java.awt.Dimension(1000, 33));
        naglowekPanel.setLayout(new java.awt.GridBagLayout());

        pokazUkryjToggleBtn.setText("Ukryj");
        pokazUkryjToggleBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pokazUkryjToggleBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 5, 3, 5);
        naglowekPanel.add(pokazUkryjToggleBtn, gridBagConstraints);

        tytulLabel.setText("TYTUL");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        naglowekPanel.add(tytulLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        add(naglowekPanel, gridBagConstraints);

        internalPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        internalPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(internalPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void pokazUkryjToggleBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pokazUkryjToggleBtnActionPerformed
        if (!pokazUkryjToggleBtn.isSelected()) {
            pokazUkryjToggleBtn.setText("Pokaż");
            internalPanel.setVisible(false);
        } else {
            pokazUkryjToggleBtn.setText("Ukryj");
            internalPanel.setVisible(true);
        }
        fireOpenCloseAction();
        repaint();
    }//GEN-LAST:event_pokazUkryjToggleBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel internalPanel;
    private javax.swing.JPanel naglowekPanel;
    private javax.swing.JToggleButton pokazUkryjToggleBtn;
    private javax.swing.JLabel tytulLabel;
    // End of variables declaration//GEN-END:variables

    public UITogglePanel initInternalPanel(T panel) {
        internalPanel.removeAll();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        internalPanel.setPreferredSize(panel.getPreferredSize());
        internalPanel.add(panel, gridBagConstraints);
        mainPanel = panel;
        return this;
    }

    public T getMainPanel() {
        return mainPanel;
    }

    public void setTytul(String tytul) {
        tytulLabel.setText(tytul);
    }

    public String getTytul() {
        return tytulLabel.getText();
    }

    public void setTitleBackground(Color color) {
        naglowekPanel.setBackground(color);
    }

    public Color getTitleBackground() {
        return naglowekPanel.getBackground();
    }

    public Border getTitleBorder() {
        return naglowekPanel.getBorder();
    }

    public void setTitleBorder(Border border) {
        naglowekPanel.setBorder(border);
    }

    public Font getTitleFont() {
        return tytulLabel.getFont();
    }

    public void setTitleFont(Font font) {
        tytulLabel.setFont(font);
    }

    public JToggleButton getPokazUkryjToggleBtn() {
        return pokazUkryjToggleBtn;
    }

    public boolean isOpen() {
        return internalPanel.isVisible();
    }

    public JPanel getInternalPanel() {
        return internalPanel;
    }

    public void setOpen(boolean open) {
        internalPanel.setVisible(open);
        getPokazUkryjToggleBtn().setSelected(open);
        getPokazUkryjToggleBtn().setText(open ? "Ukryj" : "Pokaż");
        fireOpenCloseAction();
    }

    public void addTogglePanelOpenCloseListener(TogglePanelOpenCloseListener listener) {
        if (!togglePanelOpenCloseListeners.contains(listener)) {
            togglePanelOpenCloseListeners.add(listener);
        }
    }

    public void removeTogglePanelOpenCloseListener(TogglePanelOpenCloseListener listener) {
        if (togglePanelOpenCloseListeners.contains(listener)) {
            togglePanelOpenCloseListeners.remove(listener);
        }
    }

    public void fireOpenCloseAction() {
        OpenCloseActionEvent openCloseActionEvent = new OpenCloseActionEvent(this, -1, "", internalPanel.isVisible());
        for (TogglePanelOpenCloseListener listener : togglePanelOpenCloseListeners) {
            listener.openCloseActionPerformed(openCloseActionEvent);
        }
    }

    public static interface TogglePanelOpenCloseListener {

        public void openCloseActionPerformed(OpenCloseActionEvent evt);
    }

    public static class OpenCloseActionEvent extends ActionEvent {

        private boolean isOpen;

        public OpenCloseActionEvent(UITogglePanel o, int i, String string, boolean isOpen) {
            super(o, i, string);
            this.isOpen = isOpen;
        }

        public boolean isIsOpen() {
            return isOpen;
        }

        public UITogglePanel getTogglePanel() {
            return (UITogglePanel) getSource();
        }
    }

    @Override
    public void setEnabled(boolean bln) {
        super.setEnabled(bln);
        getPokazUkryjToggleBtn().setEnabled(bln);
    }

    public JPanel getNaglowekPanel() {
        return naglowekPanel;
    }

    public void setNaglowekPanel(JPanel naglowekPanel) {
        this.naglowekPanel = naglowekPanel;
    }

}
