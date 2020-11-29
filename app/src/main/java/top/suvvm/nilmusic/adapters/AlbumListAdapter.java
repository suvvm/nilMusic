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
import top.suvvm.nilmusic.activities.AlbumListActivity;
import top.suvvm.nilmusic.pojo.AlbumModel;


public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {
    private Context context;
    private View itemView;
    private RecyclerView recyclerView;
    private boolean isRvHeightSet;     // 表示recyclerView高度是否已经设定完毕
    private List<AlbumModel> dataSource;

    public AlbumListAdapter(Context context, RecyclerView recyclerView, List<AlbumModel> dataSource) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public AlbumListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(context).inflate(R.layout.item_list_music, parent, false);
        return new AlbumListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumListAdapter.ViewHolder holder, int position) {
        final AlbumModel albumModel = dataSource.get(position);

        if (!isRvHeightSet)
            setRecyclerViewHeight();
        // 使用Clide将目标url图片资源显示在目标item的imageView上
        Glide.with(context).load(albumModel.getPoster())
                .into(holder.ivIcon);
        holder.tvName.setText(albumModel.getName());
        holder.tvAuthor.setText(albumModel.getPlayNum());
        // 为每个音乐元素注册点击事件
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
        ImageView ivIcon;
        View itemView;
        TextView tvName, tvAuthor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            // 获取item的imageView
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
        }
    }
}
