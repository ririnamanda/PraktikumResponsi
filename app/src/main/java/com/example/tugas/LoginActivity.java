package com.example.tugas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email,pass;
    ProgressBar progressBar;
    Button login;
    TextView daftar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //inisialisasi setiap komponen View
        email = findViewById(R.id.emailLogin);
        pass = findViewById(R.id.passLogin);
        progressBar = findViewById(R.id.progresBar);
        daftar = findViewById(R.id.txRegis);
        login = findViewById(R.id.bLogin);

        mAuth = FirebaseAuth.getInstance();

        //funsi saat komponen daftar dipilih
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //memanggil activity Registrasi
                startActivity(new Intent(getApplicationContext(),RegisActivity.class));
                finish();
            }
        });

        //fungsi saat tombol login dipilih
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi untuk menampung data yang diInput
                String em = email.getText().toString().trim();
                String pas = pass.getText().toString().trim();

                //fungsi untuk mengkoreksi inputan
                if (TextUtils.isEmpty(em)){
                    email.setError("Masukkan Email!");
                    return;
                }

                if (TextUtils.isEmpty(pas)){
                    pass.setError("Masukkan Password!");
                    return;
                }

                if (pas.length() < 6){
                    pass.setError("Minimal 6 Karakter!");
                    return;
                }

                //menampilkan progresBar
                progressBar.setVisibility(View.VISIBLE);

                //fungsi untuk mengautentikasi akun user
                mAuth.signInWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login Berhasil!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(LoginActivity.this,"Error! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}