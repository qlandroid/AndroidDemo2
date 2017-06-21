package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.utils.AssetReadUtils;

/**
 * Created by Administrator on 2017-6-21.
 */
public class CanvasMeshFrag extends BaseFragment {

    @BindView(R.id.canvas_mesh_tv)
    TextView tv;
    @BindView(R.id.canvas_mesh_iv)
    ImageView iv;


    private String content;

    @Override
    public int createViewId() {
        return R.layout.frag_canvas_mesh;
    }

    @Override
    public void initData() {
        super.initData();
        content = AssetReadUtils.readFile(getContext(), "Canvas_drawBitmapMesh.txt");
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        tv.setText(content);
        iv.setImageResource(R.drawable.canvas_mesh);


    }
}
