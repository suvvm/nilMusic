package top.suvvm.nilmusic.helps;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * @ClassName: MediaPlayerHelp
 * @Description: 单例
 * @Author: SUVVM
 * @Date: 2020/5/30 14:01
 */
public class MediaPlayerHelp {
    // 在静态域中声明单例的实例对象
    private volatile static MediaPlayerHelp instance;

    private Context context;
    private MediaPlayer mediaPlayer;
    private OnMeidaPlayerHelperListener onMeidaPlayerHelperListener;
    private String path;
    public void setOnMeidaPlayerHelperListener(OnMeidaPlayerHelperListener onMeidaPlayerHelperListener) {
        this.onMeidaPlayerHelperListener = onMeidaPlayerHelperListener;
    }

    // private 构造器
    private MediaPlayerHelp(Context context) {
        this.context = context;
        mediaPlayer = new MediaPlayer();
    }

    // 获取当前播放音乐路径
    public String getPath() {
        return path;
    }

    // setPath 指定播放音乐地址
    public void setPath(String path) {

        // 判断音乐是否正在播放 或是否更换音乐
        if (mediaPlayer.isPlaying() || !this.path.equals(path)) {
            mediaPlayer.reset();    // 正在播放或更换音乐则重置播放状态
        }
        this.path = path;
        // 设置播放音乐路径
        try {
            mediaPlayer.setDataSource(context, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 准备播放
        mediaPlayer.prepareAsync(); // 异步加载音乐
        // 捕获异步加载完成通知
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (onMeidaPlayerHelperListener != null) {
                    onMeidaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }

    public interface OnMeidaPlayerHelperListener {
        void onPrepared(MediaPlayer mp);
    }

    // start 播放音乐
    public void start () {
        if (mediaPlayer.isPlaying())
            return;
        mediaPlayer.start();
    }
    // pause 暂停播放
    public void pause() {
        if (!mediaPlayer.isPlaying())
            return;
        mediaPlayer.pause();
    }

    // 加锁的访问该实例的方法
    public static MediaPlayerHelp getInstance(Context context) {
        // 检验单例实例是否创建
        if (instance == null) {
            synchronized(MediaPlayerHelp.class) {
                // 进入同步代码块后再次检验
                if (instance == null) {
                    instance = new MediaPlayerHelp(context);
                }
            }
        }
        return instance;
    }

}
