package dadm.scaffold.space;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dadm.scaffold.R;
import dadm.scaffold.ScaffoldActivity;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameObject;

public class GameController extends GameObject {

    private static final int TIME_BETWEEN_ENEMIES = 400;
    private static final int MAX_SCORE = 10000;
    private static final long START_TIME_IN_MILLIS = 10000; //10 s
    public int currentScore;
    public int currentLives;
    private long currentMillis;
    private List<Asteroid> asteroidPool = new ArrayList<Asteroid>();
    private int enemiesSpawned;
    private Activity mainActivity;
    private GameEngine theGameEngine;


    private long timeInMilliseconds = START_TIME_IN_MILLIS;
    private CountDownTimer countDownTimer;
    private boolean timerRunning, timerFinish;

    public TextView textScore;
    public ImageView hit0, hit1, hit2, hit3, hit4;

    public GameController(GameEngine gameEngine, Activity mainActivity) {
        // We initialize the pool of items now
        for (int i=0; i<100; i++) {
            asteroidPool.add(new Asteroid(this, gameEngine));
        }
        this.mainActivity = mainActivity;
        this.theGameEngine = gameEngine;

        textScore = mainActivity.findViewById(R.id.text_score);

        hit0 = mainActivity.findViewById(R.id.img_hit0);
        hit1 = mainActivity.findViewById(R.id.img_hit1);
        hit2 = mainActivity.findViewById(R.id.img_hit2);
        hit3 = mainActivity.findViewById(R.id.img_hit3);
        hit4 = mainActivity.findViewById(R.id.img_hit4);

        hit0.setVisibility(View.VISIBLE);
        hit1.setVisibility(View.INVISIBLE);
        hit2.setVisibility(View.INVISIBLE);
        hit3.setVisibility(View.INVISIBLE);
        hit4.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startGame() {
        currentMillis = 0;
        enemiesSpawned = 0;
        currentScore = 0;
        currentLives = 4;

        timerRunning = false;
        timerFinish = false;
        startStop();
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        currentMillis += elapsedMillis;

        long waveTimestamp = enemiesSpawned*TIME_BETWEEN_ENEMIES;
        if (currentMillis > waveTimestamp) {
            // Spawn a new enemy
            Asteroid a = asteroidPool.remove(0);
            a.init(gameEngine);
            gameEngine.addGameObject(a);
            enemiesSpawned++;
            return;
        }

        //Comprueba la condición de victoria y derrota para pasar a la pantalla "Score"
        //if (currentLives <= 0 || currentScore >= MAX_SCORE){
        if (currentLives <= 0 || timerFinish){
            //Se ejecuta al instante, se puede meter un tiempo de espera para pasar a la pantalla final
            gameEngine.stopGame();

            Context context = mainActivity.getApplicationContext();
            SharedPreferences sp = context.getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("score", currentScore);
            editor.commit();

            ((ScaffoldActivity)mainActivity).scoreMenu();
        }

    }

    @Override
    public void onDraw(Canvas canvas) {
        //textScore.setText(String.valueOf(currentScore) + "/" + String.valueOf(MAX_SCORE));


        if(currentLives == 4){
            hit0.setVisibility(View.VISIBLE);
            hit1.setVisibility(View.INVISIBLE);
            hit2.setVisibility(View.INVISIBLE);
            hit3.setVisibility(View.INVISIBLE);
            hit4.setVisibility(View.INVISIBLE);
        }else if(currentLives == 3){
            hit0.setVisibility(View.INVISIBLE);
            hit1.setVisibility(View.VISIBLE);
            hit2.setVisibility(View.INVISIBLE);
            hit3.setVisibility(View.INVISIBLE);
            hit4.setVisibility(View.INVISIBLE);
        }else if(currentLives == 2){
            hit0.setVisibility(View.INVISIBLE);
            hit1.setVisibility(View.INVISIBLE);
            hit2.setVisibility(View.VISIBLE);
            hit3.setVisibility(View.INVISIBLE);
            hit4.setVisibility(View.INVISIBLE);
        }else if(currentLives == 1){
            hit0.setVisibility(View.INVISIBLE);
            hit1.setVisibility(View.INVISIBLE);
            hit2.setVisibility(View.INVISIBLE);
            hit3.setVisibility(View.VISIBLE);
            hit4.setVisibility(View.INVISIBLE);
        }else{
            hit0.setVisibility(View.INVISIBLE);
            hit1.setVisibility(View.INVISIBLE);
            hit2.setVisibility(View.INVISIBLE);
            hit3.setVisibility(View.INVISIBLE);
            hit4.setVisibility(View.VISIBLE);
        }
    }

    public void returnToPool(Asteroid asteroid) {
        asteroidPool.add(asteroid);
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
                timerRunning = false;
                textScore.setText("FIN");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Finaliza el juego si termina el contador
                //timerFinish = true;

                theGameEngine.stopGame();

                Context context = mainActivity.getApplicationContext();
                SharedPreferences sp = context.getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("score", currentScore);
                editor.commit();

                ((ScaffoldActivity)mainActivity).scoreMenu();
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
        textScore.setText(timeLeftText);
    }

    public void resetTimer(){
        timeInMilliseconds = START_TIME_IN_MILLIS;
        updateTimer();
    }
}
