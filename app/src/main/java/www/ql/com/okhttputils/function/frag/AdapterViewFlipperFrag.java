package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.AdapterViewFlipper;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.CommonAdapter;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-16.
 */
public class AdapterViewFlipperFrag extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.adapter_view_flipper)
    AdapterViewFlipper avf;
    @BindView(R.id.frag_adapter_view_flipper_btn_auto)
    Button btnAuto;
    @BindView(R.id.frag_adapter_view_flipper_btn_last)
    Button btnLast;
    @BindView(R.id.frag_adapter_view_flipper_btn_next)
    Button btnNext;


    private CommonAdapter<Integer> adapter;
    private List<Integer> list = new ArrayList<>();

    @Override
    public int createViewId() {
        return R.layout.frag_adapter_view_filpper;
    }

    @Override
    public void initData() {
        super.initData();
        int[] a = {R.drawable.jirou,R.drawable.jidan,R.drawable.pig,R.drawable.yarou};
        for (int aId : a){
            list.add(aId);
        }
        adapter = new CommonAdapter<Integer>(getContext(),list,R.layout.item_image) {
            @Override
            public void setItemContent(ViewHolder holder, Integer integer) {
                holder.setImageResource(R.id.item_iv,integer);
            }
        };
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnAuto.setOnClickListener(this);
        btnLast.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        avf.setAdapter(adapter);
        //设置自动播放的时间为 2描
        avf.setFlipInterval(2_000);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.frag_adapter_view_flipper_btn_auto:
                avf.startFlipping();
                break;
            case R.id.frag_adapter_view_flipper_btn_last:
                avf.showPrevious();
                avf.stopFlipping();
                break;
            case R.id.frag_adapter_view_flipper_btn_next:
                avf.showNext();
                avf.stopFlipping();
                break;
        }
    }
}
