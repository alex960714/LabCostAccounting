package com.example.user.labcostaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ShowTotalActivity extends AppCompatActivity {
    private DBOperations db = DBOperations.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_layout);
        TextView total = (TextView) findViewById(R.id.totalCountLabel);
        total.setText(String.valueOf(getTotal()));
    }

    public void onClosingTotal(View view) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    public double getTotal() {
        double total = 0.0;
        for (DBRecord cost : db.getData()) {
            total += cost.getAmount();
        }
        return total;
    }
}
