package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fitness.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_EXERCISES = "exercises";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_EXERCISES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "exercise_name TEXT," +
                "sets INTEGER," +
                "calories INTEGER)";
        db.execSQL(createTable);
        Log.d("DatabaseHelper", "Database and table created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES);
        onCreate(db);
    }

    public boolean insertExercise(String exerciseName, int sets, int calories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("exercise_name", exerciseName);
        contentValues.put("sets", sets);
        contentValues.put("calories", calories);

        long result = db.insert(TABLE_EXERCISES, null, contentValues);
        Log.d("DatabaseHelper", "Inserted exercise: " + exerciseName + ", Sets: " + sets + ", Calories: " + calories);
        return result != -1; // Returns true if insert was successful
    }

    public Cursor getAllExercises() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EXERCISES, null);
    }

    public boolean isExerciseLogged(String exerciseName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_EXERCISES + " WHERE exercise_name = ?";
        Cursor cursor = db.rawQuery(query, new String[]{exerciseName});

        boolean exists = (cursor.getCount() > 0); // If count is greater than 0, the exercise exists
        cursor.close(); // Don't forget to close the cursor
        return exists;
    }

}
