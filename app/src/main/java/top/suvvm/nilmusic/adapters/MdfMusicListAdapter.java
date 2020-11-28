package top.suvvm.nilmusic.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.activities.PlayMusicActivity;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.http.AlbumClient;
import top.suvvm.nilmusic.http.MusicClient;
import top.suvvm.nilmusic.pojo.AddMusicRespModel;
import top.suvvm.nilmusic.pojo.MusicModel;

public class MdfMusicListAdapter extends RecyclerView.Adapter<MdfMusicListAdapter.ViewHolder> {
    private Context context;
    private View itemView;
    private RecyclerView recyclerView;
    private boolean isRvHeightSet;     // 表示recyclerView高度是否已经设定完毕
    private List<MusicModel> dataSource;
    private String albumID;

    public MdfMusicListAdapter(Context context, RecyclerView recyclerView, List<MusicModel> dataSource, String albumID) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.dataSource = dataSource;
        this.albumID = albumID;
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
                final LayoutInflater inflater = LayoutInflater.from(context);
                final View inpView = inflater.inflate(R.layout.input_add_music, null);
                new AlertDialog.Builder(context).setTitle("请输入修改音乐信息")
                        .setView(inpView)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                EditText etName, etPoster, etPath, etAuthor;
                                etName = inpView.findViewById(R.id.et_name);
                                etPoster = inpView.findViewById(R.id.et_poster);
                                etPath = inpView.findViewById(R.id.et_path);
                                etAuthor = inpView.findViewById(R.id.et_author);
                                String name, poster, path, author;
                                name = etName.getText().toString();
                                poster = etPoster.getText().toString();
                                path = etPath.getText().toString();
                                author = etAuthor.getText().toString();
                                if ("".equals(name) || "".equals(poster) || "".equals(path) || "".equals(author) ) {
                                    return;
                                }
                                MusicModel music = new MusicModel();
                                music.setName(name);
                                music.setPoster(poster);
                                music.setPath(path);
                                music.setAuthor(author);
                                music.setId(musicModel.getId());
                                try {
                                    MusicClient.MdfMusic(music);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                RealmHelp realmHelpInner = new RealmHelp();
                                realmHelpInner.updateMusicInfo(music);
                                realmHelpInner.close();
                            }
                        }).setNegativeButton("取消",null).show();
            }
        });
        // 删除
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("请确定是否删除目标")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try {
                                    MusicClient.DelMusic(Integer.valueOf(albumID), Integer.valueOf(musicModel.getId()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                RealmHelp realmHelp = new RealmHelp();
                                realmHelp.deleteMusic(musicModel.getId());
                                realmHelp.close();
                            }
                        }).setNegativeButton("取消",null).show();
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
