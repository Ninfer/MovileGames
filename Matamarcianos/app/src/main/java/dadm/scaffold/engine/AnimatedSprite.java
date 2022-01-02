package dadm.scaffold.engine;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;

public abstract class AnimatedSprite extends Sprite{
    private final AnimationDrawable mAnimationDrawable;
    private int mTotalTime;
    private long mCurrentTime;
    public AnimatedSprite(GameEngine gameEngine, int drawableRes){
        super(gameEngine,drawableRes);
        // Now, the drawable must be an animation drawable
        mAnimationDrawable=(AnimationDrawable)spriteDrawable;
        // Caculate the total time of the animation
        mCurrentTime=0;
        mTotalTime=0;
        for(int i=0; i<mAnimationDrawable.getNumberOfFrames();i++){
            mTotalTime+=mAnimationDrawable.getDuration(i);
        }
    }
    protected Bitmap obtainDefaultBitmap(){
        AnimationDrawable ad=(AnimationDrawable)spriteDrawable;
        return ((BitmapDrawable)ad.getFrame(0)).getBitmap();
    }
    @Override
    public void onUpdate(long elapsedMillis,GameEngine gameEngine){
        mCurrentTime+=elapsedMillis;
        if (mCurrentTime > mTotalTime) {
            if(mAnimationDrawable.isOneShot()){
                return;
            }
            else{
                mCurrentTime%=mTotalTime;
            }
        }
        long animationElapsedTime=0;
        for(int i=0; i<mAnimationDrawable.getNumberOfFrames();i++){
            animationElapsedTime+=mAnimationDrawable.getDuration(i);
            if(animationElapsedTime>mCurrentTime){
                bitmap=((BitmapDrawable) mAnimationDrawable.getFrame(i)).getBitmap();
                break;
            }
        }
    }
}
