package com.chanda.employeeattendence.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.chanda.employeeattendence.R;
import com.chanda.employeeattendence.adapters.AttendenceAdapter;
import com.chanda.employeeattendence.contract.ReportScreenActivityContract;
import com.chanda.employeeattendence.model.AttendanceModel;
import com.chanda.employeeattendence.model.AttendanceParam;
import com.chanda.employeeattendence.model.EmployeeAttendanceDetail;
import com.chanda.employeeattendence.presenter.ReportScreenActivityPresenter;
import com.chanda.employeeattendence.view.ReportScreenActivityView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReportScreenActivity extends AppCompatActivity implements ReportScreenActivityView {
    @BindView(R.id.tv_report_heading)
    TextView tvHeading;
    @BindView(R.id.rv_report)
    RecyclerView rvReport;

    AttendenceAdapter adapter;
    //    List<AttendanceParam> paramList = new ArrayList<>();
    private ReportScreenActivityPresenter presenter;
    String entryAt, exitAt;
    double loggedHours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        // Hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // Show status bar
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String month = intent.getStringExtra("month");
        String year = intent.getStringExtra("year");
        String empName = intent.getStringExtra("name");
        String empId = intent.getStringExtra("id");
        //tvHeading.setText(empName + " Attendance Report for " + month + " " + year);

        presenter = new ReportScreenActivityContract(this, this);
        int monthNumber = getMonthNumber(month);
        String fromDate = year + "-" + monthNumber + "-" + "01";
        String toDate = getLastDateOfMonth(year, monthNumber);
        tvHeading.setText(empName + " Attendance Report for " + month + " " + year);

        // parameter initialise
        AttendanceParam param = new AttendanceParam();
        param.setEmpId(empId);
        param.setFromDate(fromDate);
        param.setToDate(toDate);
        // api call
        presenter.postEmployee(param);


//        AttendanceModel attendanceModel=new AttendanceModel(1,8.5);
//        adapter = new AttendenceAdapter(Collections.singletonList(attendanceModel), this);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
//        mLayoutManager.setReverseLayout(true);
//        mLayoutManager.setStackFromEnd(true);
//        rvReport.setLayoutManager(mLayoutManager);
//        rvReport.setAdapter(adapter);

    }

    @Override
    public void internetConnectionError(String message) {

    }

    @Override
    public void postEmployeeSuccess(List<EmployeeAttendanceDetail> attendanceDetails) {
        for (int i = 0; i < 1; i++) {
            System.out.println("EmpId " + attendanceDetails.get(i).getEmpId() + "Entry at" + attendanceDetails.get(i).getEntryAt() + "Exit at " + attendanceDetails.get(i).getExitAt());
            entryAt = attendanceDetails.get(i).getEntryAt();
            exitAt = attendanceDetails.get(i).getExitAt();
            double loggedHours = getLoggedInHours(entryAt, exitAt);
            AttendanceModel attendanceModel = new AttendanceModel(i+1, loggedHours);
            adapter = new AttendenceAdapter(Collections.singletonList(attendanceModel), this);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
            rvReport.setLayoutManager(mLayoutManager);
            rvReport.setAdapter(adapter);
        }
// set attendance detail
        Toast.makeText(this, "get Employee Attendance data success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void postEmployeeFail(String errMessage) {

    }

    public int getMonthNumber(String monthName) {
        int monthNumber = 0;
        try {
            Date date = new SimpleDateFormat("MMM").parse(monthName);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            monthNumber = cal.get(Calendar.MONTH);

        } catch (Exception exc) {
            exc.printStackTrace();

        }
        return (monthNumber + 1);
    }

    public String getLastDateOfMonth(String year, int monthNumber) {
        int yearInInt = Integer.parseInt(year);
        YearMonth yearMonth = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            yearMonth = YearMonth.of(yearInInt, monthNumber);
        }
        LocalDate lastDate = null;
        String lastDateInString = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lastDate = yearMonth.atEndOfMonth();
            lastDateInString = lastDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        return lastDateInString;
    }

    public double getLoggedInHours(String entryAt, String exitAt) {
        System.out.println("EntryAt:"+entryAt +" "+ exitAt);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(entryAt);
            d2 = format.parse(exitAt);

            //in milliseconds
            long difference = d2.getTime() - d1.getTime();
            long hour = TimeUnit.MILLISECONDS.toHours(difference);
            difference -= TimeUnit.HOURS.toMillis(hour);
            long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
            String time = String.valueOf(hour) + "." + String.valueOf(minutes);
            System.out.println("getDiff "+ time);
            return Double.parseDouble(time);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

