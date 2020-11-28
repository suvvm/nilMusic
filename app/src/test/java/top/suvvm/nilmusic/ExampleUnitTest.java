package top.suvvm.nilmusic;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

import java.io.IOException;

import top.suvvm.nilmusic.http.UserClient;
import top.suvvm.nilmusic.pojo.LoginModel;
import top.suvvm.nilmusic.pojo.UserModel;
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
        System.out.println(DataUtils.getMusicData());
    }

    @Test
    public void testLogin() {
        UserModel user = new UserModel();
        user.setPhone("17854293661");
        user.setPassword("Poiuytrewq1");
        try {
            LoginModel resp = UserClient.login(user);
            System.out.println(JSON.toJSONString(resp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}