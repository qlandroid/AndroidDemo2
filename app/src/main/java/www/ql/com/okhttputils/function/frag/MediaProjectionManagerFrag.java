package www.ql.com.okhttputils.function.frag;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import www.ql.com.okhttputils.BaseFragment;

/**
 * Created by mrqiu on 2017/6/25.
 */

public class MediaProjectionManagerFrag extends BaseFragment {


    private static final int CAPTURE_CODE = 0x123;
    private MediaProjectionManager projectionManager ;
    private int screenDnsity;
    private int displayWidth = 360;
    private int displayHeight = 640;
    private boolean screenSharing;
    private MediaProjection mediaProjection;
    private VirtualDisplay virtualDisplay;
    private Surface surface;
    private SurfaceView surfaceView;

    @Override
    public void initData() {
        super.initData();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenDnsity = metrics.densityDpi;


    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        surface = surfaceView.getHolder().getSurface();
        //控制界面上的SurfaceView组件的宽度和高度
        ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();
        lp.height = displayHeight;
        lp.width = displayWidth;
        surfaceView.setLayoutParams(lp);
        projectionManager = (MediaProjectionManager) getContext().getSystemService(Context.MEDIA_PROJECTION_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaProjection != null){
            mediaProjection.stop();
            mediaProjection  = null;
        }
    }

    public void onToggleScreenShare(View view){
        if (Build.VERSION.SDK_INT <21){
            Toast.makeText(getContext(), "android版本较低无法操作", Toast.LENGTH_SHORT).show();
           return;
        }
        if (((ToggleButton)view).isChecked()){

            shareScreen();
        }else {
            stopScreenSharing();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void stopScreenSharing() {
        screenSharing = false;
        if (virtualDisplay == null){
            return;
        }
        virtualDisplay.release();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void shareScreen() {
        screenSharing = true;
        if (surface ==null){
            return;
        }

        if (mediaProjection == null){
            Intent intent = projectionManager.createScreenCaptureIntent();
            startActivityForResult(intent,CAPTURE_CODE);
        }
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_CODE){
            if (resultCode != Activity.RESULT_OK){
                Toast.makeText(getContext(), "用户取消捕捉", Toast.LENGTH_SHORT).show();
                return;
            }
            mediaProjection = projectionManager.getMediaProjection(resultCode,data);
            virtualDisplay = mediaProjection.createVirtualDisplay("屏幕捕捉",displayWidth,displayHeight,screenDnsity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,surface,null,null);

        }
    }
}
