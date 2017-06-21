package www.ql.com.okhttputils.function.frag;

import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.ql.bindview.BindView;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * 图片 熏熏显示
 * Created by Administrator on 2017-6-21.
 */
public class ClipDrawableFrag extends BaseFragment {

    @BindView(R.id.clip_drawable_iv)
    ImageView iv;

    @Override
    public int createViewId() {
        return R.layout.frag_clip_drawable;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        //请看 imageVIew 中设置的资源文件
        final ClipDrawable drawable = (ClipDrawable) iv.getDrawable();
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x123){
                    drawable.setLevel(drawable.getLevel() + 200);
                }
            }
        };

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Message msg = new Message();
                msg.what = 0x123;
                handler.sendMessage(msg);
                if (drawable.getLevel() >= 10000){
                    timer.cancel();
                }
            }
        },0,300);
    }
}
