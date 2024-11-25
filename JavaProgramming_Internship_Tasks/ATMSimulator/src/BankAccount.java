import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private String pin;
    private List<String> transactionHistory;

    public BankAccount(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited : Rs. " + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrew : Rs. " + amount);
            return true;
        } else {
            transactionHistory.add("Failed withdrawal attempt : Rs. " + amount);
            return false;
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
