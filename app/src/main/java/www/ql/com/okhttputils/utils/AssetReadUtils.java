package www.ql.com.okhttputils.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-6-21.
 */
public class AssetReadUtils {

    public static String readFile(Context context,String fileName){
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = context.getAssets().open(fileName);
            byte[] buf = new byte[1024];
            int len = -1 ;
            while((len = is.read(buf)) != -1){
                sb.append(new String(buf,0,len,"utf-8"));
            }
            is.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
