package com.example.quiz_mario_borja.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class dbQuiz extends DbHelper{

    Context context;

    public dbQuiz(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertQuestion(String nombre, String pregunta, String ayuda){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("pregunta", pregunta);
            values.put("ayuda", ayuda);

            id = db.insert(TABLE_QUESTIONS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
}
