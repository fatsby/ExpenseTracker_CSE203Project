package fatsby.login;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.manager.FormsManager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends JPanel {
    public Login() {
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();
        btnLogin = new JButton("Login");
        chkRemember = new JCheckBox("Remember me");
        JPanel panel1 = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "fill,250:280"));
        panel1.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,"" +
                "showRevealButton:true");

//        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter your username or Email");
//        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Enter your password");


        JLabel lbTitle = new JLabel("Welcome back!");
        lbTitle.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");
        JLabel description = new JLabel("Please sign in to access your account.");
        description.putClientProperty(FlatClientProperties.STYLE,"" +
                "[light]foreground:lighten(@foreground,30%);" +
                "[dark]foreground:darken(@foreground,3%);");
        panel1.add(lbTitle);
        panel1.add(description);
        panel1.add(new JLabel("Username:"), "gapy 10");
        panel1.add(txtUsername);
        panel1.add(new JLabel("Password:"), "gapy 10");
        panel1.add(txtPassword);
        panel1.add(chkRemember,"grow 0, gapy 10");
        panel1.add(btnLogin, "gapy 10");
        panel1.add(createSignUpLabel(), "gapy 10");
        add(panel1);
    }

    private Component createSignUpLabel(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 ,0));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "background:null");
        JButton regButton = new JButton("<html><a href=\"#\">Sign Up</a></html>");
        regButton.setContentAreaFilled(false);
        regButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        regButton.addActionListener(e -> {
            FormsManager.getInstance().showForm(new Register());
        });
        JLabel noAccountLabel = new JLabel("Don't have an account?");

        panel.add(noAccountLabel);
        panel.add(regButton);
        return panel;
    }

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JCheckBox chkRemember;
    private JButton btnLogin;
}
