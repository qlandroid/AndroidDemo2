package www.ql.com.okhttputils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import www.ql.com.okhttputils.function.DrawViewAty;
import www.ql.com.okhttputils.function.FragActivity;
import www.ql.com.okhttputils.function.ListViewActivity;
import www.ql.com.okhttputils.function.PullToRefreshListViewAty;
import www.ql.com.okhttputils.function.ViewListAty;
import www.ql.com.okhttputils.function.frag.AdapterViewFlipperFrag;
import www.ql.com.okhttputils.function.frag.AnimTweenFrag;
import www.ql.com.okhttputils.function.frag.BallFrag;
import www.ql.com.okhttputils.function.frag.CanvasMeshFrag;
import www.ql.com.okhttputils.function.frag.ClipDrawableFrag;
import www.ql.com.okhttputils.function.frag.DrawViewPathFrag;
import www.ql.com.okhttputils.function.frag.FragmentManagerFrag;
import www.ql.com.okhttputils.function.frag.IntentDetailsFrag;
import www.ql.com.okhttputils.function.frag.ListViewDetailsFrag;
import www.ql.com.okhttputils.function.frag.LocaleListFrag;
import www.ql.com.okhttputils.function.frag.MatrixFrag;
import www.ql.com.okhttputils.function.frag.NotificationFrag;
import www.ql.com.okhttputils.function.frag.SrcFileDetailsFrag;
import www.ql.com.okhttputils.function.frag.StackViewFrag;
import www.ql.com.okhttputils.function.frag.TextViewDetaileFrag;
import www.ql.com.okhttputils.function.frag.ViewSwitcherFrag;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ArrayList<FunctionBean> list = new ArrayList<>();

    private ArrayList<FunctionBean> searchList = new ArrayList<>();
    private CommonAdapter<FunctionBean> mAdapter;
    private ListView listView;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = new ListView(this);
        LinearLayout group = new LinearLayout(this);
        etSearch = new EditText(this);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        listView.setLayoutParams(lp);
        etSearch.setSingleLine();
        ViewGroup.LayoutParams etLp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        etSearch.setLayoutParams(etLp);

        group.addView(etSearch);
        group.addView(listView);

        group.setOrientation(LinearLayout.VERTICAL);

        setContentView(group);

        addFunction();

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    searchList.clear();
                    for (FunctionBean bean : list) {
                        if (bean.getContent().contains(s)) {
                            //包含
                            searchList.add(bean);
                        }
                    }
                    mAdapter.update(searchList);
                }else {
                    mAdapter.update(list);
                }
            }
        });

        mAdapter = new CommonAdapter<FunctionBean>(this, list, android.R.layout.simple_list_item_1) {
            @Override
            public void setItemContent(ViewHolder holder, FunctionBean functionBean) {
                holder.setText(android.R.id.text1, functionBean.getContent());
            }
        };
        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(this);
    }

    private void addFunction() {
        list.add(new FunctionBean(ListViewActivity.class, "listView可移动的item"));

        list.add(new FunctionBean(PullToRefreshListViewAty.class, "上下拉加载的listview"));

        list.add(new FunctionBean(DrawViewAty.class, "自定义View绘制"));

        list.add(new FunctionBean(ViewListAty.class, "有弹出框的TextView"));

        list.add(new FunctionBean("adapterViewFlipper自动播放", AdapterViewFlipperFrag.class.getCanonicalName()));

        list.add(new FunctionBean("stackView实现叠加效果", StackViewFrag.class.getCanonicalName()));

        list.add(new FunctionBean("ViewSwitcher实现无限切换", ViewSwitcherFrag.class.getCanonicalName()));

        list.add(new FunctionBean("notify 功能显示", NotificationFrag.class.getCanonicalName()));

        list.add(new FunctionBean("menuFrag 菜单", NotificationFrag.class.getCanonicalName()));

        list.add(new FunctionBean("自定义View中PathEffect 属性", DrawViewPathFrag.class.getCanonicalName()));

        list.add(new FunctionBean("listview详细属性", ListViewDetailsFrag.class.getCanonicalName()));

        list.add(new FunctionBean("fragmentManager中的属性，Fragment的使用", FragmentManagerFrag.class.getCanonicalName()));

        list.add(new FunctionBean("intent启动模式action,category属性等", IntentDetailsFrag.class.getCanonicalName()));

        list.add(new FunctionBean("src资源文件下的所有文件夹", SrcFileDetailsFrag.class.getCanonicalName()));

        list.add(new FunctionBean("textview所有属性", TextViewDetaileFrag.class.getCanonicalName()));

        list.add(new FunctionBean("国际化，String类型,type", LocaleListFrag.class.getCanonicalName()));

        list.add(new FunctionBean("小球自定义View游戏", BallFrag.class.getCanonicalName()));

        list.add(new FunctionBean("matrix修改View的偏移，旋转，倾斜等", MatrixFrag.class.getCanonicalName()));

        list.add(new FunctionBean("ClipDrawable可以让图片循序渐进的展开", ClipDrawableFrag.class.getCanonicalName()));

        list.add(new FunctionBean("自定义View可以带扭曲，扭曲旗帜等作用", CanvasMeshFrag.class.getCanonicalName()));

        list.add(new FunctionBean("Tween补间动画", AnimTweenFrag.class.getCanonicalName()));


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FunctionBean bean = list.get(position);
        Intent intent = new Intent();
        intent.setClass(this, bean.getClazz());
        intent.putExtra("frag", bean.getFragName());
        startActivity(intent);

    }

    public static class FunctionBean {
        private Class clazz;
        private String content;
        private String fragName;

        public FunctionBean(Class clazz, String content) {
            this.clazz = clazz;
            this.content = content;
        }

        public FunctionBean(Class clazz, String content, String fragName) {
            this.clazz = clazz;
            this.content = content;
            this.fragName = fragName;
        }

        public FunctionBean(String content, String fragName) {
            this.content = content;
            this.fragName = fragName;
            this.clazz = FragActivity.class;
        }

        public String getFragName() {
            return fragName;
        }

        public void setFragName(String fragName) {
            this.fragName = fragName;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
