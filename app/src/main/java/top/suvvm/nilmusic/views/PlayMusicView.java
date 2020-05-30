package top.suvvm.nilmusic.views;

import android.content.Context;
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
    }

    // 切换播放状态
    private void trigger() {
        if (isPlaying) {
            stopMusic();
        } else {
            playMusic();;
        }
    }

    // 播放音乐
    public void playMusic() {
        // 使用startAnimation 执行动画
        // 执行碟片旋转
        playMusicLayout.startAnimation(playMusicAnim);
        // 执行指针旋转至碟片
        ivNeedle.startAnimation(playNeedleAnim);
        // 隐藏播放按钮
        ivPlay.setVisibility(View.GONE);
        // 改变播放状态
        isPlaying = true;
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
    }

    // 设置光盘中央音乐封面图片
    public void setMusicIcon(String icon) {
        Glide.with(context).load(icon).into(ivIcon);
    }
}
