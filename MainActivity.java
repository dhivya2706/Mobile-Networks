package com.example.loc;

import androidx.appcompat.app.AppCompatActivity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etPlace;
    Button btnGetLocation;
    TextView tvResult;
    ImageView imgPlace;

    // Replace with your real Google Maps Static API key
    private String API_KEY = "AQ.Ab8RN6KEaMjMbL1GRwTOjzGzToie7nf9TYhJAXHaXtth0A7mwg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPlace = findViewById(R.id.etPlace);
        btnGetLocation = findViewById(R.id.btnGetLocation);
        tvResult = findViewById(R.id.tvResult);
        imgPlace = findViewById(R.id.imgPlace);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeName = etPlace.getText().toString().trim();

                if (placeName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a place name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocationName(placeName, 1);

                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        double latitude = address.getLatitude();
                        double longitude = address.getLongitude();

                        tvResult.setText("Latitude: " + latitude + "\nLongitude: " + longitude);

                        // Static Map URL
                        String url = "https://maps.googleapis.com/maps/api/staticmap?center="
                                + latitude + "," + longitude
                                + "&zoom=15&size=600x400&markers=color:red%7C"
                                + latitude + "," + longitude
                                + "&key=" + API_KEY;

                        // Load image using Glide
                        Glide.with(MainActivity.this)
                                .load(url)
                                .placeholder(R.drawable.img) // optional
                                .error(R.drawable.img)       // optional
                                .into(imgPlace);

                    } else {
                        tvResult.setText("Location not found!");
                        imgPlace.setImageDrawable(null);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    tvResult.setText("Error: " + e.getMessage());
                    imgPlace.setImageDrawable(null);
                }
            }
        });
    }
}
