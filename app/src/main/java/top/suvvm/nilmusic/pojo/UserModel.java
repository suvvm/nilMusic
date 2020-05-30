package top.suvvm.nilmusic.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @ClassName: UserModel
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/30 16:10
 */
public class UserModel extends RealmObject {
    @PrimaryKey
    private String phone;
    @Required
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
