package top.suvvm.nilmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.AlbumListActivity;

/**
 * @ClassName: MusicGridAdapter
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/27 13:42
 */
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private Context context;

    // 返回歌单中的一个元素
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_grid_music, parent, false));   // layout映射为view
    }

    @Override
    public void onBindViewHolder(@NonNull MusicGridAdapter.ViewHolder holder, int position) {
        // 使用Clide将目标url图片资源显示在目标item的imageView上
        Glide.with(context).load("http://res.lgdsunday.club/poster-1.png")
                .into(holder.ivIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumListActivity.class);
                context.startActivity(intent);
            }
        });
    }

    // 歌单元素
    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 获取item的imageView
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }



    public MusicGridAdapter (Context context) {
        this.context = context;
    }




}
