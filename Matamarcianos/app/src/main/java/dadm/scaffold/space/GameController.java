package dadm.scaffold.space;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameObject;
import dadm.scaffold.counter.GameFragment;

public class GameController extends GameObject {

    private static final int TIME_BETWEEN_ENEMIES = 500;
    public int currentScore;
    public int currentLives;
    private long currentMillis;
    private List<Asteroid> asteroidPool = new ArrayList<Asteroid>();
    private int enemiesSpawned;
    private Activity mainActivity;

    public TextView textScore;

    public GameController(GameEngine gameEngine, Activity mainActivity) {
        // We initialize the pool of items now
        for (int i=0; i<10; i++) {
            asteroidPool.add(new Asteroid(this, gameEngine));
        }
        this.mainActivity = mainActivity;

        textScore = mainActivity.findViewById(R.id.score_text);
    }

    @Override
    public void startGame() {
        currentMillis = 0;
        enemiesSpawned = 0;
        currentScore = 0;
        currentLives = 3;
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
        if (currentScore >= 1000){
            textScore.setText("VICTORIA PARA LA MADRE PATRIA");

        }
        else{
            textScore.setText(String.valueOf(currentScore));
        }
    }

    public void returnToPool(Asteroid asteroid) {
        asteroidPool.add(asteroid);
    }
}
