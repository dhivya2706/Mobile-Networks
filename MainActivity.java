package com.example.feedback;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etEmail, etFeedback;
    RatingBar ratingService, ratingCleanliness;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etFeedback = findViewById(R.id.etFeedback);
        ratingService = findViewById(R.id.ratingService);
        ratingCleanliness = findViewById(R.id.ratingCleanliness);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String feedback = etFeedback.getText().toString().trim();
                float serviceRating = ratingService.getRating();
                float cleanlinessRating = ratingCleanliness.getRating();

                if(name.isEmpty() || email.isEmpty() || feedback.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Explicit intent to ResultActivity
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                intent.putExtra("feedback", feedback);
                intent.putExtra("serviceRating", serviceRating);
                intent.putExtra("cleanlinessRating", cleanlinessRating);

                startActivity(intent); // launch ResultActivity
            }
        });
    }
}
