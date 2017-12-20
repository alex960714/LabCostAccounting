package com.example.user.labcostaccounting;

import android.widget.CheckBox;
import android.widget.TextView;

public class CostHolder {
    private CheckBox selection;
    private TextView name;
    private TextView assetLiability;
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

    public TextView getAssetLiability() {
        return assetLiability;
    }

    public void setAssetLiability(TextView assetLiability) {
        this.assetLiability = assetLiability;
    }

    public TextView getAmount() {
        return amount;
    }

    public void setAmount(TextView amount) {
        this.amount = amount;
    }
}
