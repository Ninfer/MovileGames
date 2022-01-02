package dadm.scaffold.engine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class Sprite extends ScreenGameObject {

    public double rotation;

    public double pixelFactor;

    protected Bitmap bitmap;

    private final Matrix matrix = new Matrix();
    private final Paint mPaint = new Paint();
    public int mAlpha = 255;
    public double mScale = 1;

    private GameEngine theGameEngine;

    public Drawable spriteDrawable;

    protected Sprite (GameEngine gameEngine, int drawableRes) {
        this.theGameEngine = gameEngine;

        Resources r = gameEngine.getContext().getResources();
        spriteDrawable = r.getDrawable(drawableRes);

        this.pixelFactor = gameEngine.pixelFactor;

        this.height = (int) (spriteDrawable.getIntrinsicHeight() * this.pixelFactor);
        this.width = (int) (spriteDrawable.getIntrinsicWidth() * this.pixelFactor);

        this.bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();

        radius = Math.max(height, width)/2;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (positionX > canvas.getWidth()
                || positionY > canvas.getHeight()
                || positionX < - width
                || positionY < - height) {
            return;
        }
        float scaleFactor = (float) (pixelFactor*mScale);
        matrix.reset();
        matrix.postScale( scaleFactor, scaleFactor);
        matrix.postTranslate((float) positionX, (float) positionY);
        matrix.postRotate((float) rotation, (float) (positionX + width/2), (float) (positionY + height/2));
        mPaint.setAlpha(mAlpha);
        canvas.drawBitmap(bitmap, matrix, mPaint);
    }

    public void setBitmap(int drawableRes) {
        Resources r = theGameEngine.getContext().getResources();
        Drawable spriteDrawable = r.getDrawable(drawableRes);
        this.bitmap = ((BitmapDrawable) spriteDrawable).getBitmap();
    }
}
