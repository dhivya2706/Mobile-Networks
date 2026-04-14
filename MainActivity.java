package com.example.draw1;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private DownloadProgressView progressView;
    private Handler handler = new Handler();
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressView = findViewById(R.id.progressView);
        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> startFakeDownload());
    }

    private void startFakeDownload() {
        progressStatus = 0;

        new Thread(() -> {
            while (progressStatus <= 100) {

                handler.post(() -> progressView.setProgress(progressStatus));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                progressStatus++;
            }
        }).start();
    }
}