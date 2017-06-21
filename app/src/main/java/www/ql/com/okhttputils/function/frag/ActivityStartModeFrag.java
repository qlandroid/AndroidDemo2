package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * 用于显示 Activity的启动模式
 * Created by Administrator on 2017-6-19.
 */
public class ActivityStartModeFrag extends BaseFragment{

    @BindView(R.id.activity_start_mode_tv_content)
    TextView tvContent;

    @Override
    public int createViewId() {
        return R.layout.frag_activity_start_mode;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        StringBuffer sb = new StringBuffer();
        sb.append("\t").append("设置清单文件中Activity中launchMode属性，该属性包含四个值")
                .append("\r\n")
                .append("\t").append("1.standard : 标准模式，默认的加载模式")
                .append("\r\n")
                .append("\t").append("2.singleTop : Task栈顶 单例模式 ，如果当前Activity在栈顶，重复启动当前Activity不会重新启动，")
                .append("\r\n")
                .append("\t").append("3.singleTask : Task栈内 单例模式，如果栈内 存在要启动的Activity 会将该Activity栈上的Activity移出，显示要启动并已经存在的Activity")
                .append("\r\n")
                .append("\t").append("4.singleInstance : 全局单例模式 创建一个新的栈")
        ;

        tvContent.setText(sb.toString());
    }
}
