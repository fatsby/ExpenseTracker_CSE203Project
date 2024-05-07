package fatsby.manager;

import fatsby.login.rememberMe;
import fatsby.main.SidePanel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class expenseWriter{


    public static void writeExpense(String amount, String reason, String time) throws IOException {
        File history;
        String directoryPath = "src/main/java/fatsby/manager/users/" + rememberMe.getUsername();
        // Define the file path including the directory
        String filePath = directoryPath + "/history.txt";
        history = new File(filePath);
        if (!history.exists()) {
            history.createNewFile();
            try (FileWriter fw = new FileWriter(history, true);){
                fw.write(amount+":"+reason+":"+time+System.lineSeparator());
            }
        } else {
            try (FileWriter fw = new FileWriter(history, true);){
                fw.write(amount+":"+reason+":"+time+System.lineSeparator());
            }
        }
    }
}
