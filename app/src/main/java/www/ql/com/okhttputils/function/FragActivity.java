package www.ql.com.okhttputils.function;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import www.ql.com.okhttputils.R;

public class FragActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction tr = mFragmentManager.beginTransaction();
        String frag = getIntent().getStringExtra("frag");
        try {
            Fragment fragment = (Fragment) Class.forName(frag).newInstance();
            tr.replace(R.id.frag_fl_content,fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



}
