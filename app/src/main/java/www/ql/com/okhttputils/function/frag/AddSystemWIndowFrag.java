package www.ql.com.okhttputils.function.frag;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.ql.bindview.BindView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by mrqiu on 2017/7/8.
 */

public class AddSystemWindowFrag extends BaseFragment {
    public static View sWindowView;


    @BindView(R.id.add_system_window_tv_content)
    TextView tvContent;




    @Override
    public int createViewId() {
        return R.layout.frag_add_system_window;
    }

    @Override
    public void initWidget(final View view) {
        super.initWidget(view);

        tvContent.setText("创建的窗口建议使用服务进行创建，这样创建完毕后，一直显示在窗体中");

        Context ctx = getActivity().getApplicationContext();
        Button btn = new Button(ctx);
        btn.setText("hello window");
        btn.setOnClickListener(new View.OnClickListener() {
            private int clickCount = 0;
            @Override
            public void onClick(View v) {
                clickCount ++;
                ((Button) v).setText("点击19此 关闭此窗口 click  count = " + clickCount);

                if (clickCount == 19){
                    WindowManager wm = (WindowManager) v.getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                    wm.removeView(sWindowView);
                }
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,100,WindowManager.LayoutParams.TYPE_PRIORITY_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING
                , PixelFormat.RGBX_8888
        );
        lp.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
        lp.setTitle("title");
        lp.token = new Binder();
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        sWindowView = btn;
        wm.addView(btn,lp);


    }

}
