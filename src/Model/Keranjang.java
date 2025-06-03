package Model;

import java.util.ArrayList;
import java.util.List;

public class Keranjang {
    private List<Produk> daftarProduk = new ArrayList<>();

    public void tambahProduk(Produk produk) {
        daftarProduk.add(produk);
    }

    public void hapusProduk(Produk produk) {
        daftarProduk.remove(produk);
    }

    public List<Produk> getDaftarProduk() {
        return daftarProduk;
    }

    public int hitungTotal() {
        int total = 0;
        for (Produk p : daftarProduk) {
            total += p.getHarga();
        }
        return total;
    }

    public void kosongkan() {
        daftarProduk.clear();
    }
}
