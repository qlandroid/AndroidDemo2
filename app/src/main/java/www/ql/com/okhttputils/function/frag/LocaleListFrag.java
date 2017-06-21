package www.ql.com.okhttputils.function.frag;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.CommonAdapter;

/**
 * Created by Administrator on 2017-6-20.
 */
public class LocaleListFrag extends BaseFragment implements TextWatcher {
    CommonAdapter<String> adapter;
    private ListView lv;

    @Override
    public View createView() {
        lv = new ListView(getContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lv.setLayoutParams(lp);
        LinearLayout ll  = new LinearLayout(getContext());
        FrameLayout.LayoutParams llLp = (FrameLayout.LayoutParams) ll.getLayoutParams();
        if (llLp == null){
            llLp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(llLp);
        EditText editText = new EditText(getContext());
        editText.addTextChangedListener(this);
        ll.addView(editText);
        ll.addView(lv);
        return ll;
    }

    List<String> list = new ArrayList<>();
    List<String> searchList = new ArrayList<>();
    @Override
    public void initData() {
        super.initData();
        Locale[] lo = Locale.getAvailableLocales();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < lo.length; i++) {
            Locale l = lo[i];

            String country = l.getDisplayCountry();
            sb.append(country).append(" = ");
            String countryCode = l.getCountry();

            sb.append(countryCode).append("\r\n");

            String language = l.getDisplayLanguage();
            sb.append(language).append(" = ");
            String languageCode = l.getLanguage();
            sb.append(languageCode);
            list.add(sb.toString());
            sb.delete(0,sb.length());
        }

    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        adapter= new CommonAdapter<String>(getContext(),null,android.R.layout.simple_list_item_1) {
            @Override
            public void setItemContent(ViewHolder holder, String s) {
                holder.setText(android.R.id.text1,s);
            }
        };
        lv.setAdapter(adapter);
        adapter.update(list);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {


        searchList.clear();

        if (s.toString().isEmpty()){
            adapter.update(list);
            return;
        }

        for (String a : list){
            if (a.contains(s)){
                searchList.add(a);
            }
        }
        adapter.update(searchList);
    }
}
