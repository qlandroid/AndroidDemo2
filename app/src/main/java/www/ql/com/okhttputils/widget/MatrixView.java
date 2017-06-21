package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-20.
 */
public class MatrixView extends View {
    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = ((BitmapDrawable) context.getResources().getDrawable(R.mipmap.shi)).getBitmap();
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.setFocusable(true);
    }

    public MatrixView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Bitmap bitmap;
    private Matrix matrix = new Matrix();
    //设置倾斜角度
    private float sx = 0.0f;
    //位图的宽高
    private int width, height;
    //缩放比例
    private float scale = 1.0f;
    //判断缩放还是旋转
    private boolean isScale = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置Matrix;
        matrix.reset();
        if (!isScale) {
            //旋转Matrix
            matrix.setSkew(sx, 0);
        } else {
            //缩放Matrix
            matrix.setSkew(scale, scale);

        }
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        canvas.drawBitmap(bitmap2, matrix, null);
    }

    public void setMatrixLeft(){
        isScale = false;
        sx += 0.1;
        postInvalidate();
    }
    public void setMatrixRight(){
        isScale = false;
        sx -= 0.1;
        postInvalidate();
    }
    public void setMatrixBig(){
        isScale = true;
        if (scale < 2.0) {
            scale += 0.1;

        }
        postInvalidate();
    }
    public void setMatrixSmall(){
        isScale = true;
        if (scale > 0.5) {
            scale -= 0.1;
        }
        postInvalidate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_A://向左倾斜
                isScale = false;
                sx += 0.1;
                postInvalidate();
                break;
            case KeyEvent.KEYCODE_D://向右倾斜
                isScale = false;
                sx -= 0.1;
                postInvalidate();
                break;
            case KeyEvent.KEYCODE_W://放大
                isScale = true;
                if (scale < 2.0) {
                    scale += 0.1;

                }
                postInvalidate();
                break;
            case KeyEvent.KEYCODE_S://缩小
                isScale = true;
                if (scale > 0.5) {
                    scale -= 0.1;
                }
                postInvalidate();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
}
