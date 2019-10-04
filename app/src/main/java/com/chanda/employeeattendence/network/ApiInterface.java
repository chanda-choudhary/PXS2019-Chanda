package com.chanda.employeeattendence.network;

import com.chanda.employeeattendence.model.AttendanceParam;
import com.chanda.employeeattendence.model.AttendanceModel;
import com.chanda.employeeattendence.model.EmployeeAttendanceDetail;
import com.chanda.employeeattendence.model.EmployeeModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/accounting/att_rprt_api.php?e76c37b493ea168cea60b8902072387caf297979")
    Call<List<EmployeeModel>> getAllEmployee();

//    @POST("/accounting/att_rprt_api.php?e76c37b493ea168cea60b8902072387caf297979 ")
//    Call<List<EmployeeAttendanceDetail>> getEmployeeAttendanceDetail(@Query("emp_id") String empID, @Query("from_dt")String fromDate, @Query("to_dt") String toDate);

    @POST("/accounting/att_rprt_api.php?e76c37b493ea168cea60b8902072387caf297979 ")
    @FormUrlEncoded
    Call<List<EmployeeAttendanceDetail>> getEmployeeAttendanceDetail(@Field("emp_id") String empID,
                                                                     @Field("from_dt") String fromDate,
                                                                     @Field("to_dt") String toDate);
}
