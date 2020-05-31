package top.suvvm.nilmusic.migration;

import io.realm.DynamicRealm;
import io.realm.RealmList;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * @ClassName: Migration
 * @Description: TODO
 * @Author: SUVVM
 * @Date: 2020/5/31 21:39
 */
public class Migration  implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();
        // 第一次迁移
        if (oldVersion == 0) {

            // 告知realm发生变动的模型和字段
            schema.create("MusicModel")
                    .addField("musicId", String.class)
                    .addField("name", String.class)
                    .addField("poster", String.class)
                    .addField("path", String.class)
                    .addField("author", String.class);

            schema.create("AlbumModel")
                    .addField("albumId", String.class)
                    .addField("name", String.class)
                    .addField("poster", String.class)
                    .addField("playNum", String.class)
                    .addRealmListField("list", schema.get("MusicModel"));

            schema.create("MusicSourceModel")
                    .addRealmListField("album", schema.get("AlbumModel"))
                    .addRealmListField("hot", schema.get("MusicModel"));

            oldVersion = newVersion;
        }
    }
}
