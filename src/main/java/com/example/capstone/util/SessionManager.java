package com.example.capstone.util;

import com.example.capstone.model.User;
import java.io.*;

// SRP: This class has ONE job — manage the user session file
public class SessionManager {

    private static final String SESSION_FILE = "session.dat";

    public static void saveSession(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SESSION_FILE))) {
            oos.writeObject(user);
            System.out.println("Session saved for: " + user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loadSession() {
        File file = new File(SESSION_FILE);
        if (!file.exists()) return null;
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SESSION_FILE))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void clearSession() {
        File file = new File(SESSION_FILE);
        if (file.exists()) {
            file.delete();
            System.out.println("Session cleared.");
        }
    }

    public static boolean isLoggedIn() {
        return new File(SESSION_FILE).exists();
    }
}