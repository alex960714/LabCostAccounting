package com.example.user.labcostaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddNewActivity extends AppCompatActivity {
    private DBOperations db = DBOperations.getInstance();
    private DBRecord newDBRecord;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        saveBtn = (Button)findViewById(R.id.saveButton);
        cancelBtn = (Button)findViewById(R.id.cancelButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newDBRecord.setName(getName());
                newDBRecord.setAmount(getStatus() ? getAmount() : -getAmount());
                db.createCost(newDBRecord);
                closePanel();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePanel();
            }
        });

        this.newDBRecord = new DBRecord();
    }

    public void closePanel() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public String getName() {
        return ((EditText) findViewById(R.id.nameEditText)).getText().toString();
    }

    public boolean getStatus() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.costRadioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        return "Income".equals(((RadioButton)radioGroup.findViewById(radioId)).getText().toString());
    }

    public double getAmount() {
        return Double.valueOf(((EditText) findViewById(R.id.amountEditText)).getText().toString());
    }
}