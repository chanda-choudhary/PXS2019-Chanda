package com.chanda.employeeattendence.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chanda.employeeattendence.R;
import com.chanda.employeeattendence.model.AttendanceModel;
import com.chanda.employeeattendence.model.AttendanceParam;

import java.util.ArrayList;
import java.util.List;

public class AttendenceAdapter extends RecyclerView.Adapter<AttendenceAdapter.ViewHolder> {
    private List<AttendanceModel> attendanceModels;
    private Context mContext;

    public AttendenceAdapter(List<AttendanceModel> attendanceModels, Context mContext) {
        this.attendanceModels = attendanceModels;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_list_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDateOfMonth.setText(Integer.toString(attendanceModels.get(position).getDayOfMonth()));
        holder.tvNoOfHoursLogged.setText(Double.toString(attendanceModels.get(position).getLoggedHours()));
    }

    @Override
    public int getItemCount() {
        return attendanceModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDateOfMonth, tvNoOfHoursLogged;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDateOfMonth=itemView.findViewById(R.id.tv_date_of_month);
            tvNoOfHoursLogged=itemView.findViewById(R.id.tv_no_of_hours_logged);

        }
    }
}
