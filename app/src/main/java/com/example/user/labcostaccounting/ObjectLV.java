package com.example.user.labcostaccounting;

public class ObjectLV {

    public String Information;
    public boolean isAlign;
    public int Summ;


    public ObjectLV(String element, boolean active, int money ) {
        Information = element;
        isAlign = active;
        Summ = money;
    }

    public String getInformation() {
        return Information;
    }
    public int getSumm() {
        return Summ;
    }
    public boolean getAlign() {
        return isAlign;
    }

}
