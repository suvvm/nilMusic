package top.suvvm.nilmusic.helps;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;

import java.io.FileNotFoundException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import top.suvvm.nilmusic.migration.Migration;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.MusicModel;
import top.suvvm.nilmusic.pojo.MusicSourceModel;
import top.suvvm.nilmusic.pojo.UserModel;
import top.suvvm.nilmusic.utils.DataUtils;

/**
 * @ClassName: RealmHelp
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/30 16:09
 */
public class RealmHelp {

    private Realm realm;

    public RealmHelp() {
        realm = Realm.getDefaultInstance();
    }

    // 保存用户至Realm数据库
    public void saveUser (UserModel userModel) {
        // 开启事务 Realm数据库所有改动行操作必须在事务中
        realm.beginTransaction();
        realm.insert(userModel);
//        realm.insertOrUpdate(userModel);
        // 提交事务
        realm.commitTransaction();
    }

    // 关闭数据库减少引用计数
    public void close() {
        // realm数据库连接实例存在且未被关闭
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    // 获取所有用户
    public List<UserModel> getAllUser() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        RealmResults<UserModel> results = query.findAll();
        return results;
    }

    // 验证用户信息
    public boolean validateUser(String phone, String password) {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        query.equalTo("phone", phone)
                .equalTo("password", password);
        UserModel userModel = query.findFirst();
        if (userModel != null)
            return true;
        return false;
    }

    // 获取当前登录用户
    public UserModel getUser() {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        UserModel user = query.equalTo("phone", UserHelp.getInstance().getPhone()).findFirst();
        return user;
    }

    // 修改用户密码
    public void changePassword (String password) {
        UserModel userModel = getUser();
        realm.beginTransaction();
        userModel.setPassword(password);
        realm.commitTransaction();
    }

    // 修改用户密码 废弃
    @Deprecated
    public void changePasswordByUser (UserModel userModel) {
        RealmQuery<UserModel> query = realm.where(UserModel.class);
        UserModel user = query.equalTo("phone", userModel.getPhone()).findFirst();

        realm.beginTransaction();
        user.setPassword(userModel.getPassword());
        realm.commitTransaction();
    }
    // 用户登录存放数据
    // 用户退出删除数据

    // 保存音乐源数据
    public void setMusicSource() {
        // 获取json数据
        // String musicJson = DataUtils.getJsonFromUrl("https://www.suvvm.work/nilMusicData/DataSource.json");
        // String musicJson = DataUtils.getJsonFromAssets(context, "DataSource.json");
        String musicJson = DataUtils.getMusicData();
        System.out.println(musicJson);
        realm.beginTransaction();
        realm.createObjectFromJson(MusicSourceModel.class, musicJson);
        realm.commitTransaction();
    }

    // 删除音乐源数据
    public void removeMusicSource(Context context) {
        realm.beginTransaction();
        realm.delete(MusicSourceModel.class);
        realm.delete(MusicModel.class);
        realm.delete(AlbumModel.class);
        realm.commitTransaction();
    }

    // realm数据库发生结构性变化，进行数据迁移


    // 创建RealmConfiguration
    private static RealmConfiguration getRealmConfiguration () {
        return new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new Migration())
                .build();
    }
    // 通知realm进行数据迁移，为realm设置最新配置
    public static void migration() {
        // 创建RealmConfiguration
        RealmConfiguration configuration = getRealmConfiguration();
        // 为realm设置最新配置
        Realm.setDefaultConfiguration(configuration);
        // 通知realm进行数据迁移
        try {
            Realm.migrateRealm(configuration);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 返回音乐源数据
    public MusicSourceModel getMusicSource() {
        return realm.where(MusicSourceModel.class).findFirst();
    }

    // 根据id返回歌单
    public AlbumModel getAlbum(String albumId) {
        return realm.where(AlbumModel.class).equalTo("id", albumId).findFirst();
    }

    // 根据id返回音乐
    public MusicModel getMusic(String musicId) {
        return realm.where(MusicModel.class).equalTo("id", musicId).findFirst();
    }

}
