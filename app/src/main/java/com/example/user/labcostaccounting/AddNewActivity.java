package com.example.user.labcostaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Date;

public class AddNewActivity extends AppCompatActivity {
    private String dt = null;
    private Date cal = null;

    private RadioButton radioBD;
    private RadioButton radioBR;
    private EditText SummET;
    private EditText InfET;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_item);

        radioBD = (RadioButton)findViewById(R.id.costRadioButton);
        radioBR = (RadioButton)findViewById(R.id.incomeRadioButton);
        SummET = (EditText)findViewById(R.id.amountEditText);
        InfET = (EditText)findViewById(R.id.nameEditText);
        saveBtn = (Button)findViewById(R.id.saveButton);
        cancelBtn = (Button)findViewById(R.id.cancelButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveClick();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelClick();
            }
        });
    }

    public void saveClick() {
        Information.getInformation().setAmount(Integer.parseInt(SummET.getText().toString()));
        Information.getInformation().setInfo(InfET.getText().toString());
        if (radioBR.isChecked()) {
            Information.getInformation().setIsCost();
        } else Information.getInformation().setIsIncome();
        DBOperations.Get().AddElement(Information.getInformation().getInfo(), Information.getInformation().getCost(), Information.getInformation().getAmount());
        Information.getInformation().cleanAll();
        Information.getInformation().setCount(DBOperations.Get().GetCount());
        Intent intent = new Intent(AddNewActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void cancelClick() {
        Intent intent = new Intent(AddNewActivity.this, MainActivity.class);
        Information.getInformation().cleanAll();
        startActivity(intent);
    }
}