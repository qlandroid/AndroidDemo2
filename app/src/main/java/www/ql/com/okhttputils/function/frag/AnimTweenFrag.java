package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.anim.MyAnimation;
import www.ql.com.okhttputils.utils.AssetReadUtils;

/**
 * Created by Administrator on 2017-6-21.
 */
public class AnimTweenFrag extends BaseFragment {

    @BindView(R.id.anim_tween_iv0)
    ImageView iv0;
    @BindView(R.id.anim_tween_tv)
    TextView tv;
    @BindView(R.id.anim_tween_btn_start)
    Button btnStart;
    @BindView(R.id.anim_tween_tv_anim)
    TextView tvAnim;

    private Animation anim;
    private MyAnimation myAnim;


    @Override
    public int createViewId() {
        return R.layout.frag_anim_tween;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnStart.setOnClickListener(this);
        String content = AssetReadUtils.readFile(getContext(), "anim_tween.txt");
        tv.setText(content);

        anim = AnimationUtils.loadAnimation(getContext(), R.anim.anim_a);
        iv0.setAnimation(anim);
        //设置动画结束后保留状态
        //anim.setFillAfter(true);


        myAnim = new MyAnimation(500, 150, 5000);


    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()) {
            case R.id.anim_tween_btn_start:
                iv0.startAnimation(anim);
                tvAnim.startAnimation(myAnim);
                break;
        }
    }
}
