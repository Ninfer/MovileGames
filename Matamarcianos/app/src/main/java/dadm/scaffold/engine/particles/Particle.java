package dadm.scaffold.engine.particles;

import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;

import java.util.ArrayList;

/**
 * Created by Raul Portales on 02/04/15.
 */
public class Particle extends Sprite {

    private final ParticleSystem mParent;

    private long mTimeToLive;

    public double mSpeedX;
    public double mSpeedY;

    public double mRotationSpeed;

    private ArrayList<ParticleModifier> mModifiers;
    private long mTotalMillis;
    protected Particle(ParticleSystem particleSystem, GameEngine gameEngine, int drawableRes) {
        super(gameEngine, drawableRes);
        mParent = particleSystem;
    }

    @Override
    public void startGame() {

    }

    @Override
    public void removeFromGameEngine(GameEngine gameEngine) {
        super.removeFromGameEngine(gameEngine);
        mParent.returnToPool(this);
    }

    public void activate(GameEngine gameEngine, long timeToLive, double x, double y, ArrayList<ParticleModifier> modifiers) {
        mTimeToLive = timeToLive;
        positionX = x-width/2;
        positionY = y-height/2;
        addToGameEngine(gameEngine);
        mModifiers = modifiers;
        mTotalMillis = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        mTotalMillis += elapsedMillis;
        if (mTotalMillis > mTimeToLive) {
            // Return it to the pool
            removeFromGameEngine(gameEngine);
        }
        else {
            positionX += mSpeedX*elapsedMillis;
            positionY += mSpeedY*elapsedMillis;
            rotation += mRotationSpeed*elapsedMillis/1000d;
            for (int i=0; i<mModifiers.size(); i++) {
                mModifiers.get(i).apply(this, mTotalMillis);
            }
        }
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {

    }
}
