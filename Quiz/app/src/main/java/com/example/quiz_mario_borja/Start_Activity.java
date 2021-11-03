package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_mario_borja.db.DbHelper;
import com.example.quiz_mario_borja.db.DbQuiz;

public class Start_Activity extends AppCompatActivity {

    private Button startButton,leaveButton;
    private RadioGroup radioGruop;
    private EditText nameText;
    private String name;
    public int lvl = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.jugar_button);
        leaveButton = findViewById(R.id.salir_button);
        radioGruop = findViewById(R.id.Radio_Group);
        nameText = findViewById(R.id.name_text);

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

        // Cargar preferencias
        cargarPreferencias();

        //Selección de modo de juego
        radioGruop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton_facil){
                    Toast.makeText(Start_Activity.this, "¡Modo Fácil Activado!",Toast.LENGTH_LONG).show();
                    //lvl = 5;
                } else if(checkedId == R.id.radioButton_medio){
                    Toast.makeText(Start_Activity.this, "¡Modo Normal Activado!",Toast.LENGTH_LONG).show();
                    //lvl = 10;
                } else if(checkedId == R.id.radioButton_dificil){
                    Toast.makeText(Start_Activity.this, "¡Modo Dificil Activado!",Toast.LENGTH_LONG).show();
                    //lvl = 15;
                }
            }
        });

        // Empezar a jugar
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameText.getText().toString().equals("Nombre")){
                    Toast.makeText(Start_Activity.this, "Nombre anónimo de usuario utilizado!",Toast.LENGTH_LONG).show();
                    nameText.setText("R4nD0m_3");
                }
                guardarPreferencias();
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
    public void cargarPreferencias(){
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);

        String name = sp.getString("name", "Nombre");
        int dificult = sp.getInt("dificult", 2131231156);

        nameText.setText(name);
        radioGruop.check(dificult);
    }

    public void guardarPreferencias(){
        SharedPreferences sp = getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", nameText.getText().toString());
        editor.putInt("dificult", radioGruop.getCheckedRadioButtonId());

        editor.commit();
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