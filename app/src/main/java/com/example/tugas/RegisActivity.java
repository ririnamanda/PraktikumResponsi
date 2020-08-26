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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisActivity extends AppCompatActivity {

    EditText email,pass,repass;
    Button regis;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        //inisialisasi setiap komponen View
        email = findViewById(R.id.emailReg);
        pass = findViewById(R.id.passReg);
        repass = findViewById(R.id.repassReg);
        progressBar = findViewById(R.id.pBRegis);
        regis = findViewById(R.id.bRegis);

        mAuth = FirebaseAuth.getInstance();

        //funsi saat tombol regis diclick
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //inisialisasi variabel untuk menampung data inputan
                String em = email.getText().toString().trim();
                String pas = pass.getText().toString().trim();
                String rePas = repass.getText().toString().trim();

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

                if (TextUtils.isEmpty(rePas)){
                    repass.setError("Masukkan Password!");
                    return;
                }

                if (!rePas.equals(pas)){
                    repass.setError("Password Salah!");
                    return;
                }

                //menampilkan progresBar
                progressBar.setVisibility(View.VISIBLE);

                //fungsi untuk mendaftarkan akun user
                mAuth.createUserWithEmailAndPassword(em,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){//jika berhasil
                            Toast.makeText(RegisActivity.this,"Regis Berhasil!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {//jika gagal
                            Toast.makeText(RegisActivity.this,"Error! "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
}