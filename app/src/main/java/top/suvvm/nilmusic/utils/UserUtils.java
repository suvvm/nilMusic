package top.suvvm.nilmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;

import java.io.IOException;
import java.util.List;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.LoginActivity;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.http.HttpClient;
import top.suvvm.nilmusic.http.UserClient;
import top.suvvm.nilmusic.pojo.HttpRespModel;
import top.suvvm.nilmusic.pojo.LoginModel;
import top.suvvm.nilmusic.pojo.UserModel;

/**
 * @ClassName: UserUtils
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/25 13:33
 */
public class UserUtils {

    // 验证用户
    public static boolean judgeLoginDate(final Context context, String pnum, String psw) {
        if (!RegexUtils.isMobileExact(pnum)) {
            Toast.makeText(context, "手机号无效" , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(context, "输入密码为空" , Toast.LENGTH_SHORT).show();
            return false;
        }
//        // 用户手机号是否存在
//        if (!userExistFromPhone(pnum)) {
//            Toast.makeText(context, "输入手机号未注册" , Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        // 手机号与密码是否匹配
//        RealmHelp realmHelp = new RealmHelp();
//        boolean res = realmHelp.validateUser(pnum, EncryptUtils.encryptMD5ToString(psw));
//
//        if (!res) {
//            Toast.makeText(context, "密码错误" , Toast.LENGTH_SHORT).show();
//            return false;
//        }

        UserModel userModel = new UserModel();
        userModel.setPhone(pnum);
        // 加密存储密码
        userModel.setPassword(psw);

        Integer uid;
        try {
            LoginModel resp = UserClient.login(userModel);
            if (!resp.getCode().equals(HttpClient.HandlerSuccess)) {
                Toast.makeText(context, resp.getMsg() , Toast.LENGTH_SHORT).show();
                return false;
            }
            uid = resp.getUid();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // 保存用户登录标记
        boolean isSave = SharedPreferencesUtils.saveUser(context, pnum, uid);
        if (!isSave) {
            Toast.makeText(context, "登录状态保存错误" , Toast.LENGTH_SHORT).show();
            return false;
        }

        // 利用单例UserHelper保存用户登录信息
        UserHelp.getInstance().setPhone(pnum);
        UserHelp.getInstance().setId(uid.toString());

        RealmHelp realmHelp = new RealmHelp();
        // 保存音乐源数据
        realmHelp.setMusicSource(context);

        realmHelp.close();

//        final Context finContext = context;
//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RealmHelp realmHelp = new RealmHelp();
//                // 保存音乐源数据
//                realmHelp.setMusicSource(finContext);
//
//                realmHelp.close();
//            }
//        });
//        thread.run();
        return true;
    }

    // 退出登录
    public static void logout(Context context) {
        // 删除SharePreferences里保存的用户标记
        boolean isRemove = SharedPreferencesUtils.removeUser(context);
        if (!isRemove) {
            Toast.makeText(context, "用户状态清除错误" , Toast.LENGTH_SHORT).show();
            return;
        }
        // 删除数据源
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.removeMusicSource(context);
        realmHelp.close();

        Intent intent = new Intent(context, LoginActivity.class);
        // 添加intent标志，清空task栈并创建新的task栈，保证栈中只有一个Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        // 设置跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

    }

    // 用户注册
    public static boolean registerUser (Context context, String phone, String password, String passwordConfirm) {
        // 判断手机号是否合法
        if (!RegexUtils.isMobileExact(phone)) {
            Toast.makeText(context, "手机号无效" , Toast.LENGTH_SHORT).show();
            return false;
        }
        // 判断密码是否为空与两次密码是否相同
        if (StringUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            Toast.makeText(context, "密码无效" , Toast.LENGTH_SHORT).show();
            return false;
        }
//        // 验证当前手机号是否已被注册
//        if (userExistFromPhone(phone)) {
//            Toast.makeText(context, "手机号已存在" , Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        // 保存用户信息至realm数据库
        // 保存用户信息至服务端
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        // 加密存储密码
        userModel.setPassword(password);
        try {
            HttpRespModel resp = UserClient.register(userModel);
            System.out.println(resp.toString());
            if (!resp.getCode().equals(HttpClient.HandlerSuccess)) {
                Toast.makeText(context, resp.getMsg() , Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
//        saveUser(userModel);
        return true;
    }
    // 根据手机号判断用户是否存在
    public static boolean userExistFromPhone(String phone) {
        boolean res = false;
        RealmHelp realmHelp = new RealmHelp();
        // 获取全部用户
        List<UserModel> userModelList = realmHelp.getAllUser();
        for (UserModel userModel : userModelList) {
            if (userModel.getPhone().equals(phone)) {   // 手机号已存在
                res = true;
                break;
            }
        }
        realmHelp.close();
        return res;
    }
    // 将目标用户信息保存到realm数据库
    public static void saveUser (UserModel userModel) {
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }
    // 验证是否存在已登录用户
    public static boolean validateUserLogin(Context context) {
        return SharedPreferencesUtils.isLoginUser(context);
    }
    // 修改密码
    public static boolean changePassword(Context context, String oldPassword, String password, String passwordConfirm) {
        // 验证用户输入合法性
        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(context, "请输入原密码" , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入新密码" , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(passwordConfirm)) {
            Toast.makeText(context, "请输入确认密码" , Toast.LENGTH_SHORT).show();
            return false;
        }
        // 验证新密码与确认是否相同
        if (!password.equals(passwordConfirm)) {
            Toast.makeText(context, "密码不一致" , Toast.LENGTH_SHORT).show();
            return false;
        }
        // 验证原密码是否正确
        RealmHelp realmHelp = new RealmHelp();
        UserModel userModel = realmHelp.getUser();  // 在realm中获取当前登录用户模型
        if (!EncryptUtils.encryptMD5ToString(oldPassword).equals(userModel.getPassword())) {
            Toast.makeText(context, "原密码不正确" , Toast.LENGTH_SHORT).show();
            return false;
        }
        // 更新密码记录
        realmHelp.changePassword(EncryptUtils.encryptMD5ToString(password));
        realmHelp.close();
        return true;
    }
}
