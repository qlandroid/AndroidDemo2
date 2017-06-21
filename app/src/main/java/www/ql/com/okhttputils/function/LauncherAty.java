package www.ql.com.okhttputils.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import www.ql.com.okhttputils.HomeActivity;
import www.ql.com.okhttputils.R;

public class LauncherAty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null)
        if (Intent.ACTION_CREATE_SHORTCUT.equals(getIntent().getAction())){
            Intent resultShortcutIntent = new Intent();

            resultShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,"好想你");
            resultShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON,Intent.ShortcutIconResource.fromContext(this, R.mipmap.tie));
            resultShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT,new Intent(this, HomeActivity.class));

           setResult(Activity.RESULT_OK,resultShortcutIntent);
        }
        finish();

    }
}
