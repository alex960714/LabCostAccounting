package com.example.user.labcostaccounting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button delBtn;
    private Button totalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn=(Button)findViewById(R.id.addButton);
        delBtn=(Button)findViewById(R.id.deleteButton);
        totalBtn =(Button)findViewById(R.id.totalButton);
    }
}
