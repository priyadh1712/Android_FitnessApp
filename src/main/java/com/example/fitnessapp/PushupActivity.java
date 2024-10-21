package com.example.fitnessapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PushupActivity extends AppCompatActivity {
    private EditText setsInput;
    private EditText repsInput;
    private TextView timerText;
    private TextView caloriesBurnedText;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 30000; // 30 seconds for demo purposes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushup);

        setsInput = findViewById(R.id.sets_input);
        repsInput = findViewById(R.id.reps_input);
        timerText = findViewById(R.id.timer_text);
        caloriesBurnedText = findViewById(R.id.calories_burned);
        Button startTimerButton = findViewById(R.id.start_timer_button);

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                caloriesBurnedText.setText("Calories Burned: " + calculateCalories());
            }
        }.start();
    }

    private void updateTimer() {
        int seconds = (int) (timeLeftInMillis / 1000);
        timerText.setText("Timer: " + seconds);
    }

    private int calculateCalories() {
        // Example calculation: 0.5 calories per push-up (adjust as needed)
        int sets = Integer.parseInt(setsInput.getText().toString());
        int reps = Integer.parseInt(repsInput.getText().toString());
        return sets * reps * 1; // Adjust the formula based on actual data
    }
}
