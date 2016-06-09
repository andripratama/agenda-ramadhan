package nekadgeek.agendaramadhan.realm;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by AndriPratama on 6/8/2016.
 */
public class RealmHelper {
    private static final String TAG = "RealmHelper";

    private Realm realm;
    private RealmResults<Agenda> realmResult;
    public Context context;

    public RealmHelper(Context context) {
        realm = Realm.getInstance(context);
        this.context = context;
    }

    //add data
    public void addAgenda(String judul, String deskripsi, String tanggal) {
        Agenda agenda = new Agenda();
        agenda.setId((int) (System.currentTimeMillis() / 1000));
        agenda.setJudul(judul);
        agenda.setDeskripsi(deskripsi);
        agenda.setTanggal(tanggal);

        realm.beginTransaction();
        realm.copyToRealm(agenda);
        realm.commitTransaction();

        showToast(judul + " berhasil disimpan!");
    }

    //read semua data dalam bentuk list
    public ArrayList<AgendaModel> findAllAgenda() {
        ArrayList<AgendaModel> listAgenda = new ArrayList<>();

        realmResult = realm.where(Agenda.class).findAll();
        realmResult.sort("id", Sort.DESCENDING);
        if (realmResult.size() > 0) {
            for (int i = 0; i < realmResult.size(); i++) {
                String judul, deskripsi, tanggal;
                int id = realmResult.get(i).getId();
                judul = realmResult.get(i).getJudul();
                deskripsi = realmResult.get(i).getDeskripsi();
                tanggal = realmResult.get(i).getTanggal();
                listAgenda.add(new AgendaModel(id, judul, deskripsi, tanggal));
            }
        } else {
            showToast("Database Kosong!");
        }
        return listAgenda;
    }

    //update data
    public void updateAgenda(int id, String judul, String deskripsi, String tanggal) {
        realm.beginTransaction();
        Agenda agenda = realm.where(Agenda.class).equalTo("id", id).findFirst();
        agenda.setJudul(judul);
        agenda.setDeskripsi(deskripsi);
        agenda.setTanggal(tanggal);

        showToast(judul + " berhasil diuppdate!");
    }

    //delete data
    public void deleteData(int id) {
        RealmResults<Agenda> dataDesults = realm.where(Agenda.class).equalTo("id", id).findAll();
        realm.beginTransaction();
        dataDesults.remove(0);
        dataDesults.removeLast();
        dataDesults.clear();
        realm.commitTransaction();

        showToast("Hapus data berhasil.");
    }

    private void showToast(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
