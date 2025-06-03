package Service;

import Model.Pengguna;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static final String FILE_NAME = "users.txt";

    public static boolean registerUser(Pengguna user) {
        List<Pengguna> users = loadUsers();

        //User
        for (Pengguna u : users) {
            if (u.getUsername().equalsIgnoreCase(user.getUsername())) {
                return false; // Username sudah digunakan
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Pengguna> loadUsers() {
        List<Pengguna> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return users;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.add(new Pengguna(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static boolean loginUser(String username, String password) {
        List<Pengguna> users = loadUsers();
        for (Pengguna user : users) {
            if (user.getUsername().equalsIgnoreCase(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
