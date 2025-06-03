package Gui;

import Model.Keranjang;
import Model.Produk;
import Service.TransaksiService;

import javax.swing.*;
import java.awt.*;

public class KeranjangFrame extends JFrame {
    private Keranjang keranjang;
    private JPanel panel;

    public KeranjangFrame(Keranjang keranjang) {
        this.keranjang = keranjang;

        setTitle("Keranjang Belanja");
        setSize(350, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        tampilkanDaftarProduk();

        JButton checkoutBtn = new JButton("Checkout");
        JButton backBtn = new JButton("Kembali");

        checkoutBtn.addActionListener(e -> {
            TransaksiService transaksiService = new TransaksiService();
            transaksiService.checkout(keranjang);
            tampilkanDaftarProduk(); // Refresh setelah checkout
        });

        backBtn.addActionListener(e -> {
            new HomeFrame().setVisible(true);
            this.dispose();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backBtn);
        buttonPanel.add(checkoutBtn);

        add(new JScrollPane(panel), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void tampilkanDaftarProduk() {
        panel.removeAll();

        if (keranjang.getDaftarProduk().isEmpty()) {
            panel.add(new JLabel("Keranjang kosong."));
        } else {
            for (Produk p : keranjang.getDaftarProduk()) {
                panel.add(new JLabel(p.getNama() + " - Rp " + p.getHarga()));
            }

            panel.add(Box.createVerticalStrut(10));
            panel.add(new JLabel("Total: Rp " + keranjang.hitungTotal()));
        }

        panel.revalidate();
        panel.repaint();
    }
}
