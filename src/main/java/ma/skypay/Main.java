package ma.skypay;


public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        try {
            account.deposit(1000);
            System.out.println("Balance after deposit: " + account.getBalance());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}