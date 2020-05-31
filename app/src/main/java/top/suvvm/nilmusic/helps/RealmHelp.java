package top.suvvm.nilmusic.helps;

import com.blankj.utilcode.util.StringUtils;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import top.suvvm.nilmusic.pojo.UserModel;

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
}
