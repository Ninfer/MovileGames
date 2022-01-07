package dadm.scaffold.space;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.LinearInterpolator;
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

    private static final int TIME_BETWEEN_ENEMIES = 150;
    private static final int MAX_SCORE = 10000;
    private static final long START_TIME_IN_MILLIS = 90000; //2m 30s (1000 = 1s)
    public int currentScore;
    public int currentLives;
    public int enemiesKilled, lastLive, countEnemies;
    private long currentMillis;
    private List<Asteroid> asteroidPool = new ArrayList<Asteroid>();
    private int enemiesSpawned;
    private Activity mainActivity;
    private GameEngine theGameEngine;


    private long timeInMilliseconds = START_TIME_IN_MILLIS;
    private CountDownTimer countDownTimer;
    private boolean timerRunning, timerFinish;

    public TextView textTime, textScore, textX2;
    public ImageView hit0, hit1, hit2, hit3, hit4;

    //Background parallax variables
    final ImageView backgroundOne1, backgroundOne2, backgroundTwo1, backgroundTwo2,
            backgroundThree1, backgroundThree2, backgroundFour1, backgroundFour2;
    final ValueAnimator animatorOne = ValueAnimator.ofFloat(0.0f, -1.0f); // 1.0 para la derecha, -1.0 para la izq
    final ValueAnimator animatorTwo = ValueAnimator.ofFloat(0.0f, -1.0f);
    final ValueAnimator animatorThree = ValueAnimator.ofFloat(0.0f, -1.0f);
    final ValueAnimator animatorFour = ValueAnimator.ofFloat(0.0f, -1.0f);

    public GameController(GameEngine gameEngine, Activity mainActivity) {
        // We initialize the pool of items now
        for (int i=0; i<300; i++) {
            asteroidPool.add(new Asteroid(this, gameEngine));
        }
        this.mainActivity = mainActivity;
        this.theGameEngine = gameEngine;

        textTime = mainActivity.findViewById(R.id.text_time);
        textScore = mainActivity.findViewById(R.id.text_score);
        textX2 = mainActivity.findViewById(R.id.text_x2);

        //textX2.setVisibility(View.INVISIBLE);

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

        //Parallax behaviour
        backgroundOne1 = mainActivity.findViewById(R.id.background_one_1);
        backgroundOne2 = mainActivity.findViewById(R.id.background_one_2);

        backgroundTwo1 = mainActivity.findViewById(R.id.background_two_1);
        backgroundTwo2 = mainActivity.findViewById(R.id.background_two_2);

        backgroundThree1 = mainActivity.findViewById(R.id.background_three_1);
        backgroundThree2 = mainActivity.findViewById(R.id.background_three_2);

        backgroundFour1 = mainActivity.findViewById(R.id.background_four_1);
        backgroundFour2 = mainActivity.findViewById(R.id.background_four_2);

        //Backgroun 1
        animatorOne.setRepeatCount(ValueAnimator.INFINITE);
        animatorOne.setInterpolator(new LinearInterpolator());
        animatorOne.setDuration(90000L); //Cambiar la velocidad de la animación
        animatorOne.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundOne1.getWidth();
                final float translationX = width * progress;
                backgroundOne1.setTranslationX(translationX);
                backgroundOne2.setTranslationX(translationX + width);
            }
        });
        animatorOne.start();

        //Backgroun 2
        animatorTwo.setRepeatCount(ValueAnimator.INFINITE);
        animatorTwo.setInterpolator(new LinearInterpolator());
        animatorTwo.setDuration(50000L); //Cambiar la velocidad de la animación
        animatorTwo.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundTwo1.getWidth();
                final float translationX = width * progress;
                backgroundTwo1.setTranslationX(translationX);
                backgroundTwo2.setTranslationX(translationX + width);
            }
        });
        animatorTwo.start();

        //Backgroun 3
        animatorThree.setRepeatCount(ValueAnimator.INFINITE);
        animatorThree.setInterpolator(new LinearInterpolator());
        animatorThree.setDuration(30000L); //Cambiar la velocidad de la animación
        animatorThree.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundThree1.getWidth();
                final float translationX = width * progress;
                backgroundThree1.setTranslationX(translationX);
                backgroundThree2.setTranslationX(translationX + width);
            }
        });
        animatorThree.start();

        //Backgroun 4
        animatorFour.setRepeatCount(ValueAnimator.INFINITE);
        animatorFour.setInterpolator(new LinearInterpolator());
        animatorFour.setDuration(20000L); //Cambiar la velocidad de la animación
        animatorFour.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();
                final float width = backgroundFour1.getWidth();
                final float translationX = width * progress;
                backgroundFour1.setTranslationX(translationX);
                backgroundFour2.setTranslationX(translationX + width);
            }
        });
        animatorFour.start();


    }

    @Override
    public void startGame() {
        currentMillis = 0;
        enemiesSpawned = 0;
        enemiesKilled = 0;
        countEnemies = 0;
        currentScore = 0;
        currentLives = 4;
        lastLive = 4;

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

        //Comprueba la condición de victoria y derrota para pasar a la pantalla "Score" (para otro modo de juego)
        //if (currentLives <= 0 || currentScore >= MAX_SCORE){
        if (currentLives <= 0 || timerFinish){
            //Se ejecuta al instante, se puede meter un tiempo de espera para pasar a la pantalla final
            gameEngine.stopGame();
            gameEngine.stopGame();
            startStop();

            int finalScore = calculateFinalScore();

            Context context = mainActivity.getApplicationContext();
            SharedPreferences sp = context.getSharedPreferences("defaultSettings", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("finalScore", finalScore);
            editor.putInt("score", currentScore);
            editor.putInt("enemies", enemiesKilled);
            editor.putInt("lives", currentLives);
            editor.commit();

            ((ScaffoldActivity)mainActivity).scoreMenu();
        }

    }

    @Override
    public void onDraw(Canvas canvas) {
        textScore.setText(String.valueOf(currentScore));

        if(currentLives == lastLive && countEnemies >= 20){
            textX2.setVisibility(View.VISIBLE);
        }else{
            textX2.setVisibility(View.INVISIBLE);
        }

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

    public int calculateFinalScore(){
        return 0;
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
                textTime.setText("FIN");
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
        textTime.setText(timeLeftText);
    }

    public void resetTimer(){
        timeInMilliseconds = START_TIME_IN_MILLIS;
        updateTimer();
    }
}
