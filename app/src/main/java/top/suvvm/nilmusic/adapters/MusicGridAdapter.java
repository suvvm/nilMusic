package top.suvvm.nilmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.AlbumListActivity;
import top.suvvm.nilmusic.pojo.AlbumModel;

/**
 * @ClassName: MusicGridAdapter
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/27 13:42
 */
public class MusicGridAdapter extends RecyclerView.Adapter<MusicGridAdapter.ViewHolder> {

    private Context context;
    private List<AlbumModel> dataSource;
    // 返回歌单中的一个元素
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_grid_music, parent, false));   // layout映射为view
    }

    @Override
    public void onBindViewHolder(@NonNull MusicGridAdapter.ViewHolder holder, int position) {
        final AlbumModel albumModel = dataSource.get(position);



        // 使用Clide将目标url图片资源显示在目标item的imageView上
        Glide.with(context).load(albumModel.getPoster())
                .into(holder.ivIcon);

        holder.tvPlayNum.setText(albumModel.getPlayNum());
        holder.tvName.setText(albumModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumListActivity.class);
                // 传递参数
                intent.putExtra(AlbumListActivity.ALBUM_ID, albumModel.getId());
                context.startActivity(intent);
            }
        });
    }

    // 歌单元素
    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivIcon;
        View itemView;
        TextView tvPlayNum, tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 获取item的imageView
            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvPlayNum = itemView.findViewById(R.id.tv_play_num);
            tvName = itemView.findViewById(R.id.tv_name);


        }
    }



    public MusicGridAdapter (Context context, List<AlbumModel> dataSource) {
        this.context = context;
        this.dataSource = dataSource;
    }




}
