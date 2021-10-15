package com.example.quiz_mario_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Variables de layout
    private Button nextButton, finishButton, exitButton, AButton, BButton, CButton, DButton;
    private TextView numText, questionText, timeText, helpText;
    private Switch helpSwitch;
    //Variables de gestion de preguntas
    private static final long START_TIME_IN_MILLIS = 60000; //1 min (creo)
    private int questionOrder, questionSum, trueButton;
    private long timeInMilliseconds = START_TIME_IN_MILLIS;
    private CountDownTimer countDownTimer;
    private boolean timerRunning, answerChoosen;
    //Puntuación del juego
    public int jofrancos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignación de los botones a las variables
        nextButton = findViewById(R.id.next_button);
        finishButton = findViewById(R.id.finish_button);
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
        questionSum = 1;
        helpSwitch.setChecked(false);
        helpText.setVisibility(View.INVISIBLE);
        answerChoosen = false;
        timerRunning = false;                            //Inicio de la lógica del timer
        questionOrder = rand.nextInt(5);          //Numero aleatorio por el que empezarán las preguntas
        resetButtons();                                 //Se asegura que los botones tienen los colores predeterminados
        chooseQuestion(questionOrder);                  //Inicio aleatorio del carruesl de preguntas
        startStop();                                    //Inicio del contador de tiempo

        //Funcionalidad de los 4 botones de juego, los 4 funcionan igual
        AButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprueba si la tecla pulsada es la correcta y si está dentro de tiempo.
                // Si es así, suma los puntos, cambia el color del botón y se asegura de que has elegido una opción
                if(trueButton == 1 && !answerChoosen){
                    AButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                    answerChoosen = true;
                }else{
                    AButton.setBackgroundColor(getResources().getColor(R.color.red));
                    answerChoosen = true;
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");
                //nextButton.setEnabled(true);
                //nextButton.setVisibility(View.VISIBLE);

                //Lógica para resetear las preguntas y cambiar la funcionalidad del botón si
                // se correspondía con la última pregunta
                if (questionSum == 5){
                    //nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });
        BButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 2 && !answerChoosen){
                    BButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                    answerChoosen = true;
                }else{
                    BButton.setBackgroundColor(getResources().getColor(R.color.red));
                    answerChoosen = true;
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");
                //nextButton.setEnabled(true);
                //nextButton.setVisibility(View.VISIBLE);

                if (questionSum == 5){
                    //nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });
        CButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 3 && !answerChoosen){
                    CButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                    answerChoosen = true;
                }else{
                    CButton.setBackgroundColor(getResources().getColor(R.color.red));
                    answerChoosen = true;
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");
                //nextButton.setEnabled(true);
                //nextButton.setVisibility(View.VISIBLE);

                if (questionSum == 5){
                    //nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                }
            }
        });
        DButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueButton == 4 && !answerChoosen){
                    DButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    jofrancos += 3;
                    answerChoosen = true;
                }else{
                    DButton.setBackgroundColor(getResources().getColor(R.color.red));
                    answerChoosen = true;
                }
                stopTimer();
                timerRunning = false;
                timeText.setText("FIN");
                //nextButton.setEnabled(true);
                //nextButton.setVisibility(View.VISIBLE);

                if (questionSum == 5){
                    //nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                } else {
                    nextButton.setEnabled(true);
                    nextButton.setVisibility(View.VISIBLE);
                }

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionOrder >= 4){
                    questionOrder = 0;
                }else{
                    questionOrder++;
                }

                //Genera la siguiente pregunta, resetea los colores de los botones en default,
                //resetea el timer y vuelve a empezar tras pulsar.
                questionSum++;
                answerChoosen = false;
                helpSwitch.setChecked(false);
                helpText.setVisibility(View.INVISIBLE);
                resetButtons();
                resetTimer();
                chooseQuestion(questionOrder);
                startStop();
            }
        });
        //Botón que aparece al completar las 5 preguntas, sirve para lanzar la actividad de resultados
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("jofrancos", jofrancos);
                startActivity(intent);
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

    //Carrusel de preguntas definidas a fuego en el código, se establece el texto de la pregunta,
    // la respuesta de los botones y el banner de número de preguntas. Guarda con un id el botón
    // que contiene la respuesta correcta asociada con el texto que se le ha asignado.
    private void chooseQuestion(int num){
        switch (num){
            case 0:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año pisó la luna el ser humano?");
                helpText.setText("La guerra fría estaba en su apogeo");
                AButton.setText("1966");
                trueButton = 1;
                BButton.setText("1976");
                CButton.setText("1866");
                DButton.setText("1956");

                /*if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }*/

                break;
            case 1:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año salio el DLC The Last of Us: Left Behind?");
                helpText.setText("Salió un año después que el juego original");
                AButton.setText("2013");
                BButton.setText("2014");
                trueButton = 2;
                CButton.setText("2016");
                DButton.setText("2017");

                /*if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }*/

                break;
            case 2:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año se publicó el juego de ROL de mesa 'Dungeons & Dragons'?");
                helpText.setText("20 años después de la publicación del libro 'El señor de los anillos'");
                AButton.setText("1980");
                BButton.setText("1966");
                CButton.setText("1974");
                trueButton = 3;
                DButton.setText("1983");

                /*if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }*/

                break;
            case 3:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Qué año llegó al espacio el primer astronauta Español?");
                helpText.setText("Tiene trampa");
                AButton.setText("2001");
                BButton.setText("1995");
                CButton.setText("1965");
                DButton.setText("1973");
                trueButton = 4;

                /*if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }*/

                break;
            case 4:
                numText.setText(String.valueOf(questionSum) + "/5");
                questionText.setText("¿Que año se publicó el libro 'El Capital' de Karl Marx?");
                helpText.setText("Fue escrito tras el inicio de la revolución industrial en Europa");
                AButton.setText("1917");
                BButton.setText("1867");
                trueButton = 2;
                CButton.setText("1887");
                DButton.setText("1697");

                /*if(questionSum == 5){
                    nextButton.setEnabled(false);
                    finishButton.setEnabled(true);
                    finishButton.setVisibility(View.VISIBLE);
                }*/

                break;
            default:
                break;
        }

    }

    //Método que devuelve los botones a sus colores por defecto al comenzar una nueva pregunta
    private void resetButtons(){
        AButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        BButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        CButton.setBackgroundColor(getResources().getColor(R.color.platinum));
        DButton.setBackgroundColor(getResources().getColor(R.color.platinum));

        nextButton.setEnabled(false);
        nextButton.setVisibility(View.INVISIBLE);

        finishButton.setEnabled(false);
        finishButton.setVisibility(View.INVISIBLE);

        timeText.setText("INICIO");
    }

    //Lógica de funcionamiento del relog a través de la funcionalidad de CountDownTimer y un flag
    // que recoge cuándo el relog está en funcionamiento
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
                //Si se acaba el tiempo, desabilita los puntos asociados a los botones de victoria y
                // pone visible el botón para pasar de pregunta.
                timerRunning = false;
                answerChoosen = true;
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);
                timeText.setText("FIN");
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