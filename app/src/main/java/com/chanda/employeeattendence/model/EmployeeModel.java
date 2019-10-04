package com.chanda.employeeattendence.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeModel {
        @SerializedName("emp_id")
        @Expose
        private String empId;
        @SerializedName("name")
        @Expose
        private String name;

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

