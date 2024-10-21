package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Button bodybuildingButton = findViewById(R.id.bodybuilding_button);
        Button cardioButton = findViewById(R.id.cardio_button);

        bodybuildingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start BodybuildingActivity when the button is clicked
                Intent intent = new Intent(ExerciseActivity.this, BodybuildingActivity.class);
                startActivity(intent);
            }
        });

        cardioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CardioActivity when the button is clicked
                Intent intent = new Intent(ExerciseActivity.this, CardioActivity.class);
                startActivity(intent);
            }
        });
    }
}


