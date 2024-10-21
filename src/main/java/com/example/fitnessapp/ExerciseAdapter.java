package com.example.fitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private ArrayList<Exercise> exerciseList;

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        public TextView typeText;
        public TextView durationText;
        public TextView caloriesText;

        public ExerciseViewHolder(View itemView) {
            super(itemView);
            typeText = itemView.findViewById(R.id.exercise_type);
            durationText = itemView.findViewById(R.id.exercise_duration);
            caloriesText = itemView.findViewById(R.id.exercise_calories);
        }
    }

    public ExerciseAdapter(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise currentExercise = exerciseList.get(position);
        holder.typeText.setText(currentExercise.getType());
        holder.durationText.setText("Duration: " + currentExercise.getDuration());
        holder.caloriesText.setText("Calories: " + currentExercise.getCalories());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
