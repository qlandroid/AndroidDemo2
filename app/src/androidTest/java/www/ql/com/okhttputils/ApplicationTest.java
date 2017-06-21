package www.ql.com.okhttputils;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;

import www.ql.com.okhttputils.function.FragActivity;
import www.ql.com.okhttputils.function.frag.StackViewFrag;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
        Activity aty ;
        Intent intent = new Intent(getContext(),FragActivity.class);
        intent.putExtra("frag", StackViewFrag.class.getCanonicalName());


    }
}