package www.ql.com.okhttputils.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import www.ql.com.okhttputils.R;

public class ViewListAty extends AppCompatActivity {

    private AutoCompleteTextView actv;
    private MultiAutoCompleteTextView mactv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_aty);
        actv = (AutoCompleteTextView) findViewById(R.id.view_list_actv);
        //设置下拉菜单中的提示标题
        actv.setCompletionHint("completionHint");
        //设置用户至少输入几个字符才会显示提示
        actv.setThreshold(2);
        String[] strs = {"abcde","fasdd","12345"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,strs);

        actv.setAdapter(adapter);

        mactv = (MultiAutoCompleteTextView) findViewById(R.id.view_list_mactv);
        actv.setThreshold(1);
        mactv.setAdapter(adapter);




    }
}
