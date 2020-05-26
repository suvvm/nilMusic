package top.suvvm.nilmusic.activities;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import top.suvvm.nilmusic.R;

public class BaseActivity extends Activity {
    private ImageView ivBack, ivMe;
    private TextView tvTitle;
    // 初始化导航栏函数 isShowBack控制是否显示返回 title控制标题 isShowMe控制是否显示个人
    protected void initNavigationBar(boolean isShowBack, String title, boolean isShowMe) {
        // 获取View
        ivBack = findViewById(R.id.iv_back);
        ivMe = findViewById(R.id.iv_me);
        tvTitle = findViewById(R.id.tv_title);
        // 设置导航栏可见性
        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        // 设置导航栏title文字
        tvTitle.setText(title);
        // 设置导航栏返回点击事件
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        // 设置导航栏个人主页点击事件
        ivMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaseActivity.this, MeActivity.class));
            }
        });
    }
}
