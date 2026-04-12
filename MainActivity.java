package com.example.agecalculator;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvDob, tvCurrent, tvResult;
    Button btnCalculate;

    int dobYear, dobMonth, dobDay;
    int curYear, curMonth, curDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDob = findViewById(R.id.tvDob);
        tvCurrent = findViewById(R.id.tvCurrent);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        Calendar calendar = Calendar.getInstance();

        // Select Date of Birth
        tvDob.setOnClickListener(v -> {
            DatePickerDialog picker = new DatePickerDialog(
                    MainActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        dobYear = year;
                        dobMonth = month;
                        dobDay = dayOfMonth;
                        tvDob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            picker.show();
        });

        // Select Current Date
        tvCurrent.setOnClickListener(v -> {
            DatePickerDialog picker = new DatePickerDialog(
                    MainActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        curYear = year;
                        curMonth = month;
                        curDay = dayOfMonth;
                        tvCurrent.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            picker.show();
        });

        // Calculate Age
        btnCalculate.setOnClickListener(v -> calculateAge());
    }

    private void calculateAge() {
        int years = curYear - dobYear;
        int months = curMonth - dobMonth;
        int days = curDay - dobDay;

        if (days < 0) {
            months--;
            days += 30;
        }

        if (months < 0) {
            years--;
            months += 12;
        }

        tvResult.setText(years + " Years " + months + " Months " + days + " Days");
    }
}
