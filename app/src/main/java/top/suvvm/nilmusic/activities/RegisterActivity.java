package top.suvvm.nilmusic.activities;

import androidx.appcompat.app.AppCompatActivity;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.utils.UserUtils;
import top.suvvm.nilmusic.views.InputView;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;

public class RegisterActivity extends BaseActivity {

    private InputView inputPhone, inputPassword, inputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    // 初始化view
    private void initView() {
        initNavigationBar(true, "注册", false);
        inputPhone = findViewById(R.id.input_phone);
        inputPassword = findViewById(R.id.input_password);
        inputPasswordConfirm = findViewById(R.id.input_password_confirm);
    }
    // 注册按钮点击事件
    public void onRegisterClick(View view) {
        // 获取输入数据
        String phone = inputPhone.getInputVal();
        String password = inputPassword.getInputVal();
        String passwordConfirm = inputPasswordConfirm.getInputVal();
        // 注册用户至realm
        boolean res = UserUtils.registerUser(this, phone, password, passwordConfirm);
        // 注册失败
        if (!res) return;
        // 注册成功
        onBackPressed();
    }
}
