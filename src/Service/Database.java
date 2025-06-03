package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:src/Res/TokoOnline.db");
    }

    public static void setupDatabase() {
        try (Connection conn = getConnection()) {
            System.out.println("Koneksi berhasil ke database SQLite.");
        } catch (SQLException e) {
            System.err.println("Gagal koneksi ke database: " + e.getMessage());
        }
    }
}
