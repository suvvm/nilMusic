package top.suvvm.nilmusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.RegexUtils;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.LoginActivity;

/**
 * @ClassName: UserUtils
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/25 13:33
 */
public class UserUtils {

    // 验证登录输入内容合法性
    public static boolean judgeLoginDate(Context context, String pnum, String psw) {

        if (!RegexUtils.isMobileExact(pnum)) {
            Toast.makeText(context, "手机号无效" , Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(psw)) {
            Toast.makeText(context, "输入密码为空" , Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // 退出登录
    public static void logout(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        // 添加intent标志，清空task栈并创建新的task栈，保证栈中只有一个Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        // 设置跳转动画
        ((Activity)context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

    }
}
