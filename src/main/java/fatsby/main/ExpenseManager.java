package fatsby.main;

import fatsby.manager.expenseWriter;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExpenseManager extends JFrame {
    private MoneyAddedListener moneyAddedListener;

    public ExpenseManager(MoneyAddedListener listener) {
        this.moneyAddedListener = listener;
        init();
    }
    private void init() {
        setTitle("Add or Remove Expense");
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        tabbedPane = new JTabbedPane();
        addExpensePanel = new JPanel( new MigLayout("wrap, fillx", "fill"));
        reasonLabel = new JLabel("Reason:");
        amountLabel = new JLabel("Amount:");
        reasonTextField = new JTextField(10);
        amountTextField = new JTextField(10);
        addButton = new JButton("Add Expense");

        //Add Panel
        addExpensePanel.add(amountLabel, "");
        addExpensePanel.add(amountTextField, "gapy 10");
        addExpensePanel.add(reasonLabel, "");
        addExpensePanel.add(reasonTextField, "gapy 10");
        addExpensePanel.add(addButton, "gapy 10");
        addButton.addActionListener(e -> {
            amount = amountTextField.getText();
            String reason = reasonTextField.getText();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            String nowStr = now.format(dtf);
            try {
                expenseWriter.writeExpense(amount, reason, nowStr);
                moneyAddedListener.onMoneyAdded(Integer.parseInt(amount));
                JOptionPane.showMessageDialog(null, "Expense Added");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Failed to add Expense");
                throw new RuntimeException(ex);
            }
        });



        tabbedPane.addTab("Add Expenses", addExpensePanel);
        add(tabbedPane);

    }

    private JTabbedPane tabbedPane;
    private JPanel addExpensePanel;
    private JLabel reasonLabel;
    private JLabel amountLabel;
    private JTextField amountTextField;
    private JButton addButton;
    private  JTextField reasonTextField;
    public static String amount;

}
