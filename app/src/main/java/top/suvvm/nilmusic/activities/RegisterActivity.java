package top.suvvm.nilmusic.activities;

import androidx.appcompat.app.AppCompatActivity;
import top.suvvm.nilmusic.R;

import android.os.Bundle;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }
    // 初始化view
    private void initView() {
        initNavigationBar(true, "注册", false);
    }
}
