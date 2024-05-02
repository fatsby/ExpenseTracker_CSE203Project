package fatsby.main;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import fatsby.login.Login;
import fatsby.login.rememberMe;
import fatsby.manager.FormsManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class Application extends JFrame {
    public Application() throws IOException {
        init();
    }

    private void init() throws IOException {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 700));
        setLocationRelativeTo(null);
        setResizable(false);
        if (rememberMe.checkRemember()){
            setContentPane(new SidePanel());
        }else{
            setContentPane(new Login());
        }
        FormsManager.getInstance().initApplication(this);

    }
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatMacLightLaf.registerCustomDefaultsSource("fatsby.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY,Font.PLAIN,13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> {
            try {
                new Application().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
