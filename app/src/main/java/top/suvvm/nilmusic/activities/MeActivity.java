package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.utils.UserUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MeActivity extends BaseActivity {

    private TextView tvUser;

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
        tvUser = findViewById(R.id.tv_user);
        // 设置用户名
        tvUser.setText("用户名: " + UserHelp.getInstance().getPhone());
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
