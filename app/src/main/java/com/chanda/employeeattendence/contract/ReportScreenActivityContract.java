package com.chanda.employeeattendence.contract;

import android.app.Dialog;
import android.content.Context;

import com.chanda.employeeattendence.model.AttendanceParam;
import com.chanda.employeeattendence.model.EmployeeAttendanceDetail;
import com.chanda.employeeattendence.network.ApiClient;
import com.chanda.employeeattendence.network.ApiInterface;
import com.chanda.employeeattendence.presenter.ReportScreenActivityPresenter;
import com.chanda.employeeattendence.util.CommonUtil;
import com.chanda.employeeattendence.view.ReportScreenActivityView;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportScreenActivityContract implements ReportScreenActivityPresenter {

    private ReportScreenActivityView activityView;
    private Context mContext;
    private Dialog loadingDialog;

    public ReportScreenActivityContract(ReportScreenActivityView activityView, Context mContext) {
        this.activityView = activityView;
        this.mContext = mContext;
        this.loadingDialog = CommonUtil.getDialog(mContext);
    }
    @Override
    public void postEmployee(AttendanceParam param) {
        if (CommonUtil.isInternetConnected(mContext)) {
            loadingDialog.show();
            ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
            System.out.println("param:"+ param.getEmpId() + param.getFromDate() + param.getToDate());
            Call<List<EmployeeAttendanceDetail>> call = apiInterface.getEmployeeAttendanceDetail(param.getEmpId(), param.getFromDate(),param.getToDate());
            call.enqueue(new Callback<List<EmployeeAttendanceDetail>>() {
                @Override
                public void onResponse(Call<List<EmployeeAttendanceDetail>> call, Response<List<EmployeeAttendanceDetail>> response) {
                    loadingDialog.dismiss();
                    if (response.isSuccessful()) {
                        System.out.println("Success: "+response.body());
                        activityView.postEmployeeSuccess(response.body());
                    } else {
                        //Show error msg
                    }

                }

                @Override
                public void onFailure(Call<List<EmployeeAttendanceDetail>> call, Throwable t) {
                    loadingDialog.dismiss();
                    //Show error msg
                }
            });
        } else {

        }
    }
}
