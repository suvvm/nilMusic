package top.suvvm.nilmusic.helps;

import android.content.Context;

/**
 * @ClassName: UserHelp
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/30 19:56
 */
public class UserHelp {
    // 用户登录未过期时用sharedPreferences保存登录标记（手机号）
    // userHelp单例保存登录用户信息

    // 用户标记（手机号）
    private String phone;

    // 在静态域中声明单例的实例对象
    private volatile static UserHelp instance;

    public UserHelp() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // 加锁的访问该实例的方法
    public static UserHelp getInstance() {
        // 检验单例实例是否创建
        if (instance == null) {
            synchronized(UserHelp.class) {
                // 进入同步代码块后再次检验
                if (instance == null) {
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }
}
