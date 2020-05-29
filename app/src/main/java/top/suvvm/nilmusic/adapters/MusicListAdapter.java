package top.suvvm.nilmusic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;

/**
 * @ClassName: MusicListAdapter
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/27 17:40
 */
public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    private Context context;
    private View itemView;
    private RecyclerView recyclerView;
    private boolean isRvHeightSet;     // 表示recyclerView高度是否已经设定完毕

    public MusicListAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(context).inflate(R.layout.item_list_music, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (!isRvHeightSet)
            setRecyclerViewHeight();
        // 使用Clide将目标url图片资源显示在目标item的imageView上
        Glide.with(context).load("http://res.lgdsunday.club/poster-1.png")
                .into(holder.ivIcon);

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    // 计算RecyclerView高度
    private void setRecyclerViewHeight() {
        if (isRvHeightSet || recyclerView == null)
            return;
        isRvHeightSet = true;
        // 获取一个ItemView的高度
        RecyclerView.LayoutParams itemLayoutParams =  (RecyclerView.LayoutParams)itemView.getLayoutParams();
        // 获取ItemView数量
        int itemCnt = getItemCount();
        // 计算RecyclerView高度
        int recyclerViewHeight = itemLayoutParams.height * itemCnt;
        // 设置RecyclerView高度
        LinearLayout.LayoutParams recyclerViewLayoutParams = (LinearLayout.LayoutParams)recyclerView.getLayoutParams();
        recyclerViewLayoutParams.height = recyclerViewHeight;
        recyclerView.setLayoutParams(recyclerViewLayoutParams);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 获取item的imageView
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
