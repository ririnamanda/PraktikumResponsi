package com.example.tugas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tugas.API.Api;
import com.example.tugas.AdapterList.AdapterListView;
import com.example.tugas.ModelData.DataObject;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //variable List
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi variabel list
        recyclerView = findViewById(R.id.rList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.pgM);

        progressBar.setVisibility(View.VISIBLE);

        //method untuk mengambil data
        getData();
    }

    public void getData() {

        //method mengambil api untuk mendapatkan data
        Call<List<DataObject>> call = Api.getInstance().getApi().getCountries();
        call.enqueue(new Callback<List<DataObject>>() {
            @Override
            public void onResponse(Call<List<DataObject>> call, Response<List<DataObject>> response) {
                recyclerView.setAdapter(new AdapterListView(MainActivity.this,response.body()));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DataObject>> call, Throwable t) {
                //menampilkan pesan Toast saat gagal mengambil data
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    //method untuk menjalankan menuBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inisialisasi menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu,menu);
        return true;
    }

    //method perintah saat item menu dipilih / diclick
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //fungsi untuk logout
        FirebaseAuth.getInstance().signOut();

        //memanggil activity Login
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));

        //menghentikan MainActivity agar tidak terjadi penumpukan
        finish();
        return super.onOptionsItemSelected(item);
    }
}