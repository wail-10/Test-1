package ma.skypay;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService{
    private List<Transaction> transactions;
    private int balance;
    private LocalDate transactionDate;

    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = 0;
        this.transactionDate = null;
    }

    public int getBalance() {
        return balance;
    }

    public void setTransactionDate(LocalDate date) {
        this.transactionDate = date;
    }

    private LocalDate getTransactionDate() {
        return transactionDate != null ? transactionDate : LocalDate.now();
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        this.balance += amount;
        transactions.add(new Transaction(getTransactionDate(), amount, this.balance, TransactionType.DEPOSIT));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance -= amount;
        transactions.add(new Transaction(getTransactionDate(), amount, this.balance, TransactionType.WITHDRAWAL));
    }

    @Override
    public void printStatement() {
        System.out.println("Date || Amount || Balance");

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction transaction = transactions.get(i);
            System.out.println(transaction.toString());
        }
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
