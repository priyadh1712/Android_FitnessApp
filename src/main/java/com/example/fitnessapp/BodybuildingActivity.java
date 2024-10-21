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

public class BodybuildingActivity extends AppCompatActivity {

    private EditText setsInput;
    private EditText repsInput;
    private TextView timerText;
    private TextView caloriesText;
    private Button startButton;
    private Button exerciseLogButton; // Button for navigating to Exercise Log
    private CountDownTimer countDownTimer;
    private long timeInMillis = 60000; // 1 minute for demonstration
    private boolean isTimerRunning = false;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bodybuilding);

        setsInput = findViewById(R.id.sets_input);
        repsInput = findViewById(R.id.reps_input);
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
                Intent intent = new Intent(BodybuildingActivity.this, ExerciseLogActivity.class);
                startActivity(intent);
            }
        });
    }

    private void startTimer() {
        if (isTimerRunning) {
            return;
        }

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
    }

    private void updateTimer() {
        int seconds = (int) (timeInMillis / 1000);
        String timeLeft = String.format("%02d:%02d", seconds / 60, seconds % 60);
        timerText.setText(timeLeft);
    }

    private void calculateCalories() {
        int sets = Integer.parseInt(setsInput.getText().toString());
        int reps = Integer.parseInt(repsInput.getText().toString());
        int caloriesBurned = (sets * reps) * 5; // Adjust the formula as needed
        caloriesText.setText("Calories Burned: " + caloriesBurned);

        // Log the exercise into the database
        boolean isInserted = dbHelper.insertExercise("Bodybuilding", sets, caloriesBurned);
        if (isInserted) {
            Toast.makeText(this, "Exercise logged", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error logging exercise", Toast.LENGTH_SHORT).show();
        }
    }

}
