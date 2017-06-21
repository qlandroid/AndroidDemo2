package www.ql.com.okhttputils.function.frag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import www.ql.com.okhttputils.BaseFragment;

/**
 * Created by Administrator on 2017-6-20.
 */
public class BallFrag extends BaseFragment {

    private int tableWidth;
    private int tableHeight;
    private int racketY;//球拍的垂直位置；

    private final int RACKET_HEIGHT = 30;
    private final int RACKET_WIDTH = 190;
    //小球大小
    private final int BALL_SIZE = 16;
    //小球纵向的运行速度
    private int ySpeed = 15;
    Random rand = new Random();
    //返回一个-0.5~0.5的比率，用于控制小球的运行方向
    private double xyRate = rand.nextDouble() - 0.5;
    //小球横向的运行速度
    private int xSpeed = (int) (ySpeed * xyRate * 2);
    //ballX和ballY代表小球的坐标
    private int ballX = rand.nextInt(200) + 20;
    private int ballY = rand.nextInt(10) + 20;
    //racketX 代表球拍的水平位置
    private int racketX = rand.nextInt(200);
    //游戏是否结束的旗标
    private boolean isLose = false;

    @Override
    public View createView() {
        gameView = new GameView(getContext());
        return gameView;
    }

    @Override
    public void initData() {
        super.initData();
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        //获取屏幕的宽高
        tableHeight = metrics.heightPixels;
        tableWidth = metrics.widthPixels;
        racketY = tableHeight - 100;
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x123) {
                    gameView.invalidate();
                }
            }
        };
        gameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                switch (event.getKeyCode()){
                    case KeyEvent.KEYCODE_A:
                        if (racketX > 0)  racketX -=10;
                        break;
                    case KeyEvent.KEYCODE_D:
                        if (racketX < tableWidth -RACKET_WIDTH) racketX += 10;
                        break;

                }
                gameView.invalidate();

                return true;
            }
        });
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //如果小球碰到左边边框
                if (ballX <=0 || ballX >= tableWidth -BALL_SIZE){
                    xSpeed = -xSpeed;
                }
                //如果小球高度超出球拍位置，且横向不在球拍范围之内，游戏结束
                if (ballY >= racketY - BALL_SIZE && (ballX < racketX || ballX > racketX + RACKET_WIDTH)){
                    timer.cancel();
                    //设置游戏是否结束的旗标为true;
                    isLose = true;
                }else if (ballY<=0 || (ballY >=racketY -BALL_SIZE && ballX > racketX && ballX <= racketX + RACKET_WIDTH)){
                    //如果小球位于球拍之内，且到达球拍位置，小球反弹
                    ySpeed = - ySpeed;
                }
                //小球坐标增加
                ballY += ySpeed;
                ballX += xSpeed;
                //发送消息通知系统重新绘制组件
                handler.sendEmptyMessage(0X123);
            }
        },0,100);
    }

    private GameView gameView;

    public class GameView extends View {

        Paint paint = new Paint();

        public GameView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setStyle(Paint.Style.FILL);
            //设置去锯齿
            paint.setAntiAlias(true);
            //如果游戏已经结束
            if (isLose) {
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏已经结束", tableWidth / 2 - 100, 200, paint);
            } else {
                paint.setColor(Color.rgb(255, 0, 0));
                canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
                paint.setColor(Color.rgb(80, 80, 200));
                canvas.drawRect(racketX, racketY, racketX + RACKET_WIDTH, racketY + RACKET_HEIGHT, paint);

            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            racketX  = (int) event.getX();
            invalidate();
            return true;
        }
    }
}
