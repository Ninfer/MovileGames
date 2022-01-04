package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_mario_borja.db.DbHelper;
import com.example.quiz_mario_borja.db.DbQuiz;

public class Start_Activity extends AppCompatActivity {

    private Button startButton,leaveButton, optionsButton;
    private TextView nameText;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.jugar_button);
        leaveButton = findViewById(R.id.salir_button);
        optionsButton = findViewById(R.id.opciones_button);
        nameText = findViewById(R.id.name_text);

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

        // Cargar preferencias
        cargarPreferencias();

        // Empezar a jugar
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start_Activity.this, Options_Activity.class);
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

    // Creación y uso del menú ded opciones
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_principal:
                goingMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goingMenu(){
        Intent intent = new Intent(Start_Activity.this, Options_Activity.class);
        startActivity(intent);
    }

    // Lectura de las SharePreferences guardadas en el anterior uso de la app
    public void cargarPreferencias(){
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);

        name = sp.getString("name", "Extraño");

        nameText.setText(name);
    }

    // Método que recoge si es la primera vez que se inicia la app para crear la base de datos
    private int getFirstTimeRun() {
        SharedPreferences sp = getSharedPreferences("defaultSettings", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;z
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}