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
import www.ql.com.okhttputils.function.frag.AFrag;
import www.ql.com.okhttputils.function.frag.AdapterViewFlipperFrag;
import www.ql.com.okhttputils.function.frag.AnimTweenFrag;
import www.ql.com.okhttputils.function.frag.BallFrag;
import www.ql.com.okhttputils.function.frag.CameraFrag;
import www.ql.com.okhttputils.function.frag.CanvasMeshFrag;
import www.ql.com.okhttputils.function.frag.ClipDrawableFrag;
import www.ql.com.okhttputils.function.frag.DrawViewPathFrag;
import www.ql.com.okhttputils.function.frag.FragmentManagerFrag;
import www.ql.com.okhttputils.function.frag.IntentDetailsFrag;
import www.ql.com.okhttputils.function.frag.ListViewDetailsFrag;
import www.ql.com.okhttputils.function.frag.LocaleListFrag;
import www.ql.com.okhttputils.function.frag.MatrixFrag;
import www.ql.com.okhttputils.function.frag.NotificationFrag;
import www.ql.com.okhttputils.function.frag.ResFileDetailsFrag;
import www.ql.com.okhttputils.function.frag.StackViewFrag;
import www.ql.com.okhttputils.function.frag.SurfaceViewFrag;
import www.ql.com.okhttputils.function.frag.TextViewDetailFrag;
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

        searchList.addAll(list);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                searchList.clear();
                if (!s.toString().isEmpty()) {

                    for (FunctionBean bean : list) {
                        if (bean.getContent().contains(s)) {
                            //包含
                            searchList.add(bean);
                        }
                    }
                    mAdapter.update(searchList);
                } else {
                    searchList.addAll(list);
                    mAdapter.update(searchList);
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

        list.add(new FunctionBean("src资源文件下的所有文件夹", ResFileDetailsFrag.class.getCanonicalName()));

        list.add(new FunctionBean("textview所有属性", TextViewDetailFrag.class.getCanonicalName()));

        list.add(new FunctionBean("国际化，String类型,type", LocaleListFrag.class.getCanonicalName()));

        list.add(new FunctionBean("小球自定义View游戏", BallFrag.class.getCanonicalName()));

        list.add(new FunctionBean("matrix修改View的偏移，旋转，倾斜等", MatrixFrag.class.getCanonicalName()));

        list.add(new FunctionBean("ClipDrawable可以让图片循序渐进的展开", ClipDrawableFrag.class.getCanonicalName()));

        list.add(new FunctionBean("自定义View可以带扭曲，扭曲旗帜等作用", CanvasMeshFrag.class.getCanonicalName()));

        list.add(new FunctionBean("Tween补间动画", AnimTweenFrag.class.getCanonicalName()));

        list.add(new FunctionBean("surfaceView 的基本使用", SurfaceViewFrag.class.getCanonicalName()));

        list.add(new FunctionBean("environment访问手机内文件，手机目录，", "Environment", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("sharedPreferences的基本使用，", "SharedPreferences.txt", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("contentProvider内容提供者的基本使用，", "Content_provider", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("系统的Action，常见的Action，", "system_action", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("多媒体基本使用,MediaPlayer_soundPool", "MediaPlayer_SoundPool", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("多媒体基本使用,MediaRecorder录制音频", "MediaRecorder", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("Toast的重复出现解决办法", "toast", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("发送短信SmsManager短信管理器,电话管理器TelephonyManager", "sms_and_telephone_manager", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("AudioManager手机音频管理器", "AudioManager", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("震动器Vibrator,手机震动器", "Vibrator", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("手机闹钟服务AlarmManager", "AlarmManager", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("手机壁纸WallpaperManager", "WallpaperManager", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("相机的基本操作，CameraManager", "CameraManager", AFrag.class.getCanonicalName()));

        list.add(new FunctionBean("照相机的案例", CameraFrag.class.getCanonicalName()));


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        FunctionBean bean = searchList.get(position);
        Intent intent = new Intent();
        intent.setClass(this, bean.getClazz());
        intent.putExtra("frag", bean.getFragName());
        intent.putExtra(FragActivity.INTENT_FILE_NAME, bean.getFileName());
        intent.putExtra(FragActivity.INTENT_TITLE, bean.getContent());
        startActivity(intent);

    }

    public static class FunctionBean {
        private Class clazz;
        private String content;
        private String fragName;
        private String fileName;


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

        public FunctionBean(String content, String fileName, String fragName) {
            this.content = content;
            this.fragName = fragName;
            this.fileName = fileName;
            this.clazz = FragActivity.class;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
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

        public String getFileName() {
            return fileName;
        }
    }
}
