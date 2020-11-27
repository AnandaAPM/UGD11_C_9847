package com.pbp.gd11_c_9847.ui.pdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pbp.gd11_c_9847.R;
import com.pbp.gd11_c_9847.UserDAO;

import java.util.List;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {
    private Context context;
    private View view;
    private List<UserDAO> mhs;
    public static final String BASE_URL ="https://pbp.pelangidb.com/images/";

    public MahasiswaAdapter(List<UserDAO> mhs, Context context){
        this.mhs=mhs;
        this.context=context;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.adapter_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder holder, int position) {
        final UserDAO listMahasiswa = mhs.get ( position );
        String harga = "Rp"+Double.toString ( listMahasiswa.getHarga () );
        holder.txtNo.setText ( listMahasiswa.getNama () );
        holder.txtNama.setText ( listMahasiswa.getPengarang () );
        holder.txtNIM.setText ( harga);
        Glide.with ( context )
                .load ( BASE_URL+listMahasiswa.getGambar () )
                .diskCacheStrategy ( DiskCacheStrategy.NONE )
                .skipMemoryCache ( true )
                .into ( holder.imageView );


    }

    @Override
    public int getItemCount() {
        return mhs.size ();
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNo, txtNama, txtNIM;
        ImageView imageView;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNo=itemView.findViewById(R.id.tvNomorMahasiswa);
            txtNama=itemView.findViewById(R.id.tvNamaMahasiswa);
            txtNIM=itemView.findViewById(R.id.tvNIMMAhasiswa);
            imageView = itemView.findViewById ( R.id.gambar );
        }
    }



}
