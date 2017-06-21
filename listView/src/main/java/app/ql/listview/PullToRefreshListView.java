package app.ql.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-6-14.
 */
public class PullToRefreshListView extends ListView implements AbsListView.OnScrollListener{
    public PullToRefreshListView(Context context) {
        super(context);
        init(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        TextView tv = new TextView(context);
        tv.setText("我出来了");
        int headContentHeight = tv.getMeasuredHeight();
        int headContentWidth = tv.getMeasuredWidth();

        tv.setPadding(0, -1 * headContentHeight, 0, 0);
        tv.invalidate();

        Log.v("size", "width:" + headContentWidth + " height:"
                + headContentHeight);

        addHeaderView(tv, null, false);
        addHeaderView(tv);

        setOnScrollListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
