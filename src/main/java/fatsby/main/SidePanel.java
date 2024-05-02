package fatsby.main;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.login.Login;
import fatsby.login.Register;
import fatsby.login.rememberMe;
import fatsby.manager.FormsManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SidePanel extends JPanel {
    public SidePanel() throws IOException {
        init();
    }
    private void init() throws IOException {
        signOutButton = new JButton("Sign Out");
        username = new JLabel();

        setLayout(new MigLayout("fill, insets 20", "[center]", "[]"));
        JPanel panel = new JPanel(new MigLayout("wrap", ""));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JLabel fatsbycc = new JLabel("fatsby.cc");
        ImageIcon fatsbyIcon = new ImageIcon(getClass().getResource("/icons/leafvillage.png"));
        fatsbycc.setIcon(fatsbyIcon);
        fatsbycc.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");


        ImageIcon signOut = new ImageIcon(getClass().getResource("/icons/Logout.png"));
        //Resize icon
        Image i = signOut.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        signOut = new ImageIcon(i);
        signOutButton.setIcon(signOut);
        signOutButton.setContentAreaFilled(false);
        signOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOutButton.addActionListener(e -> {
            try {
                rememberMe.rememberWrite(rememberMe.getUsername(), false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            FormsManager.getInstance().showForm(new Login());
        });
        username.setText("Hello, " + rememberMe.getUsername());

        panel.add(fatsbycc, "center, wrap");
        panel.add(username, "left, wrap");
        panel.add(signOutButton, "center, wrap");
        add(panel, "dock west, gap 6 6 6 6, width 165!");
    }

    private JButton signOutButton;
    private JLabel username;
}
