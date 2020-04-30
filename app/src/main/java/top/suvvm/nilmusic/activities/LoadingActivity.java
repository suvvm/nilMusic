package top.suvvm.nilmusic.activities;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import top.suvvm.nilmusic.R;

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
     * @Description: 设置timer等待3秒后跳转至LoginActivity
     * @Return:
     */
    private void loading () {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 跳转至mainActivity并结束当前活动
                // Log.e("LoginActivity", "toMainActivity");
                // Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                Intent intent = new Intent(LoadingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
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
