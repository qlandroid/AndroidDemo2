package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoView;
import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * ‘
 * TextView的所有属性
 * Created by Administrator on 2017-6-20.
 */
public class TextViewDetaileFrag extends BaseFragment {

    @BindView(R.id.text_view_details_iv0)
    ImageView iv0;
    @BindView(R.id.text_view_details_iv1)
    ImageView iv1;
    @BindView(R.id.text_view_details_iv2)
    ImageView iv2;
    @BindView(R.id.text_view_details_iv3)
    ImageView iv3;
    @BindView(R.id.text_view_details_iv4)
    ImageView iv4;
    @BindView(R.id.text_view_details_iv5)
    ImageView iv5;
    @BindView(R.id.text_view_details_iv6)
    ImageView iv6;
    @BindView(R.id.text_view_details_photoV)
    PhotoView photoView;

    @Override
    public int createViewId() {
        return R.layout.frag_text_view_details;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);

        photoView.setImageResource(R.drawable.text_view0);

        iv0.setImageResource(R.drawable.text_view0);
        iv1.setImageResource(R.drawable.text_view1);
        iv2.setImageResource(R.drawable.text_view2);
        iv3.setImageResource(R.drawable.text_view3);
        iv4.setImageResource(R.drawable.text_view4);
        iv5.setImageResource(R.drawable.text_view5);
        iv6.setImageResource(R.drawable.text_view6);

        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
        iv3.setOnClickListener(this);
        iv4.setOnClickListener(this);
        iv5.setOnClickListener(this);
        iv6.setOnClickListener(this);
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()) {
            case R.id.text_view_details_iv0:
                photoView.setImageResource(R.drawable.text_view0);
                break;
            case R.id.text_view_details_iv1:
                photoView.setImageResource(R.drawable.text_view1);

                break;
            case R.id.text_view_details_iv2:
                photoView.setImageResource(R.drawable.text_view2);

                break;
            case R.id.text_view_details_iv3:
                photoView.setImageResource(R.drawable.text_view3);

                break;
            case R.id.text_view_details_iv4:
                photoView.setImageResource(R.drawable.text_view4);

                break;
            case R.id.text_view_details_iv5:
                photoView.setImageResource(R.drawable.text_view5);
                break;
            case R.id.text_view_details_iv6:
                photoView.setImageResource(R.drawable.text_view6);
                break;

        }
    }
}
