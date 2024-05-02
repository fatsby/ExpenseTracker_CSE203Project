package fatsby.login;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class loginMechanics {
    private loginMechanics(){
    }

    public static void saveRegistrationInfo(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            try (FileWriter writer = new FileWriter("users.txt", true)) {
                writer.write(username + ":" + hashedPassword + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing the password");
        }
    }

    private static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean checkLogin(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userInfo = line.split(":");
                    if (userInfo[0].equals(username) && userInfo[1].equals(hashedPassword)) {
                        return true; // Login successful
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing the password");
        }
        return false; // Login failed
    }
}
