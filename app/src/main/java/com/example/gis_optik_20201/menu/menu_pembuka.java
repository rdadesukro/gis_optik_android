package com.example.gis_optik_20201.menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gis_optik_20201.R;
import com.github.squti.guru.Guru;

public class menu_pembuka extends AppCompatActivity {
    String role,status_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pembuka);
        status_login = Guru.getString("status_loign", "false");
        role = Guru.getString("role", "false");
        getSupportActionBar().hide();
        star();
    }

    void star(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (status_login.equals("false")){
                    Intent intent  = new Intent(menu_pembuka.this, menu_utama.class);
                    startActivity(intent);


                }else {

                        Intent intent  = new Intent(menu_pembuka.this, menu_utama_admin.class);
                        startActivity(intent);


                }




            }
        }, 3000L); //3000 L = 3 detik
    }
}