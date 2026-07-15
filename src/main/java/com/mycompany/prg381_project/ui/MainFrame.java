package com.mycompany.prg381_project.ui;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainFrame extends javax.swing.JFrame {

    private CardLayout cardlayout;
    private JPanel jpanel;
    
    public MainFrame() {
        initComponents();
        
        cardlayout = new CardLayout();
        jpanel = new JPanel(cardlayout);
        
        jpanel.add(new LoginPanel(this), "LOGIN");
        jpanel.add(new DashboardPanel(), "DASHBOARD");
        jpanel.add(new MaterialsPanel(), "MATERIALS");
        jpanel.add(new SuppliersPanel(), "SUPPLIERS");
        jpanel.add(new CleanersPanel(), "CLEANERS");
        jpanel.add(new StockIssuancePanel(), "STOCK_ISSUANCE");
        jpanel.add(new ReportsPanel(), "REPORTS");
        
        setContentPane(jpanel);
        
        cardlayout.show(jpanel, "LOGIN");
    }

    public void showPanel(String name){
        cardlayout.show(jpanel, name);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout contentContainerLayout = new javax.swing.GroupLayout(contentContainer);
        contentContainer.setLayout(contentContainerLayout);
        contentContainerLayout.setHorizontalGroup(
            contentContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        contentContainerLayout.setVerticalGroup(
            contentContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(contentContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(contentContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentContainer;
    // End of variables declaration//GEN-END:variables
}
