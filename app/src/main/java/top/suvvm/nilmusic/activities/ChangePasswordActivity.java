package top.suvvm.nilmusic.activities;

import androidx.appcompat.app.AppCompatActivity;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.utils.UserUtils;
import top.suvvm.nilmusic.views.InputView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangePasswordActivity extends BaseActivity {

    private InputView inputOldPassword, inputPassword, inputPasswordConfirm;

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

        inputOldPassword = findViewById(R.id.input_old_password);
        inputPassword = findViewById(R.id.input_password);
        inputPasswordConfirm = findViewById(R.id.input_password_confirm);
    }
    // 修改密码提交按钮点击事件
    public void onSubmitClick (View view) {
        String oldPassword = inputOldPassword.getInputVal();
        String password = inputPassword.getInputVal();
        String passwordConfirm = inputPasswordConfirm.getInputVal();
        boolean res = UserUtils.changePassword(this, oldPassword, password, passwordConfirm);
        if (!res) {
            return;
        }
        // 退出登录返回登录页面
        UserUtils.logout(this);
    }
}
