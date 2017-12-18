package com.example.user.labcostaccounting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList loads = new ArrayList();
    int countloads = 0;
    boolean flagactivity = false;
    private int _ActiveElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBOperations.Initialize(this);
        DrawList();
    }

    public void UpdateElements() {
        DrawList();
    }

    public void addClick(View view) {
        Intent intent = new Intent(MainActivity.this, AddNewActivity.class);
        startActivity(intent);
    }
    public void savebase(View view) {
        Info.Get().setlist(loads);
    }

    public void loadbase(View view) {
        loads= Info.Get().getlist();
        UpdateElements();
    }


    private void DrawList() {
        ListView LV = (ListView) findViewById(R.id.table);

        ArrayList loads = Info.Get().getlist();

        ArrayList loadsbase = DBOperations.Get().GetElements();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, loads);
        LV.setAdapter(adapter);


        for (int i = 0; i < Info.Get().getcount(); i++) {
            ObjectLV add = (ObjectLV) loadsbase.get(i);
            String addinf = add.getInformation();
            int addsumm = add.getSumm();
            boolean addrashod = add.getAlign();
            if (addrashod) {
                loads.add((String) ("Доход: " + addinf + "  " + addsumm ));
            } else {
                loads.add((String) ("Расход:  " + addinf + "  " + addsumm ));
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

    public void deleteclick(View view) {
        DBOperations.Get().DeleteElement(_ActiveElement);
        _ActiveElement = 0;
    }


}