package ma.skypay;


public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        try {
            account.deposit(1000);
            System.out.println("Balance after deposit: " + account.getBalance());
            account.deposit(2000);
            System.out.println("Balance after second deposit: " + account.getBalance());
            account.withdraw(500);
            System.out.println("Balance after withdrawal: " + account.getBalance());

            System.out.println("=== Bank Statement ===");
            account.printStatement();

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}