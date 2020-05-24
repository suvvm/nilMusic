package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;

import android.os.Bundle;
import android.view.View;

/**
 * @ClassName: LoadingActivity
 * @Description: 登录活动
 * @Author: SUVVM
 * @Date: 2020/04/30 21:28
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(false, "登录", false);
    }

    // 注册按钮点击事件，跳转至注册页面
    public void onRegisterClick(View view) {
    }
}
