package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-16.
 */
public class ViewSwitcherFrag extends BaseFragment {

    @BindView(R.id.frag_view_switcher)
    private ViewSwitcher vSwitcher;
    @BindView(R.id.frag_view_switcher_last)
    private Button btnLast;
    @BindView(R.id.frag_view_switcher_next)
    private Button btnNext;

    private Animation mInAnimation,mOutAnimation;

    private int index;
    int[] iconId ={R.drawable.icon2,R.drawable.icon3,R.drawable.icon4};

    @Override
    public int createViewId() {
        return R.layout.frag_view_switcher;
    }

    @Override
    public void initData() {
        super.initData();
        int UIWidth = getResources().getDisplayMetrics().widthPixels;


        mInAnimation = new TranslateAnimation(UIWidth,0,0,0);
        mInAnimation.setDuration(2000);

        mOutAnimation = new TranslateAnimation(0,-UIWidth,0,0);
        mOutAnimation.setDuration(2000);

    }


    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnLast.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        vSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv = new ImageView(getContext());
                iv.setImageResource(iconId[index % iconId.length]);
                return iv;
            }
        });
    }


    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.frag_view_switcher_last:
                index --;
                vSwitcher.setOutAnimation(mInAnimation);
                vSwitcher.setInAnimation(mOutAnimation);
                vSwitcher.showPrevious();
                break;
            case R.id.frag_view_switcher_next:
                index ++ ;
                vSwitcher.setOutAnimation(mOutAnimation);
                vSwitcher.setInAnimation(mInAnimation);
                vSwitcher.showNext();
                break;
        }
    }
}
