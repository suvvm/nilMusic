package top.suvvm.nilmusic;

import org.junit.Test;

import top.suvvm.nilmusic.utils.DataUtils;
import top.suvvm.nilmusic.utils.UserUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testGetData() {
        DataUtils.getMusicData();
    }
}