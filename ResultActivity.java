package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView tvSummary;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvSummary = findViewById(R.id.tvSummary);
        btnSubmit = findViewById(R.id.btnSendEmail);

        // Receive data via explicit intent
        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String feedback = getIntent().getStringExtra("feedback");
        float serviceRating = getIntent().getFloatExtra("serviceRating", 0);
        float cleanlinessRating = getIntent().getFloatExtra("cleanlinessRating", 0);

        String summary = "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Service Rating: " + serviceRating + "/5\n"
                + "Cleanliness Rating: " + cleanlinessRating + "/5\n"
                + "Feedback: " + feedback;

        tvSummary.setText(summary);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show success message
                Toast.makeText(ResultActivity.this, "Submitted Successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
