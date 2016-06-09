package nekadgeek.agendaramadhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import nekadgeek.agendaramadhan.realm.AdapterAgenda;
import nekadgeek.agendaramadhan.realm.AgendaModel;
import nekadgeek.agendaramadhan.realm.RealmHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static RecyclerView recyclerView;
    private static RealmHelper helper;
    private static AdapterAgenda adapter;
    private static ArrayList<AgendaModel> listAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listAgenda = new ArrayList<>();
        helper = new RealmHelper(MainActivity.this);

        recyclerView = (RecyclerView) findViewById(R.id.rvAgenda);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TambahAgenda.class);
                startActivity(intent);
            }
        });

        setRecyclerView();
    }

    public static void setRecyclerView() {
        try {
            listAgenda = helper.findAllAgenda();
            Log.d(TAG, "setRecyclerView: " + listAgenda);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AdapterAgenda adapter = new AdapterAgenda(listAgenda, new AdapterAgenda.OnItemClickListener() {
            @Override
            public void onClick(AgendaModel item) {

            }
        });
        recyclerView.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        try {
            listAgenda = helper.findAllAgenda();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
