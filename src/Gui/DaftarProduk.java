package Gui;

import Model.Produk;
import Service.ProdukService;

import javax.swing.*;
import java.awt.*;

public class DaftarProduk extends JFrame {
    private ProdukService produkService = new ProdukService();

    public DaftarProduk() {
        setTitle("Daftar Produk");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

        for (Produk p : produkService.getSemuaProduk()) {
            JPanel itemPanel = new JPanel(new BorderLayout());
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            try {
                ImageIcon icon = new ImageIcon(p.getGambarPath());
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                JLabel imageLabel = new JLabel(new ImageIcon(img));
                itemPanel.add(imageLabel, BorderLayout.WEST);
            } catch (Exception e) {
                itemPanel.add(new JLabel("No Image"), BorderLayout.WEST);
            }

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(new JLabel("<html><b>" + p.getNama() + "</b><br>Rp " + p.getHarga() + "</html>"));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JButton detailButton = new JButton("Detail");
            JButton beliButton = new JButton("Beli");
            JButton tambahButton = new JButton("Tambah Produk");

            detailButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "ID: " + p.getId() +
                                "\nNama: " + p.getNama() +
                                "\nHarga: Rp " + p.getHarga() +
                                "\nKategori: " + p.getKategori(),
                        "Detail Produk",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            beliButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this,
                        "Produk " + p.getNama() + " ditambahkan ke keranjang!",
                        "Beli Produk",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            buttonPanel.add(detailButton);
            buttonPanel.add(beliButton);
            buttonPanel.add(tambahButton);
            infoPanel.add(buttonPanel);

            itemPanel.add(infoPanel, BorderLayout.CENTER);
            containerPanel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(containerPanel);
        add(scrollPane);
    }

    public void refresh() {
        this.dispose();
        new DaftarProduk().setVisible(true);
    }

    public static void main(String[] args) {
        new DaftarProduk().setVisible(true);
    }
}
