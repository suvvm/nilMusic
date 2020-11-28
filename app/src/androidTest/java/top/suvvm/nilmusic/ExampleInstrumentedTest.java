package top.suvvm.nilmusic;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.utils.DataUtils;
import top.suvvm.nilmusic.utils.UserUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("top.suvvm.nilmusic", appContext.getPackageName());
//
//        RealmHelp realmHelp = new RealmHelp();
//        realmHelp.removeMusicSource(appContext);
        //       UserUtils.logout(appContext);
    }

    @Test
    public void testGetData() {
        System.out.println(DataUtils.getMusicData());
    }
}
