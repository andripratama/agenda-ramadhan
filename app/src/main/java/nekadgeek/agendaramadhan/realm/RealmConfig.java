package nekadgeek.agendaramadhan.realm;

import android.app.Application;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by AndriPratama on 6/8/2016.
 */
public class RealmConfig extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .schemaVersion(0)
                .migration(new DataMigration())
                .build();

        Realm.setDefaultConfiguration(configuration);
    }

    private class DataMigration implements RealmMigration{

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            RealmSchema schema = realm.getSchema();
            if (oldVersion == 0 ){
                schema.create("Agenda")
                        .addField("id", int.class)
                        .addField("judul", String.class)
                        .addField("deskripsi", String.class)
                        .addField("tanggal", String.class);
                oldVersion++;
            }
        }
    }
}
