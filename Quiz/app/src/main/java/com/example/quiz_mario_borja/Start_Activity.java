package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start_Activity extends AppCompatActivity {

    private Button startButton,leaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startButton = findViewById(R.id.jugar_button);
        leaveButton = findViewById(R.id.salir_button);

        Intent intent = new Intent(Start_Activity.this, MainActivity.class);

        // empezar a jugar
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}