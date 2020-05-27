package top.suvvm.nilmusic.activities;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MusicGridAdapter;
import top.suvvm.nilmusic.adapters.MusicListAdapter;
import top.suvvm.nilmusic.views.GridSpaceItemDecoration;

import android.os.Bundle;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerViewGrid, recyclerViewList;
    private MusicGridAdapter musicGridAdapter;
    private MusicListAdapter musicListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(false, "nilMusic", true);
        // 获取recyclerViewGrid 初始化歌单网格
        recyclerViewGrid = findViewById(R.id.rv_grid);
        // 每行3个元素
        recyclerViewGrid.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerViewGrid.addItemDecoration(new GridSpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.albumMarginSize), recyclerViewGrid));
        recyclerViewGrid.setNestedScrollingEnabled(false);  // 禁止滚动
        musicGridAdapter = new MusicGridAdapter(this);
        recyclerViewGrid.setAdapter(musicGridAdapter);
        // 获取recyclerViewGrid 初始化歌曲列表
        recyclerViewList = findViewById(R.id.rv_list);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));    // 分割线
        recyclerViewList.setNestedScrollingEnabled(false);  // 禁止滚动
        musicListAdapter = new MusicListAdapter(this, recyclerViewList);
        recyclerViewList.setAdapter(musicListAdapter);
    }
}
