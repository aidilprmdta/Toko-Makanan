package Gui;

import Model.Pengguna;
import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public RegisterFrame() {
        setTitle("Register");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        //Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        //Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        //Register
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::registerUser);

        //Kembali
        JButton kembali = new JButton("Kembali");
        kembali.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        //Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(registerButton);
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        buttonPanel.add(kembali);
        buttonPanel.add(registerButton);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);

        add(panel);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void registerUser(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan password harus diisi!");
            return;
        }

        Pengguna newUser = new Pengguna(username, password);
        boolean success = UserService.registerUser(newUser);

        if (success) {
            JOptionPane.showMessageDialog(this, "Registrasi berhasil! Silakan login.");
            new LoginFrame().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username sudah terdaftar.");
        }
    }
}
