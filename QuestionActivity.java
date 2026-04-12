package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    TextView tvQuestion;
    RadioGroup radioGroup;
    Button btnNext;

    String[] questions = {
            "Question 1: What does CPU stand for?",
            "Question 2: What language is used for Android app development?",
            "Question 3: Which company developed the Java programming language?",
            "Question 4: What does HTML stand for?",
            "Question 5: Which one is a cloud computing service?"
    };

    String[][] options = {
            {"Central Processing Unit", "Computer Personal Unit", "Central Program Unit", "Control Processing Unit"},
            {"Python", "Java", "C++", "Swift"},
            {"Microsoft", "Sun Microsystems", "Apple", "IBM"},
            {"Hyper Text Markup Language", "High Text Machine Language", "Hyperlink Text Mark Language", "Home Tool Markup Language"},
            {"Amazon Web Services", "Linux", "Ubuntu", "GitHub"}
    };

    int[] answers = {0, 1, 1, 0, 0};

    int questionIndex, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Toast.makeText(this, "QuestionActivity started", Toast.LENGTH_SHORT).show();

        tvQuestion = findViewById(R.id.tvQuestion);
        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);

        questionIndex = getIntent().getIntExtra("questionIndex", 0);
        score = getIntent().getIntExtra("score", 0);

        loadQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if(selectedId == -1) {
                    Toast.makeText(QuestionActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedRadio = findViewById(selectedId);
                int selectedIndex = radioGroup.indexOfChild(selectedRadio);

                if(selectedIndex == answers[questionIndex]){
                    score++;
                }

                questionIndex++;

                if(questionIndex < questions.length){
                    loadQuestion(); // update UI
                    radioGroup.clearCheck();
                } else {
                    // Quiz finished
                    Intent resultIntent = new Intent(QuestionActivity.this, ResultActivity.class);
                    resultIntent.putExtra("score", score);
                    startActivity(resultIntent);
                    finish();
                }
            }
        });
    }

    private void loadQuestion(){
        tvQuestion.setText(questions[questionIndex]);
        radioGroup.removeAllViews();
        for(String option : options[questionIndex]){
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            rb.setTextSize(18f);
            radioGroup.addView(rb);
        }
    }
}
