package top.suvvm.nilmusic.views;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
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
    private ImageView ivIcon;

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

    private void init(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.play_music, this, false);
        ivIcon = view.findViewById(R.id.iv_icon);
        addView(view);

    }

    // 设置光盘中央音乐封面图片
    public void setMusicIcon(String icon) {
        Glide.with(context).load(icon).into(ivIcon);
    }
}
