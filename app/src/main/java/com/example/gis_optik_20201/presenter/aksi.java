package com.example.gis_optik_20201.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.example.gis_optik_20201.menu.menu_login;
import com.example.gis_optik_20201.menu.menu_utama;
import com.example.gis_optik_20201.menu.menu_utama_admin;
import com.example.gis_optik_20201.model.login.Response_login;
import com.example.gis_optik_20201.model.user.IsiItem_user;
import com.example.gis_optik_20201.model.user.Response_user;
import com.example.gis_optik_20201.server.ApiRequest;
import com.example.gis_optik_20201.server.Retroserver_server_AUTH;
import com.example.gis_optik_20201.view.coba_view;
import com.github.squti.guru.Guru;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import java.io.IOException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import maes.tech.intentanim.CustomIntent;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class aksi {

    private Context ctx;
    private coba_view countryView;
    private Retroserver_server_AUTH countryService;
    public aksi(coba_view view, Context ctx) {
        this.countryView = view;
        this.ctx = ctx;

        if (this.countryService == null) {
            this.countryService = new Retroserver_server_AUTH();
        }
    }




    public  void  edit_foto(MultipartBody.Part foto, ProgressDialog pDialog ){
        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Edit Foto Profil");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);

        Call<Response_login> sendbio = api.edit_foto(foto);


        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                String kode = response.body().getKode();
                Log.i("kode_foto", "onResponse: " + kode);

                if (kode.equals("1")) {
                    Guru.putString("foto_profil", response.body().getNama());
                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();

                } else {

                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                Log.i("cek_error", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
    public void login(String email, String password, ProgressDialog pDialog) {
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logging In...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.login(email,password);

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                try {
                    String kode = response.body().getKode();
                    String role = String.valueOf(response.body().getRole());
                    Log.i("role_kode", "onResponse: " + role);

                    //role = 1 = user
                    //role = 0 = pemilik

                    if (kode.equals("1")) {
                        finalPDialog.dismiss();

                            new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
                            Guru.putString("status_loign", "true");
                            Guru.putString("role", role);
                            Guru.putString("user_id", String.valueOf(response.body().getUserId()));
                            Guru.putString("auth", response.body().getAccessToken());
                            Intent intent = new Intent((Activity) ctx, menu_utama_admin.class);
                            intent.putExtra("Fragmentone", 3); //pass zero for Fragmentone.
                            ctx.startActivity(intent);
                            CustomIntent.customType((Activity) ctx,"fadein-to-fadeout");


                    } else {
                        finalPDialog.dismiss();
                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    }
                }catch (Exception e){
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                Log.e("cek_eror_login", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }

    public void register(String nik,String username,String password, SweetAlertDialog pDialog,String email,String alamat,String telpon,int role ) {
        pDialog = new SweetAlertDialog((Activity) ctx, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
        pDialog.setTitleText("Simpan Data");
        pDialog.setContentText("Mohon tunggu sedang memproses...");
        pDialog.setCancelable(false);
        pDialog.show();


        SweetAlertDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.register(nik,username,email,alamat,telpon,role,password,password);

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                try {
                    String kode = response.body().getKode();
                    String role = String.valueOf(response.body().getRole());
                    Log.i("role_kode", "onResponse: " + role);

                    //role = 1 = pemilik
                    //role = 2 = usert
                    if (kode.equals("1")) {
                        finalPDialog.dismissWithAnimation();
//                    login_new(email,password,finalPDialog);
                        dialog_berhasil_register("Berhasil",response.body().getMessage());
                    } else {
                        finalPDialog.dismissWithAnimation();
                        dialog_gagal("Gagal",response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.i("cek_error_login", "onResponse: "+e);
                    finalPDialog.dismiss();
                }



            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                Log.e("cek_eror_login", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }




    public void update_password(String password,String password_baru,ProgressDialog pDialog) {

        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Update Password...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.edit_pass(password,password_baru);

        ProgressDialog finalPDialog = pDialog;
        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                String kode = response.body().getKode();
                Log.i("kode_update", "onResponse: " + kode);

                if (kode.equals("1")) {
                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();

                } else if (kode.equals("3")){
                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                }else {
                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });

    }
    public  void logout(ProgressDialog pDialog){
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Logout...");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.logout();

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                String kode = response.body().getKode();
                Log.i("kode", "onResponse: " + kode);

                if (kode.equals("1")) {
                    finalPDialog.dismiss();
                    Guru.putString("status_loign", "false");
                    Guru.putString("auth", "");
                    String status_login = Guru.getString("status_loign", "false");
                    Intent intent = new Intent((Activity) ctx, menu_utama.class);
                    intent.putExtra("Fragmentone", 3);
                    ctx.startActivity(intent);
                    CustomIntent.customType((Activity) ctx, "fadein-to-fadeout");

                } else {
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }

    public  void edit_nohp(ProgressDialog pDialog,String no_hp,String id_user){
        pDialog = new ProgressDialog(ctx);
        pDialog.setTitle("Mohon Tunggu!!!");
        pDialog.setMessage("Edit No Handphone");
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
        ProgressDialog finalPDialog = pDialog;
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_login> sendbio = api.edit_no_hp(no_hp);

        sendbio.enqueue(new Callback<Response_login>() {
            @Override
            public void onResponse(Call<Response_login> call, Response<Response_login> response) {

                String kode = response.body().getKode();
                Log.i("kode", "onResponse: " + kode);

                if (kode.equals("1")) {
                    finalPDialog.dismiss();
                    Guru.putString("no_hp", response.body().getNama());
                    finalPDialog.dismiss();
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();

                } else {
                    new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                }

            }
            @Override
            public void onFailure(Call<Response_login> call, Throwable t) {
                Log.i("cek_error_no_hp", "onFailure: "+t);

                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
            }
        });
    }

    public void get_data_user(){
        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
        Call<Response_user> call = api.get_data_user();
        call.enqueue(new Callback<Response_user>() {
            @Override
            public void onResponse(Call<Response_user> call, Response<Response_user> response) {

                try {

                    if (response.isSuccessful()) {
                        Response_user data = response.body();
                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("isi_data", "onResponse: "+data);
                        if (data != null && data.getIsi() != null) {
                            List<IsiItem_user> result = data.getIsi();
                            countryView.data_user(result);
                        }



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
                    Log.e("onResponse", "There is an error" + e);
                    //data();
                    e.printStackTrace();
                }

            }


            @Override
            public void onFailure(Call<Response_user> call, Throwable t) {
                t.printStackTrace();
                //  sliderView_bener.setBackgroundResource(R.drawable.bg_no_item_city);
                Log.i("ewkwkwkwkw", "onFailure: " + t);
                if (t instanceof IOException) {

                } else {

                }

            }
        });

    }
    void dialog_berhasil_register(String judul,String pesan) {
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


