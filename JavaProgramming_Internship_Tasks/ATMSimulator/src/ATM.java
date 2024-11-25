import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATM extends JFrame {
    private BankAccount account;
    private JLabel messageLabel;
    private boolean isAuthenticated = false;

    public ATM(BankAccount account) {
        this.account = account;
        initializeUI();
    }

    private void initializeUI() {
        // Set up the frame
        setTitle("ATM Machine");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Instruction and message area
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JLabel instructionLabel = new JLabel("Welcome to the ATM!");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(instructionLabel);

        messageLabel = new JLabel("Please enter your PIN.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        topPanel.add(messageLabel);

        add(topPanel, BorderLayout.NORTH);

        // ATM buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transactionHistoryButton = new JButton("Transaction History");
        JButton exitButton = new JButton("Exit");

        buttonPanel.add(checkBalanceButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(transactionHistoryButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Action Listeners for buttons
        checkBalanceButton.addActionListener(e -> performCheckBalance());
        depositButton.addActionListener(e -> performDeposit());
        withdrawButton.addActionListener(e -> performWithdraw());
        transactionHistoryButton.addActionListener(e -> performTransactionHistory());
        exitButton.addActionListener(e -> System.exit(0));

        // PIN authentication
        authenticateUser();

        // Make the frame visible
        setVisible(true);
    }

    private void authenticateUser() {
        String inputPin = JOptionPane.showInputDialog(this, "Enter your 4-digit PIN:");
        if (inputPin != null && account.authenticate(inputPin)) {
            isAuthenticated = true;
            messageLabel.setText("Authentication successful. Please choose an option.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid PIN. Access denied.");
            System.exit(0);
        }
    }

    private void performCheckBalance() {
        if (isAuthenticated) {
            messageLabel.setText("Your current balance is : Rs. " + account.getBalance());
        }
    }

    private void performDeposit() {
        if (isAuthenticated) {
            String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                account.deposit(amount);
                messageLabel.setText("Deposited Rs. " + amount + " successfully!");
            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private void performWithdraw() {
        if (isAuthenticated) {
            String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                if (account.withdraw(amount)) {
                    messageLabel.setText("Withdrew Rs. " + amount + " successfully!");
                } else {
                    messageLabel.setText("Insufficient balance or invalid amount.");
                }
            } catch (NumberFormatException e) {
                messageLabel.setText("Invalid input. Please enter a numeric value.");
            }
        }
    }

    private void performTransactionHistory() {
        if (isAuthenticated) {
            StringBuilder history = new StringBuilder("<html>Transaction History :<br>");
            for (String transaction : account.getTransactionHistory()) {
                history.append(transaction).append("<br>");
            }
            history.append("</html>");
            messageLabel.setText(history.toString());
        }
    }
}
