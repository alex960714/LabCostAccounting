package com.example.user.labcostaccounting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DBOperations db;
    private List<DBRecord> data;
    private TableAdapter adapter;
    private Button addBtn;
    private Button deleteBtn;
    private Button totalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = (Button)findViewById(R.id.addButton);
        deleteBtn = (Button)findViewById(R.id.delButton);
        totalBtn = (Button)findViewById(R.id.totalButton);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddNewActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Integer id : adapter.getSelected()) {
                    db.removeCost(id);
                }
                adapter.clearSelected();
                updateData();
            }
        });

        totalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ShowTotalActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        this.db = DBOperations.getInstance();
        this.db.init(openOrCreateDatabase("cost_accounting", MODE_PRIVATE, null));

        initTable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void initTable() {
        data = db.getData();

        ListView table = (ListView) findViewById(R.id.table);
        adapter = new TableAdapter(data, this);
        table.setAdapter(adapter);
    }

    public void updateData() {
        if (data != null) {
            data.clear();
            data.addAll(db.getData());
            adapter.notifyDataSetChanged();
        }
    }
}