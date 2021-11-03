package com.example.quiz_mario_borja.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbQuiz extends DbHelper{

    Context context;

    public DbQuiz(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertQuestion(String pregunta, String ayuda, String abutton, String bbutton, String cbutton,String dbutton, int correct){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("pregunta", pregunta);
            values.put("ayuda", ayuda);
            values.put("abutton", abutton);
            values.put("bbutton", bbutton);
            values.put("cbutton", cbutton);
            values.put("dbutton", dbutton);
            values.put("correct", correct);

            id = db.insert(TABLE_QUESTIONS, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }


    public long insertPlayer(String nombre, int puntos){

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("puntos", puntos);

            id = db.insert(TABLE_RESULT, null, values);

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    // Agrega los datos de las preguntas a la base de datos
    public void addQuestions(){
        String pregunta, ayuda, abutton, bbutton, cbutton, dbutton;
        int correct;
        long id;

        pregunta = "¿Qué año pisó la luna el ser humano?";
        ayuda = "La guerra fría estaba en su apogeo";
        abutton = "1966";
        bbutton = "1976";
        cbutton = "1866";
        dbutton = "1956";
        correct = 1;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        pregunta = "¿Qué año salio el DLC The Last of Us: Left Behind?";
        ayuda = "Salió un año después que el juego original";
        abutton = "2013";
        bbutton = "2014";
        cbutton = "2016";
        dbutton = "2017";
        correct = 2;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        pregunta = "¿Qué año se publicó el juego de ROL de mesa 'Dungeons & Dragons'?";
        ayuda = "20 años después de la publicación del libro 'El señor de los anillos'";
        abutton = "1980";
        bbutton = "1966";
        cbutton = "1974";
        dbutton = "1983";
        correct = 3;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        pregunta = "¿Qué año llegó al espacio el primer astronauta Español?";
        ayuda = "Tiene trampa";
        abutton = "2001";
        bbutton = "1995";
        cbutton = "1965";
        dbutton = "1973";
        correct = 4;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        pregunta = "¿Que año se publicó el libro 'El Capital' de Karl Marx?";
        ayuda = "Fue escrito tras el inicio de la revolución industrial en Europa";
        abutton = "1917";
        bbutton = "1867";
        cbutton = "1887";
        dbutton = "1697";
        correct = 2;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

    }
}
