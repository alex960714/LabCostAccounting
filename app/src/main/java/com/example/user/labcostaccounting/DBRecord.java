package com.example.user.labcostaccounting;

public class DBRecord {
    private int id;
    private String name;
    private boolean isAsset;
    private Double amount;

    public DBRecord() { }

    public DBRecord(int id, String name, boolean isAsset, Double amount) {
        this.id = id;
        this.name = name;
        this.isAsset = isAsset;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAsset() {
        return isAsset;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}