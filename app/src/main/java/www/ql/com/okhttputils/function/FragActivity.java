package www.ql.com.okhttputils.function;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import www.ql.com.okhttputils.R;
import www.ql.com.okhttputils.function.frag.AFrag;

public class FragActivity extends AppCompatActivity {
    public static final String INTENT_TITLE = "title";
    public static final String INTENT_FILE_NAME = "fileName";

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction tr = mFragmentManager.beginTransaction();
        String frag = getIntent().getStringExtra("frag");
        String title = getIntent().getStringExtra(INTENT_TITLE);
        String fileName = getIntent().getStringExtra(INTENT_FILE_NAME);

        if (AFrag.class.getCanonicalName().equals(frag)) {
            AFrag fragment =AFrag.newInstance(title,fileName);
            tr.replace(R.id.frag_fl_content, fragment).commit();
            return;
        }

        try {
            Fragment fragment = (Fragment) Class.forName(frag).newInstance();
            tr.replace(R.id.frag_fl_content, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
