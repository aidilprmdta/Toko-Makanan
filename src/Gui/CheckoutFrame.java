package Gui;

import Model.Keranjang;
import Model.Produk;

import javax.swing.*;
import java.awt.*;

public class CheckoutFrame extends JFrame {
    private Keranjang keranjang;

    public CheckoutFrame(Keranjang keranjang) {
        this.keranjang = keranjang;

        setTitle("Checkout");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        StringBuilder detail = new StringBuilder();

        for (Produk p : keranjang.getDaftarProduk()) {
            detail.append(p.getNama()).append(" - Rp ").append(p.getHarga()).append("\n");
        }

        double total = keranjang.hitungTotal();
        detail.append("\nTotal: Rp ").append(total);
        area.setText(detail.toString());

        JTextField bayarField = new JTextField();
        JLabel kembalianLabel = new JLabel("Kembalian: Rp 0");

        JButton bayarButton = new JButton("Bayar");
        bayarButton.addActionListener(e -> {
            try {
                double bayar = Double.parseDouble(bayarField.getText());
                if (bayar < total) {
                    JOptionPane.showMessageDialog(this, "Uang tidak cukup!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double kembalian = bayar - total;
                    kembalianLabel.setText("Kembalian: Rp " + kembalian);
                    JOptionPane.showMessageDialog(this, "Terima kasih telah berbelanja!");
                    keranjang.kosongkan(); // reset keranjang
                    dispose(); // tutup frame setelah selesai
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Masukkan jumlah uang yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            CheckoutFrame checkoutFrame = new CheckoutFrame(keranjang); // keranjang yang sedang aktif
            checkoutFrame.setVisible(true);
        });

        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        inputPanel.add(new JLabel("Masukkan jumlah uang:"));
        inputPanel.add(bayarField);
        inputPanel.add(kembalianLabel);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(bayarButton, BorderLayout.SOUTH);

        add(new JScrollPane(area), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
