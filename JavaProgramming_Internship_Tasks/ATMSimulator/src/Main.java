import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Create a bank account with an initial balance and PIN
        BankAccount account = new BankAccount(10000.0, "4321");

        // Launch the ATM interface
        SwingUtilities.invokeLater(() -> new ATM(account));
    }
}
