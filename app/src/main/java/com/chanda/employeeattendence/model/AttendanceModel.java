package com.chanda.employeeattendence.model;

public class AttendanceModel {
    int dayOfMonth;
    String loggedHours;

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public String getLoggedHours() {
        return loggedHours;
    }

    public void setLoggedHours(String loggedHours) {
        this.loggedHours = loggedHours;
    }
}
