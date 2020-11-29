package top.suvvm.nilmusic.activities;

import jp.wasabeef.glide.transformations.BlurTransformation;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.helps.MediaPlayerHelp;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.MusicModel;
import top.suvvm.nilmusic.views.PlayMusicView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class PlayMusicActivity extends BaseActivity {

    public static final String MUSIC_ID = "musicId";
    public static final String ALBUM_ID = "albumId";
    public static final String IS_CUSTOMIZE = "isCustomize";
    public static final String CUSTOMIZE_MODEL = "customizeModel";

    private ImageView bgImageView, ivPre, ivNext;
    private PlayMusicView playMusicView;
    private TextView tvName, tvAuthor;
    private String musicId;
    private MusicModel musicModel;
    private RealmHelp realmHelp;
    private SeekBar sbMusic;
    private MediaPlayerHelp mediaPlayerHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        // 隐藏statusBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initData();
        initView();

    }

    // 初始化音乐数据
    private void initData() {
        if (getIntent().getBooleanExtra(IS_CUSTOMIZE,false)) {
            realmHelp = new RealmHelp();
            musicModel = (MusicModel)getIntent().getSerializableExtra(CUSTOMIZE_MODEL);
            return;
        }
        musicId = getIntent().getStringExtra(MUSIC_ID);
        realmHelp = new RealmHelp();
        musicModel = realmHelp.getMusic(musicId);
    }

    private void initView() {
        // 获取背景图片imageView
        bgImageView = findViewById(R.id.iv_bg);
        ivPre = findViewById(R.id.iv_pre);
        ivNext = findViewById(R.id.iv_next);
        // 获取音乐名TextView
        tvName = findViewById(R.id.tv_name);
        // 获取作者TextView
        tvAuthor = findViewById(R.id.tv_author);

        sbMusic = (SeekBar)findViewById(R.id.sb_music);
        MediaPlayerHelp.sbMusic = sbMusic;
        // glide-transformations 高斯模糊
        // 将transformation封装至RequestOptions  BlurTransformation 传入模糊程度 与 bitmap宽高比（传入10代表被处理后的图片宽高为原图的十分之一）
        Glide.with(this).load(musicModel.getPoster())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(bgImageView);
        tvName.setText(musicModel.getName());
        tvAuthor.setText(musicModel.getAuthor());
        // 初始化音乐播放

        playMusicView = findViewById(R.id.play_music_view);
        playMusicView.setMusicIcon(musicModel.getPoster());
        playMusicView.playMusic(musicModel.getPath());
        //给进度条设置监听(实现快进功能)
        sbMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //首先获取seekbar拖动后的位置
                int progress = sbMusic.getProgress();
                //跳转到某个位置播放
                playMusicView.seekTo(progress);
            }
        });
    }

    public void onNextClick(View view) {
        toNextMusic();
    }

    public void toNextMusic() {
        String albumID = getIntent().getStringExtra(ALBUM_ID);
        if (albumID == null) {
            return;
        }
        AlbumModel album = realmHelp.getAlbum(albumID);
        String newMusicID = "";
        for (int i = 0; i < album.getList().size(); i++) {
            if (album.getList().get(i).getId().equals(musicId)){
                if (i + 1 < album.getList().size()) {
                    newMusicID = album.getList().get(i + 1).getId();
                } else {
                    newMusicID = album.getList().get(0).getId();
                }
                break;
            }
        }
        if ("".equals(newMusicID)) {
            return;
        }
        musicId = newMusicID;
        musicModel = realmHelp.getMusic(newMusicID);
        initView();
    }

    public void onPreClick(View view) {
       toPreMusic();
    }

    public void toPreMusic() {
        String albumID = getIntent().getStringExtra(ALBUM_ID);
        if (albumID == null) {
            return;
        }
        AlbumModel album = realmHelp.getAlbum(albumID);
        String newMusicID = "";
        for (int i = 0; i < album.getList().size(); i++) {
            if (album.getList().get(i).getId().equals(musicId)){
                if (i - 1 >= 0) {
                    newMusicID = album.getList().get(i - 1).getId();
                } else {
                    newMusicID = album.getList().get(album.getList().size() - 1).getId();
                }
                break;
            }
        }
        if ("".equals(newMusicID)) {
            return;
        }
        musicId = newMusicID;
        musicModel = realmHelp.getMusic(newMusicID);
        initView();
    }

    // 后退按钮点击事件
    public void onBackClick(View view) {
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }
}
