package com.example.gis_optik_20201.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.adapter.adapter_optik;
import com.example.gis_optik_20201.model.optik.IsiItem_optik;
import com.example.gis_optik_20201.presenter.optik;
import com.example.gis_optik_20201.view.optik_view;
import com.github.squti.guru.Guru;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class menu_optik extends AppCompatActivity implements optik_view, adapter_optik.OnImageClickListener {

    private TextView txtTdkAda;
    private ImageView imgData2;
    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar;
    private adapter_optik adapter_optik;
    String role;
    com.example.gis_optik_20201.presenter.optik optik;
    private FloatingActionButton floatingActionButton2;
    AlertDialog.Builder acion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_optik);

        initView();
        role = Guru.getString("role", "false");
        Log.i("isi_role", "onCreate: " + role);
        optik = new optik(this, menu_optik.this);
        optik.get_optik("optik", "");

        if (role.equals("user")) {
            floatingActionButton2.setVisibility(View.GONE);
        } else {
            floatingActionButton2.setVisibility(View.VISIBLE);
        }

        swifeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                optik.get_optik("optik", "");
            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guru.putString("edit","new");
                Intent materi = new Intent(menu_optik.this, menu_tambah_optik.class);
                startActivity(materi);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        optik.get_optik("optik", "");

    }


    @Override
    public void optik(List<IsiItem_optik> optik) {
        try {
            //  progKes.setVisibility(View.VISIBLE);
            Log.i("isi_event", "event: " + optik.size());
            adapter_optik = new adapter_optik(menu_optik.this, optik, 1, this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_optik.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setAdapter(adapter_optik);
            swifeRefresh.setRefreshing(false);
            if (optik.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void status(String status, String pesan) {

    }

    private void initView() {
        txtTdkAda = (TextView) findViewById(R.id.txt_tdk_ada);
        imgData2 = (ImageView) findViewById(R.id.img_data2);
        swifeRefresh = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        rvAku = (RecyclerView) findViewById(R.id.rv_aku);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
    }

    @Override
    public void onImageClick(int id, String nama, String alamat, String phone, String foto, double lat, double lng, String status, String status_bpsjs, String informasi,String jam) {
        final CharSequence[] dialogitem = {"Edit", "Delete"};
        acion = new AlertDialog.Builder(this);
        acion.setCancelable(true);
        acion.setItems(dialogitem, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case 0:
                        Intent intent  = new Intent(menu_optik.this, menu_tambah_optik.class);
                        Guru.putString("nama",nama);
                        Guru.putString("edit","edit");
                        Guru.putString("id_optik", String.valueOf(id));
                        Guru.putString("alamat",alamat);
                        Guru.putString("lat", String.valueOf(lat));
                        Guru.putString("lng", String.valueOf(lng));
                        Guru.putString("phone",phone);
                        Guru.putString("status", status);
                        Guru.putString("status_bpjs", status_bpsjs);
                        Guru.putString("jam", jam);
                        Guru.putString("foto",foto);
                        Guru.putString("informasi",informasi);


                        startActivity(intent);
                        break;
                    case 1:

                        //e hapus(id);

                        break;
                }
            }
        }).show();
    }
}