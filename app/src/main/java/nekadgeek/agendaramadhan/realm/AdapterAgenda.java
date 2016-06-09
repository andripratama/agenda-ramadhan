package nekadgeek.agendaramadhan.realm;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import nekadgeek.agendaramadhan.MainActivity;
import nekadgeek.agendaramadhan.R;

/**
 * Created by AndriPratama on 6/9/2016.
 */
public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolder> {

    private RealmHelper helper;
    private final OnItemClickListener listener;
    private ArrayList<AgendaModel> agenda;


    public AdapterAgenda(ArrayList<AgendaModel> agenda, OnItemClickListener listener) {
        this.agenda = agenda;
        this.listener = listener;
    }


    @Override
    public AdapterAgenda.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda, null);
        ViewHolder vh = new ViewHolder(view);
        helper = new RealmHelper(parent.getContext());
        return vh;
    }


    @Override
    public void onBindViewHolder(final AdapterAgenda.ViewHolder holder, final int position) {
        holder.click(agenda.get(position), listener);
        holder.judul.setText(agenda.get(position).getJudul());
        holder.deskripsi.setText(agenda.get(position).getDeskripsi());
        holder.tanggal.setText(agenda.get(position).getTanggal());

        final int id = agenda.get(position).getId();

        //event button saat button done ditekan
        holder.btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteData(id); //delete data berdasarkan id

                //sintaks untuk menload activity
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return agenda.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, judul, deskripsi, tanggal;
        Button btnDone;


        public ViewHolder(View itemView) {
            super(itemView);
            judul = (TextView) itemView.findViewById(R.id.tvJudul);
            deskripsi = (TextView) itemView.findViewById(R.id.tvDeskripsi);
            tanggal = (TextView) itemView.findViewById(R.id.tvTanggal);
            btnDone = (Button) itemView.findViewById(R.id.btnDone);
        }


        public void click(final AgendaModel agendaModel, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(agendaModel);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(AgendaModel item);
    }


}

