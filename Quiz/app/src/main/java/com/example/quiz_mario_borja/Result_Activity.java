package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Result_Activity extends AppCompatActivity {

    private Button restartButton, exitButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        restartButton = findViewById(R.id.restart_button);
        exitButton = findViewById(R.id.exit_button2);
        resultText = findViewById(R.id.result_num);

        String result = String.valueOf(getIntent().getExtras().getInt("jofrancos"));
        resultText.setText(result + " JOFRANCOS");



        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result_Activity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Muestra un mensaje de tipo "Toast" con los puntos conseguidos hasta el momento
                // y sale al menú pricipal.
                Intent outIntent = new Intent(Result_Activity.this, Start_Activity.class);
                Toast.makeText(Result_Activity.this,  "¡No olvides volver a jugar pronto!", Toast.LENGTH_LONG).show();
                startActivity(outIntent);
            }
        });
    }
}