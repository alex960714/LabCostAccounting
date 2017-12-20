package com.example.user.labcostaccounting;

public class DBRecord {
    private int id;
    private String name;
    private boolean isAsset;
    private Double amount;
    private int version;

    public DBRecord() { }

    public DBRecord(int id, String name, boolean isAsset, Double amount, int version) {
        this.id = id;
        this.name = name;
        this.isAsset = isAsset;
        this.amount = amount;
        this.version = version;
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

    public void setAsset(boolean asset) {
        isAsset = asset;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}