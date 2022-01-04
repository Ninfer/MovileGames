package dadm.scaffold.space;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameObject;
import dadm.scaffold.counter.GameFragment;

public class GameController extends GameObject {

    private static final int TIME_BETWEEN_ENEMIES = 500;
    private static final int MAX_SCORE = 2000;
    public int currentScore;
    public int currentLives;
    private long currentMillis;
    private List<Asteroid> asteroidPool = new ArrayList<Asteroid>();
    private int enemiesSpawned;
    private Activity mainActivity;

    public TextView textScore;
    public ImageView hit0, hit1, hit2, hit3, hit4;

    public GameController(GameEngine gameEngine, Activity mainActivity) {
        // We initialize the pool of items now
        for (int i=0; i<10; i++) {
            asteroidPool.add(new Asteroid(this, gameEngine));
        }
        this.mainActivity = mainActivity;

        textScore = mainActivity.findViewById(R.id.score_text);

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

    }

    @Override
    public void onDraw(Canvas canvas) {
        if (currentScore >= MAX_SCORE){
            textScore.setText("VICTORIA PARA LA MADRE PATRIA");

        }
        else{
            textScore.setText(String.valueOf(currentScore) + "/" + String.valueOf(MAX_SCORE));
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
}
