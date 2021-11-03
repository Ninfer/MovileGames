package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz_mario_borja.db.DbHelper;
import com.example.quiz_mario_borja.db.DbQuiz;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables de layout
    private Button exitButton, AButton, BButton, CButton, DButton;
    private TextView numText, questionText, timeText, helpText;
    private Switch helpSwitch;
    //Variables de gestion de preguntas
    private static final long START_TIME_IN_MILLIS = 15000; //15 s
    private int trueButton, questionOrder;
    private int questionList [];
    private long timeInMilliseconds = START_TIME_IN_MILLIS;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    //Puntuación del juego
    public int jofrancos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recoge variables de Start_Activity
        int lvl = getIntent().getExtras().getInt("lvl");

        //Asignación de los botones a las variables
        exitButton = findViewById(R.id.exit_button);
        AButton = findViewById(R.id.button_A);
        BButton = findViewById(R.id.button_B);
        CButton = findViewById(R.id.button_C);
        DButton = findViewById(R.id.button_D);

        helpSwitch = findViewById(R.id.help_switch);

        //Asignación de los textos a las variables
        numText = findViewById(R.id.num_text);
        questionText = findViewById(R.id.question_text);
        timeText = findViewById(R.id.time_text);
        helpText = findViewById(R.id.help_text);

        Intent intent = new Intent(MainActivity.this, Result_Activity.class);
        Intent outIntent = new Intent(MainActivity.this, Start_Activity.class);
        Random rand = new Random();

        //Inicio de las preguntas al empezar la actividad. Lógica de la estructura iniciada
        helpSwitch.setChecked(false);
        helpText.setVisibility(View.INVISIBLE);
        timerRunning = false;                           //Inicio de la lógica del timer
        questionOrder = 0;
        questionList = new int[lvl];
        resetButtons();                                 //Se asegura que los botones tienen los colores predeterminados
        chooseQuestions(lvl);
        startStop();                                    //Inicio del contador de tiempo

        //Funcionalidad de los 4 botones de juego, los 4 funcionan igual
        AButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprueba si la tecla pulsada es la correcta y si está dentro de tiempo.
                // Si es así, suma los puntos, cambia el color del botón y se asegura de que has elegido una opción

                AButton.setEnabled(false);
                BButton.setEnabled(false);
                CButton.setEnabled(false);
                DButton.setEnabled(false);

                if(trueButton == 1){
                    AButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    AButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");

                // Feedback para que el usuario sepa que ha acertado
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Si es la última pregunta, pasa a la pantalla de resultados, sino, continua
                        if(questionOrder == questionList.length - 1){
                            intent.putExtra("jofrancos", jofrancos);
                            startActivity(intent);
                        } else {
                            helpSwitch.setChecked(false);
                            helpText.setVisibility(View.INVISIBLE);
                            resetButtons();
                            resetTimer();
                            startStop();
                            questionOrder ++;
                            question();
                        }
                    }
                }, 1000);
            }
        });
        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AButton.setEnabled(false);
                BButton.setEnabled(false);
                CButton.setEnabled(false);
                DButton.setEnabled(false);

                if(trueButton == 2){
                    BButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    BButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");

                // Feedback para que el usuario sepa que ha acertado
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Si es la última pregunta, pasa a la pantalla de resultados, sino, continua
                        if(questionOrder == questionList.length - 1){
                            intent.putExtra("jofrancos", jofrancos);
                            startActivity(intent);
                        } else {
                            helpSwitch.setChecked(false);
                            helpText.setVisibility(View.INVISIBLE);
                            resetButtons();
                            resetTimer();
                            startStop();
                            questionOrder ++;
                            question();
                        }
                    }
                }, 1000);
            }
        });
        CButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AButton.setEnabled(false);
                BButton.setEnabled(false);
                CButton.setEnabled(false);
                DButton.setEnabled(false);

                if(trueButton == 3){
                    CButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    CButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");

                // Feedback para que el usuario sepa que ha acertado
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Si es la última pregunta, pasa a la pantalla de resultados, sino, continua
                        if(questionOrder == questionList.length - 1){
                            intent.putExtra("jofrancos", jofrancos);
                            startActivity(intent);
                        } else {
                            helpSwitch.setChecked(false);
                            helpText.setVisibility(View.INVISIBLE);
                            resetButtons();
                            resetTimer();
                            startStop();
                            questionOrder ++;
                            question();
                        }
                    }
                }, 1000);
            }
        });
        DButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AButton.setEnabled(false);
                BButton.setEnabled(false);
                CButton.setEnabled(false);
                DButton.setEnabled(false);

                if(trueButton == 4){
                    DButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                }else{
                    DButton.setBackgroundColor(getResources().getColor(R.color.red));
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");

                // Feedback para que el usuario sepa que ha acertado
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Si es la última pregunta, pasa a la pantalla de resultados, sino, continua
                        if(questionOrder == questionList.length - 1){
                            intent.putExtra("jofrancos", jofrancos);
                            startActivity(intent);
                        } else {
                            helpSwitch.setChecked(false);
                            helpText.setVisibility(View.INVISIBLE);
                            resetButtons();
                            resetTimer();
                            startStop();
                            questionOrder ++;
                            question();
                        }
                    }
                }, 1000);
            }
        });

        //Funcionalidad de los botones del foot
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Muestra un mensaje de tipo "Toast" con los puntos conseguidos hasta el momento
                // y sale al menú pricipal.
                Toast.makeText(MainActivity.this, "¡"+ String.valueOf(jofrancos) + " Jofrancos conseguidos!", Toast.LENGTH_LONG).show();
                startActivity(outIntent);
            }
        });
        updateTimer();
    }

    //Funcionalidad del boton Switch
    public void switchButton(View view){
        if (view.getId() == R.id.help_switch){
            if(helpSwitch.isChecked()){
                helpText.setVisibility(View.VISIBLE);
            }else{
                helpText.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void chooseQuestions(int lvl){
        Random rand = new Random();
        while(questionOrder < lvl){
            int aux = rand.nextInt(lvl);
            boolean val = contains(questionList, questionOrder, aux + 1);
            if((questionOrder > 0) && (!val))
            //if((questionOrder > 0) && (aux + 1 != questionList[questionOrder - 1]))
            {
                questionList[questionOrder] = aux + 1;
                questionOrder ++;
            } else if(questionOrder == 0) {
                questionList[questionOrder] = aux + 1;
                questionOrder ++;
            }
        }
        questionOrder = 0;
        question();
    }

    public boolean contains(int[] list, int length, int key) {
        boolean val = false;
        for(int i = 0; i < length; i++){
            if(list[i] == key) val = true;
        }
        return val;
    }

    private void question(){
        DbHelper dbHelper = new DbHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor question = null;

        question = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_QUESTIONS + " WHERE id = " + questionList[questionOrder], null);

        if(question.moveToFirst()){
            questionText.setText(question.getString(1).toString());
            helpText.setText(question.getString(2).toString());
            AButton.setText(question.getString(3).toString());
            BButton.setText(question.getString(4).toString());
            CButton.setText(question.getString(5).toString());
            DButton.setText(question.getString(6));
            trueButton = question.getInt(7);
        }

        numText.setText(String.valueOf(questionOrder + 1) + "/" + String.valueOf(questionList.length));

        question.close();
    }

    //Método que devuelve los botones a sus colores por defecto al comenzar una nueva pregunta
    private void resetButtons(){
        AButton.setEnabled(true);
        BButton.setEnabled(true);
        CButton.setEnabled(true);
        DButton.setEnabled(true);
        AButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        BButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        CButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        DButton.setBackgroundColor(getResources().getColor(R.color.platinum));

        timeText.setText("INICIO");
    }

    //Lógica de funcionamiento del reloj a través de la funcionalidad de CountDownTimer y un flag
    // que recoge cuándo el reloj está en funcionamiento
    public void startStop(){
        if(timerRunning){
            stopTimer();
        }else{
            starTimer();
        }
    }

    public void starTimer(){
        //Se crea el objeto "cuenta atrás" con el tiempo que queremos y el paso del intervalo (1000)
        countDownTimer = new CountDownTimer(timeInMilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeInMilliseconds = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                // Si se acaba el tiempo, desabilita los puntos asociados a los botones de victoria y
                // pone visible el botón para pasar de pregunta.
                timerRunning = false;
                timeText.setText("FIN");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                helpSwitch.setChecked(false);
                helpText.setVisibility(View.INVISIBLE);
                resetButtons();
                resetTimer();
                startStop();
                questionOrder ++;
                question();
            }
        }.start();

        timerRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        timerRunning = false;
    }

    //Método llamado por cada tick del reloj. Hace un set en el botón de los valores actuales de tiempo
    public void updateTimer(){
        int minutes = (int) timeInMilliseconds / 60000;
        int seconds = (int) timeInMilliseconds % 60000 / 1000;
        /*
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds
         */
        String timeLeftText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        timeText.setText(timeLeftText);
    }

    public void resetTimer(){
        timeInMilliseconds = START_TIME_IN_MILLIS;
        updateTimer();
    }
}