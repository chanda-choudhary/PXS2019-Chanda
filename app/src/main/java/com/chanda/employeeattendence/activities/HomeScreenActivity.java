package com.chanda.employeeattendence.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.chanda.employeeattendence.R;
import com.chanda.employeeattendence.contract.HomeScreenActivityContract;
import com.chanda.employeeattendence.model.EmployeeModel;
import com.chanda.employeeattendence.presenter.HomeScreenActivityPresenter;
import com.chanda.employeeattendence.util.CommonUtil;
import com.chanda.employeeattendence.view.HomeScreenActivityView;
import com.chanda.employeeattendence.adapters.EmployeeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreenActivity extends AppCompatActivity implements HomeScreenActivityView {

    @BindView(R.id.sp_employee_list)
    Spinner spEmployeeListl;
    @BindView(R.id.sp_month)
    Spinner spMonth;
    @BindView(R.id.sp_year)
    Spinner spYear;
    @BindView(R.id.btn_view_attendence_report)
    Button btnViewAttendenceReport;

    String employeeId;
    String empName;
    String month;
    String year;

    private EmployeeModel employeeResponse;
    HomeScreenActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Show status bar
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        presenter = new HomeScreenActivityContract(this, this);

        btnViewAttendenceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, ReportScreenActivity.class);
                intent.putExtra("month", month);
                intent.putExtra("year", year);
                intent.putExtra("name", empName);
                intent.putExtra("id", employeeId);
                startActivity(intent);
            }
        });
        spEmployeeListl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {

                } else {
                    EmployeeModel employeeModel = (EmployeeModel) adapterView.getAdapter().getItem(i);
                    employeeId = employeeModel.getEmpId();
                    empName = employeeModel.getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        spMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = (String) adapterView.getAdapter().getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = (String) adapterView.getAdapter().getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // to initialise view data
        initView();
    }

    private void initView() {

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, CommonUtil.months);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMonth.setAdapter(arrayAdapter);

        String years[] = new String[200];
        for (int i = 0; i < CommonUtil.years.length; i++) {
            years[i] = String.valueOf(CommonUtil.years[i]);
        }
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, years);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spYear.setAdapter(arrayAdapter1);

        // api call
        presenter.getEmployee();
    }

    @Override
    public void internetConnectionError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getEmployeeSuccess(List<EmployeeModel> response) {
        //System.out.println("EmpName:" + response.get(3).getName());
        // set list to spinner adapter to show employee list in spinner
        Toast.makeText(this, "get Employee data success", Toast.LENGTH_LONG).show();
        EmployeeAdapter adapter = new EmployeeAdapter(this, response);
        spEmployeeListl.setAdapter(adapter);
    }

    @Override
    public void getEmployeeFail(String errMessage) {
        Toast.makeText(this, errMessage, Toast.LENGTH_LONG).show();
    }
}
