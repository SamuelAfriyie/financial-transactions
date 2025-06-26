package com.example.financial_transactions.model;

public class Payment {
    private String id;

    public Payment(String id) {
        this.id = id;
    }

    public Payment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
