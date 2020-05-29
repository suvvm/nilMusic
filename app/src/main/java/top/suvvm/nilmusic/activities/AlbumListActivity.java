package top.suvvm.nilmusic.activities;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MusicListAdapter;

import android.os.Bundle;

public class AlbumListActivity extends BaseActivity {

    private RecyclerView recyclerViewList;
    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);

        initView();
    }


    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(true, "专辑列表", false);
        // 获取recyclerViewGrid 初始化歌曲列表
        recyclerViewList = findViewById(R.id.rv_list);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));    // 分割线
        musicListAdapter = new MusicListAdapter(this, null);
        recyclerViewList.setAdapter(musicListAdapter);
    }
}
