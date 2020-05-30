package top.suvvm.nilmusic.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.helps.MediaPlayerHelp;

/**
 * @ClassName: PlayMusicView
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/29 22:19
 */
public class PlayMusicView extends FrameLayout {

    private Context context;
    private View view;
    private ImageView ivIcon, ivNeedle, ivPlay;
    private Animation playMusicAnim, playNeedleAnim, stopNeedleAnim;
    private FrameLayout playMusicLayout;
    private boolean isPlaying;
    private MediaPlayerHelp mediaPlayerHelp;
    private String path;

    public PlayMusicView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    // api21以上才会调用
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PlayMusicView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    // 初始化音乐播放组件
    private void init(Context context) {
        // MediaPlayer
        this.context = context;
        // 获取各个组件
        view = LayoutInflater.from(context).inflate(R.layout.play_music, this, false);
        playMusicLayout = view.findViewById(R.id.fl_play_music);
        ivNeedle = view.findViewById(R.id.iv_needle);
        ivIcon = view.findViewById(R.id.iv_icon);
        ivPlay = view.findViewById(R.id.iv_play);
        // 定义执行动画 光盘转动 指针移向光盘 指针离开光盘
        playMusicAnim = AnimationUtils.loadAnimation(context,R.anim.play_music_anim);
        playNeedleAnim = AnimationUtils.loadAnimation(context, R.anim.play_needle_anim);
        stopNeedleAnim = AnimationUtils.loadAnimation(context,R.anim.stop_needle_anim);
        // 设置播放音乐组件点击事件
        playMusicLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trigger();
            }
        });
        addView(view);
        // 获取MediaPlayerHelp实例
        mediaPlayerHelp = MediaPlayerHelp.getInstance(context);
    }

    // 切换播放状态
    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic(path);;
        }
    }

    // 播放音乐
    public void playMusic(String path) {
        // 记录当前音乐路径
        this.path = path;
        // 使用startAnimation 执行动画
        // 执行碟片旋转
        playMusicLayout.startAnimation(playMusicAnim);
        // 执行指针旋转至碟片
        ivNeedle.startAnimation(playNeedleAnim);
        // 隐藏播放按钮
        ivPlay.setVisibility(View.GONE);
        // 改变播放状态
        isPlaying = true;
        // 判断当前音乐是否已经播放
        if (path.equals(mediaPlayerHelp.getPath())) {
            // 如果当前音乐已经在播放，直接执行start继续播放当前音乐
            mediaPlayerHelp.start();
        } else {
            // 如果当前播放的音乐并未播放，调用setPath设置mediaPlay状态
            mediaPlayerHelp.setPath(path);
            // 监听，准备完成后调用start方法
            mediaPlayerHelp.setOnMeidaPlayerHelperListener(new MediaPlayerHelp.OnMeidaPlayerHelperListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayerHelp.start();
                }
            });
        }



    }

    // 停止播放
    public void stopMusic() {
        // 删除碟片动画效果
        playMusicLayout.clearAnimation();
        // 执行执行指针旋转出碟片
        ivNeedle.startAnimation(stopNeedleAnim);
        // 显示播放按钮
        ivPlay.setVisibility(View.VISIBLE);
        // 改变播放状态
        isPlaying = false;
        // 暂停播放
        mediaPlayerHelp.pause();
    }

    // 设置光盘中央音乐封面图片
    public void setMusicIcon(String icon) {
        Glide.with(context).load(icon).into(ivIcon);
    }
}
