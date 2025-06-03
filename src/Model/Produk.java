package Model;

public class Produk {
    private String id;
    private String nama;
    private int harga;
    private String kategori;
    private String gambarPath;

    public Produk(String id, String nama, int harga, String kategori, String gambarPath) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.gambarPath = gambarPath;
    }

    public String getId() {
        return id;
    }
    public String getNama() {
        return nama;
    }
    public int getHarga() {
        return harga;
    }
    public String getKategori() {
        return kategori;
    }
    public  String getGambarPath() {
        return gambarPath;
    }


    @Override
    public String toString() {
        return nama + " - Rp " + harga;
    }
}
