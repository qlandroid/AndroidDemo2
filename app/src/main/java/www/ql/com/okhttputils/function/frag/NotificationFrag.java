package www.ql.com.okhttputils.function.frag;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.ql.bindview.BindView;
import android.view.View;
import android.widget.Button;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.HomeActivity;
import www.ql.com.okhttputils.R;

/**
 * 使用闪光灯 添加的权限
 * android.permission.FLASHLIGHT
 * 添加操作振动器的权限
 * android.permission.VIBRATE
 * Created by Administrator on 2017-6-16.
 */
public class NotificationFrag extends BaseFragment {

    private static final int NOTIFY_ID = 20;

    @BindView(R.id.frag_notify_btn_cancel)
    Button btnCancel;
    @BindView(R.id.frag_notify_btn_show)
    Button btnShow;

    NotificationManager notificationManager;

    @Override
    public int createViewId() {
        return R.layout.frag_notification;
    }

    @Override
    public void initData() {
        super.initData();
        notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnCancel.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.frag_notify_btn_cancel:
                cancelNotify();
                break;
            case R.id.frag_notify_btn_show:
                send();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void send() {
        Intent intent = new Intent(getContext(), HomeActivity.class);
        PendingIntent pi = PendingIntent.getActivity(getContext(), 33, intent, 0);
        Notification notify = new Notification.Builder(getContext())
                //设置打开该通知，该通知自动小时
                .setAutoCancel(true)
                //设置显示在状态栏的通知提示信息
                .setTicker("有消息")
                //设置通知的图标
                .setSmallIcon(R.drawable.icon1)
                //设置震动 间隔
                .setVibrate(new long[]{0, 50, 100, 150})
                //设置通知内容的标题
                .setContentTitle("我是title")
                //设置通知内容
                .setContentText("我买的彩票啥时候能中奖")
                //设置使用系统默认的声音，默认Led灯
                .setDefaults(Notification.DEFAULT_ALL)
                //设置通知的自定义声音
                //.setSound(Uri.parse("android.resource://org.crazy))
                .setWhen(System.currentTimeMillis())
                //设置通知将要启动的Intent
                .setContentIntent(pi)
                .build();
        //发送通知
        notificationManager.notify(NOTIFY_ID, notify);
    }

    private void cancelNotify() {
        notificationManager.cancel(NOTIFY_ID);
    }
}

