package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-21.
 */
public class MeshView extends View {
    private int drawableId;
    private Bitmap bitmap;
    //定义两个常量，这两个常量指定该图片横向，纵向上都被划分为20格
    private final int WIDTH = 20;
    private final int HEIGHT = 20;
    //记录该图片上包含441个定点
    private final int COUNT = (WIDTH + 1) * (HEIGHT + 1) * 2;
    //定义一个数组，保存bitmap上的 21*21个点的坐标
    private final float[] verts = new float[COUNT * 2];
    //定义一个数组，记录bitmap 上的21*21个点经过扭曲后的坐标
    private final float[] orig = new float[COUNT * 2];

    public MeshView(Context context) {
        super(context);
    }

    public MeshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        drawableId = R.drawable.icon3;
        bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        //获取图片宽度高度
        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        int index = 0;
        for (int i = 0; i <= HEIGHT; i++) {
            float fy = bitmapHeight * i / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = bitmapWidth * x / WIDTH;
                //初始化 orig verts数组
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                index++;
            }

        }
        setBackgroundColor(Color.WHITE);
    }

    public MeshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        drawableId = R.drawable.icon3;
        bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        //获取图片宽度高度
        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();
        int index = 0;
        for (int i = 0; i <= HEIGHT; i++) {
            float fy = bitmapHeight * i / HEIGHT;
            for (int x = 0; x <= WIDTH; x++) {
                float fx = bitmapWidth * x / WIDTH;
                //初始化 orig verts数组
                orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
                orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
                index++;
            }

        }
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //对bitmap 按verts数组进行扭曲
        //从第一个点(由5个参数0 控制)开始扭曲
        canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);

    }

    //工具方法，用于根据触摸时间的位置计算verts数组里各个元素的值
    private void warp(float cx, float cy) {
        for (int i = 0; i < COUNT * 2 - 1; i++) {
            float dx = cx - orig[i + 0];
            float dy = cy - orig[i + 1];
            float dd = dx * dx + dy * dy;
            //计算每个坐标与当前(cx,cy)之间的距离
            float d = (float) Math.sqrt(dd);
            //计算扭曲度，距离当前点（cx,cy）越远，扭曲度越小
            float pull = 80000 / ((float) (dd * d));
            //对verts 数组(保存bitmap 上21 * 21 个点经过扭曲后的坐标)重新复制
            if (pull >= 1) {
                verts[i + 0] = cx;
                verts[i + 1] = cy;
            } else {
                //控制个顶点向触摸时间发生偏移
                verts[i + 0] = orig[i + 0] + dx * pull;
                verts[i + 1] = orig[i + 1] + dy * pull;
            }
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        warp(event.getX(), event.getY());
        return true;
    }
}
