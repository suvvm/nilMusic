package top.suvvm.nilmusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.blankj.utilcode.util.SPStaticUtils;

import top.suvvm.nilmusic.constants.SharePreferencesConstants;
import top.suvvm.nilmusic.helps.UserHelp;

/**
 * @ClassName: SharedPreferencesUtils
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/30 20:12
 */
public class SharedPreferencesUtils {
    // 当用户登录时，利用SharedPreferences保存用户登录标记（手机号）
    public static boolean saveUser(Context context, String phone, String id) {
        SharedPreferences preferences = context.getSharedPreferences(SharePreferencesConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SharePreferencesConstants.SP_KEY_PHONE, phone);
        editor.putString(SharePreferencesConstants.SP_KEY_ID, id);
        boolean res = editor.commit();
        return res;
    }
    // 登出时删除SharedPreferences用户标记
    public static boolean removeUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(SharePreferencesConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(SharePreferencesConstants.SP_KEY_PHONE);
        editor.remove(SharePreferencesConstants.SP_KEY_ID);
        boolean res = editor.commit();
        return res;
    }

    // 验证是否存在已登录用户
    public static boolean isLoginUser(Context context) {
        boolean res = false;

        SharedPreferences preferences = context.getSharedPreferences(SharePreferencesConstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = preferences.getString(SharePreferencesConstants.SP_KEY_PHONE, "");
        String id = preferences.getString(SharePreferencesConstants.SP_KEY_ID, "");
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(id)) {    // 已存在登录用户
            res = true;
            // 利用单例UserHelper保存用户登录信息
            UserHelp.getInstance().setPhone(phone);
            UserHelp.getInstance().setId(id);
        }

        return res;
    }

}
