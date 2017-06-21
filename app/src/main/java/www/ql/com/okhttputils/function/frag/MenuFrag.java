package www.ql.com.okhttputils.function.frag;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import www.ql.com.okhttputils.BaseFragment;
import www.ql.com.okhttputils.R;

/**
 * Created by Administrator on 2017-6-16.
 */
public class MenuFrag extends BaseFragment {
    @Override
    public int createViewId() {
        return R.layout.frag_notification;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        SubMenu sm =  menu.addSubMenu("添加字体Size");
        sm.setIcon(R.mipmap.ic_launcher);
        sm.setHeaderIcon(R.mipmap.ai);
        sm.setHeaderTitle("headerTitle");
        for (int i = 0; i < 10; i++) {
            sm.add(0,i,0,"item -->" + i);
        }
        menu.add(0,11,0,"普通菜单");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                Toast.makeText(getContext(), "点击子菜单", Toast.LENGTH_SHORT).show();
                break;
            case 11://普通菜单
                Toast.makeText(getContext(), "普通菜单", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
