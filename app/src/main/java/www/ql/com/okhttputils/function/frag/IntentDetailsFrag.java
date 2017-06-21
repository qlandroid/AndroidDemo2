package www.ql.com.okhttputils.function.frag;

import android.content.Intent;
import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.function.IconAty;

/**
 * Created by Administrator on 2017-6-19.
 */
public class IntentDetailsFrag extends BaseFragment {

    @BindView(R.id.intent_details_iv_0)
    ImageView iv0;
    @BindView(R.id.intent_details_iv_1)
    ImageView iv1;
    @BindView(R.id.intent_details_tv_content)
    TextView tvContent;

    @Override
    public int createViewId() {
        return R.layout.frag_intent_details;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        StringBuffer content = new StringBuffer();
        try {
            InputStream io = getContext().getAssets().open("intent_component.txt");
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = io.read(buf)) != -1){
                content.append(new String(buf,0,len,"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        iv0.setImageResource(R.drawable.intent_action);
        iv1.setImageResource(R.drawable.intent_category);
        iv0.setOnClickListener(this);
        iv1.setOnClickListener(this);
        tvContent.setText(content);
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.intent_details_iv_0:
                toShowIconAty(R.drawable.intent_action);
                break;
            case R.id.intent_details_iv_1:
                toShowIconAty(R.drawable.intent_category);
                break;
        }
    }

    private void toShowIconAty(int iconId){
        Intent intent = new Intent(getContext(), IconAty.class);
        intent.putExtra(IconAty.INTENT_ICON_ID,iconId);
        startActivity(intent);
    }
}
