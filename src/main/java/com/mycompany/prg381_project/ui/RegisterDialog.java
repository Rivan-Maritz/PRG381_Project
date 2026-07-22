package com.mycompany.prg381_project.ui;

import com.mycompany.prg381_project.BusinessLayer.UsersService;
import com.mycompany.prg381_project.BusinessLayer.exceptions.BusinessException;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Simple modal registration dialog, opened from the "Register" button on
 * the Login screen. All validation and the actual account creation go
 * through UsersService - this class only gathers input and shows
 * whatever message the service reports back.
 */
public class RegisterDialog extends JDialog {

    private final UsersService usersService = new UsersService();
    private boolean registered = false;

    private final JTextField usernameField = new JTextField(18);
    private final JTextField emailField = new JTextField(18);
    private final JPasswordField passwordField = new JPasswordField(18);
    private final JPasswordField confirmPasswordField = new JPasswordField(18);
    private final JComboBox<String> roleCombo = new JComboBox<>(new String[]{"Storekeeper", "Supervisor"});

    public RegisterDialog(java.awt.Frame owner) {
        super(owner, "Register New Account", true);
        buildUI();
        pack();
        setLocationRelativeTo(owner);
    }

    /** True if an account was successfully created before the dialog closed. */
    public boolean wasRegistered() {
        return registered;
    }

    private void buildUI() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(6, 6, 6, 6);
        gc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addRow(form, gc, row++, "Username:", usernameField);
        addRow(form, gc, row++, "Email:", emailField);
        addRow(form, gc, row++, "Password:", passwordField);
        addRow(form, gc, row++, "Confirm Password:", confirmPasswordField);
        addRow(form, gc, row++, "Role:", roleCombo);

        JButton registerBtn = new JButton("Register");
        JButton cancelBtn = new JButton("Cancel");
        registerBtn.addActionListener(e -> onRegister());
        cancelBtn.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(registerBtn);
        buttonPanel.add(cancelBtn);

        gc.gridx = 0; gc.gridy = row; gc.gridwidth = 2;
        form.add(buttonPanel, gc);

        setContentPane(form);
    }

    private void addRow(JPanel form, GridBagConstraints gc, int row, String label, java.awt.Component field) {
        gc.gridwidth = 1;
        gc.gridx = 0; gc.gridy = row; gc.weightx = 0;
        form.add(new JLabel(label), gc);
        gc.gridx = 1; gc.weightx = 1;
        form.add(field, gc);
    }

    private void onRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();

        try {
            usersService.register(username, password, confirmPassword, email, role);
            JOptionPane.showMessageDialog(this, "Account created. You can now log in.");
            registered = true;
            dispose();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Registration Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
