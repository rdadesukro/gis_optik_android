package com.example.gis_optik_20201.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


import com.example.gis_optik_20201.menu.menu_login;
import com.example.gis_optik_20201.menu.menu_riset_password;
import com.example.gis_optik_20201.model.action.Response_action;
import com.example.gis_optik_20201.server.ApiRequest;
import com.example.gis_optik_20201.server.Retroserver_server_AUTH;
import com.example.gis_optik_20201.view.email_view;
import com.github.squti.guru.Guru;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class email {

    private Context ctx;
    private email_view countryView;
    private Retroserver_server_AUTH countryService;
    public email(email_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }


    public void send_email(String email,ProgressDialog pDialog){
        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Cek Email...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_action> call = api.send_email(email);
        call.enqueue(new Callback<Response_action>() {
            @Override
            public void onResponse(Call<Response_action> call, Response<Response_action> response) {
                try {
                    if (response.isSuccessful()) {
                        String kode = response.body().getKode();
                        String pesan = response.body().getMessage();
                        if (kode.equals("1")){
                            finalPDialog.dismiss();
                            Guru.putString("email_riset", email);
                            dialog_berhasil("Berhasil",pesan);
                        }else{
                            finalPDialog.dismiss();
                            dialog_gagal("Gagal",pesan);
                        }
                        //countryView.send_email(kode,pesan, finalPDialog1);
                    } else {
                        // error case
                        switch (response.code()) {
                            case 404:
                                Toast.makeText(ctx, "not found", Toast.LENGTH_SHORT).show();
                                break;
                            case 500:
                                Toast.makeText(ctx,"server broken", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                Toast.makeText(ctx, "Notif error", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                } catch (Exception e) {
                    finalPDialog.dismiss();
                    dialog_gagal("Gagal", String.valueOf(e));
                    Log.e("onResponse", "There is an error" + e);
                    //data();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Response_action> call, Throwable t) {
                t.printStackTrace();
                //  sliderView_bener.setBackgroundResource(R.drawable.bg_no_item_city);
                Log.i("ewkwkwkwkw", "onFailure: " + t);
                if (t instanceof IOException) {

                } else {

                }

            }
        });

    }
    public  void edit_password(String kode,String password,ProgressDialog pDialog,String email){
        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Riset Password...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        Log.i("data_kode", "edit_password: "+kode);

        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_action> sendbio = api.edit_password(kode,email,password);

        sendbio.enqueue(new Callback<Response_action>() {
            @Override
            public void onResponse(Call<Response_action> call, Response<Response_action> response) {

                String kode = response.body().getKode();
                String pesan = response.body().getMessage();
                Log.i("kode_riset", "onResponse: " + kode);


                if (kode.equals("1")) {
                    dialog_berhasil_riset("Berhasil",pesan);
                    finalPDialog.dismiss();


                } else {
                    dialog_gagal("Gagal",pesan);
                    finalPDialog.dismiss();
                }
            }
            @Override
            public void onFailure(Call<Response_action> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }


    void dialog_berhasil(String judul,String pesan) {
        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(judul);
        pDialog.setContentText(pesan);
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();

                CustomIntent.customType(ctx, "fadein-to-fadeout");
                Intent intent = new Intent((Activity) ctx, menu_riset_password.class);
                intent.putExtra("Fragmentone", 3); //pass zero for Fragmentone.
                ctx.startActivity(intent);
            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
    void dialog_berhasil_riset(String judul,String pesan) {
        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(judul);
        pDialog.setContentText(pesan);
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
//
                Intent intent = new Intent(ctx, menu_login.class);
                ctx.startActivity(intent);
                CustomIntent.customType(ctx, "fadein-to-fadeout");
            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }
    void dialog_gagal(String judul,String pesan) {
        SweetAlertDialog pDialog = new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE);
        pDialog.setCancelable(false);
        pDialog.setTitleText(judul);
        pDialog.setContentText(pesan);
        pDialog.setConfirmText("Ok");
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();

    }

    }


