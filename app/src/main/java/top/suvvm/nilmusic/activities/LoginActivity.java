package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.utils.UserUtils;
import top.suvvm.nilmusic.views.InputView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @ClassName: LoadingActivity
 * @Description: 登录活动
 * @Author: SUVVM
 * @Date: 2020/04/30 21:28
 */
public class LoginActivity extends BaseActivity {

    private InputView inputPhone;
    private InputView inputPassword;

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
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
    }

    // 注册按钮点击事件，跳转至注册页面
    public void onRegisterClick(View view) {

    }

    // 登录按钮点击事件
    public void onCommitClick(View view) {
        String pnum = inputPhone.getInputVal();
        String psw = inputPassword.getInputVal();
        // 验证用户输入内容是否合法
        if (!UserUtils.judgeLoginDate(this, pnum, psw))
            return;
        // 跳转至MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
