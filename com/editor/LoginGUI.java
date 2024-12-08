package com.editor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    private static HashMap<String, String> userDatabase = new HashMap<>(); // Stores email-password pairs
    private static String loggedInUser = null;

    public LoginGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPanel);
        contentPanel.setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 50, 100, 25);
        contentPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 50, 180, 25);
        contentPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 100, 25);
        contentPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 180, 25);
        contentPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 140, 120, 30);
        contentPanel.add(loginButton);

        createAccountButton = new JButton("Create Account");
        createAccountButton.setBounds(210, 140, 120, 30);
        contentPanel.add(createAccountButton);

        // Add Action Listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateAccount();
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
            loggedInUser = email;
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            new MainGUI().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCreateAccount() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (userDatabase.containsKey(email)) {
            JOptionPane.showMessageDialog(this, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            userDatabase.put(email, password);
            JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }
}
