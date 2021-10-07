package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button finishButton, addButton;
    public static int jofrancos = 0;

    //private TextView text_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //text_1 = findViewById(R.id.text_1);
        finishButton = findViewById(R.id.terminar_button);
        addButton = findViewById(R.id.add_button);

        Intent intent = new Intent(MainActivity.this, Result_Activity.class);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jofrancos = jofrancos + 1;
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}