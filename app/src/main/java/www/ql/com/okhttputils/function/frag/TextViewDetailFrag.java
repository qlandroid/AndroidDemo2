package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * ‘
 * TextView的所有属性
 * Created by Administrator on 2017-6-20.
 */
public class TextViewDetailFrag extends BaseFragment {

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
        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.drawable.text_view0);
        list.add(R.drawable.text_view1);
        list.add(R.drawable.text_view2);
        list.add(R.drawable.text_view3);
        list.add(R.drawable.text_view4);
        list.add(R.drawable.text_view5);
        list.add(R.drawable.text_view6);
        ListViewDetailsFrag listViewDetailsFrag = ListViewDetailsFrag.newInstance(list,"TextView的常用属性");
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_fl_content,listViewDetailsFrag).commit();

    }


}
