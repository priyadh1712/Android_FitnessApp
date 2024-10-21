package com.example.fitnessapp;

import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class CardioActivity extends AppCompatActivity {

    private EditText durationInput; // Duration input in minutes
    private TextView timerText;
    private TextView caloriesText;
    private Button startButton;
    private Button exerciseLogButton; // Button for navigating to Exercise Log
    private CountDownTimer countDownTimer;
    private long timeInMillis; // Timer value in milliseconds
    private boolean isTimerRunning = false;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);

        durationInput = findViewById(R.id.duration_input);
        timerText = findViewById(R.id.timer_text);
        caloriesText = findViewById(R.id.calories_text);
        startButton = findViewById(R.id.start_button);
        exerciseLogButton = findViewById(R.id.exercise_log_button); // Initialize the button
        dbHelper = new DatabaseHelper(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        // Set the click listener for the exercise log button
        exerciseLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardioActivity.this, ExerciseLogActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTimer() {
        if (isTimerRunning) {
            return;
        }

        try {
            int durationInMinutes = Integer.parseInt(durationInput.getText().toString());
            timeInMillis = durationInMinutes * 60 * 1000; // Convert to milliseconds
            isTimerRunning = true;
            startButton.setEnabled(false);

            countDownTimer = new CountDownTimer(timeInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeInMillis = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    isTimerRunning = false;
                    startButton.setEnabled(true);
                    calculateCalories();
                }
            }.start();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid duration.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTimer() {
        int seconds = (int) (timeInMillis / 1000);
        String timeLeft = String.format("%02d:%02d", seconds / 60, seconds % 60);
        timerText.setText(timeLeft);
    }

    private void calculateCalories() {
        int durationInMinutes = Integer.parseInt(durationInput.getText().toString());
        int caloriesBurned = durationInMinutes * 8; // Adjust the formula as needed
        caloriesText.setText("Calories Burned: " + caloriesBurned);

        // Log the exercise into the database
        boolean isInserted = dbHelper.insertExercise("Cardio", durationInMinutes, caloriesBurned);
        if (isInserted) {
            Toast.makeText(this, "Exercise logged", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error logging exercise", Toast.LENGTH_SHORT).show();
        }
    }


}
