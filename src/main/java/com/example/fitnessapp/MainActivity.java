package com.example.fitnessapp; // Change this to your actual package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.BodybuildingActivity;
import com.example.fitnessapp.CardioActivity;
import com.example.fitnessapp.R;

public class MainActivity extends AppCompatActivity {

    private Button goToExerciseButton;
    private Button bodybuildingButton;
    private Button cardioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToExerciseButton = findViewById(R.id.go_to_exercise_button);
        bodybuildingButton = findViewById(R.id.bodybuilding_button);
        cardioButton = findViewById(R.id.cardio_button);

        goToExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This should show the exercise selection buttons
                bodybuildingButton.setVisibility(View.VISIBLE);
                cardioButton.setVisibility(View.VISIBLE);
            }
        });

        bodybuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start BodybuildingActivity
                Intent intent = new Intent(MainActivity.this, BodybuildingActivity.class);
                startActivity(intent);
            }
        });

        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CardioActivity
                Intent intent = new Intent(MainActivity.this, CardioActivity.class);
                startActivity(intent);
            }
        });
    }
}
