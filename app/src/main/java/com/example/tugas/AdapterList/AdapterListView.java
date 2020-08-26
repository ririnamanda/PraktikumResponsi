package com.example.tugas.AdapterList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas.DetailActivity;
import com.example.tugas.ModelData.CountryInfo;
import com.example.tugas.ModelData.DataObject;
import com.example.tugas.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterListView extends RecyclerView.Adapter<AdapterListView.ViewHolder> {

    Context context;
    List<DataObject> objectList;

    public AdapterListView(Context context, List<DataObject> objectList) {
        this.context = context;
        this.objectList = objectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inisialisasi layout untuk menampilkan data
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //inisialisasi Model data sesuai respon dari API
        CountryInfo countryInfo = objectList.get(position).getCountryInfo();
        String url = countryInfo.getFlag();

        //menampilkan data yang didapatkan pada setiap elemen View / Tampilan
        holder.nama.setText(objectList.get(position).getCountry());
        Picasso.get().load(url).into(holder.flag);

        //fungsi untuk menjalankan perintah saat List item dipilih atau diclick
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi activity baru
                Intent intent = new Intent(context, DetailActivity.class);
                //fungsi untuk membawa data yang telah dipilih ke Activity yang dituju
                intent.putExtra("Nama",objectList.get(position).getCountry());
                intent.putExtra("Flag",url);
                intent.putExtra("Sembuh",objectList.get(position).getRecovered());
                intent.putExtra("Kasus",objectList.get(position).getCases());
                intent.putExtra("Meninggal",objectList.get(position).getDeaths());
                //mamanggil activity
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama;
        ImageView flag;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            nama = itemView.findViewById(R.id.namaCountry);
            flag = itemView.findViewById(R.id.ic_flag);
        }
    }
}
