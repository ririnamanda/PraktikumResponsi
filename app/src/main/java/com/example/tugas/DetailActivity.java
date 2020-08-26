package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas.API.Api;
import com.example.tugas.ModelData.DataObject;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView nama,semb,posi,meni;

    private List<DataObject> objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageDet);
        nama = findViewById(R.id.namaDet);
        semb = findViewById(R.id.txsemb);
        posi = findViewById(R.id.txposi);
        meni = findViewById(R.id.txmeni);

        //menangkap data yang dibawa dari activity sebelumnya
        Intent intent = getIntent();
        String namaC = intent.getStringExtra("Nama");
        String flag = intent.getStringExtra("Flag");
        String sem = intent.getStringExtra("Sembuh");
        String kas = intent.getStringExtra("Kasus");
        String men = intent.getStringExtra("Meninggal");

        //menampilkan data yang dibawa
        Picasso.get().load(flag).into(imageView);
        nama.setText(namaC);
        semb.setText("Sembuh\t:\t"+sem+"\tJiwa");
        posi.setText("Kasus\t:\t"+kas+"\tJiwa");
        meni.setText("Meninggal\t:\t"+men+"\tJiwa");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}