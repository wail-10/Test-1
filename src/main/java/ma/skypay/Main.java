package ma.skypay;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        try {
            account.setTransactionDate(LocalDate.of(2012, 1, 10));
            account.deposit(1000);
            System.out.println("Balance after deposit: " + account.getBalance());
            account.setTransactionDate(LocalDate.of(2012, 1, 13));
            account.deposit(2000);
            System.out.println("Balance after second deposit: " + account.getBalance());
            account.setTransactionDate(LocalDate.of(2012, 1, 14));
            account.withdraw(500);
            System.out.println("Balance after withdrawal: " + account.getBalance());

            System.out.println("=== Bank Statement ===");
            account.printStatement();

            System.out.println("\n=== Testing Exception Handling ===");

            try {
                account.withdraw(-100);
            } catch (IllegalArgumentException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            try {
                account.withdraw(10000); // More than balance
            } catch (IllegalArgumentException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }

            try {
                account.deposit(0);
            } catch (IllegalArgumentException e) {
                System.out.println("Caught expected exception: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}