package com.example.user.labcostaccounting;

import java.util.ArrayList;

public class Information {

    private static Information _Instance;

    private ArrayList loads = new ArrayList();
    private String info;
    private int amount;
    private boolean isCost;
    private int count;

    public Information() {
        info =null;
        amount = 0;
        isCost = false;
        count = 0;
    }

    public void cleanAll() {
        info = null;
        amount = 0;
        isCost = false;
    }

    public static Information getInformation() {
        if (_Instance == null)
            _Instance = new Information();
        return _Instance;
    }

    public void setInfo(String inf) {
        info =inf;
    }
    public void setAmount(int s) {
        amount =s;
    }
    public void setIsCost() {
        isCost = true;
    }
    public void setIsIncome() {
        isCost = false;
    }
    public String getInfo() {
        return info;
    }
    public int getAmount() {
        return amount;
    }
    public boolean getCost() {
        return isCost;
    }
    public ArrayList getList() { return loads; }
    public void setList(ArrayList al) {loads = al;}
    public void setCount(int i) {count=i;}
    public int getCount() {return count;}
}
