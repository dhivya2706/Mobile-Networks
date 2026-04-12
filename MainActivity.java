package com.example.message;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    EditText phone, name, roll, cgpa;
    Button sendBtn;

    private static final int SMS_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        roll = findViewById(R.id.roll);
        cgpa = findViewById(R.id.cgpa);
        sendBtn = findViewById(R.id.sendBtn);

        // Request Permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SMS_PERMISSION_CODE);
        }

        sendBtn.setOnClickListener(v -> sendSMS());
    }

    private void sendSMS() {

        String number = phone.getText().toString();

        String message =
                "Student: " + name.getText().toString()
                        + ", RollNo: " + roll.getText().toString()
                        + ", CGPA: " + cgpa.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message,
                    null, null);

            Toast.makeText(this,
                    "SMS Sent!",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(this,
                    "SMS Failed!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
