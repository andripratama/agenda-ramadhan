package nekadgeek.agendaramadhan.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AndriPratama on 6/8/2016.
 */
public class Agenda extends RealmObject {
    @PrimaryKey
    private int id;
    private String judul;
    private String deskripsi;
    private String tanggal;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getJudul() { return judul; }

    public void setJudul(String judul) { this.judul = judul; }

    public String getDeskripsi() { return deskripsi; }

    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getTanggal() { return tanggal; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }
}
