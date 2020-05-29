package top.suvvm.nilmusic.activities;

import jp.wasabeef.glide.transformations.BlurTransformation;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.views.PlayMusicView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PlayMusicActivity extends BaseActivity {

    private ImageView bgImageView;
    private PlayMusicView playMusicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // 隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initView();

    }

    private void initView() {
        // 获取背景图片imageView
        bgImageView = findViewById(R.id.iv_bg);
        // glide-transformations 高斯模糊
        // 将transformation封装至RequestOptions  BlurTransformation 传入模糊程度 与 bitmap宽高比（传入10代表被处理后的图片宽高为原图的十分之一）
        Glide.with(this).load("http://res.lgdsunday.club/poster-1.png")
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(bgImageView);
        // 初始化音乐播放
        playMusicView = findViewById(R.id.play_music_view);
        playMusicView.setMusicIcon("http://res.lgdsunday.club/poster-1.png");
    }

    // 后退按钮点击事件
    public void onBackClick(View view) {
        onBackPressed();
    }
}
