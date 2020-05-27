package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.utils.UserUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        initView();
    }

    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(true, "个人中心", false);
    }

    // 修改密码
    public void onChangeClick(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    // 退出登录
    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
