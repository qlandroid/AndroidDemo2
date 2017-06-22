package www.ql.com.okhttputils.function.frag;

import android.os.Bundle;
import android.ql.bindview.BindView;
import android.view.View;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.utils.AssetReadUtils;

/**
 * Created by Administrator on 2017-6-22.
 */
public class AFrag extends BaseFragment {

    public static final String B_TITLE = "B_TITLE";
    public static final String B_FILE_NAME = "B_FILE_NAME";;

    public static AFrag newInstance(String title,String fileName) {

        Bundle args = new Bundle();
        args.putString(B_TITLE,title);
        args.putString(B_FILE_NAME,fileName);
        AFrag fragment = new AFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.frag_a_tv_title)
    TextView tvTitle;
    @BindView(R.id.frag_a_tv_content)
    TextView tvContent;

    private String title,fileName;

    @Override
    public int createViewId() {
        return R.layout.frag_a;
    }

    @Override
    public void initData() {
        super.initData();
        Bundle b = getArguments();
        title = b.getString(B_TITLE);
        fileName = b.getString(B_FILE_NAME);
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        tvTitle.setText(title);
        tvContent.setText(AssetReadUtils.readFile(getContext(),fileName));
    }
}
