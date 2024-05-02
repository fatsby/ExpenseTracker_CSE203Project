package fatsby.main;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class SidePanel extends JPanel {
    public SidePanel(){
        init();
    }
    private void init(){
        signOutButton = new JButton("Sign Out");

        setLayout(new MigLayout("fill, insets 20", "[center]", "[]"));
        JPanel panel = new JPanel(new MigLayout("wrap", ""));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JLabel fatsbycc = new JLabel("fatsby.cc");
        fatsbycc.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");

        ImageIcon signOut = new ImageIcon((Objects.requireNonNull(getClass().getResource("Logout.png"))));
        signOutButton.setContentAreaFilled(false);
        signOutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOutButton.setIcon(signOut);

        panel.add(fatsbycc, "center, wrap");
        panel.add(signOutButton);
        add(panel, "dock west, gap 6 6 6 6, width 140!");
    }

    private JButton signOutButton;
}
