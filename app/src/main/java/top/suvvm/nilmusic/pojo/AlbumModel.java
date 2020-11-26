package top.suvvm.nilmusic.pojo;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @ClassName: AlbumModel
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 18:42
 */
public class AlbumModel extends RealmObject {
    private String id;
    private String name;
    private String poster;
    private String playNum;
    private RealmList<MusicModel> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPlayNum() {
        return playNum;
    }

    public void setPlayNum(String playNum) {
        this.playNum = playNum;
    }

    public RealmList<MusicModel> getList() {
        return list;
    }

    public void setList(RealmList<MusicModel> list) {
        this.list = list;
    }
}
