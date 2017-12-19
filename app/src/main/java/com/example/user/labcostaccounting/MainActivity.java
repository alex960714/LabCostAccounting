package com.example.user.labcostaccounting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList loads = new ArrayList();
    int countloads = 0;
    boolean flagactivity = false;
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
        DrawList();
    }

    public void UpdateElements() {
        DrawList();
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
                DBOperations.Get().DeleteElement(_ActiveElement);
                _ActiveElement = 0;
            }
        });
    }
/*    public void savebase(View view) {
        Information.getInformation().setList(loads);
    }

    public void loadbase(View view) {
        loads= Information.getInformation().getList();
        UpdateElements();
    }*/


    private void DrawList() {
        ListView LV = (ListView) findViewById(R.id.table);

        ArrayList loads = Information.getInformation().getList();

        ArrayList loadsbase = DBOperations.Get().GetElements();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loads);
        LV.setAdapter(adapter);


        for (int i = 0; i < Information.getInformation().getCount(); i++) {
            Record add = (Record) loadsbase.get(i);
            String addInfo = add.getInformation();
            int addSum = add.getSumm();
            boolean addCost = add.getCost();
            if (addCost) {
                loads.add((String) ("Income: " + addInfo + "  " + addSum ));
            } else {
                loads.add((String) ("Cost:  " + addInfo + "  " + addSum ));
            }
            System.out.println("ii = " + i);
        }


        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                _ActiveElement = pos;
                return true;
            }
        });
    }



}