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

    public long insertQuestion(String pregunta, String ayuda, String abutton, String bbutton, String cbutton,String dbutton, int correct, String song){

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
            values.put("song", song);

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
            Log.i("DB", "JUGADOR " + nombre + " AÑADIDO");

        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    // Agrega los datos de las preguntas a la base de datos
    public void addQuestions(){
        String pregunta, ayuda, abutton, bbutton, cbutton, dbutton, song;
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
        song = "eeuu";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "urss";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "internacional";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "urss";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "payaso";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "eeuu";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "espana";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "marsellesa";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> música árabe
        pregunta = "¿Qué año entraron los Musulmanes en la Península?";
        ayuda = "Abre el libro de Historia de España";
        abutton = "661";
        bbutton = "717";
        cbutton = "745";
        dbutton = "712";
        correct = 4;
        song = "arabe";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música ->
        pregunta = "¿Qué año se descubrió (porque antes no existía) América?";
        ayuda = "Ese mismo año terminó la Reconquista (porque ya se había conquistado antes) con la toma de Granada";
        abutton = "1492";
        bbutton = "1942";
        cbutton = "1498";
        dbutton = "1959";
        correct = 1;
        song = "indio";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
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
        song = "tercios";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> Another brick in the world
        pregunta = "¿Qué año calló el Muro De Berlín?";
        ayuda = "Dos años después ocurrió la Disolución de la URSS";
        abutton = "1989";
        bbutton = "1945";
        cbutton = "1917";
        dbutton = "2000";
        correct = 1;
        song = "brick";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música ->
        pregunta = "¿Qué año comenzó la I Guerra Mundial?";
        ayuda = "Ocurrió antes que la II Guerra Mundial";
        abutton = "1918";
        bbutton = "1945";
        cbutton = "1914";
        dbutton = "1903";
        correct = 3;
        song = "imundial";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> Madre anoche en las trincheras
        pregunta = "¿Qué año terminó la Guerra Civil Española?";
        ayuda = "Este mismo año, comenzó la II Guerra Mundial";
        abutton = "1918";
        bbutton = "1939";
        cbutton = "1945";
        dbutton = "1936";
        correct = 2;
        song = "trinchera";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música ->
        pregunta = "¿Qué año terminó la II Guerra Mundial?";
        ayuda = "Este mismo año, comenzó la II Guerra Mundial";
        abutton = "1918";
        bbutton = "1939";
        cbutton = "1936";
        dbutton = "1945";
        correct = 4;
        song = "iimundial";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> El pueblo unido jamás será vencido
        pregunta = "¿Qué año murió Salvador Allende, Ex Presidente de Chile?";
        ayuda = "Ocurrió en un golpe de Estado Dirigido por Pinochet";
        abutton = "1989";
        bbutton = "1920";
        cbutton = "1975";
        dbutton = "1973";
        correct = 4;
        song = "pueblo";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> El pueblo unido jamás será vencido
        pregunta = "¿Qué año comenzó la guerra entre Palestina e Israel?";
        ayuda = "Ocurrió tras la II Guerra Mundial";
        abutton = "1950";
        bbutton = "1948";
        cbutton = "1945";
        dbutton = "1920";
        correct = 2;
        song = "palestina";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> El pueblo unido jamás será vencido
        pregunta = "¿Qué año terminó de reconstruirse la 'Gran Muralla China'?";
        ayuda = "Terminó a mediados de la Edad Moderna";
        abutton = "1644";
        bbutton = "1368";
        cbutton = "206";
        dbutton = "1492";
        correct = 1;
        song = "china";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> El pueblo unido jamás será vencido
        pregunta = "¿Qué año se lanzaron las bombas atómicas a Hiroshima y Nagasaki?";
        ayuda = "Ocurrió con el fin de la II Guerra Mundial";
        abutton = "1918";
        bbutton = "1975";
        cbutton = "1945";
        dbutton = "1495";
        correct = 3;
        song = "atomica";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }

        //Música -> El pueblo unido jamás será vencido
        pregunta = "¿Qué año los Vikingos cruzaron el Mar del Norte y llegaron a Lindisfarne?";
        ayuda = "Ocurrió con el fin de la II Guerra Mundial";
        abutton = "1492";
        bbutton = "1212";
        cbutton = "793";
        dbutton = "712";
        correct = 3;
        song = "vikingo";

        id = insertQuestion(pregunta,ayuda,abutton,bbutton,cbutton,dbutton,correct,song);
        if(id > 0){
            Log.i("DB", "PREGUNTA " + id + " AÑADIDA");
        } else {
            Log.w("DB", "ERROR AL AÑADIR PREGUNTA");
        }



    }
}
