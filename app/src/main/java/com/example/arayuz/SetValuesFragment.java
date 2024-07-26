package com.example.arayuz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SetValuesFragment extends Fragment {
    private EditText wp1Latitude, wp1Longitude;
    private EditText wp2Latitude, wp2Longitude;
    private EditText wp3Latitude, wp3Longitude;
    private EditText wp4Latitude, wp4Longitude;
    private EditText altitude, speed;
    private Button setButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_values, container, false);

        wp1Latitude = view.findViewById(R.id.edittext_wp1_latitude);
        wp1Longitude = view.findViewById(R.id.edittext_wp1_longitude);
        wp2Latitude = view.findViewById(R.id.edittext_wp2_latitude);
        wp2Longitude = view.findViewById(R.id.edittext_wp2_longitude);
        wp3Latitude = view.findViewById(R.id.edittext_wp3_latitude);
        wp3Longitude = view.findViewById(R.id.edittext_wp3_longitude);
        wp4Latitude = view.findViewById(R.id.edittext_wp4_latitude);
        wp4Longitude = view.findViewById(R.id.edittext_wp4_longitude);
        altitude = view.findViewById(R.id.edittext_altitude);
        speed = view.findViewById(R.id.edittext_speed);
        setButton = view.findViewById(R.id.btn_set);

        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SetValuesFragment", "SET button clicked");

                try {
                    double wp1Lat = Double.parseDouble(wp1Latitude.getText().toString());
                    double wp1Long = Double.parseDouble(wp1Longitude.getText().toString());
                    double wp2Lat = Double.parseDouble(wp2Latitude.getText().toString());
                    double wp2Long = Double.parseDouble(wp2Longitude.getText().toString());
                    double wp3Lat = Double.parseDouble(wp3Latitude.getText().toString());
                    double wp3Long = Double.parseDouble(wp3Longitude.getText().toString());
                    double wp4Lat = Double.parseDouble(wp4Latitude.getText().toString());
                    double wp4Long = Double.parseDouble(wp4Longitude.getText().toString());
                    float alt = Float.parseFloat(altitude.getText().toString());
                    float spd = Float.parseFloat(speed.getText().toString());

                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("WaypointsPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("wp1_lat", String.valueOf(wp1Lat));
                    editor.putString("wp1_long", String.valueOf(wp1Long));
                    editor.putString("wp2_lat", String.valueOf(wp2Lat));
                    editor.putString("wp2_long", String.valueOf(wp2Long));
                    editor.putString("wp3_lat", String.valueOf(wp3Lat));
                    editor.putString("wp3_long", String.valueOf(wp3Long));
                    editor.putString("wp4_lat", String.valueOf(wp4Lat));
                    editor.putString("wp4_long", String.valueOf(wp4Long));
                    editor.putString("altitude", String.valueOf(alt));
                    editor.putString("speed", String.valueOf(spd));

                    editor.apply();

                    // Show a message indicating values are set
                    Toast.makeText(getActivity(), "Configurations set", Toast.LENGTH_SHORT).show();

                    // Log values to ensure they are parsed correctly
                    Log.d("SetValuesFragment", "Waypoint 1: (" + wp1Lat + ", " + wp1Long + ")");
                    Log.d("SetValuesFragment", "Waypoint 2: (" + wp2Lat + ", " + wp2Long + ")");
                    Log.d("SetValuesFragment", "Waypoint 3: (" + wp3Lat + ", " + wp3Long + ")");
                    Log.d("SetValuesFragment", "Waypoint 4: (" + wp4Lat + ", " + wp4Long + ")");
                    Log.d("SetValuesFragment", "Altitude: " + alt);
                    Log.d("SetValuesFragment", "Speed: " + spd);

                } catch (NumberFormatException e) {
                    Log.e("SetValuesFragment", "Invalid number format", e);
                    Toast.makeText(getActivity(), "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
