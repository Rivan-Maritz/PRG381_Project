/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.prg381_project.ui;

import com.mycompany.prg381_project.BusinessLayer.MaterialsService;
import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;
import com.mycompany.prg381_project.model.materialsModel;
import com.mycompany.prg381_project.model.suppliersModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Materials Management screen.
 *
 * This form was hand-built (not through the NetBeans GUI editor) because the
 * original .form only had fields for Name and Stock - there was no way to
 * pick a Supplier or enter a Type, even though both are required columns
 * (SupplierID is a NOT NULL foreign key). All validation and persistence
 * now go through MaterialsService - this class only gathers input, calls
 * the service, and renders the result.
 */
public class MaterialsPanel extends javax.swing.JPanel {

    private MainFrame mainFrame;
    private final MaterialsService materialsService = new MaterialsService();

    private JTextField matNameTxt;
    private JTextField matTypeTxt;
    private JTextField matStockTxt;
    private JTextField matReorderTxt;
    private JComboBox<SupplierItem> supplierCombo;
    private JTextField matSearchTxt;
    private JButton matSearchBtn;
    private JButton matAddBtn;
    private JButton matUpdateBtn;
    private JButton matDeleteBtn;
    private JButton matClearBtn;
    private JButton backBtn;
    private JTable matTable;
    private DefaultTableModel tableModel;

    private static final String[] COLUMNS = {
        "ID", "Name", "Type", "Stock", "Reorder Level", "Supplier", "Date Added"
    };

    public MaterialsPanel() {
        initComponents();
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /** Small wrapper so the supplier combo box shows the name but carries the ID. */
    private static class SupplierItem {
        final int id;
        final String name;
        SupplierItem(int id, String name) { this.id = id; this.name = name; }
        @Override public String toString() { return name; }
    }

    private void initComponents() {
        setBackground(new java.awt.Color(189, 224, 254));
        setLayout(new BorderLayout(10, 10));

        JLabel headingLabel = new JLabel("Materials Management");
        headingLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        JPanel headingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headingPanel.setOpaque(false);
        headingPanel.add(headingLabel);

        backBtn = new JButton("Back to Dashboard");
        backBtn.addActionListener(e -> { if (mainFrame != null) mainFrame.showPanel(MainFrame.DASHBOARD); });
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.add(headingPanel, BorderLayout.WEST);
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backPanel.setOpaque(false);
        backPanel.add(backBtn);
        topBar.add(backPanel, BorderLayout.EAST);

        // ---- form fields ----
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;

        matNameTxt = new JTextField(15);
        matTypeTxt = new JTextField(15);
        matStockTxt = new JTextField(8);
        matReorderTxt = new JTextField(8);
        supplierCombo = new JComboBox<>();

        int row = 0;
        addFormRow(formPanel, gc, row++, "Name:", matNameTxt);
        addFormRow(formPanel, gc, row++, "Type:", matTypeTxt);
        addFormRow(formPanel, gc, row++, "Stock:", matStockTxt);
        addFormRow(formPanel, gc, row++, "Reorder Level:", matReorderTxt);
        addFormRow(formPanel, gc, row++, "Supplier:", supplierCombo);

        matSearchTxt = new JTextField(15);
        matSearchBtn = new JButton("Search");
        matSearchBtn.addActionListener(this::matSearchBtnActionPerformed);
        addFormRow(formPanel, gc, row++, "Search by name:", matSearchTxt);

        JPanel searchBtnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchBtnPanel.setOpaque(false);
        searchBtnPanel.add(matSearchBtn);
        gc.gridx = 1; gc.gridy = row++;
        formPanel.add(searchBtnPanel, gc);

        // ---- CRUD buttons ----
        matAddBtn = new JButton("Add");
        matUpdateBtn = new JButton("Update");
        matDeleteBtn = new JButton("Delete");
        matClearBtn = new JButton("Clear");
        matAddBtn.addActionListener(this::matAddBtnActionPerformed);
        matUpdateBtn.addActionListener(this::matUpdateBtnActionPerformed);
        matDeleteBtn.addActionListener(this::matDeleteBtnActionPerformed);
        matClearBtn.addActionListener(this::matClearBtnActionPerformed);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.setOpaque(false);
        buttonPanel.add(matAddBtn);
        buttonPanel.add(matUpdateBtn);
        buttonPanel.add(matDeleteBtn);
        buttonPanel.add(matClearBtn);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // ---- table ----
        tableModel = new DefaultTableModel(COLUMNS, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        matTable = new JTable(tableModel);
        matTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) populateFieldsFromSelectedRow();
        });
        JScrollPane tableScroll = new JScrollPane(matTable);

        add(topBar, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(tableScroll, BorderLayout.CENTER);
    }

    private void addFormRow(JPanel formPanel, GridBagConstraints gc, int row, String label, java.awt.Component field) {
        gc.gridx = 0; gc.gridy = row; gc.weightx = 0;
        formPanel.add(new JLabel(label), gc);
        gc.gridx = 1; gc.weightx = 1;
        formPanel.add(field, gc);
    }

    /** Loads all materials into the table. Called at startup and by MainFrame on navigation. */
    public void refreshTable() {
        loadSupplierCombo();
        populateTable(materialsService.getAllMaterials());
    }

    private void loadSupplierCombo() {
        SupplierItem selected = (SupplierItem) supplierCombo.getSelectedItem();
        DefaultComboBoxModel<SupplierItem> model = new DefaultComboBoxModel<>();
        for (suppliersModel s : materialsService.getAllSuppliers()) {
            model.addElement(new SupplierItem(s.getSupplierID(), s.getName()));
        }
        supplierCombo.setModel(model);
        if (selected != null) {
            for (int i = 0; i < model.getSize(); i++) {
                if (model.getElementAt(i).id == selected.id) {
                    supplierCombo.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void populateTable(List<materialsModel> materials) {
        tableModel.setRowCount(0);
        for (materialsModel m : materials) {
            String supplierName = materialsService.getSupplierName(m.getSupplierid());
            tableModel.addRow(new Object[]{
                m.getMaterialID(), m.getMName(), m.getType(), m.getStock(),
                m.getReorderLevel(), supplierName, m.getDateAdded()
            });
        }
    }

    private void populateFieldsFromSelectedRow() {
        int row = matTable.getSelectedRow();
        if (row == -1) return;
        matNameTxt.setText(String.valueOf(tableModel.getValueAt(row, 1)));
        matTypeTxt.setText(String.valueOf(tableModel.getValueAt(row, 2)));
        matStockTxt.setText(String.valueOf(tableModel.getValueAt(row, 3)));
        matReorderTxt.setText(String.valueOf(tableModel.getValueAt(row, 4)));
        String supplierName = String.valueOf(tableModel.getValueAt(row, 5));
        for (int i = 0; i < supplierCombo.getItemCount(); i++) {
            if (supplierCombo.getItemAt(i).name.equals(supplierName)) {
                supplierCombo.setSelectedIndex(i);
                break;
            }
        }
    }

    private void matSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {
        populateTable(materialsService.searchMaterials(matSearchTxt.getText()));
    }

    private void matAddBtnActionPerformed(java.awt.event.ActionEvent evt) {
        SupplierItem supplier = (SupplierItem) supplierCombo.getSelectedItem();
        try {
            materialsService.addMaterial(
                matNameTxt.getText(), matTypeTxt.getText(), matStockTxt.getText(),
                matReorderTxt.getText(), supplier != null ? supplier.id : null);
            JOptionPane.showMessageDialog(this, "Material added successfully.");
            matClearBtnActionPerformed(null);
            refreshTable();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void matUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int row = matTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to update.");
            return;
        }
        SupplierItem supplier = (SupplierItem) supplierCombo.getSelectedItem();
        int id = (int) tableModel.getValueAt(row, 0);
        try {
            materialsService.updateMaterial(id,
                matNameTxt.getText(), matTypeTxt.getText(), matStockTxt.getText(),
                matReorderTxt.getText(), supplier != null ? supplier.id : null);
            JOptionPane.showMessageDialog(this, "Material updated successfully.");
            matClearBtnActionPerformed(null);
            refreshTable();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void matDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {
        int row = matTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a material to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to delete this material?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        int id = (int) tableModel.getValueAt(row, 0);
        try {
            materialsService.deleteMaterial(id);
            JOptionPane.showMessageDialog(this, "Material deleted successfully.");
            matClearBtnActionPerformed(null);
            refreshTable();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void matClearBtnActionPerformed(java.awt.event.ActionEvent evt) {
        matNameTxt.setText("");
        matTypeTxt.setText("");
        matStockTxt.setText("");
        matReorderTxt.setText("");
        matSearchTxt.setText("");
        matTable.clearSelection();
    }
}
