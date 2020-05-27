package top.suvvm.nilmusic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;

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

    }

    // 歌单元素
    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }



    public MusicGridAdapter (Context context) {
        this.context = context;
    }




}
