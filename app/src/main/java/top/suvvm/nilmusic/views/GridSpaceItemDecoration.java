package top.suvvm.nilmusic.views;

import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: GridSpaceItemDecoration
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/27 15:38
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private Integer space;
    public GridSpaceItemDecoration(int space, RecyclerView parent) {
        this.space = space;
        getRecyclerViewOffsets(parent);
    }

    /**
     * 设置ItemView的偏移量（实现分界线）
     * @param outRect Item矩形边界
     * @param view  ItemView
     * @param parent  RecyclerView
     * @param state RecyclerView状态
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        // 向左偏移space
        outRect.left = space;
        // 判断item是否为每行第一个元素 每行3个 行不通，便宜不一致导致高度不一致
//        if (parent.getChildLayoutPosition(view) % 3 == 0) {
//            outRect.left = 0;
//        }

    }
    // 使用margin负值消除每行第一个元素前的边界
    private void getRecyclerViewOffsets(RecyclerView parent) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -space;
        parent.setLayoutParams(layoutParams);
    }
}
