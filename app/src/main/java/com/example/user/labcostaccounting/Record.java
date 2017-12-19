package com.example.user.labcostaccounting;

public class Record {

    public String Information;
    public boolean isCost;
    public int Summ;


    public Record(String element, boolean active, int money ) {
        Information = element;
        isCost = active;
        Summ = money;
    }

    public String getInformation() {
        return Information;
    }
    public int getSumm() {
        return Summ;
    }
    public boolean getCost() {
        return isCost;
    }

}
