package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017-6-14.
 */
public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private Paint mPaint;


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
    }

    private Paint.Style mPaintStyle = Paint.Style.STROKE;

    public void setPaintStyle(Paint.Style style) {
        mPaintStyle = style;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景颜色
        canvas.drawColor(Color.WHITE);

        Paint paint = new Paint();
        paint.setAntiAlias(true);//区锯齿
        paint.setColor(Color.BLUE);
        if (isAddShader){
            Shader sh  = new LinearGradient(0,0,40,60,new int[]{Color.GRAY,Color.YELLOW,Color.BLUE,Color.MAGENTA},null, Shader.TileMode.REPEAT);
            paint.setShader(sh);
        }

        paint.setStyle(mPaintStyle);
        paint.setStrokeWidth(4);
        int viewWidth = this.getWidth();
        //绘制圆形
        canvas.drawCircle(viewWidth / 10 + 10, viewWidth / 10 + 10, viewWidth / 10, paint);
        //绘制正方形
        canvas.drawRect(10, viewWidth / 5 + 20, viewWidth / 5 + 10, viewWidth * 2 / 5 + 20, paint);
        //绘制圆角矩形
        RectF rel = new RectF(10, viewWidth / 2 + 40, 10 + viewWidth / 5, viewWidth * 3 / 5 + 50);
        canvas.drawRoundRect(rel, 15, 15, paint);

        //绘制椭圆形
        RectF rell = new RectF(10, viewWidth * 3 / 5 + 50, 10 + viewWidth / 5, viewWidth * 7 / 10 + 50);
        canvas.drawOval(rell, paint);

        //顶一个Path对象封闭成一个三角形
        Path path1 = new Path();
        path1.moveTo(10, viewWidth * 9 / 10 + 60);
        path1.lineTo(viewWidth / 5 + 10, viewWidth * 9 / 10 + 60);
        path1.lineTo(viewWidth / 10 + 10, viewWidth * 7 / 10 + 60);
        path1.close();

        //根据path绘制三角形
        canvas.drawPath(path1, paint);

        //绘制五角星
        Path path2 = new Path();
        path2.moveTo(10 + viewWidth / 15, viewWidth * 9 / 10 + 70);
        path2.moveTo(10 + viewWidth / 2, viewWidth * 9 / 10 + 70);
        path2.moveTo(10 + viewWidth / 10, viewWidth + 70);
        path2.moveTo(10 + viewWidth / 70, viewWidth * 11 / 10 + 70);
        path2.close();
        canvas.drawPath(path2, paint);
    }

    boolean isAddShader ;

    public void setAddShader(boolean isAddShader){
        this.isAddShader = isAddShader;
        invalidate();
    }

}
