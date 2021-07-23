//package com.example.gis_optik_20201.presenter;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.Color;
//import android.util.Log;
//
//import com.example.gis_optik_20201.server.ApiRequest;
//import com.example.gis_optik_20201.server.Retroserver_server_AUTH;
//import com.example.gis_optik_20201.view.foto_view;
//import com.jeevandeshmukh.glidetoastlib.GlideToast;
//
//import java.io.IOException;
//import java.util.List;
//
//import cn.pedant.SweetAlert.SweetAlertDialog;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class foto {
//
//    private Context ctx;
//    private foto_view countryView;
//    private Retroserver_server_AUTH countryService;
//    public foto(foto_view view, Context ctx) {
//        this.countryView = view;
//        this.ctx = ctx;
//
//        if (this.countryService == null) {
//            this.countryService = new Retroserver_server_AUTH();
//        }
//    }
//
//    public void get_foto(String role) {
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//        Log.i("isi_server", "isi_server: "+Retroserver_server_AUTH.getClient().baseUrl());
//        Call<Response_lapangan> call;
//        if (role.equals("1")){
//            call = api.get_lapangan();
//        }else {
//            call = api.get_data_lapangan_pemilik();
//        }
//        call.enqueue(new Callback<Response_lapangan>() {
//            @Override
//            public void onResponse(Call<Response_lapangan> call, Response<Response_lapangan> response) {
//
//                try {
//
//                    if (response.isSuccessful()) {
//                        Response_lapangan data = response.body();
//                        //Toast.makeText(ctx, ""+ response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.i("isi_data", "onResponse: "+data);
//                        if (data != null && data.getIsi() != null) {
//                            List<IsiItem_lapangan> result = data.getIsi();
//                         //   countryView.lapangan(result);
//                        }
//                    }
//                } catch (Exception e) {
//                    Log.e("onResponse", "There is an error" + e);
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Response_lapangan> call, Throwable t) {
//                t.printStackTrace();
//                Log.i("cek_error", "onFailure: " + t);
//                if (t instanceof IOException) {
//
//
//                } else {
//
//
//                }
//            }
//        });
//    }
//
//    public void simpan_lapangan(SweetAlertDialog pDialog,
//                                RequestBody id,
//                                RequestBody nama,
//                                RequestBody alamat,
//                                RequestBody phone,
//                                RequestBody lat,
//                                RequestBody lng,
//                                RequestBody status,
//                                String jenis,
//                                MultipartBody.Part foto) {
//        pDialog = new SweetAlertDialog((Activity) ctx, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
//        pDialog.setTitleText("Simpan Data");
//        pDialog.setContentText("Mohon tunggu sedang memproses...");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//
//
//        SweetAlertDialog finalPDialog = pDialog;
//        ApiRequest api = Retroserver_server_AUTH.getClient().create(ApiRequest.class);
//
//        Call<Response_login> sendbio = null;
//        if (jenis.equals("new")){
//            sendbio = api.simpan_lapangan(nama,
//                    alamat,
//                    phone,
//                    lat,
//                    lng,
//                    status,
//                    foto);
//        }else {
//            sendbio = api.edit_lapangan(
//                    id,
//                    nama,
//                    alamat,
//                    phone,
//                    lat,
//                    lng,
//                    status,
//                    foto);
//        }
//
//
//        sendbio.enqueue(new Callback<Response_login>() {
//            @Override
//            public void onResponse(Call<Response_login> call, Response<Response_login> response) {
//
//                try {
//                    String kode = response.body().getKode();
//                    String role = String.valueOf(response.body().getRole());
//                    Log.i("role_kode", "onResponse: " + role);
//
//                    //role = 1 = pemilik
//                    //role = 2 = usert
//                    if (kode.equals("1")) {
//                        finalPDialog.dismissWithAnimation();
////                    login_new(email,password,finalPDialog);
//                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.SUCCESSTOAST, GlideToast.CENTER).show();
//
//                    } else {
//                        finalPDialog.dismissWithAnimation();
//                        new GlideToast.makeToast((Activity) ctx, "" + response.body().getMessage(), GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
//
//                    }
//                }catch (Exception e){
//                    Log.i("cek_error_login", "onResponse: "+e);
//                    finalPDialog.dismiss();
//                }
//
//
//
//            }
//            @Override
//            public void onFailure(Call<Response_login> call, Throwable t) {
//                Log.e("cek_eror_login", "onFailure: "+t);
//
//                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
//            }
//        });
//
//    }
//
//
//    }
//
//
