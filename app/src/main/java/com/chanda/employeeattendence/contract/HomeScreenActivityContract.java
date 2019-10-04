package com.chanda.employeeattendence.contract;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.chanda.employeeattendence.activities.HomeScreenActivity;
import com.chanda.employeeattendence.model.EmployeeModel;
import com.chanda.employeeattendence.network.ApiClient;
import com.chanda.employeeattendence.network.ApiInterface;
import com.chanda.employeeattendence.presenter.HomeScreenActivityPresenter;
import com.chanda.employeeattendence.util.CommonUtil;
import com.chanda.employeeattendence.view.HomeScreenActivityView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreenActivityContract  implements HomeScreenActivityPresenter {
    private HomeScreenActivityView homeScreenActivityView;
    private Context mContext;
    private Dialog loadingDialog;
    public HomeScreenActivityContract(HomeScreenActivity homeScreenActivityView, Context mContext) {
        this.homeScreenActivityView = homeScreenActivityView;
        this.mContext = mContext;
        this.loadingDialog = CommonUtil.getDialog(mContext);
    }
    @Override
    public void getEmployee() {
        if (CommonUtil.isInternetConnected(mContext)) {
            loadingDialog.show();
            ApiInterface apiInterface = ApiClient.getRetrofitInstance().create(ApiInterface.class);
            Call<List<EmployeeModel>> call = apiInterface.getAllEmployee();
            call.enqueue(new Callback<List<EmployeeModel>>() {
                @Override
                public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                    loadingDialog.dismiss();
                    if (response.isSuccessful()){
                        homeScreenActivityView.getEmployeeSuccess(response.body());
                    }else {
                        //Show error msg
                    }

                }

                @Override
                public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                    loadingDialog.dismiss();
                    //Show error msg
                }
            });
        }
    }
}
