package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result_Activity extends AppCompatActivity {

    private Button restartButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        restartButton = findViewById(R.id.restart_button);
        resultText = findViewById(R.id.result_text);

        //resultText.setText(MainActivity.jofrancos);

        Intent intent = new Intent(Result_Activity.this, MainActivity.class);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}