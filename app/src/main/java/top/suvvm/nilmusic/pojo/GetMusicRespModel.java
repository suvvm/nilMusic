package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class GetMusicRespModel extends HttpRespModel {
    private List<MusicModel> musicList;

    @JSONField(name="music_list")
    public List<MusicModel> getMusicList() {
        return musicList;
    }

    @JSONField(name="music_list")
    public void setMusicList(List<MusicModel> musicList) {
        this.musicList = musicList;
    }
}
