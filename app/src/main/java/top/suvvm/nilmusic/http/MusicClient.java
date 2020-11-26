package top.suvvm.nilmusic.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.suvvm.nilmusic.pojo.GetMusicRespModel;
import top.suvvm.nilmusic.pojo.HttpRespModel;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.MusicModel;

public class MusicClient extends HttpClient {
    public static HttpRespModel CreateMusic (AlbumModel album, MusicModel music) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", Integer.valueOf(album.getId()));
        jsonObject.put("name", music.getName());
        jsonObject.put("poster",music.getPoster());
        jsonObject.put("path",music.getPath());
        jsonObject.put("author",music.getAuthor());
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);
        final Request request = new Request.Builder()
                .url(ALBUM_ADD_MUSIC_URL)
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

    public static GetMusicRespModel GetMusic (String aid) throws IOException {
        String reqUrl = String.format(ALL_MUSIC_URL, aid);
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
        return JSON.parseObject(response[0].body().string(), GetMusicRespModel.class);
    }

    public static HttpRespModel MdfMusic (MusicModel music) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", Integer.valueOf(music.getId()));
        jsonObject.put("name", music.getName());
        jsonObject.put("poster",music.getPoster());
        jsonObject.put("path",music.getPath());
        jsonObject.put("author",music.getAuthor());
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);

        final Request request = new Request.Builder()
                .url(MDF_MUSIC_URL)
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

    public static HttpRespModel DelMusic (AlbumModel album , MusicModel music) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("aid", Integer.valueOf(album.getId()));
        jsonObject.put("mid", Integer.valueOf(music.getId()));
        RequestBody body = RequestBody.create(jsonObject.toJSONString(), HTTPJSON);

        final Request request = new Request.Builder()
                .url(DELETE_MUSIC_URL)
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
}
