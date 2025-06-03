//mengimport file gui dan service
import Gui.LoginFrame;
import Service.Database;

public class Main {
    public static void main(String[] args) {
        // Setup database jika belum ada
        Database.setupDatabase();

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}

