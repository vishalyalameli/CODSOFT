import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double checkBalance() {
        return balance;
    }
}

class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("Welcome to the ATM!");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("Please select an option (1/2/3/4): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    double balance = bankAccount.checkBalance();
                    System.out.println("Your account balance is $" + balance);
                    break;
                case "2":
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    if (bankAccount.deposit(depositAmount)) {
                        System.out.println("Deposited $" + depositAmount + " successfully.");
                    } else {
                        System.out.println("Invalid amount. Please enter a positive number.");
                    }
                    break;
                case "3":
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    if (bankAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrew $" + withdrawAmount + " successfully.");
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                    break;
                case "4":
                    System.out.println("Thank you for using the ATM!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1/2/3/4).");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your initial account balance: $");
        double initialBalance = Double.parseDouble(scanner.nextLine());
        BankAccount userAccount = new BankAccount(initialBalance);
        ATM atm = new ATM(userAccount);
        atm.run();
        scanner.close();
    }
}
