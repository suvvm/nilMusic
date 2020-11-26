package top.suvvm.nilmusic.activities;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.pojo.MusicModel;
import top.suvvm.nilmusic.utils.UserUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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

    // 自定义播放
    public void onCustomizeClick(View view) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        final View inpView = inflater.inflate(R.layout.input_customize, null);
        final Context context = this;
        new AlertDialog.Builder(this).setTitle("请输入音乐信息")
                .setView(inpView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        MusicModel musicModel = new MusicModel();
                        EditText etName, etAuthor, etPath, etPoster;
                        etName = inpView.findViewById(R.id.et_name);
                        etAuthor = inpView.findViewById(R.id.et_author);
                        etPath = inpView.findViewById(R.id.et_path);
                        etPoster = inpView.findViewById(R.id.et_poster);

                        musicModel.setId("1000000");
                        musicModel.setAuthor(etAuthor.getText().toString());
                        musicModel.setName(etName.getText().toString());
                        musicModel.setPath(etPath.getText().toString());
                        musicModel.setPoster(etPoster.getText().toString());

                        Intent intent = new Intent(context, PlayMusicActivity.class);
                        intent.putExtra(PlayMusicActivity.MUSIC_ID,1000000);
                        intent.putExtra(PlayMusicActivity.IS_CUSTOMIZE, true);
                        intent.putExtra(PlayMusicActivity.CUSTOMIZE_MODEL, musicModel);
                        context.startActivity(intent);

                    }
                }).setNegativeButton("取消",null).show();
    }

    // 退出登录
    public void onLogoutClick(View view) {
        UserUtils.logout(this);
    }
}
