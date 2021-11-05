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

        //Música -> himno de EEUU
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

        //Música -> himno de la URRS
        pregunta = "¿Qué año llegó al espacio el ser humano?";
        ayuda = "La guerra fría estaba en su apogeo";
        abutton = "1961";
        bbutton = "1976";
        cbutton = "1866";
        dbutton = "1966";
        correct = 1;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> la internacional
        pregunta = "¿Qué año se proclamó la Segunda República Española?";
        ayuda = "33 años después de que España perdiera la última colonia";
        abutton = "1936";
        bbutton = "1931";
        cbutton = "1928";
        dbutton = "1945";
        correct = 2;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> himno de la URRS
        pregunta = "¿Qué año se proclamó la URSS?";
        ayuda = "5 años después de que comenzara la 'Revolución de Octubre' en Rusia";
        abutton = "1927";
        bbutton = "1931";
        cbutton = "1922";
        dbutton = "1917";
        correct = 3;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música de payaso
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

        //Música -> himno de EEUU
        pregunta = "¿Qué año declaró la independencia EEUU?";
        ayuda = "Ese mismo año, Adam Smith publicó su libro 'La Riqueza de las Naciones'";
        abutton = "1779";
        bbutton = "1776";
        cbutton = "1867";
        dbutton = "1697";
        correct = 2;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> himno de España
        pregunta = "¿Qué año entra en vigor la última 'Constitución Española'?";
        ayuda = "Ocurrió tras la muerte del Dictador";
        abutton = "1987";
        bbutton = "1975";
        cbutton = "1875";
        dbutton = "1978";
        correct = 4;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> himno La Marsellesa
        pregunta = "¿Qué año ocurrió la 'Revolución Francesa'?";
        ayuda = "Se proclamó el famoso lema 'Liberté, Égalité, Fraternité'";
        abutton = "1492";
        bbutton = "1776";
        cbutton = "1779";
        dbutton = "1789";
        correct = 3;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> música árabe
        pregunta = "¿Qué año entraron los Musulmanes en la Península?";
        ayuda = "Ocurrió en mitad de la guerra fría entre EEUU y la URSS";
        abutton = "661";
        bbutton = "717";
        cbutton = "745";
        dbutton = "712";
        correct = 4;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> música árabe
        pregunta = "¿Qué año se descubrió (porque antes no existía) América?";
        ayuda = "Ese mismo año terminó la Reconquista (porque ya se había conquistado antes) con la toma de Granada";
        abutton = "1492";
        bbutton = "1942";
        cbutton = "1498";
        dbutton = "1959";
        correct = 1;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> himno de los tercios
        pregunta = "¿Qué año se fundaron los Tercios Españoles?";
        ayuda = "Años después del descubrimiento de américa";
        abutton = "1492";
        bbutton = "1630";
        cbutton = "1558";
        dbutton = "1534";
        correct = 4;

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

    }
}
