package com.example.user.labcostaccounting;

public class DBRecord {

    public String information;
    public boolean isCost;
    public int sum;


    public DBRecord(String element, boolean active, int money ) {
        information = element;
        isCost = active;
        sum = money;
    }

    public String getInformation() {
        return information;
    }
    public int getSum() {
        return sum;
    }
    public boolean getCost() {
        return isCost;
    }

}
