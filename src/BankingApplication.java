import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public BankAccount(int accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Current balance: $" + balance);
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History for Account Number " + accountNumber);
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

class Transaction {
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        // Add code to set the current date for the transaction.
    }

    @Override
    public String toString() {
        return type + ": $" + amount + " on " + date;
    }
}

class Bank {
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

public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            try {
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Display Transaction History");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the account holder's name: ");
                        scanner.nextLine();
                        String accountHolderName = scanner.nextLine();
                        bank.createAccount(accountHolderName);
                        break;
                    case 2:
                        System.out.print("Enter the account number: ");
                        int depositAccountNumber = scanner.nextInt();
                        BankAccount depositAccount = bank.findAccount(depositAccountNumber);
                        if (depositAccount != null) {
                            System.out.print("Enter the amount to deposit: $");
                            double depositAmount = scanner.nextDouble();
                            depositAccount.deposit(depositAmount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the account number: ");
                        int withdrawAccountNumber = scanner.nextInt();
                        BankAccount withdrawAccount = bank.findAccount(withdrawAccountNumber);
                        if (withdrawAccount != null) {
                            System.out.print("Enter the amount to withdraw: $");
                            double withdrawAmount = scanner.nextDouble();
                            withdrawAccount.withdraw(withdrawAmount);
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the account number: ");
                        int checkBalanceAccountNumber = scanner.nextInt();
                        BankAccount checkBalanceAccount = bank.findAccount(checkBalanceAccountNumber);
                        if (checkBalanceAccount != null) {
                            System.out.println("Account Holder Name: " + checkBalanceAccount.getAccountHolderName());
                            System.out.println("Account Balance: $" + checkBalanceAccount.getBalance());
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter the account number: ");
                        int transactionHistoryAccountNumber = scanner.nextInt();
                        BankAccount transactionHistoryAccount = bank.findAccount(transactionHistoryAccountNumber);
                        if (transactionHistoryAccount != null) {
                            transactionHistoryAccount.displayTransactionHistory();
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 6:
                        System.out.println("Thank you for using the Banking Application!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Clear the input buffer.
            }
        }

    }
}
