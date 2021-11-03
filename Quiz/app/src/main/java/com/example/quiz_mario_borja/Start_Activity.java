package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quiz_mario_borja.db.DbHelper;
import com.example.quiz_mario_borja.db.DbQuiz;

public class Start_Activity extends AppCompatActivity {

    private Button startButton,leaveButton;
    public int lvl = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.jugar_button);
        leaveButton = findViewById(R.id.salir_button);

        Intent intent = new Intent(Start_Activity.this, MainActivity.class);

        // Creación de la Base de Datos
        DbHelper dbHelper = new DbHelper(Start_Activity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Comprobación de la creación de la Base de Datos
        if (db != null){
            Log.i("DB", "BASE DE DATOS CREADA");
        } else {
            Log.w("DB", "ERROR AL CREAR LA BASE DE DATOS");
        }
        // Se añaden las preguntas si es la primera vez que se abre la app
        DbQuiz dbQuiz = new DbQuiz(Start_Activity.this);
        switch(getFirstTimeRun()){
            case 0: // Caso para la primera vez que abres la app
                dbQuiz.addQuestions();
                Log.i("DB", "Preguntas añadidas por primera vez");
                break;
            case 1: // Caso para la app ya iniciada
                Log.i("DB", "Las preguntas ya fueron añadidas");
                break;
            case 2: // Caso de actualización de la app
                dbHelper.onUpgrade(db,1,2);
                dbQuiz.addQuestions();
                Log.i("DB", "Lista de Preguntas actualizada");
                break;
        }

        // empezar a jugar
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("lvl", lvl);
                startActivity(intent);
            }
        });

        // salir de la aplicación
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true); // necesario para que el botón no devuelva a la actividad anterior
                // finish();
                System.exit(1); // también puede usarse finish(), pero esto detiene la ejecucion
            }
        });
    }

    // Método que recoge si es la primera vez que se inicia la app para crear la base de datos
    private int getFirstTimeRun() {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}