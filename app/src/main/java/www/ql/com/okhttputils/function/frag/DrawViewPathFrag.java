package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-16.
 */
public class DrawViewPathFrag extends BaseFragment {

    @BindView(R.id.path_draw_tv)
    TextView tvContent;
    @Override
    public int createViewId() {
        return R.layout.frag_path_draw;
    }

    @Override
    public void initWidget(View view) {
        StringBuffer sb = new StringBuffer();
        sb.append("设置Paint的属性 setPathEffect \r\n \t设置的类型为 PathEffect 详情请看自定义ViewPathView");
        tvContent.setText(sb.toString());
    }
}
