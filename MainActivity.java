package com.example.draw2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paintView = findViewById(R.id.paintView);

        Button btnColor = findViewById(R.id.btnColor);
        Button btnUndo = findViewById(R.id.btnUndo);
        Button btnRedo = findViewById(R.id.btnRedo);
        Button btnClear = findViewById(R.id.btnClear);

        btnColor.setOnClickListener(v -> {
            AmbilWarnaDialog colorPicker =
                    new AmbilWarnaDialog(this, Color.BLACK,
                            new AmbilWarnaDialog.OnAmbilWarnaListener() {

                                @Override
                                public void onCancel(AmbilWarnaDialog dialog) {}

                                @Override
                                public void onOk(AmbilWarnaDialog dialog, int color) {
                                    paintView.setColor(color);
                                }
                            });

            colorPicker.show();
        });

        btnUndo.setOnClickListener(v -> paintView.undo());

        btnRedo.setOnClickListener(v -> paintView.redo());

        btnClear.setOnClickListener(v -> paintView.clearCanvas());
    }
}