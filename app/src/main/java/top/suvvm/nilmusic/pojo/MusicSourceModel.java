package top.suvvm.nilmusic.pojo;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * @ClassName: MusicSourceModel
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 18:39
 */
public class MusicSourceModel extends RealmObject {
    private RealmList<AlbumModel> album;
    private RealmList<MusicModel> hot;
    private RealmList<AlbumModel> self;

    public RealmList<AlbumModel> getSelf() {
        return self;
    }

    public void setSelf(RealmList<AlbumModel> self) {
        this.self = self;
    }

    public RealmList<AlbumModel> getAlbum() {
        return album;
    }

    public void setAlbum(RealmList<AlbumModel> album) {
        this.album = album;
    }

    public RealmList<MusicModel> getHot() {
        return hot;
    }

    public void setHot(RealmList<MusicModel> hot) {
        this.hot = hot;
    }
}
