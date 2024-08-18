import java.util.ArrayList;

public class Bank {
    private ArrayList<BankAccount> accounts;
    private int accountNumberCounter;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.accountNumberCounter = 1000; // Start account numbers from 1000.
    }

    public void createAccount(String accountHolderName) {
        int accountNumber = accountNumberCounter++;
        BankAccount account = new BankAccount(accountNumber, accountHolderName);
        accounts.add(account);
        System.out.println("Account created successfully. Account Number: " + accountNumber);
    }

    public BankAccount findAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}
