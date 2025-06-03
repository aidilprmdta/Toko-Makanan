package Service;

import Model.Produk;

import java.util.ArrayList;
import java.util.List;

public class ProdukService {
    private List<Produk> daftarProduk = new ArrayList<>();

    public ProdukService() {
        // Data produk yang akan ditambahkan ke dalam daftar
        daftarProduk.add(new Produk("P001", "Bakso Mercon", 25000,"Makanan","src/Res/1.jpg"));
        daftarProduk.add(new Produk("P002", "Mie Aceh", 18000,"Makanan", "src/Res/2.jpg"));
        daftarProduk.add(new Produk("P003", "Mie Ayam", 15000,"Makanan", "src/Res/3.jpg"));
        daftarProduk.add(new Produk("P004", "Mie Goreng", 13000, "Makanan", "src/Res/4.jpg"));
        daftarProduk.add(new Produk("P005", "Mie Balap", 17000, "Makanan","src/Res/5.jpg"));
        daftarProduk.add(new Produk("P006", "Mie Padeh", 8000, "Makanan","src/Res/6.jpg"));
        daftarProduk.add(new Produk("P007", "Pangsit Goreng", 10000, "Makanan","src/Res/7.jpg"));
        daftarProduk.add(new Produk("P008", "Mie Pangsit", 15000, "Makanan","src/Res/8.jpg"));
        daftarProduk.add(new Produk("P009", "Mie Terbang", 12000, "Makanan","src/Res/9.jpg"));
        daftarProduk.add(new Produk("P0010", "Bakso Kosong", 13000, "Makanan","src/Res/10.jpg"));
        daftarProduk.add(new Produk("P0011", "MieDang", 18000, "Makanan","src/Res/11.jpg"));
        daftarProduk.add(new Produk("P0012", "Sirup Gelas ABC", 7000, "Minuman","src/Res/12.jpg"));
        daftarProduk.add(new Produk("P0013", "Kopi Hitam", 7000, "Minuman","src/Res/13.jpg"));
        daftarProduk.add(new Produk("P0014", "Jus Alpukat", 8000, "Minuman","src/Res/14.jpg"));
        daftarProduk.add(new Produk("P0015", "Jus Wortel", 8000, "Minuman","src/Res/15.jpg"));
        daftarProduk.add(new Produk("P0016", "Jus Pinang", 8000, "Minuman","src/Res/16.jpg"));
        daftarProduk.add(new Produk("P0017", "Jus Mangga", 8000, "Minuman","src/Res/17.jpg"));
        daftarProduk.add(new Produk("P0018", "Jus Kuini", 8000, "Minuman","src/Res/18.jpg"));
        daftarProduk.add(new Produk("P0019", "Pangsit Kuah", 13000, "Makanan","src/Res/19.jpg"));
        daftarProduk.add(new Produk("P0020", "Bakso Oleng", 10000, "Makanan","src/Res/20.jpg"));
        daftarProduk.add(new Produk("P0021", "Minas", 13000, "Makanan","src/Res/21.jpg"));
        daftarProduk.add(new Produk("P0022", "Soto", 10000, "Makanan","src/Res/22.jpg"));
        daftarProduk.add(new Produk("P0023", "Bakso Kosong", 13000, "Makanan","src/Res/23.jpg"));
        daftarProduk.add(new Produk("P0024", "Gacoan Kw", 8000, "Makanan","src/Res/24.jpg"));
        daftarProduk.add(new Produk("P0025", "Milo", 8000, "Minuman","src/Res/25.jpg"));
        daftarProduk.add(new Produk("P0026", "Es Teh", 5000, "Minuman","src/Res/26.jpg"));
        daftarProduk.add(new Produk("P0027", "Cappucino", 8000, "Minuman","src/Res/27.jpg"));
        daftarProduk.add(new Produk("P0028", "Bakso Urat", 15000, "Makanan","src/Res/28.jpg"));
        daftarProduk.add(new Produk("P0029", "Mie Rebus", 12000, "Makanan","src/Res/29.jpg"));
        daftarProduk.add(new Produk("P0030", "Mie Ayam Bakso", 15000, "Makanan","src/Res/30.jpg"));
        daftarProduk.add(new Produk("P0031", "Mie Kocok", 5000, "Makanan","src/Res/31.jpg"));
        daftarProduk.add(new Produk("P0032", "Sirup Jeruk ABC", 7000, "Minuman","src/Res/32.jpg"));
        daftarProduk.add(new Produk("P0033", "Mie Orak Arik", 15000, "Makanan","src/Res/33.jpg"));
        daftarProduk.add(new Produk("P0034", "Mie Ayam Bakso", 15000, "Makanan","src/Res/34.jpg"));
        daftarProduk.add(new Produk("P0035", "Matcha Susu", 118000, "Makanan","src/Res/35.jpg"));
        daftarProduk.add(new Produk("P0034", "Americano", 18000, "Makanan","src/Res/36.jpg"));
    }

    public List<Produk> getSemuaProduk() {
        return daftarProduk;
    }
}