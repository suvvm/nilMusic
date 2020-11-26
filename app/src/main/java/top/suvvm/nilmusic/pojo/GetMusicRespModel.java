package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class GetMusicRespModel extends HttpRespModel {
    private List<JSONObject> musicList;

    @JSONField(name="music_list")
    public List<JSONObject> getMusicList() {
        return musicList;
    }

    @JSONField(name="music_list")
    public void setMusicList(List<JSONObject> musicList) {
        this.musicList = musicList;
    }

    @Override
    public String toString() {
        return "GetMusicRespModel{" +
                "musicList=" + musicList +
                '}';
    }
}
