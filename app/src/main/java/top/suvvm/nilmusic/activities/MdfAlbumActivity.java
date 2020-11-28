package top.suvvm.nilmusic.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MdfAlbumListAdapter;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.pojo.MusicSourceModel;

public class MdfAlbumActivity extends BaseActivity {

    private RecyclerView recyclerViewAlbumList;
    private MdfAlbumListAdapter albumListAdapter;
    private RealmHelp realmHelp;
    private MusicSourceModel musicSourceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mdf_album);

        initData();
        initView();
    }

    // 初始化音乐源数据
    private void initData() {
        realmHelp = new RealmHelp();
        musicSourceModel = realmHelp.getMusicSource();
    }

    // 初始化View
    private void initView() {
        // 初始化导航栏
        initNavigationBar(true, "nilMusic", false);

        // 获取recyclerViewGrid 初始化专辑列表
        recyclerViewAlbumList = findViewById(R.id.rv_list);
        recyclerViewAlbumList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlbumList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));    // 分割线
        albumListAdapter = new MdfAlbumListAdapter(this, recyclerViewAlbumList, musicSourceModel.getSelf());
        recyclerViewAlbumList.setAdapter(albumListAdapter);

    }

    // 销毁时关闭realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }

}
