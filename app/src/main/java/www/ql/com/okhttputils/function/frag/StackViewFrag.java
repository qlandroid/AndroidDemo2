package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.Button;
import android.widget.StackView;

import java.util.ArrayList;
import java.util.List;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.CommonAdapter;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-16.
 */
public class StackViewFrag extends BaseFragment {

    @BindView(R.id.frag_stack_view)
    StackView sv;
    @BindView(R.id.frag_stack_view_btn_last)
    Button btnLast;
    @BindView(R.id.frag_stack_view_btn_next)
    Button btnNext;

    private CommonAdapter<Integer> adapter;
    private List<Integer> list = new ArrayList<>();

    @Override
    public int createViewId() {
        return R.layout.frag_stack_view;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnLast.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        sv.setAdapter(adapter);
    }

    @Override
    public void initData() {
        super.initData();
//        int[] ids = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5};
        int[] ids = {R.drawable.icon1,R.drawable.icon1,R.drawable.icon1,R.drawable.icon1,R.drawable.icon1,R.drawable.icon1,R.drawable.icon1,R.drawable.icon1};
        for(int id : ids){
            list.add(id);
        }
        adapter = new CommonAdapter<Integer>(getContext(),list,R.layout.item_image) {
            @Override
            public void setItemContent(ViewHolder holder, Integer integer) {
                holder.setImageResource(R.id.item_iv,integer);
            }
        };
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.frag_stack_view_btn_next:
                sv.showNext();
                break;
            case R.id.frag_stack_view_btn_last:
                sv.showPrevious();
                break;
        }
    }
}
