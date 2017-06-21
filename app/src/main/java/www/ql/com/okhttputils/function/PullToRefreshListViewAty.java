package www.ql.com.okhttputils.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import app.ql.listview.PullToRefreshListView;
import www.ql.com.okhttputils.R;

public class PullToRefreshListViewAty extends AppCompatActivity {

    private PullToRefreshListView mlv;
    private ArrayAdapter<String> adapter;
    ArrayList<String> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_list_view_aty);

        loadMore();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,list);

        mlv = (PullToRefreshListView) findViewById(R.id.pull_to_mlv);
        mlv.setAdapter(adapter);

    }
    int count =0;
    private void loadMore(){
        for (int i = 0; i < 10; i++) {
            list.add("item"+ count);
            count++;
        }
    }
}
