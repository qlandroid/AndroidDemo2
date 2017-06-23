package www.ql.com.okhttputils.function.frag;

import android.os.Bundle;
import android.ql.bindview.BindView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-19.
 */
public class ListViewDetailsFrag extends BaseFragment {

    private static final String INTENT_LIST = "INTENT_LIST";
    private static final String INTENT_TITLE = "INTENT_TITLE";

    @BindView(R.id.list_view_vp)
    ViewPager vp;
    @BindView(R.id.list_view_tv_title)
    TextView tvTitle;

    private List<Integer> list = new ArrayList<>();
    private String title;

    public static ListViewDetailsFrag newInstance(ArrayList<Integer> list, String title) {

        Bundle args = new Bundle();
        args.putIntegerArrayList(INTENT_LIST, list);
        args.putString(INTENT_TITLE,title);
        ListViewDetailsFrag fragment = new ListViewDetailsFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int createViewId() {
        return R.layout.frag_list_view_deatils;
    }


    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle == null) {
            list.add(R.drawable.list_view_deatils_0);
            list.add(R.drawable.list_view_details_1);
        } else {
            list = bundle.getIntegerArrayList(INTENT_LIST);
            title = bundle.getString(INTENT_TITLE);
        }
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);

        tvTitle.setText(title);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter();
        vp.setAdapter(adapter);
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView pv = new PhotoView(getContext());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            pv.setLayoutParams(lp);

            pv.setImageResource(list.get(position));
            container.addView(pv);
            return pv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
