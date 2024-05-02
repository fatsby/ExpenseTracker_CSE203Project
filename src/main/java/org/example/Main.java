package org.example;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.kitfox.svg.app.beans.SVGIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel MainPanel;
    private JPanel creditcardPanel;


    public Main() throws IOException {
        setContentPane(MainPanel);
        setTitle("Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setSize(420, 720);
        setLocationRelativeTo(null);

        tabbedPane1.putClientProperty("JTabbedPane.tabAreaAlignment", "fill");
        tabbedPane1.putClientProperty("JTabbedPane.tabIconPlacement", SwingConstants.TOP);
        //Set Icons
        SVGIcon moneyIcon = new SVGIcon();
        moneyIcon.setSvgURI(new File("icons/money_50x.svg").toURI());
        tabbedPane1.setIconAt(0, moneyIcon);

        SVGIcon starIcon = new SVGIcon();
        starIcon.setSvgURI(new File("icons/stars.svg").toURI());
        tabbedPane1.setIconAt(1, starIcon);

        SVGIcon settingsIcon = new SVGIcon();
        settingsIcon.setSvgURI(new File("icons/settings.svg").toURI());
        tabbedPane1.setIconAt(2, settingsIcon);


    }

    public static void main(String[] args) throws IOException {
        FlatMacLightLaf.setup();
        Main mainFrame = new Main();
    }

}