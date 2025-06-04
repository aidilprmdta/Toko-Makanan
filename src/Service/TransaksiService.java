package Service;

import Model.Keranjang;
import Model.Produk;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransaksiService {

    public void checkout(Keranjang keranjang) {
        if (keranjang.getDaftarProduk().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Keranjang masih kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            System.out.println("Keranjang masih kosong!"); // Cetak ke console
            return;
        }

        int totalBayar = keranjang.hitungTotal();
        StringBuilder struk = new StringBuilder();
        struk.append("=========== FAIM FOODIE ===========\n");
        struk.append("     Jl. Garuda Sakti KM 1 no 21   \n");
        struk.append("     No. Tlp 082263247210        \n\n");

        LocalDateTime sekarang = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String waktuStr = sekarang.format(format);
        struk.append("Tanggal & Waktu: ").append(waktuStr).append("\n\n");

        struk.append("Daftar Produk:\n");
        for (Produk p : keranjang.getDaftarProduk()) {
            struk.append("- ").append(p.getNama()).append(" = Rp ").append(p.getHarga()).append("\n");
        }

        struk.append("\nTotal Bayar    : Rp ").append(totalBayar).append("\n");

        // Cetak struk ke console sebelum menampilkan dialog input
        System.out.println(struk.toString());

        String inputUang = JOptionPane.showInputDialog(null,
                struk.toString() + "\nMasukkan jumlah uang pembeli (Rp):",
                "Pembayaran", JOptionPane.PLAIN_MESSAGE);

        if (inputUang == null) {
            // User menekan cancel
            System.out.println("Transaksi dibatalkan oleh pengguna"); // Cetak ke console
            return;
        }

        try {
            int uangDibayar = Integer.parseInt(inputUang);

            if (uangDibayar < totalBayar) {
                String pesanError = "Uang tidak cukup! Transaksi dibatalkan.";
                JOptionPane.showMessageDialog(null,
                        pesanError,
                        "Transaksi Gagal", JOptionPane.ERROR_MESSAGE);
                System.out.println(pesanError); // Cetak ke console
            } else {
                int kembalian = uangDibayar - totalBayar;
                String hasil = struk.toString() +
                        "Uang Diterima  : Rp " + uangDibayar + "\n" +
                        "Kembalian      : Rp " + kembalian + "\n\n" +
                        "Terima kasih telah berbelanja!";
                JOptionPane.showMessageDialog(null, hasil, "Struk Pembayaran", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(hasil); // Cetak ke console
                keranjang.kosongkan();
            }
        } catch (NumberFormatException e) {
            String pesanError = "Input tidak valid! Masukkan angka saja.";
            JOptionPane.showMessageDialog(null,
                    pesanError,
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(pesanError); // Cetak ke console
        }
    }
}
