package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

import www.ql.com.okhttputils.R;

/**
 * Created by mrqiu on 2017/6/21.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder holder;
    private UpdateViewThread updateViewThread;
    private boolean hasSurface;
    private Bitmap back;
    private Bitmap[] fishs;
    private int fishIndex = 0 ;//定义变量记录绘制第几张图片
    //定义两个变量，记录初始位置
    private float fishX= 778;
    private float fishY = 500;
    private float fishSpeed = 6 ;//速度
    //定义角度
    private int fishAngle = new Random().nextInt(600);
    Matrix matrix = new Matrix();

    public MySurfaceView(Context context) {
        super(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取该SurfaceView对应的SurfaceHolder，并将该类的实例作为其Callback
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;
        back  = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
        fishs = new Bitmap[10];
        //初始化图片
        for (int i = 0; i < 10; i++) {
            fishs[i] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        }

    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取该SurfaceView对应的SurfaceHolder，并将该类的实例作为其Callback
        holder = getHolder();
        holder.addCallback(this);
        hasSurface = false;
        back  = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher);
        fishs = new Bitmap[10];
        //初始化图片
        for (int i = 0; i < 10; i++) {
            fishs[i] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

        }
    }

    public void resume(){
        //床架和启动图像更新线程
        if(updateViewThread == null){
            updateViewThread = new UpdateViewThread();
            if (hasSurface == true)
                updateViewThread.start();
        }
    }

    public void pause(){
        //停止图像更新线程
        if (updateViewThread != null){
            updateViewThread.requestExitAndWart();
            updateViewThread = null;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //当SurfaceView 被创建时回调的方法
        hasSurface = true;
        resume();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (updateViewThread!=null){
            updateViewThread.onWindowREsize(width,height);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
        pause();
    }

    class UpdateViewThread extends Thread{
        //定义一个记录图像是否跟新完成的旗帜
        private boolean done;
        UpdateViewThread(){
            super();
            done = false;
        }

        @Override
        public void run() {
            SurfaceHolder surfaceHolder = holder;
            //重复绘制循环，知道线程停止；
            while(!done){
                //锁定surfaceView 并返回要绘图的Canvas;
                Canvas canvas = surfaceHolder.lockCanvas();
                //绘制背景图片
                if (back == null) return;
                canvas.drawBitmap(back,0,0,null);
                //如果鱼 出 屏幕之外，重新初始化位置
                if(fishX < 0){
                    fishX = 778;
                    fishY = 500;
                    fishAngle = new Random().nextInt(60);
                }

                if (fishY < 0 ){
                    fishX = 778;
                    fishY = 500;
                    fishAngle = new Random().nextInt(60);
                }

                //使用Matrix来控制旋转角度和位置
                matrix.reset();
                matrix.setRotate(fishAngle);
               matrix.postTranslate(fishX=-2,fishY =+2);
                canvas.drawBitmap(fishs[fishIndex++ % fishs.length],matrix,null);
                //解锁Canvas，并渲染图像
                surfaceHolder.unlockCanvasAndPost(canvas);
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        public void requestExitAndWart(){
            //把这个线程标记为完成,并合并到主程序线程中
            done = true;
            try{
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void onWindowREsize(int w,int h){
            //处理SurfaceView的大小改变事件
        }
    }
}
