package fatsby.manager;

import java.io.*;

public class User {
    private String username;
    private int money = 0;
    private File file_money;
    private File file_username;

    public User() {}

    public User(String username, int money) {
        this.username = username;
        this.money = money;
        // Define the directory path where the file should be created
        String directoryPath = "src/main/java/fatsby/manager/users/" + username;
        // Define the file path including the directory
        String filePath = directoryPath + "/money.txt";
        String filename = directoryPath + "/users.txt";
        // Create a File object for the directory
        File directory = new File(directoryPath);
        // Create a File object for the file
        this.file_money = new File(filePath);
        this.file_username = new File(filename);

        try {
            // Attempt to create the directory if it doesn't exist
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Create the file and write the initial money value to it
            if (!file_money.exists()) {
                file_money.createNewFile();
                setMoney(money); // Use setMoney to handle the file writing
            }
            if (!file_username.exists()) {
                file_username.createNewFile();
                try (FileWriter fw = new FileWriter(file_username);){
                    fw.write(username);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMoney(int money) throws IOException {
        this.money = money;
        try (FileWriter fw = new FileWriter(file_money);){
            fw.write(Integer.toString(this.money));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addMoney(int money) throws IOException {
        this.money += money;
        try (FileWriter fw = new FileWriter(file_money);){
            fw.write(Integer.toString(this.money));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void subMoney(int money) throws IOException {
        this.money -= money;
        try (FileWriter fw = new FileWriter(file_money);){
            fw.write(Integer.toString(this.money));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMoneyFromDB() throws IOException {
        String directoryPath = "src/main/java/fatsby/manager/users/" + username;
        // Define the file path including the directory
        String filePath = directoryPath + "/money.txt";
        file_money = new File(filePath);
        if (file_money.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file_money))) {
                String value = br.readLine();
                this.money = Integer.parseInt(value);
            }
        } else {
            System.err.println("File money.txt does not exist or has not been initialized.");
        }
    }

    public int getMoney(){
        return money;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
