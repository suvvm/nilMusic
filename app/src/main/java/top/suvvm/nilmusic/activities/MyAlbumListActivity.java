package top.suvvm.nilmusic.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MdfMusicListAdapter;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.http.AlbumClient;
import top.suvvm.nilmusic.http.MusicClient;
import top.suvvm.nilmusic.pojo.AddMusicRespModel;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.MusicModel;

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
        recyclerViewList.setNestedScrollingEnabled(false);  // 禁止滚动
        recyclerViewList.setAdapter(musicListAdapter);
    }

    public void onAddMusicClick (View view) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        final View inpView = inflater.inflate(R.layout.input_add_music, null);
        final Context context = this;
        new AlertDialog.Builder(this).setTitle("请输入音乐信息")
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
                        // music.setId("12345678");
                        try {
                            AddMusicRespModel resp = MusicClient.CreateMusic(albumId, music);
                            music.setId(resp.getMid().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.println(Log.ERROR, "onAddMusicClick", e.getMessage());
                        }
                        Log.println(Log.INFO, "onAddMusicClick", "create music success");
                        RealmHelp realmHelpInner = new RealmHelp();
                        realmHelpInner.updateMusicSource(albumId, music);
                        realmHelpInner.close();

//                        Thread thread = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                RealmHelp realmHelpInner = new RealmHelp();
//                                realmHelpInner.reloadMusicSource();
//                                realmHelpInner.close();
//                            }
//                        });
//                        thread.start();
//                        try {
//                            thread.join();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }).setNegativeButton("取消",null).show();
        // realmHelp.updateMusicSource();
    }

    // 销毁时关闭realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }
}
