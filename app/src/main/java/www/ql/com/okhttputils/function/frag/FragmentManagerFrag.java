package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-19.
 */
public class FragmentManagerFrag extends BaseFragment {

    @BindView(R.id.fragment_manager_tv)
    TextView tvContent;

    private StringBuffer content = new StringBuffer();

    @Override
    public int createViewId() {
        return R.layout.frag_fragment_manger;
    }

    @Override
    public void initData() {
        super.initData();

        try {
            InputStream io = getContext().getAssets().open("FragmentManager.txt");
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = io.read(buf)) != -1){
                content.append(new String(buf,0,len,"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        tvContent.setText(content.toString());
    }
}
