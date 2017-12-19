package com.example.user.labcostaccounting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList loads = new ArrayList();
    /*int countloads = 0;
    boolean flagactivity = false;*/
    private int _ActiveElement;
    private Button addBtn;
    private Button delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addClick();
        delClick();

        DBOperations.Initialize(this);
        UpdateElements();
    }

    public void addClick() {
        addBtn = (Button)findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
                startActivity(intent);
            }
        });
    }

    public void delClick() {
        delBtn = (Button)findViewById(R.id.delButton);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBOperations.getDB().deleteElement(_ActiveElement);
                _ActiveElement = 0;
            }
        });
    }


    public void UpdateElements() {
        ListView table = (ListView) findViewById(R.id.table);

        loads = Information.getInformation().getList();

        ArrayList loadsbase = DBOperations.getDB().getElements();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loads);
        table.setAdapter(adapter);


        for (int i = 0; i < DBOperations.getDB().getCount(); i++) {
            DBRecord add = (DBRecord) loadsbase.get(i);
            String addInfo = add.getInformation();
            int addSum = add.getSum();
            boolean addCost = add.getCost();
            if (addCost) {
                loads.add((String) ("Income: " + addInfo + "  " + addSum ));
            } else {
                loads.add((String) ("Cost:  " + addInfo + "  " + addSum ));
            }
            System.out.println("ii = " + i);
        }
        Information.getInformation().setList(loads);


        table.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                /*Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();*/
                _ActiveElement = position;
            }
        });
    }
}