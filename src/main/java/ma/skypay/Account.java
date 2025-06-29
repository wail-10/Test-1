package ma.skypay;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService{
    private List<Transaction> transactions;
    private int balance;

    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = 0;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        this.balance += amount;
        transactions.add(new Transaction(LocalDate.now(), amount, this.balance, TransactionType.DEPOSIT));
    }

    @Override
    public void withdraw(int amount) {

    }

    @Override
    public void printStatement() {

    }

    private static class Transaction {
        private LocalDate date;
        private int amount;
        private int balanceAfterTransaction;
        private TransactionType type;

        public Transaction(LocalDate date, int amount, int balanceAfterTransaction, TransactionType type) {
            this.date = date;
            this.amount = amount;
            this.balanceAfterTransaction = balanceAfterTransaction;
            this.type = type;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");
            String formattedDate = date.format(formatter);
            return formattedDate + " || " + (type == TransactionType.DEPOSIT ? "" : "-") + amount + " || " + balanceAfterTransaction;
        }
    }

    private enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }
}
