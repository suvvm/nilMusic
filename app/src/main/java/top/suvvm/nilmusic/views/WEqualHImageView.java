package top.suvvm.nilmusic.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * @ClassName: WEqualHImageView
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/27 15:09
 */
public class WEqualHImageView extends androidx.appcompat.widget.AppCompatImageView {
    public WEqualHImageView(Context context) {
        super(context);
    }

    public WEqualHImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WEqualHImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    // 宽高相同
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        // 获取ImageView宽度
        // int width = MeasureSpec.getSize(widthMeasureSpec);
        // 获取ImageView模式 match_parent、web_content、dp
        // int mode = MeasureSpec.getMode(widthMeasureSpec);

    }
}
