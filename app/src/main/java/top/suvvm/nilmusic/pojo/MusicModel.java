package top.suvvm.nilmusic.pojo;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * @ClassName: MusicModel
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 18:44
 */
public class MusicModel extends RealmObject implements Serializable {
    private String id;
    private String name;
    private String poster;
    private String path;
    private String author;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
