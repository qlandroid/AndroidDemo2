package www.ql.com.okhttputils.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017-6-13.
 */
public class MyTestView extends View {
    public MyTestView(Context context) {
        super(context);
    }

    public MyTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "MyTestView";
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: viewTop  = " + getTop() );
        Log.i(TAG, "onTouchEvent:  y   = " + event.getY());
        Log.i(TAG, "onTouchEvent:  rawY =  " + event.getRawY());
        return super.onTouchEvent(event);
    }
}
