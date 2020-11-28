package top.suvvm.nilmusic.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MdfMusicListAdapter;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.pojo.AlbumModel;

public class MyAlbumListActivity extends BaseActivity {
    public static final String ALBUM_ID = "albumId";

    private RecyclerView recyclerViewList;
    private MdfMusicListAdapter musicListAdapter;
    private String albumId;
    private RealmHelp realmHelp;
    private AlbumModel albumModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_album_list);

        initData();
        initView();
    }

    // 初始化歌单数据
    private void initData() {
        albumId = getIntent().getStringExtra(ALBUM_ID);
        realmHelp = new RealmHelp();
        albumModel = realmHelp.getAlbum(albumId);
    }

    // 初始化View
    private void initView () {
        // 初始化导航栏
        initNavigationBar(true, "专辑列表", false);
        // 获取recyclerViewGrid 初始化歌曲列表
        recyclerViewList = findViewById(R.id.rv_list);
        recyclerViewList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));    // 分割线
        musicListAdapter = new MdfMusicListAdapter(this, null, albumModel.getList());
        recyclerViewList.setAdapter(musicListAdapter);
    }

    // 销毁时关闭realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }
}
