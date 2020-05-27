package top.suvvm.nilmusic.activities;

import androidx.appcompat.app.AppCompatActivity;
import top.suvvm.nilmusic.R;

import android.os.Bundle;

public class ChangePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
    }

    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(true, "修改密码", false);
    }
}
