package com.example.gis_optik_20201.menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.adapter.SliderAdapterExample;
import com.example.gis_optik_20201.model.foto_slider.IsiItem_slider;
import com.example.gis_optik_20201.model.optik.IsiItem_optik;
import com.example.gis_optik_20201.presenter.optik;
import com.example.gis_optik_20201.presenter.slider;
import com.example.gis_optik_20201.view.optik_view;
import com.example.gis_optik_20201.view.slider_view;
import com.github.squti.guru.Guru;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import maes.tech.intentanim.CustomIntent;

public class menu_detail extends AppCompatActivity implements optik_view, slider_view {


    ProgressDialog progressDialog;

    com.example.gis_optik_20201.presenter.optik optik;
    com.example.gis_optik_20201.presenter.slider slider;
    String id_optik, role, id_jenis;
    private RatingBar ratingBar2;
    private TextView txtTgl;
    String status_login;
    private EditText textContent;
    double lat, lng;
    com.example.gis_optik_20201.adapter.SliderAdapterExample SliderAdapterExample;
    private SliderView bener;
    private ImageView btnCall;
    private TextView txtNama;
    private TextView txtNo;
    private ImageView imgFoto;
    private TextView txtStatus;
    private TextView txtAlamat;
    private TextView txtInformasi;
    private FloatingActionButton floatingActionButton;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
        initView();
        id_optik = Guru.getString("id_optik", "false");
        id_jenis = Guru.getString("id_jenis", "false");

        Log.i("id_lapangan", "onCreate: " + id_optik);
        status_login = Guru.getString("status_loign", "false");
        role = Guru.getString("role", "false");
        optik = new optik(this, menu_detail.this);
        slider = new slider(this, menu_detail.this);
        slider.get_slider(id_optik);
        optik.get_optik("detail", id_optik);


        if (role.equals("1")) {
            floatingActionButton.setVisibility(View.GONE);
        } else {
            floatingActionButton.setVisibility(View.VISIBLE);
        }

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + txtNo.getText()));
                startActivity(callIntent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guru.putString("id_optik", id_optik);
                Intent goInput = new Intent(menu_detail.this, menu_add_foto.class);
                startActivity(goInput);
                CustomIntent.customType(menu_detail.this, "fadein-to-fadeout");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = null;
                Intent mapIntent;
                String goolgeMap = "com.google.android.apps.maps";


                gmmIntentUri = Uri.parse("google.navigation:q=" + lat + "," + lng);

                mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage(goolgeMap);

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                } else {
                    Toast.makeText(menu_detail.this, "Google Maps Belum Terinstal. Install Terlebih dahulu.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void openWhatsApp(String number) {
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        slider.get_slider(id_optik);
        optik.get_optik("detail", id_optik);

    }

    private void initView() {

        btnCall = (ImageView) findViewById(R.id.btn_call);
        txtTgl = (TextView) findViewById(R.id.txt_tgl);
        txtNama = (TextView) findViewById(R.id.txt_nama);
        txtNo = (TextView) findViewById(R.id.txt_no);
        imgFoto = (ImageView) findViewById(R.id.img_foto);
        txtStatus = (TextView) findViewById(R.id.txt_status);
        txtAlamat = (TextView) findViewById(R.id.txt_alamat);
        txtInformasi = (TextView) findViewById(R.id.txt_informasi);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        button = (Button) findViewById(R.id.button);
        bener = (SliderView) findViewById(R.id.bener);
    }


    @Override
    public void optik(List<IsiItem_optik> optik) {
        try {
            //  progKes.setVisibility(View.VISIBLE);

            for (int i = 0; i < optik.size(); i++) {

                txtAlamat.setText(optik.get(i).getAlamat());
                txtNama.setText(optik.get(i).getNamaOptik());
                //  ratingBar2.setRating(Float.parseFloat(String.valueOf(jenis_detail.get(i).getRaiting())));
                txtInformasi.setText(optik.get(i).getInformasi());
                txtTgl.setText("Jam Buka : " + optik.get(i).getJamOprasional());
                txtNo.setText(optik.get(i).getPhone());
                Log.i("isi_foto", "jenis_detail: " + optik.get(i).getFoto());


                Glide.with(this)
                        .load("http://192.168.43.48/gis_optik/public/foto_optik/" + optik.get(i).getFoto())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .error(R.drawable.ic_check_black_24dp)
                        .centerCrop()
                        .into(imgFoto);
                lat = Double.parseDouble(String.valueOf(optik.get(i).getLat()));
                lng = Double.parseDouble(String.valueOf(optik.get(i).getLng()));

            }


        } catch (Exception e) {

        }
    }

    @Override
    public void status(String status, String pesan) {

    }

    @Override
    public void slider(List<IsiItem_slider> slider) {
        // swifeRefresh.setRefreshing(false);
        try {
            Log.i("data_slider", "slider: " + slider);
            SliderAdapterExample = new SliderAdapterExample(menu_detail.this, slider);
            bener.setSliderAdapter(SliderAdapterExample);
            bener.setIndicatorAnimation(IndicatorAnimations.THIN_WORM);
            bener.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
            bener.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            bener.setIndicatorSelectedColor(Color.WHITE);
            bener.setIndicatorUnselectedColor(Color.RED);
            bener.setScrollTimeInSec(4);
            bener.setAutoCycle(true);
            bener.startAutoCycle();
            //mRecycler.setAdapter(adapter);
            SliderAdapterExample.notifyDataSetChanged();
        } catch (Exception e) {
            Log.i("cek_error_slider", "slider: " + e);

        }


    }


    @Override
    public void status(String status) {

    }


}