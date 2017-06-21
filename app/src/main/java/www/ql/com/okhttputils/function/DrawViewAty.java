package www.ql.com.okhttputils.function;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.widget.DrawView;

public class DrawViewAty extends AppCompatActivity {

    private DrawView mDrawView;
    private TextView tv;

    private int index ;
    private Paint.Style[] styles = {Paint.Style.FILL,Paint.Style.FILL_AND_STROKE,Paint.Style.STROKE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_view_aty);
        mDrawView = (DrawView) findViewById(R.id.draw_view);
        tv = (TextView) findViewById(R.id.draw_view_tv);
    }
    private String styleType;

    public void changePaintStyle(View view) {
        Paint.Style style  = styles[index % styles.length];
        styleType = style.toString();
        mDrawView.setPaintStyle(style);
        mDrawView.invalidate();
        index ++;
        changeStatus();
    }

    private void changeStatus() {
        tv.setText("当前画笔 paintStyle  = " + styleType +"\r\n" + "是否添加了渐变"+ isAddShader);
    }

    boolean isAddShader = false;
    public void addShader(View view) {
        isAddShader = !isAddShader;
        mDrawView.setAddShader(isAddShader);
        changeStatus();
    }
}
