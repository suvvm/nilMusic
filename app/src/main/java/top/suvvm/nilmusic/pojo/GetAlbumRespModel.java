package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class GetAlbumRespModel extends HttpRespModel {
    private List<JSONObject> albumList;

    @JSONField(name="album_list")
    public List<JSONObject> getAlbumList() {
        return albumList;
    }
    @JSONField(name="album_list")
    public void setAlbumList(List<JSONObject> albumList) {
        this.albumList = albumList;
    }

    @Override
    public String toString() {
        return "{" +
                "\"album\" :" + albumList +
                '}';
    }
}
