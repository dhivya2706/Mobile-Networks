package com.example.flight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

public class FlightModeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {

            boolean isEnabled = intent.getBooleanExtra("state", false);

            if (isEnabled) {
                Toast.makeText(context, "Flight Mode is ON ✈️", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Flight Mode is OFF 📶", Toast.LENGTH_LONG).show();
            }
        }
    }
}