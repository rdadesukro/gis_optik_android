package com.example.gis_optik_20201.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.utils.PERMISSIONS;
import com.github.squti.guru.Guru;

public class menu_utama extends AppCompatActivity {

    private CardView cardOptik;
    private CardView cardMarker;
    private CardView cardPetunjuk;
    private CardView cardTentang;
    PERMISSIONS permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        initView();
        permissions = new PERMISSIONS(menu_utama.this);
        permissions.checkPermission();
        cardOptik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guru.putString("role", "user");
                Intent materi = new Intent(menu_utama.this, menu_optik.class);
                startActivity(materi);

            }
        });
        cardMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materi = new Intent(menu_utama.this, menu_marker.class);
                startActivity(materi);

            }
        });
        cardPetunjuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materi = new Intent(menu_utama.this, menu_petunjuk.class);
                startActivity(materi);

            }
        });
        cardTentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent materi = new Intent(menu_utama.this, menu_tentang_aplikasi.class);
                startActivity(materi);

            }
        });
    }

    private void initView() {
        cardOptik = (CardView) findViewById(R.id.card_optik);
        cardMarker = (CardView) findViewById(R.id.card_marker);
        cardPetunjuk = (CardView) findViewById(R.id.card_petunjuk);
        cardTentang = (CardView) findViewById(R.id.card_tentang);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logim, menu);
        MenuItem tanya = menu.findItem(R.id.login);

        tanya.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent materi = new Intent(menu_utama.this, menu_login.class);
                startActivity(materi);



                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}