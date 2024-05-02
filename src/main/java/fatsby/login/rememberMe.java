package fatsby.login;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class rememberMe {
    private rememberMe(){
    }
    public static void rememberWrite(String username, boolean isRemember) throws IOException {
        try (FileWriter fw = new FileWriter("rememberme.txt");){
            fw.write(username + ":" + isRemember +System.lineSeparator());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean checkRemember() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("rememberme.txt"));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(":");
                // Check if the first part of the line is the username and the second part is "true"
                if (userInfo.length == 2 && Objects.equals(userInfo[1], "true")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUsername() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("rememberme.txt"));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userInfo = line.split(":");
               return userInfo[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
