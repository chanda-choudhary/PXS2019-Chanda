package com.chanda.employeeattendence.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chanda.employeeattendence.R;
import com.chanda.employeeattendence.model.EmployeeModel;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter{

    private Context context;
    private List<EmployeeModel> employees;

    public EmployeeAdapter(Context context, List<EmployeeModel> employees) {
        this.context = context;
        this.employees = employees;

    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.spinner_item, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_item);
        textView.setText(employees.get(position).getName());
        return textView;
    }

}
