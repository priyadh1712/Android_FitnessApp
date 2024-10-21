package com.example.fitnessapp;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;

import java.util.ArrayList;

public class ExerciseLogActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private ArrayList<Exercise> exerciseList;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_log);

        recyclerView = findViewById(R.id.recycler_view);
        databaseHelper = new DatabaseHelper(this);
        exerciseList = new ArrayList<>();
        loadExerciseData();

        exerciseAdapter = new ExerciseAdapter(exerciseList);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadExerciseData() {
        Cursor cursor = databaseHelper.getAllExercises();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No exercises logged", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            String type = cursor.getString(1);
            int duration = cursor.getInt(2);
            int calories = cursor.getInt(3);
            exerciseList.add(new Exercise(type, duration, calories));
        }
        cursor.close();
    }
}
