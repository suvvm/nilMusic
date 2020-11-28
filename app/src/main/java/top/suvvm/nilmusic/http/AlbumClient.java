package top.suvvm.nilmusic.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.suvvm.nilmusic.pojo.CreateAlbumRespModel;
import top.suvvm.nilmusic.pojo.GetAlbumRespModel;
import top.suvvm.nilmusic.pojo.HttpRespModel;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.UserModel;

public class AlbumClient extends HttpClient {

    public static CreateAlbumRespModel CreateAlbum (AlbumModel album, Integer uid) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("name", album.getName());
        jsonObject.put("poster",album.getPoster());
        jsonObject.put("play_num",album.getPlayNum());
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);
        final Request request = new Request.Builder()
                .url(CREATE_ALBUM_URL)
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
        String resData = response[0].body().string();
        Log.println(Log.DEBUG, "CreateAlbum", resData);
        return JSON.parseObject(resData, CreateAlbumRespModel.class);
    }

    public static GetAlbumRespModel GetAllAlbum (String uid) throws IOException {
        String reqUrl = String.format(ALL_ALBUM_URL, uid);
        final Request request = new Request.Builder()
                .url(reqUrl)
                .get()
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
        String resData = response[0].body().string();
        Log.println(Log.DEBUG, "GetAllAlbum", resData);
        return JSON.parseObject(resData, GetAlbumRespModel.class);
    }

    public static HttpRespModel DelAlbum (UserModel user, AlbumModel album, Integer uid) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", uid);
        jsonObject.put("aid", Integer.valueOf(album.getId()));
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);
        final Request request = new Request.Builder()
                .url(DELETE_ALBUM_URL)
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
        String resData = response[0].body().string();
        Log.println(Log.DEBUG, "DelAlbum", resData);
        return JSON.parseObject(resData, HttpRespModel.class);
    }
}
