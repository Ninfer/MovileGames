package dadm.scaffold.engine;

import android.graphics.Canvas;

public abstract class GameObject {

    public abstract void startGame();

    public abstract void onUpdate(long elapsedMillis, GameEngine gameEngine);

    public abstract void onDraw(Canvas canvas);

    public final Runnable onAddedRunnable = new Runnable() {
        @Override
        public void run() {
            onAddedToGameUiThread();
        }
    };

    public void onAddedToGameUiThread(){
    }

    public final Runnable onRemovedRunnable = new Runnable() {
        @Override
        public void run() {
            onRemovedFromGameUiThread();
        }
    };

    public void onRemovedFromGameUiThread(){
    }
    public void addToGameEngine (GameEngine gameEngine) {
        gameEngine.addGameObject(this);
    }
    public void removeFromGameEngine (GameEngine gameEngine) {
        gameEngine.removeGameObject(this);
    }
    public void onAddedToGameEngine() {
    }

    public void onRemovedFromGameEngine() {
    }

}
