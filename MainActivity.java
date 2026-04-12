package com.example.quiz;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start first question with score = 0
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("score", 0);
                intent.putExtra("questionIndex", 0);
                startActivity(intent);
            }
        });
    }
}
