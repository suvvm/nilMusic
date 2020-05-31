package top.suvvm.nilmusic;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import io.realm.Realm;
import top.suvvm.nilmusic.helps.RealmHelp;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        // 初始化Realm数据库
        Realm.init(this);
        // 初始化Realm完成后检查是否需要数据迁移
        RealmHelp.migration();
    }
}
