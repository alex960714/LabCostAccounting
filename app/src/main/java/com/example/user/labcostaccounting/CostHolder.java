package com.example.user.labcostaccounting;

import android.widget.CheckBox;
import android.widget.TextView;

public class CostHolder {
    private CheckBox selection;
    private TextView name;
    private TextView status;
    private TextView amount;

    public CheckBox getSelection() {
        return selection;
    }

    public void setSelection(CheckBox selection) {
        this.selection = selection;
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getStatus() {
        return status;
    }

    public void setStatus(TextView status) {
        this.status = status;
    }

    public TextView getAmount() {
        return amount;
    }

    public void setAmount(TextView amount) {
        this.amount = amount;
    }
}
