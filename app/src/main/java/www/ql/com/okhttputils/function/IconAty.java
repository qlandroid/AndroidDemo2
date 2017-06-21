package www.ql.com.okhttputils.function;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import uk.co.senab.photoview.PhotoView;
import www.ql.com.okhttputils.R;

public class IconAty extends AppCompatActivity {

    public static final String INTENT_ICON_ID = "iconId";

    private PhotoView pvIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_aty);
        pvIcon = (PhotoView) findViewById(R.id.icon_photoView);

        int id = getIntent().getIntExtra(INTENT_ICON_ID,-1);
        if (-1 != id){
            pvIcon.setImageResource(id);
        }else {
            TextView tv = new TextView(this);
            setContentView(tv);
            tv.setTextColor(Color.RED);
            tv.setText("图片Id没有传过来");
        }
    }

    public static void  showIconAty(Activity aty, int iconId){
        Intent intent = new Intent(aty,IconAty.class);
        intent.putExtra(INTENT_ICON_ID,iconId);
        aty.startActivity(intent);
    }
}
