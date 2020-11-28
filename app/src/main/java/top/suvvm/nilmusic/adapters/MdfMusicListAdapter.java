package top.suvvm.nilmusic.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.PlayMusicActivity;
import top.suvvm.nilmusic.pojo.MusicModel;

public class MdfMusicListAdapter extends RecyclerView.Adapter<MdfMusicListAdapter.ViewHolder> {
    private Context context;
    private View itemView;
    private RecyclerView recyclerView;
    private boolean isRvHeightSet;     // 表示recyclerView高度是否已经设定完毕
    private List<MusicModel> dataSource;

    public MdfMusicListAdapter(Context context, RecyclerView recyclerView, List<MusicModel> dataSource) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public MdfMusicListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(context).inflate(R.layout.item_list_mdf_music, parent, false);
        return new MdfMusicListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MdfMusicListAdapter.ViewHolder holder, int position) {
        final MusicModel musicModel = dataSource.get(position);

        if (!isRvHeightSet)
            setRecyclerViewHeight();
        // 使用Clide将目标url图片资源显示在目标item的imageView上
        Glide.with(context).load(musicModel.getPoster())
                .into(holder.ivIcon);
        holder.tvName.setText(musicModel.getName());
        holder.tvAuthor.setText(musicModel.getAuthor());
        // 修改音乐
        holder.ivMdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PlayMusicActivity.class);
//                intent.putExtra(PlayMusicActivity.MUSIC_ID, musicModel.getId());
//                context.startActivity(intent);
            }
        });
        // 删除
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, PlayMusicActivity.class);
//                intent.putExtra(PlayMusicActivity.MUSIC_ID, musicModel.getId());
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
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

        ImageView ivIcon, ivDelete, ivMdf;
        View itemView;
        TextView tvName, tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            // 获取item的imageView
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            ivMdf = itemView.findViewById(R.id.iv_mdf);
        }
    }
}
