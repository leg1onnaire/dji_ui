package com.example.arayuz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayValuesFragment extends Fragment {
    private TextView displayWp1, displayWp2, displayWp3, displayWp4, displayAltitude, displaySpeed;
    private Button resetButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_values, container, false);

        displayWp1 = view.findViewById(R.id.display_wp1);
        displayWp2 = view.findViewById(R.id.display_wp2);
        displayWp3 = view.findViewById(R.id.display_wp3);
        displayWp4 = view.findViewById(R.id.display_wp4);
        displayAltitude = view.findViewById(R.id.display_altitude);
        displaySpeed = view.findViewById(R.id.display_speed);
        resetButton = view.findViewById(R.id.btn_reset);

        loadValues();

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();
            }
        });

        return view;
    }

    private void loadValues() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("WaypointsPrefs", Context.MODE_PRIVATE);

        String wp1 = sharedPreferences.getString("wp1_lat", "0.0") + ", " + sharedPreferences.getString("wp1_long", "0.0");
        String wp2 = sharedPreferences.getString("wp2_lat", "0.0") + ", " + sharedPreferences.getString("wp2_long", "0.0");
        String wp3 = sharedPreferences.getString("wp3_lat", "0.0") + ", " + sharedPreferences.getString("wp3_long", "0.0");
        String wp4 = sharedPreferences.getString("wp4_lat", "0.0") + ", " + sharedPreferences.getString("wp4_long", "0.0");
        String alt = sharedPreferences.getString("altitude", "0.0");
        String spd = sharedPreferences.getString("speed", "0.0");

        displayWp1.setText(wp1);
        displayWp2.setText(wp2);
        displayWp3.setText(wp3);
        displayWp4.setText(wp4);
        displayAltitude.setText(alt);
        displaySpeed.setText(spd);
    }

    private void resetValues() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("WaypointsPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
        Toast.makeText(getActivity(), "values have been reset", Toast.LENGTH_SHORT).show();


        loadValues();
    }
}
