package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button finishButton, addButton;
    public int jofrancos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finishButton = findViewById(R.id.terminar_button);
        addButton = findViewById(R.id.add_button);

        Intent intent = new Intent(MainActivity.this, Result_Activity.class);
        jofrancos = 0;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jofrancos = jofrancos + 1;
                Toast.makeText(MainActivity.this, "+1 Jofrancos!", Toast.LENGTH_LONG).show();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("jofrancos", jofrancos);
                startActivity(intent);
            }
        });
    }
}