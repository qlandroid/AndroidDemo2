package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017-6-16.
 */
public class PathView extends View {
    public PathView(Context context) {
        super(context);
        init( context);
    }

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init( context);
    }

    public PathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( context);
    }

    float phase;
    PathEffect[] effects = new PathEffect[7];
    int[] colors;
    private Paint mPaint;
    Path path;

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);

        //create and init Path
        path = new Path();
        path.moveTo(0,0);
        for (int i = 0; i < 41; i++) {
            //生成40个点，随机生成他们的Y 坐标并将他们练成一条PATH
            path.lineTo(i*20,(float)Math.random() * 60);
        }
        colors = new int[]{Color.BLUE,Color.BLACK,Color.CYAN,Color.GREEN,Color.MAGENTA,Color.RED,Color.YELLOW};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        effects[0] = null;
        //使用CornerPathEffect 路径效果
        effects[1] = new CornerPathEffect(10);
        //初始化DiscretePatheffect
        effects[2] = new DiscretePathEffect(3.0f,5.0f);
        effects[3] = new DashPathEffect(new float[]{20,10,5,10},phase);

        Path p = new Path();
        p.addRect(0,0,8,8,Path.Direction.CCW);
        effects[4] = new PathDashPathEffect(p,12,phase,PathDashPathEffect.Style.ROTATE);

        effects[5] = new ComposePathEffect(effects[2],effects[4]);
        effects[6 ] = new SumPathEffect(effects[4],effects[3]);

        canvas.translate(8,8);

        for (int i = 0; i < effects.length; i++) {
            mPaint.setPathEffect(effects[i]);
            mPaint.setColor(colors[i]);
            canvas.drawPath(path,mPaint);
            canvas.translate(0,60);
        }
        //改变Phase值形成动画效果
        phase += 1;
        invalidate();


    }
}
