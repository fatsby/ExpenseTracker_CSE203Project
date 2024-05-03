package fatsby.main;

import com.formdev.flatlaf.FlatClientProperties;
import fatsby.login.Login;
import fatsby.login.Register;
import fatsby.login.rememberMe;
import fatsby.manager.FormsManager;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.dashicons.Dashicons;
import org.kordamp.ikonli.fluentui.FluentUiFilledAL;
import org.kordamp.ikonli.paymentfont.PaymentFont;
import org.kordamp.ikonli.swing.FontIcon;
import org.kordamp.ikonli.websymbols.Websymbols;

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
        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.white);
        separator.setVisible(true);

        setLayout(new MigLayout("fill, insets 20", "[center]", "[]"));
        JPanel panel = new JPanel(new MigLayout("wrap", ""));
        panel.putClientProperty(FlatClientProperties.STYLE,"" +
                "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        JLabel Ezmoney = new JLabel("Ezmoney");
        FontIcon EzmoneyIcon = FontIcon.of(FluentUiFilledAL.BUILDING_BANK_24);
        EzmoneyIcon.setIconColor(Color.WHITE);
        EzmoneyIcon.setIconSize(50);
        Ezmoney.setIcon(EzmoneyIcon);
        Ezmoney.putClientProperty(FlatClientProperties.STYLE,"" +
                "font:bold +10");


        FontIcon signOutIcon = FontIcon.of(Websymbols.LOGOUT);
        signOutIcon.setIconSize(16);
        signOutIcon.setIconColor(Color.white);
        signOutButton.setIcon(signOutIcon);
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

        //JTabbedPane
            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.setTabPlacement(JTabbedPane.LEFT);
            tabbedPane.setFont(new Font("FlatRobotoFont", Font.PLAIN, 18));
            tabbedPane.addTab("Dashboard", null);
            tabbedPane.addTab("Settings", null);

            //Icons
            FontIcon DashboardIcon = FontIcon.of(Dashicons.ADMIN_HOME);
            DashboardIcon.setIconColor(Color.WHITE);
            DashboardIcon.setIconSize(18);
            tabbedPane.setIconAt(0, DashboardIcon);
            FontIcon settingsIcon = FontIcon.of(Dashicons.ADMIN_SETTINGS);
            settingsIcon.setIconColor(Color.WHITE);
            settingsIcon.setIconSize(18);
            tabbedPane.setIconAt(1, settingsIcon);

            tabbedPane.putClientProperty("JTabbedPane.tabAreaAlignment", "fill");
            tabbedPane.putClientProperty("JTabbedPane.tabAlignment", "center");


            //Dashboard Panel
            JPanel dashboardPanel = new JPanel(new MigLayout("wrap, fillx"));
            dashboardPanel.add(new JLabel("Your bank accounts"));
            dashboardPanel.add(drawCreditCard(), "width 265, height 175");
            JButton addExpenseBTN = new JButton("Add Expense");
            dashboardPanel.add(addExpenseBTN, "wrap, gapy 10");
            JSeparator separator2 = new JSeparator();
            separator2.setVisible(true);
            separator2.setOrientation(SwingConstants.HORIZONTAL);
            separator2.setBackground(Color.WHITE);
            dashboardPanel.add(separator2, "growx, span, wrap, gaptop 10, gapbottom 10");
            JLabel prevExpenseLabel = new JLabel("Previous Expenses");
            prevExpenseLabel.putClientProperty(FlatClientProperties.STYLE,"font: bold +6");
            dashboardPanel.add(prevExpenseLabel);


            JPanel anotherPanel = new JPanel(new BorderLayout());
            anotherPanel.add(new JLabel("Another Content"), BorderLayout.CENTER);

            JPanel contentPanel = new JPanel(new CardLayout());
            contentPanel.add(dashboardPanel, "Dashboard");
            contentPanel.add(anotherPanel, "Settings");


            //Tab switch mechanic
        tabbedPane.addChangeListener(e -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()));
        });
        SwingUtilities.invokeLater(() -> {
            CardLayout cl = (CardLayout)(contentPanel.getLayout());
            cl.show(contentPanel, "Dashboard");
        });



        panel.add(Ezmoney, "center, wrap");
        panel.add(username, "left, wrap");
        panel.add(separator, "growx, span, wrap, gaptop 10, gapbottom 10");
        panel.add(tabbedPane, "center, wrap");
        panel.add(signOutButton, "center, wrap");
        add(panel, "dock west, gap 6 6 6 6, width 165!");
        add(contentPanel, "grow, push");
    }

    private Component drawCreditCard(){
        JPanel creditCard = new JPanel(new MigLayout("wrap", "fill"));
        creditCard.putClientProperty(FlatClientProperties.STYLE, "arc:20;" +
                "[light]background:darken(@background,3%);" +
                "[dark]background:lighten(@background,3%);");
        FontIcon visaIcon = FontIcon.of(PaymentFont.VISA);
        visaIcon.setIconSize(20);
        visaIcon.setIconColor(Color.white);
        JLabel bankName = new JLabel("MB BANK");
        bankName.putClientProperty(FlatClientProperties.STYLE, "font: bold +5");
        bankName.setIcon(visaIcon);
        JLabel moneyLabel = new JLabel("$1000");
        creditCard.add(bankName, "right");
        creditCard.add(new JLabel("Amount of money:"), "gapy 15");
        creditCard.add(moneyLabel, "wrap");

        JLabel ccNum = new JLabel("1234   5678   876   5432");
        ccNum.putClientProperty(FlatClientProperties.STYLE,"font: +5");
        creditCard.add(ccNum, "gapy 15");
        return creditCard;
    }

    private JButton signOutButton;
    private JLabel username;
}
