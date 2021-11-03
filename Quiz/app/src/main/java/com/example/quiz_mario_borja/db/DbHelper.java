package com.example.quiz_mario_borja.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOM = "quiz.db";
    public static final String TABLE_QUESTIONS = "table_questions";
    public static final String TABLE_RESULT = "table_result";


    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOM, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_QUESTIONS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pregunta TEXT NOT NULL," +
                "ayuda TEXT NOT NULL," +
                "abutton TEXT NOT NULL," +
                "bbutton TEXT NOT NULL," +
                "cbutton TEXT NOT NULL," +
                "dbutton TEXT NOT NULL," +
                "correct INTEGER NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_RESULT + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "puntos INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE " + TABLE_RESULT);
        onCreate(db);
    }
}
