/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prg381_project.ui;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * MainFrame hosts every screen as a single reused instance inside a
 * CardLayout. Panels never create new instances of each other for
 * navigation - they call mainFrame.showPanel("NAME") instead, which also
 * refreshes that panel's data from the database before showing it.
 *
 * @author rivan
 */
public class MainFrame extends javax.swing.JFrame {

    public static final String LOGIN = "LOGIN";
    public static final String DASHBOARD = "DASHBOARD";
    public static final String MATERIALS = "MATERIALS";
    public static final String SUPPLIERS = "SUPPLIERS";
    public static final String CLEANERS = "CLEANERS";
    public static final String STOCK_ISSUANCE = "STOCK_ISSUANCE";
    public static final String REPORTS = "REPORTS";

    private final CardLayout cardLayout;
    private final JPanel container;

    private final LoginPanel loginPanel;
    private final DashboardPanel dashboardPanel;
    private final MaterialsPanel materialsPanel;
    private final SuppliersPanel suppliersPanel;
    private final CleanersPanel cleanersPanel;
    private final StockIssuancePanel stockIssuancePanel;
    private final ReportsPanel reportsPanel;

    // Simple session tracking: who is currently logged in.
    // (Full role-based access control is a separate, later task.)
    private int currentUserId = -1;
    private String currentUsername = null;

    public MainFrame() {
        initComponents();
        setTitle("Smarter Clean - University Cleaning Inventory & Issuance System");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        loginPanel = new LoginPanel();
        dashboardPanel = new DashboardPanel();
        materialsPanel = new MaterialsPanel();
        suppliersPanel = new SuppliersPanel();
        cleanersPanel = new CleanersPanel();
        stockIssuancePanel = new StockIssuancePanel();
        reportsPanel = new ReportsPanel();

        loginPanel.setMainFrame(this);
        dashboardPanel.setMainFrame(this);
        materialsPanel.setMainFrame(this);
        suppliersPanel.setMainFrame(this);
        cleanersPanel.setMainFrame(this);
        stockIssuancePanel.setMainFrame(this);
        reportsPanel.setMainFrame(this);

        container.add(loginPanel, LOGIN);
        container.add(dashboardPanel, DASHBOARD);
        container.add(materialsPanel, MATERIALS);
        container.add(suppliersPanel, SUPPLIERS);
        container.add(cleanersPanel, CLEANERS);
        container.add(stockIssuancePanel, STOCK_ISSUANCE);
        container.add(reportsPanel, REPORTS);

        setContentPane(container);
        cardLayout.show(container, LOGIN);

        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    /**
     * Switches to the named panel, refreshing its data from the database
     * first so it never shows stale information from a previous visit.
     */
    public void showPanel(String name) {
        switch (name) {
            case DASHBOARD:
                dashboardPanel.refreshStats();
                break;
            case MATERIALS:
                materialsPanel.refreshTable();
                break;
            case SUPPLIERS:
                suppliersPanel.refreshTable();
                break;
            case CLEANERS:
                cleanersPanel.refreshTable();
                break;
            case STOCK_ISSUANCE:
                stockIssuancePanel.refreshData();
                break;
            case REPORTS:
                // Reports panel loads on-demand via its own Generate button.
                break;
            default:
                break;
        }
        cardLayout.show(container, name);
    }

    public void setCurrentUser(String username, int userId) {
        this.currentUsername = username;
        this.currentUserId = userId;
    }

    public void clearCurrentUser() {
        this.currentUsername = null;
        this.currentUserId = -1;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }

    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
