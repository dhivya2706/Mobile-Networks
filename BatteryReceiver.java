package com.example.br;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class BatteryReceiver extends BroadcastReceiver {

    private TextView batteryTextView;
    public BatteryReceiver(TextView tv) {
        batteryTextView = tv;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {

            int level = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);
            float batteryPct = level * 100 / (float)scale;
            batteryTextView.setText("Battery Level: " + (int)batteryPct + "%");
            Toast.makeText(context, "Battery level: " + (int)batteryPct + "%", Toast.LENGTH_SHORT).show();
        }
    }
}