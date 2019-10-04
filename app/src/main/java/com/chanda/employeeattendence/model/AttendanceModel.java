package com.chanda.employeeattendence.model;

public class AttendanceModel {
    int dayOfMonth;
    Double loggedHours;

    public AttendanceModel(int dayOfMonth, Double loggedHours) {
        this.dayOfMonth = dayOfMonth;
        this.loggedHours = loggedHours;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public Double getLoggedHours() {
        return loggedHours;
    }

    public void setLoggedHours(Double loggedHours) {
        this.loggedHours = loggedHours;
    }
}
