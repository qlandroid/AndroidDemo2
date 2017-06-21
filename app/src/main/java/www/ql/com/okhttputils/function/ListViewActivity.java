package www.ql.com.okhttputils.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import app.ql.listview.ListViewInterceptor;
import www.ql.com.okhttputils.CommonAdapter;
import www.ql.com.okhttputils.R;

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListViewActivity";

    private CommonAdapter<ListViewItemBean> mAdapter;
    private ArrayList<ListViewItemBean> mList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        for (int i = 0; i < 30; i++) {
            mList.add(new ListViewItemBean(R.mipmap.icon, "item -->" + i));
        }

        mAdapter = new CommonAdapter<ListViewItemBean>(this, mList, R.layout.item_list_interceptor) {
            @Override
            public void setItemContent(ViewHolder holder, ListViewItemBean listViewItemBean) {
                holder.setImageResource(R.id.item_list_interceptor_iv, listViewItemBean.getIconId());
                holder.setText(R.id.item_list_interceptor_tv, listViewItemBean.getContent());
            }
        };

        ListViewInterceptor lvi = (ListViewInterceptor) findViewById(R.id.lvi);
        lvi.setOnMoveListener(new ListViewInterceptor.OnMoveListener() {
            @Override
            public void onCancelMove() {
                Log.i(TAG, "onCancelMove: ");
            }

            @Override
            public void onEndMove(int from, int to) {
                Log.i(TAG, "onEndMove: from = " + from + ", to = " + to);
            }
        });
        lvi.setMoveViewIdArea(R.id.item_list_interceptor_iv);
        lvi.setAdapter(mAdapter);
    }

    public static class ListViewItemBean {
        private int iconId;
        private String content;

        public ListViewItemBean(int iconId, String content) {
            this.iconId = iconId;
            this.content = content;
        }

        public int getIconId() {
            return iconId;
        }

        public void setIconId(int iconId) {
            this.iconId = iconId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
