package dadm.scaffold.space;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.input.InputController;
import dadm.scaffold.sound.GameEvent;

public class SpaceShipPlayer extends Sprite {

    private static final int INITIAL_BULLET_POOL_AMOUNT = 100;
    private static final long TIME_BETWEEN_BULLETS = 450;
    private static final long INVULNERAVILITY_TIME_STEP = 1000;
    List<Bullet> bullets = new ArrayList<Bullet>();
    private long timeSinceLastFire;

    private long lastFrameChangeTime = 0;
    private int frameLengthInMillisecond = 500;
    private int nextResourceIntegerId = 0;

    private int maxX;
    private int maxY;
    private double speedFactor;

    //Variables añadidas
    private final GameController gameController;
    private long invulneravility = INVULNERAVILITY_TIME_STEP;
    private boolean hited = true;

    public SpaceShipPlayer(GameEngine gameEngine, GameController gameController){
        super(gameEngine, R.drawable.ship_a);
        nextResourceIntegerId = R.drawable.ship;
        super.setBitmap(nextResourceIntegerId);
        speedFactor = pixelFactor * 200d / 1000d; // We want to move at 100px per second on a 400px tall screen
        maxX = gameEngine.width - width;
        maxY = gameEngine.height - height;

        this.gameController = gameController;

        initBulletPool(gameEngine);
    }

    private void initBulletPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            bullets.add(new Bullet(gameEngine));
        }
    }

    private Bullet getBullet() {
        if (bullets.isEmpty()) {
            return null;
        }
        return bullets.remove(0);
    }

    void releaseBullet(Bullet bullet) {
        bullets.add(bullet);
    }


    @Override
    public void startGame() {
        positionX = maxX / 2;
        positionY = maxY / 2;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.theInputController);
        checkFiring(elapsedMillis, gameEngine);
        checkInvulneravility(elapsedMillis);
    }

    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        boolean altMode = gameEngine.theInputController.altFireMode;
        if (gameEngine.theInputController.isFiring && timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            if (!altMode) {
                Bullet bullet = getBullet();
                if (bullet == null) {
                    return;
                }
                //bullet.init(this, positionX + width / 2, positionY, altMode, false);
                bullet.init(this, positionX + height, positionY + height / 2, altMode, false);

                gameEngine.addGameObject(bullet);
                timeSinceLastFire = 0;
                //gameEngine.onGameEvent(GameEvent.LaserFired);
            }
            else {
                Bullet bullet = getBullet();
                if (bullet == null) {
                    return;
                }
                bullet.init(this, positionX+20 + width, positionY + height / 2, altMode, false);

                Bullet bullet2 = getBullet();
                if (bullet2 == null) {
                    return;
                }
                bullet2.init(this, positionX-20 + width, positionY + height / 2, altMode, true);

                gameEngine.addGameObject(bullet);
                gameEngine.addGameObject(bullet2);
                timeSinceLastFire = 0;
                //gameEngine.onGameEvent(GameEvent.LaserFired);
            }
        }
        else {
            timeSinceLastFire += elapsedMillis;
        }
    }


    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Asteroid && hited){
            gameController.currentLives -= 1;
            hited = false;
            invulneravility = INVULNERAVILITY_TIME_STEP;
            Log.i("Vidas", String.valueOf(gameController.currentLives));
            if (gameController.currentLives > 0) {
                Asteroid a = (Asteroid) otherObject;
                a.removeObject(gameEngine);
                gameEngine.onGameEvent(GameEvent.SpaceshipHit);
                nextResourceIntegerId = R.drawable.ship_inv;
                super.setBitmap(nextResourceIntegerId);
            }
            else {
                gameEngine.removeGameObject(this);
                Asteroid a = (Asteroid) otherObject;
                a.removeObject(gameEngine);
                gameEngine.onGameEvent(GameEvent.SpaceshipHit);
            }
        }
    }

    private void checkInvulneravility(long elapsedMillis){
        if (invulneravility > 0){
            invulneravility -= elapsedMillis;
            hited = false;
        }else{
            hited = true;
            nextResourceIntegerId = R.drawable.ship;
            super.setBitmap(nextResourceIntegerId);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        long time = System.currentTimeMillis();
        /*
            if (time > lastFrameChangeTime + frameLengthInMillisecond) {
                lastFrameChangeTime = time;
                super.setBitmap(nextResourceIntegerId);
                if (nextResourceIntegerId == R.drawable.ship_a) {
                    nextResourceIntegerId = R.drawable.ship_b;
                } else {
                    nextResourceIntegerId = R.drawable.ship_a;
                }
            }
         */
        super.onDraw(canvas);
    }

}
