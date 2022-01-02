package dadm.scaffold.space;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.sound.GameEvent;

public class Bullet extends Sprite {

    private double speedFactor;
    private boolean altBullet;
    private boolean leftBullet;

    private SpaceShipPlayer parent;

    public Bullet(GameEngine gameEngine){
        super(gameEngine, R.drawable.bullet);

        altBullet = gameEngine.theInputController.altFireMode;

        speedFactor = gameEngine.pixelFactor * -300d / 1000d;
    }

    @Override
    public void startGame() {}

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        if (!altBullet){
            positionY += speedFactor * elapsedMillis;
            if (positionY < -height) {
                gameEngine.removeGameObject(this);
                // And return it to the pool
                parent.releaseBullet(this);
            }
        }
        else {
            positionY += speedFactor * elapsedMillis;
            if (leftBullet)
                positionX += speedFactor/2 * elapsedMillis;
            else
                positionX -= speedFactor/2 * elapsedMillis;
            if (positionY < -height) {
                gameEngine.removeGameObject(this);
                // And return it to the pool
                parent.releaseBullet(this);
            }
        }
    }


    public void init(SpaceShipPlayer parentPlayer, double initPositionX, double initPositionY, boolean type, boolean left) {
        positionX = initPositionX - width/2;
        positionY = initPositionY - height/2;
        parent = parentPlayer;
        altBullet = type;
        leftBullet = left;
    }

    private void removeObject(GameEngine gameEngine) {
        gameEngine.removeGameObject(this);
        // And return it to the pool
        parent.releaseBullet(this);
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid) {
            // Remove both from the game (and return them to their pools)
            removeObject(gameEngine);
            Asteroid a = (Asteroid) otherObject;
            a.removeObject(gameEngine);
            a.explode(gameEngine);
            gameEngine.onGameEvent(GameEvent.AsteroidHit);
            // Add some score
        }
    }
}
