package top.suvvm.nilmusic.pojo;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class GetAlbumRespModel extends HttpRespModel {
    private List<AlbumModel> albumList;

    @JSONField(name="album_list")
    public List<AlbumModel> getAlbumList() {
        return albumList;
    }
    @JSONField(name="album_list")
    public void setAlbumList(List<AlbumModel> albumList) {
        this.albumList = albumList;
    }
}
