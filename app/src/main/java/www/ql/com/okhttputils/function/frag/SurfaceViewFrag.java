package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.utils.AssetReadUtils;
import www.ql.com.okhttputils.widget.MySurfaceView;

/**
 * Created by mrqiu on 2017/6/21.
 */

public class SurfaceViewFrag extends BaseFragment {

    @BindView(R.id.surface_view)
    MySurfaceView mySurfaceView;
    @BindView(R.id.surface_view_tv)
    TextView tv;

    private String content;

    @Override
    public int createViewId() {
        return R.layout.frag_surface_view;
    }

    @Override
    public void initData() {
        super.initData();
        content = AssetReadUtils.readFile(getContext(),"surface_view_details");
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        tv.setText(content);
    }
}
