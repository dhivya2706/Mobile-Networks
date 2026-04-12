package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etMark;
    Button btnAdd, btnDisplay, btnDel;
    TextView tvResult;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etMark = findViewById(R.id.etMark);
        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnDisplay = findViewById(R.id.btnDisplay);
        tvResult = findViewById(R.id.tvResult);

        db = new DBHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int mark = Integer.parseInt(etMark.getText().toString());

                boolean inserted = db.insertStudent(name, mark);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean deleted = db.deleteStudent(name);

                if (deleted) {
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = db.getAllStudents();
                StringBuilder sb = new StringBuilder();

                while (cursor.moveToNext()) {
                    sb.append("Name: ")
                            .append(cursor.getString(1))
                            .append("  Mark: ")
                            .append(cursor.getInt(2))
                            .append("\n");
                }
                tvResult.setText(sb.toString());
            }
        });
    }
}
