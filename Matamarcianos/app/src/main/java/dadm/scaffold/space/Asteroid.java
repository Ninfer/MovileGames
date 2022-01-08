package dadm.scaffold.space;

import android.view.View;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.engine.particles.ParticleSystem;
import dadm.scaffold.engine.particles.ScaleInitializer;
import dadm.scaffold.engine.particles.ScaleModifier;

public class Asteroid extends Sprite {
    public static final int EXPLOSION_PARTICLES = 3;
    private final GameController gameController;
    private final GameEngine gameEngine;

    private double speed;
    private double speedX;
    private double speedY;
    private double rotationSpeed;
    private int maxX;
    private int maxY;
    private ParticleSystem mTrailParticleSystem;
    private ParticleSystem mExplisionParticleSystem;

    public Asteroid(GameController gameController, GameEngine gameEngine) {
        super(gameEngine, R.drawable.asteroid_2);
        //super.setBitmap(R.drawable.asteroid_2);
        this.speed = 250d * pixelFactor/1000d;
        this.gameController = gameController;
        this.gameEngine = gameEngine;

        this.maxX = gameEngine.width - width;
        this.maxY = gameEngine.height - height;

        mTrailParticleSystem = new ParticleSystem(gameEngine, 50, R.drawable.particle_dust, 600)
                .addModifier(new ScaleModifier(1, 2, 200, 600))
                .setFadeOut(200);
        mExplisionParticleSystem = new ParticleSystem(gameEngine, EXPLOSION_PARTICLES, R.drawable.particle, 700)
                .setSpeedRange(15, 40)
                .setFadeOut(300)
                .setInitialRotationRange(0, 360)
                .setRotationSpeedRange(-180, 180);
        mExplisionParticleSystem.addInitializer(new ScaleInitializer(0.5));
    }

    public void init(GameEngine gameEngine) {
        // They initialize in a [-30, 30] degrees angle
        double angle = gameEngine.random.nextDouble()*Math.PI/3d-Math.PI/6d;
        speedX = speed * Math.sin(angle);
        speedY = speed * Math.cos(angle);
        /*
        // Asteroids initialize in the central 50% of the screen vertically
        positionX = gameEngine.random.nextInt(gameEngine.height/2)+gameEngine.height/4.0;
        // They initialize outside of the screen vertically
        positionY = -width;
        */
        // Asteroids initialize in the central 50% of the screen vertically
        positionY = gameEngine.random.nextInt(gameEngine.height/2)+gameEngine.height/4.0;
        //positionY = width;
        // They initialize outside of the screen horizontally
        positionX = maxX + 20;

        rotationSpeed = angle*(180d / Math.PI)/250d; // They rotate 4 times their ange in a second.
        rotation = gameEngine.random.nextInt(360);
        mTrailParticleSystem.clearInitializers()
                .setInitialRotationRange(0,360)
                .setRotationSpeedRange(rotationSpeed * 800, rotationSpeed * 1000)
                .setSpeedByComponentsRange(-speedY * 100, speedY * 100, speedX * 100, speedX * 100);
    }
    @Override
    public void addToGameEngine (GameEngine gameEngine) {
        super.addToGameEngine(gameEngine);
        mTrailParticleSystem.addToGameEngine(gameEngine);
        mTrailParticleSystem.emit(15);
    }
    @Override
    public void startGame() {
    }

    public void removeObject(GameEngine gameEngine) {
        // Return to the pool
        gameEngine.removeGameObject(this);
        gameController.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionY += speedX * elapsedMillis;
        positionX -= speedY * elapsedMillis;
        rotation += rotationSpeed * elapsedMillis;
        if (rotation > 360) {
            rotation = 0;
        }
        else if (rotation < 0) {
            rotation = 360;
        }
        mTrailParticleSystem.setPosition(positionX + width / 2.0, positionY + height / 2.0);
        // Check of the sprite goes out of the screen and return it to the pool if so
        if (positionX < -200 || positionY < 0|| positionY > maxY + 200) {
            // Return to the pool
            gameEngine.removeGameObject(this);
            gameController.returnToPool(this);
        }
    }
    @Override
    public void removeFromGameEngine(GameEngine gameEngine) {
        super.removeFromGameEngine(gameEngine);
        mTrailParticleSystem.stopEmiting();
        mTrailParticleSystem.removeFromGameEngine(gameEngine);
    }
    @Override
    public void onRemovedFromGameEngine() {
        this.gameController.returnToPool(this);
    }
    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {
        if (otherObject instanceof Bullet) {
            gameController.enemiesKilled += 1;
            if(gameController.currentLives == gameController.lastLive){
                gameController.countEnemies += 1;
                if (gameController.countEnemies >= 20){
                    if(gameController.currentLives == 4) gameController.currentScore += 220;
                    if(gameController.currentLives == 3) gameController.currentScore += 210;
                    if(gameController.currentLives == 2) gameController.currentScore += 200;
                    if(gameController.currentLives == 1) gameController.currentScore += 190;
                } else {
                    if(gameController.currentLives == 4) gameController.currentScore += 120;
                    if(gameController.currentLives == 3) gameController.currentScore += 110;
                    if(gameController.currentLives == 2) gameController.currentScore += 100;
                    if(gameController.currentLives == 1) gameController.currentScore += 90;
                }
            } else{
                gameController.lastLive = gameController.currentLives;
                gameController.countEnemies = 0;
                if(gameController.currentLives == 4) gameController.currentScore += 120;
                if(gameController.currentLives == 3) gameController.currentScore += 110;
                if(gameController.currentLives == 2) gameController.currentScore += 100;
                if(gameController.currentLives == 1) gameController.currentScore += 90;
            }
        }
    }
    public void explode(GameEngine gameEngine) {
        mExplisionParticleSystem.oneShot(gameEngine, positionX + width / 2.0, positionY + height / 2.0, EXPLOSION_PARTICLES);
    }
}
