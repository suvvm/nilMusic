package top.suvvm.nilmusic.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.EncryptUtils;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.suvvm.nilmusic.pojo.HttpRespModel;
import top.suvvm.nilmusic.pojo.LoginModel;
import top.suvvm.nilmusic.pojo.UserModel;

public class UserClient extends HttpClient {

    public static HttpRespModel register (UserModel user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pnum", user.getPhone());
        jsonObject.put("password", EncryptUtils.encryptMD5ToString(user.getPassword()));
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);
        final Request request = new Request.Builder()
                .url(REGISTER_URL)
                .post(body)
                .build();
        final Response[] response = new Response[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response[0] = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(response[0].body().string(), HttpRespModel.class);
    }

    public static LoginModel login (UserModel user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pnum", user.getPhone());
        jsonObject.put("password", EncryptUtils.encryptMD5ToString(user.getPassword()));
        RequestBody body = RequestBody.create(jsonObject.toString(), HTTPJSON);
        final Request request = new Request.Builder()
                .url(LOGIN_URL)
                .post(body)
                .build();
        final Response[] response = new Response[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    response[0] = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(response[0].body().string(), LoginModel.class);
    }
}
