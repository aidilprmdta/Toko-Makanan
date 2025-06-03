package Gui;

import Gui.KeranjangFrame;
import Model.Produk;
import Model.Keranjang;
import Service.ProdukService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class HomeFrame extends JFrame {
    private ProdukService produkService = new ProdukService();
    private Keranjang keranjang = new Keranjang();
    private JPanel produkPanel;
    private List<Produk> produkList;
    private JComboBox<String> sortBox;
    private JTextField searchField;

    public HomeFrame() {
        setTitle("FIMA FOODIE - Home");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        produkList = new ArrayList<>(produkService.getSemuaProduk());

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel atas (search + sort)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Cari");

        searchBtn.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            produkList = filterProduk(keyword);
            urutkanProduk((String) sortBox.getSelectedItem());
            renderProduk();
        });

        topPanel.add(new JLabel("Cari:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);

        String[] opsiSort = {"Default", "Nama A-Z", "Harga Termurah", "Harga Termahal", "Makanan", "Minuman"};
        sortBox = new JComboBox<>(opsiSort);
        sortBox.addActionListener((ActionEvent e) -> {
            urutkanProduk((String) sortBox.getSelectedItem());
            renderProduk();
        });

        topPanel.add(new JLabel(" | Urutkan:"));
        topPanel.add(sortBox);

        produkPanel = new JPanel();
        produkPanel.setLayout(new GridLayout(0, 4, 5, 5));
        produkPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        renderProduk();

        // Tombol lihat keranjang
        JButton lihatKeranjangBtn = new JButton("Lihat Keranjang");
        lihatKeranjangBtn.addActionListener(e -> {
            new KeranjangFrame(keranjang).setVisible(true);
            this.dispose();
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(produkPanel), BorderLayout.CENTER);
        mainPanel.add(lihatKeranjangBtn, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void renderProduk() {
        produkPanel.removeAll();

        if (produkList.isEmpty()) {
            produkPanel.add(new JLabel("Produk tidak ditemukan."));
        } else {
            for (Produk p : produkList) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
                panel.setBackground(Color.WHITE);
                panel.setPreferredSize(new Dimension(180, 220));
                panel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Gambar
                try {
                    ImageIcon icon = new ImageIcon(p.getGambarPath());
                    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    JLabel labelGambar = new JLabel(new ImageIcon(img));
                    labelGambar.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(Box.createVerticalStrut(10));
                    panel.add(labelGambar);
                } catch (Exception ex) {
                    JLabel labelGambar = new JLabel("Gambar tidak ditemukan");
                    labelGambar.setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(labelGambar);
                }

                // Nama Produk
                JLabel labelNama = new JLabel(p.getNama());
                labelNama.setFont(new Font("Arial", Font.BOLD, 14));
                labelNama.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Harga Produk
                JLabel labelHarga = new JLabel("Rp " + p.getHarga());
                labelHarga.setFont(new Font("Arial", Font.PLAIN, 12));
                labelHarga.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Tombol Tambah
                JButton btn = new JButton("Tambah");
                btn.setAlignmentX(Component.CENTER_ALIGNMENT);
                btn.addActionListener((ActionEvent e) -> {
                    keranjang.tambahProduk(p);
                    JOptionPane.showMessageDialog(this, p.getNama() + " ditambahkan ke keranjang.");
                });

                panel.add(Box.createVerticalStrut(5));
                panel.add(labelNama);
                panel.add(labelHarga);
                panel.add(Box.createVerticalStrut(5));
                panel.add(btn);

                produkPanel.add(panel);
            }
        }

        produkPanel.revalidate();
        produkPanel.repaint();
    }

    private void urutkanProduk(String opsi) {
        switch (opsi) {
            case "Nama A-Z":
                produkList.sort(Comparator.comparing(Produk::getNama));
                break;
            case "Harga Termurah":
                produkList.sort(Comparator.comparingDouble(Produk::getHarga));
                break;
            case "Harga Termahal":
                produkList.sort((a, b) -> Double.compare(b.getHarga(), a.getHarga()));
                break;
            case "Makanan":
                produkList = new ArrayList<>();
                for (Produk produk : produkService.getSemuaProduk()) {
                    if (produk.getKategori().equalsIgnoreCase("Makanan")) {
                        produkList.add(produk);
                    }
                }
                break;
            case "Minuman":
                produkList = new ArrayList<>();
                for (Produk produk : produkService.getSemuaProduk()) {
                    if (produk.getKategori().equalsIgnoreCase("Minuman")) {
                        produkList.add(produk);
                    }
                }
                break;
            default:
                produkList = new ArrayList<>(produkService.getSemuaProduk());
                break;
        }
    }

    private List<Produk> filterProduk(String keyword) {
        if (keyword.isEmpty()) return new ArrayList<>(produkService.getSemuaProduk());

        List<Produk> hasil = new ArrayList<>();
        for (Produk p : produkService.getSemuaProduk()) {
            if (p.getNama().toLowerCase().contains(keyword)) {
                hasil.add(p);
            }
        }
        return hasil;
    }
}
