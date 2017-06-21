package www.ql.com.okhttputils.function.frag;

import android.ql.bindview.BindView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.widget.MatrixView;

/**
 * matrix设置View的倾斜 旋转偏移 等
 * Created by Administrator on 2017-6-20.
 */
public class MatrixFrag extends BaseFragment {

    @BindView(R.id.matrix_btn_big)
    Button btnBig;
    @BindView(R.id.matrix_btn_small)
    Button btnSmall;
    @BindView(R.id.matrix_btn_left)
    Button btnLeft;
    @BindView(R.id.matrix_btn_right)
    Button btnRight;
    @BindView(R.id.matrix_tv)
    TextView tv;
    @BindView(R.id.matrix_mv)
    MatrixView mv;

    private StringBuffer sb = new StringBuffer();

    @Override
    public int createViewId() {
        return R.layout.frag_matrix;
    }

    @Override
    public void initData() {
        super.initData();
        try {
            InputStream io = getContext().getAssets().open("matrix.txt");
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = io.read(buf)) != -1){
                sb.append(new String(buf,0,len,"utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        btnBig.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnSmall.setOnClickListener(this);
        tv.setText(sb.toString());
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        switch (v.getId()){
            case R.id.matrix_btn_big:
                mv.setMatrixBig();
                break;
            case R.id.matrix_btn_left:
                mv.setMatrixLeft();
                break;
            case R.id.matrix_btn_right:
                mv.setMatrixRight();
                break;
            case R.id.matrix_btn_small:
                mv.setMatrixSmall();
                break;
        }
    }
}
