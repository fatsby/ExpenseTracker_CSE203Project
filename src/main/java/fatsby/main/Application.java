package fatsby.main;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import fatsby.login.Login;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Application extends JFrame {
    public Application() {
        init();
    }
    private void init() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 700));
        setLocationRelativeTo(null);
        setContentPane(new Login());
    }
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatMacLightLaf.registerCustomDefaultsSource("fatsby.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY,Font.PLAIN,13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new Application().setVisible(true));
    }
}
