package nekadgeek.agendaramadhan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Calendar;

import nekadgeek.agendaramadhan.realm.RealmHelper;

public class TambahAgenda extends AppCompatActivity {

    private EditText textJudulAgenda; // untuk edittext edit_text_judul
    private EditText textDeskAgenda; // untuk edittext edit_text_desk
    private EditText textTaggalAgenda; // untuk edittext edit_text_tanggal
    private ImageButton imageButtonTanggal; // untuk imageButton img_btn_tanggal
    private Button buttonSave; // untuk button btn_save

    private int year, month, day;
    private DatePicker datePicker;
    static final int DATE_DIALOG_ID = 999;

    private RealmHelper realmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_agenda);

        realmHelper = new RealmHelper(TambahAgenda.this);

        textJudulAgenda = (EditText) findViewById(R.id.edit_text_judul);
        textDeskAgenda = (EditText) findViewById(R.id.edit_text_desk);
        textTaggalAgenda = (EditText) findViewById(R.id.edit_text_tanggal);
        imageButtonTanggal = (ImageButton) findViewById(R.id.img_btn_tanggal);
        buttonSave = (Button) findViewById(R.id.btn_save);
        setCurrentDateOnView();
        addListenerOnImageButton();
        saveAgenda();

    }

    public void saveAgenda(){
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul, deskripsi, tanggal;
                 judul = textJudulAgenda.getText().toString();
                 deskripsi = textDeskAgenda.getText().toString();
                 tanggal = textTaggalAgenda.getText().toString();

                realmHelper.addAgenda(judul, deskripsi, tanggal);

                finish();
                Intent intent = new Intent(TambahAgenda.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setCurrentDateOnView() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        textTaggalAgenda.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void addListenerOnImageButton() {
        imageButtonTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            textTaggalAgenda.setText(new StringBuilder().append(day).append("/")
                    .append(month).append("/").append(year));
        }
    };

}
