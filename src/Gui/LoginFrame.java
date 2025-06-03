package Gui;

import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(this::loginUser);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            new RegisterFrame().setVisible(true);
            this.dispose();
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        add(panel);
    }

    private void loginUser(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password harus diisi!");
            return;
        }

        boolean success = UserService.loginUser(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this, "Login berhasil!");
            new HomeFrame().setVisible(true); // Pastikan class HomeFrame sudah ada
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah.");
        }
    }
}
