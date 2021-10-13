package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button nextButton, finishButton, exitButton, AButton, BButton, CButton, DButton;
    private TextView numText, questionText, timeText;
    private int questionOrder, questionSum, trueButton;
    public int jofrancos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignación de los botones a las variables
        nextButton = findViewById(R.id.next_button);
        nextButton.setEnabled(false);
        nextButton.setVisibility(View.INVISIBLE);

        finishButton = findViewById(R.id.finish_button);
        finishButton.setEnabled(false);
        finishButton.setVisibility(View.INVISIBLE);

        exitButton = findViewById(R.id.exit_button);
        AButton = findViewById(R.id.button_A);
        BButton = findViewById(R.id.button_B);
        CButton = findViewById(R.id.button_C);
        DButton = findViewById(R.id.button_D);

        //Asignación de los textos a las variables
        numText = findViewById(R.id.num_text);
        questionText = findViewById(R.id.question_text);
        timeText = findViewById(R.id.time_text);

        Intent intent = new Intent(MainActivity.this, Result_Activity.class);
        Intent outIntent = new Intent(MainActivity.this, Start_Activity.class);
        Random rand = new Random();

        questionSum = 1;
        questionOrder = rand.nextInt(5);
        resertButtons();
        chooseQuestion(questionOrder);

        AButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 1){
                    AButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    AButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                timeText.setText("FIN");
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        });
        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 2){
                    BButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    BButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                timeText.setText("FIN");
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        });
        CButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 3){
                    CButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    CButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                timeText.setText("FIN");
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        });
        DButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 4){
                    DButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    DButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                timeText.setText("FIN");
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, String.valueOf(jofrancos) + " Jofrancos conseguidos!", Toast.LENGTH_LONG).show();
                startActivity(outIntent);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionOrder >= 4){
                    questionOrder = 0;
                }else{
                    questionOrder++;
                }

                questionSum++;
                resertButtons();
                chooseQuestion(questionOrder);
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
    private void chooseQuestion(int num){
        switch (num){
            case 0:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año pisó la luna el ser humano?");
                //timeText.setText();
                AButton.setText("1966");
                trueButton = 1;
                BButton.setText("1976");
                CButton.setText("1866");
                DButton.setText("1956");

                if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }

                break;
            case 1:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año salio el DLC The Last of Us: Left Behind?");
                //timeText.setText();
                AButton.setText("2013");
                BButton.setText("2014");
                trueButton = 2;
                CButton.setText("2016");
                DButton.setText("2017");

                if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }

                break;
            case 2:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año se publicó el juego de ROL de mesa 'Dungeons & Dragons'?");
                //timeText.setText();
                AButton.setText("1980");
                BButton.setText("1966");
                CButton.setText("1974");
                trueButton = 3;
                DButton.setText("1983");

                if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }

                break;
            case 3:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año llegó al espacio el primer astronauta Español?");
                //timeText.setText();
                AButton.setText("2001");
                BButton.setText("1995");
                CButton.setText("1965");
                DButton.setText("1973");
                trueButton = 4;

                if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }

                break;
            case 4:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Que año se publicó el libro 'El Capital' de Karl Marx");
                //timeText.setText();
                AButton.setText("1917");
                BButton.setText("1867");
                trueButton = 2;
                CButton.setText("1887");
                DButton.setText("1697");

                if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }

                break;
            default:
                break;
        }

    }
    private void resertButtons(){
        AButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        BButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        CButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        DButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        timeText.setText("INICIO");
    }
}