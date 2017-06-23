package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.function.IconAty;

/**
 * 资源文件下的所有文件目录以及 所对应的 对象名称；
 * Created by Administrator on 2017-6-20.
 */
public class ResFileDetailsFrag extends BaseFragment {

    @BindView(R.id.src_file_details_tv)
    TextView tv;
    @BindView(R.id.src_file_details_iv_0)
    ImageView iv0;
    @BindView(R.id.src_file_details_iv_1)
    ImageView iv1;

    @Override
    public int createViewId() {
        return R.layout.frag_src_file_details;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        iv0.setImageResource(R.drawable.src_fild_details_0);
        iv1.setImageResource(R.drawable.src_fild_details_1);
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);

        tv.setText("资源文件下(res\\) 包含的所有文件夹，文件夹下 资源文件所对应的 资源类");
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.src_file_details_iv_0:
                IconAty.showIconAty(getActivity(),R.drawable.src_fild_details_0);
                break;
            case R.id.src_file_details_iv_1:
                IconAty.showIconAty(getActivity(),R.drawable.src_fild_details_1);
                break;
        }
    }


}
