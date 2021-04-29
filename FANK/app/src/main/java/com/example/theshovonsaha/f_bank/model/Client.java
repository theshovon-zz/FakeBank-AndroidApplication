package com.example.theshovonsaha.f_bank.model;


public class Client {
    private String name;
    private double balance;
    private Transaction[] history;
    private final int MAX_SIZE_HISTORY = 10;
    private int not;

    public Client(String name, double balance) {
        history = new Transaction[MAX_SIZE_HISTORY];
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        addTransaction("DEPOSIT", amount);
    }

    public void withdraw(double amount) {
        this.balance -= amount;
        addTransaction("WITHDRAW", amount);
    }

    public String[] getStatement() {
        String[] statement = new String[this.not + 1];
        statement[0] = this.getStatus();
        for(int i = 0; i < not; i ++) {
            statement[i + 1] = history[i].getStatus();
        }
        return statement;
    }

    public String getStatus() {
        return this.name + ": $" + String.format("%.2f", this.balance);
    }

    /*
     * Methods not required by the JUnit tests
     */
    public String getName() {
        return this.name;
    }

    public double getBalance() {
        return this.balance;
    }

    private void addTransaction(String type, double amount) {
        Transaction t = new Transaction(type, amount);
        history[not] = t;
        not ++;
    }
}
