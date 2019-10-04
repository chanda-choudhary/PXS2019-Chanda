package com.chanda.employeeattendence.view;

import com.chanda.employeeattendence.model.EmployeeAttendanceDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ReportScreenActivityView {
    void internetConnectionError(String message);

    void postEmployeeSuccess(List<EmployeeAttendanceDetail> attendanceDetails);
    void postEmployeeFail(String errMessage);
}
