package com.chanda.employeeattendence.view;

import com.chanda.employeeattendence.model.EmployeeModel;

import java.util.List;

public interface HomeScreenActivityView extends ParentView{
    void internetConnectionError(String message);

    void getEmployeeSuccess(List<EmployeeModel> response);
    void getEmployeeFail(String errMessage);
}
