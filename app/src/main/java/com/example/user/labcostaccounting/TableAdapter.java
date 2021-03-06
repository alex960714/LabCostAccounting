package com.example.user.labcostaccounting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends BaseAdapter {
    private List<DBRecord> data = new ArrayList<>();
    private Activity owner;
    private List<Integer> selected = new ArrayList<>();

    public TableAdapter(List<DBRecord> data, Activity owner) {
        super();
        this.data = data;
        this.owner = owner;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CostHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = owner.getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_table, null);
            holder = new CostHolder();
            holder.setName((TextView) convertView.findViewById(R.id.nameTextView));
            holder.setStatus((TextView) convertView.findViewById(R.id.statusTextView));
            holder.setAmount((TextView) convertView.findViewById(R.id.amountTextView));
            holder.setSelection((CheckBox) convertView.findViewById(R.id.rowCheckBox));
            convertView.setTag(holder);
        } else {
            holder = (CostHolder) convertView.getTag();
        }

        DBRecord item = data.get(position);
        holder.getSelection().setTag(item.getId());
        setSelectionListener(holder.getSelection());
        holder.getName().setText(item.getName());
        holder.getStatus().setText(item.getAmount() > 0 ? "Income" : "Cost");
        holder.getAmount().setText(String.valueOf(Math.abs(item.getAmount())));

        return convertView;
    }

    public List<Integer> getSelected() {
        return selected;
    }

    public void clearSelected() {
        selected.clear();
    }

    private void setSelectionListener(final CheckBox selection) {
        selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer item = (Integer) selection.getTag();
                if (selection.isChecked()) {
                    selected.add(item);
                } else {
                    selected.remove((Object) item);
                }
            }
        });
    }
}
