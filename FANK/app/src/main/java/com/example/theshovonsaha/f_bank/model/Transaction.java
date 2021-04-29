package com.example.theshovonsaha.f_bank.model;


public class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getStatus() {
        String formattedAmount = String.format("%.2f", amount);
        return "Transaction " + this.type + ": $" + formattedAmount;
    }
}
