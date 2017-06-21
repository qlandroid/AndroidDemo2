package android.ql.bindview;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017-3-10.
 */
public class BindViewUtils {

    public static void find(Activity aty) {
        Class clazz = aty.getClass();

        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(BindView.class)) {
                    BindView f = field.getAnnotation(BindView.class);
                    int viewId = f.value();
                    Method m = clazz.getDeclaredMethod("findViewById", int.class);
                    Object view = m.invoke(aty,viewId);
                    field.setAccessible(true);
                    field.set(aty,view);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void find(Fragment frag) {
        find(frag,frag.getView());
    }

    private static void find(Object ojb,View v) {
        Class clazz = ojb.getClass();

        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.isAnnotationPresent(BindView.class)) {
                    BindView f = field.getAnnotation(BindView.class);
                    int viewId = f.value();
                    View view = v.findViewById(viewId);
                    field.setAccessible(true);
                    field.set(ojb,view);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
