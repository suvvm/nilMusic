package top.suvvm.nilmusic.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.utils.UserUtils;

/**
 * @ClassName: LoadingActivity
 * @Description: 加载活动
 * @Author: SUVVM
 * @Date: 2020/04/28 21:58
 */
public class LoadingActivity extends BaseActivity {

    private Timer timer;

    /**
     * @FunctionName: loading
     * @Description: 设置timer等待3秒后判断用户登录状态，
     *  决定跳转Activity
     * @Return:
     */
    private void loading () {
        final boolean isLogin = UserUtils.validateUserLogin(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 跳转至mainActivity并结束当前活动
                // Log.e("LoginActivity", "toMainActivity");
                // Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                if (isLogin) {  // 已存在登录
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        loading();
    }
}
