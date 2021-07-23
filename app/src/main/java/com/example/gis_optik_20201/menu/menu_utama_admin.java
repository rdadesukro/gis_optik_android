package com.example.gis_optik_20201.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.gis_optik_20201.R;
import com.github.squti.guru.Guru;

public class menu_utama_admin extends AppCompatActivity {

    private CardView cardOptik;
    private CardView cardProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama_admin);
        initView();
        cardOptik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guru.putString("role", "admin");
                Intent materi = new Intent(menu_utama_admin.this, menu_optik.class);
                startActivity(materi);


            }
        });
        cardProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        cardOptik = (CardView) findViewById(R.id.card_optik);
        cardProfil = (CardView) findViewById(R.id.card_profil);
    }
}