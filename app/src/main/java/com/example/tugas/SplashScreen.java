package com.example.tugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {

    //variabel firebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //method untuk menghilangkan Tag Bar / Title Bar
        getSupportActionBar().hide();

        //mengambil record di Firebase
        mAuth = FirebaseAuth.getInstance();

        //method untuk mengatur waktu menampilkan Splashscreen
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //mengecek apakah user sudah pernah login sebelumnya
                if (mAuth.getCurrentUser() != null){
                    //jika sudah maka akan langsung menampilkan MainActivity
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    //jika belum maka menampilkan Login
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                }

            }
        },3000);

    }
}