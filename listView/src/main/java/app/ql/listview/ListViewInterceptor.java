package app.ql.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Administrator on 2017-6-13.
 */
public class ListViewInterceptor extends ListView {
    private static final String TAG = "ListViewInterceptor";

    private boolean isOpenMove = true;
    private boolean isStartMove = false;
    private int mMinMove;

    public static final int bgColor = 0xffffffff;

    private WindowManager.LayoutParams mWindwoParams;

    private RectF mRectF = new RectF();
    private ImageView ivMoveView;

    private int mClickViewId;

    private int mPaddingLeft = 100;
    private int mOperatePosition;
    private int mHandRelativeItemViewHeight;//手指出点相对itemView的高度
    private int mListViewTopRelativeOfWindowHeight;//listview顶部相对于window窗口的顶部的距离
    private boolean isOpenEachMove;//是否开启移动时 每越过一个就 移动位置；

    private AutoScrollHelper mAutoScrollHelper;

    private OnMoveListener mOnMoveListener;

    public ListViewInterceptor(Context context) {
        super(context);
        init(context, null, 0);

    }

    public ListViewInterceptor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ListViewInterceptor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mMinMove = ViewConfiguration.get(context).getScaledTouchSlop();
        mWindwoParams = initWindowLayout();

        mAutoScrollHelper = new ListViewAutoScrollHelper(this){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isOpenMove && isStartMove) {
                    float x = event.getX();
                    float y = event.getY();
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            int endMoveToPosition = pointToPosition((int) x, (int) y);
                            if (endMoveToPosition == AdapterView.INVALID_POSITION) {
                                cancelMove();
                            } else {
                                successMove(mOperatePosition, endMoveToPosition);
                            }
                            moveEnd();
                            break;
                        case MotionEvent.ACTION_MOVE:
                            int moveToPosition = pointToPosition((int) x, (int) y);
                            if (isOpenEachMove) {
                                //开启了没移动一项都会设置图片占位
                                onEachMove(mOperatePosition, moveToPosition);
                            }
                            //需要设置显示窗口中的图片纵向的高度，随手移动而移动
                            setWindowLayout((int) y);
                            moveView();
                            View topItem = getChildAt(0);
                            int footVisiblePosition = getLastVisiblePosition() - getFirstVisiblePosition();
                            View footItem = getChildAt(footVisiblePosition);
                            int footItemHeight = footItem.getHeight();
                            int footItemTop = footItem.getTop();
                            int topItemViewHeight = topItem.getHeight();
                            if (y < (topItemViewHeight / 2) ){
                            }else if( y > footItemHeight / 3+ footItemTop){
                            }else {
                                return true;
                            }
                    }
                }
                return super.onTouch(v, event);
            }
        };
        setOnTouchListener(mAutoScrollHelper);
        mAutoScrollHelper.setEnabled(true);

    }

    private WindowManager.LayoutParams initWindowLayout() {


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.windowAnimations = 0;
        return lp;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isStartMove) {
                    return false;
                }
                if (isOpenMove) {
                    float x = ev.getX();
                    float y = ev.getY();

                    int itemClickPosition = pointToPosition((int) x, (int) y);

                    mOperatePosition = itemClickPosition;//记录当前操作项

                    if (itemClickPosition == AdapterView.INVALID_POSITION) {
                        //点击的 位置不在Item身上
                        break;
                    }
                    int lookIndex = itemClickPosition - getFirstVisiblePosition();

                    View itemMoveView = getChildAt(lookIndex);
                    View moveAreaView = itemMoveView.findViewById(mClickViewId);
                    mRectF.top = moveAreaView.getTop();
                    mRectF.right = moveAreaView.getRight();
                    mRectF.bottom = moveAreaView.getBottom();
                    mRectF.left = moveAreaView.getLeft();

                    if ((mRectF.left < x) && (mRectF.right > x)) {
                        //在区域范围内可进行的操作
                        Bitmap bg = getItemBitmap(itemMoveView);
                        ivMoveView = new ImageView(getContext());
                        ivMoveView.setImageBitmap(bg);
                        ivMoveView.setBackgroundColor(bgColor);
                        //进行设置显示到桌面上的参数
                        mHandRelativeItemViewHeight = (int) y - itemMoveView.getTop();
                        mListViewTopRelativeOfWindowHeight = (int) ev.getRawY() - (int) y;
                        setWindowLayout((int) y);
                        addWindow(ivMoveView, mWindwoParams);
                        isStartMove = true;
                        return false;
                    }
                }
        }

        return super.onInterceptTouchEvent(ev);
    }

    private void successMove(int fromPosition, int endMoveToPosition) {
        mOnMoveListener.onEndMove(fromPosition, endMoveToPosition);
    }

    /**
     * 移动结束
     */
    private void moveEnd() {
        removeViewOnWindow();
        isStartMove = false;
    }

    private void removeViewOnWindow() {
        WindowManager mWindowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        try {
            mWindowManager.removeView(ivMoveView);
        } catch (Exception e) {
            Log.i(TAG, "removeViewOnWindow: -->error");
        }

    }


    /**
     * 取消移动
     */
    private void cancelMove() {
        mOnMoveListener.onCancelMove();
    }


    private Bitmap getItemBitmap(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        return view.getDrawingCache();
    }

    /**
     * 将图片在屏幕上显示
     *
     * @param iv 所要显示的图片
     * @param lp 位置参数
     */
    private void addWindow(View iv, WindowManager.LayoutParams lp) {
        WindowManager mWindowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.addView(iv, lp);
    }

    private void setWindowLayout(int y) {
        mWindwoParams.gravity = Gravity.TOP;
        mWindwoParams.y = y - mHandRelativeItemViewHeight + mListViewTopRelativeOfWindowHeight;
        mWindwoParams.x = mPaddingLeft;
    }


    private void moveView() {
        WindowManager mWindowManager = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        try {
            mWindowManager.updateViewLayout(ivMoveView, mWindwoParams);
        } catch (Exception e) {
            Log.i(TAG, "moveView: 出现了一点点问题");
        }

    }

    public void setMoveOfPaddingLeft(int left) {
        this.mPaddingLeft = left;
    }

    /**
     * 每次移动
     *
     * @param from
     * @param to
     */
    private void onEachMove(int from, int to) {

    }

    public void setMoveViewIdArea(int idArea) {
        mClickViewId = idArea;
    }


    public void setOnMoveListener(OnMoveListener l) {
        this.mOnMoveListener = l;
    }

    public interface OnMoveListener {

        void onCancelMove();

        void onEndMove(int from, int to);
    }
}
