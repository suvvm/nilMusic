package top.suvvm.nilmusic.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import top.suvvm.nilmusic.R;
import top.suvvm.nilmusic.adapters.MdfAlbumListAdapter;
import top.suvvm.nilmusic.helps.RealmHelp;
import top.suvvm.nilmusic.helps.UserHelp;
import top.suvvm.nilmusic.http.AlbumClient;
import top.suvvm.nilmusic.pojo.AlbumModel;
import top.suvvm.nilmusic.pojo.CreateAlbumRespModel;
import top.suvvm.nilmusic.pojo.MusicModel;
import top.suvvm.nilmusic.pojo.MusicSourceModel;
import top.suvvm.nilmusic.utils.DataUtils;
import top.suvvm.nilmusic.utils.UserUtils;

public class MdfAlbumActivity extends BaseActivity {

    private RecyclerView recyclerViewAlbumList;
    private MdfAlbumListAdapter albumListAdapter;
    private RealmHelp realmHelp;
    private MusicSourceModel musicSourceModel;
    private ImageView ivAdd;

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
        recyclerViewAlbumList.setNestedScrollingEnabled(false);  // 禁止滚动
        albumListAdapter = new MdfAlbumListAdapter(this, recyclerViewAlbumList, musicSourceModel.getSelf());
        recyclerViewAlbumList.setAdapter(albumListAdapter);

    }

    public void onAddAlbumClick (View view) {
        final LayoutInflater inflater = LayoutInflater.from(this);
        final View inpView = inflater.inflate(R.layout.input_customize, null);
        final Context context = this;
        new AlertDialog.Builder(this).setTitle("请输入专辑信息")
                .setView(inpView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        EditText etName, etPlayNum, etPoster;
                        etName = inpView.findViewById(R.id.et_name);
                        etPlayNum = inpView.findViewById(R.id.et_play_num);
                        etPoster = inpView.findViewById(R.id.et_poster);
                        String name, poster, playNum;
                        name = etName.getText().toString();
                        poster = etPoster.getText().toString();
                        playNum = etPlayNum.getText().toString();
                        if ("".equals(name) || "".equals(poster) || "".equals(playNum)) {
                            return;
                        }
                        AlbumModel album = new AlbumModel();
                        album.setName(name);
                        album.setPoster(poster);
                        album.setPlayNum(playNum);
                        try {
                            CreateAlbumRespModel resp = AlbumClient.CreateAlbum(album, Integer.valueOf(UserHelp.getInstance().getId()));
                            album.setId(resp.getAid().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Log.println(Log.ERROR, "onAddAlbumClick", e.getMessage());
                        }
                        RealmHelp realmHelpInner = new RealmHelp();
                        realmHelpInner.updateAlbumSource(album);
                        realmHelpInner.close();
                    }
                }).setNegativeButton("取消",null).show();
        // startActivity(getIntent());
    }

    // 销毁时关闭realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realmHelp.close();
    }

}
