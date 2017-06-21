package www.ql.com.okhttputils;

import android.content.Context;
import android.os.Bundle;
import android.ql.bindview.BindViewUtils;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ddddd
 * Created by Administrator on 2017-6-16.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (-1 != createViewId()) {
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return li.inflate(createViewId(), container, false);
        }
        View view = createView();
        return view;
    }

    public int createViewId() {
        return -1;
    }

    public View createView() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindViewUtils.find(this);
        initData();
        initWidget(view);
    }

    public void initWidget(View view) {
    }

    public void initData() {
    }

    @Override
    public void onClick(View v) {
        clickWidget(v);
    }

    public void clickWidget(View v) {
    }
}
