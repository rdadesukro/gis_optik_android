package com.example.gis_optik_20201.server;


import com.example.gis_optik_20201.model.action.Response_action;
import com.example.gis_optik_20201.model.foto_slider.Response_slider;
import com.example.gis_optik_20201.model.login.Response_login;
import com.example.gis_optik_20201.model.optik.Response_optik;
import com.example.gis_optik_20201.model.user.Response_user;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ApiRequest {


    @FormUrlEncoded
    @POST("send_email")
    Call<Response_action> send_email(
            @Field("email") String email);


    @Multipart
    @POST("edit_foto_profil")
    Call<Response_login> edit_foto(@Part MultipartBody.Part foto);

    @FormUrlEncoded
    @POST("register")
    Call<Response_login> register(
            @Field("nama") String name,
            @Field("username") String username,
            @Field("email") String email,
            @Field("alamat") String alamat,
            @Field("telpon") String telpon,
            @Field("role") int role,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation);

    @Multipart
    @POST("add_optik")
    Call<Response_login> simpan_optik(
            @Part("nama_optik") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("phone") RequestBody phone,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("status") RequestBody status,
            @Part("status_bpjs") RequestBody status_bpjs,
            @Part("jam_oprasional") RequestBody jam_oprasional,
            @Part("informasi") RequestBody informasi,
            @Part MultipartBody.Part foto);



    @Multipart
    @POST("add_jenis")
    Call<Response_login> add_jenis(
            @Part("nama") RequestBody nama,
            @Part("informasi") RequestBody informasi,
            @Part("harga") RequestBody harga,
            @Part("lapangan_id") RequestBody lapangan_id,
            @Part("status") RequestBody status,
            @Part MultipartBody.Part foto);


    @FormUrlEncoded
    @POST("riset_password")
    Call<Response_action> edit_password(
            @Field("kode") String kode,
            @Field("email") String email,
            @Field("password_baru") String password_baru);


    @Multipart
    @POST("edit_optik")
    Call<Response_login> edit_optik(
            @Part("id") RequestBody id,
            @Part("nama_optik") RequestBody nama,
            @Part("alamat") RequestBody alamat,
            @Part("phone") RequestBody phone,
            @Part("lat") RequestBody lat,
            @Part("lng") RequestBody lng,
            @Part("status") RequestBody status,
            @Part("status_bpjs") RequestBody status_bpjs,
            @Part("jam_oprasional") RequestBody jam_oprasional,
            @Part("informasi") RequestBody informasi,
            @Part MultipartBody.Part foto);



    @FormUrlEncoded
    @POST("edit_pass")
    Call<Response_login> edit_pass(
            @Field("password") String password,
            @Field("password_baru") String password_baru);


    @FormUrlEncoded
    @POST("add_raiting")
    Call<Response_login> add_raiting(
            @Field("jenis_id") String jenis_id,
            @Field("raiting") float raiting,
            @Field("lapangan_id") String lapangan_id);

    @Multipart
    @POST("add_foto")
    Call<Response_login> add_foto(
            @Part("optik_id") RequestBody optik_id,
            @Part("nama") RequestBody nama,
            @Part MultipartBody.Part foto);

    @Multipart
    @POST("edit_foto")
    Call<Response_login> edit_foto(
            @Part("id") RequestBody id,
            @Part("optik_id") RequestBody optik_id,
            @Part("nama") RequestBody nama,
            @Part MultipartBody.Part foto);



    @FormUrlEncoded
    @POST("add_komentar")
    Call<Response_login> add_komentar(
            @Field("jenis_id") String jenis_id,
            @Field("komentar") String komentar);



    @FormUrlEncoded
    @POST("login")
    Call<Response_login> login(
            @Field("username") String username,
            @Field("password") String password);


    @POST("get_data_optik")
    Call<Response_optik> get_optik();


    @FormUrlEncoded
    @POST("get_detail_optik")
    Call<Response_optik> get_detail_optik( @Field("id") String id);


    @POST("get_foto_slider")
    Call<Response_slider> get_foto();








    @GET("get_foto_slider")
    Call<Response_slider> get_slider(@Query("id") String id);






    @FormUrlEncoded
    @POST("simpan_pertanyaan")
    Call<Response_action> simpan_curhatan(@Field("isi_pertanyaan") String isi_pertanyaan);

    @FormUrlEncoded
    @POST("cek_data")
    Call<Response_login> cek_data(
            @Field("email") String username,
              @Field("password") String password);



    @POST("get_data_user")
    Call<Response_user> get_data_user();


    @GET("logout")
    Call<Response_login> logout();

    @FormUrlEncoded
    @POST("hapus_token")
    Call<Response_login> hapus_token(
            @Field("token") String token);

    @FormUrlEncoded
    @POST("hapus_optik")
    Call<Response_action> hapus_optik(
            @Field("id") String id);


    @FormUrlEncoded
    @POST("hapus_foto")
    Call<Response_action> hapus_foto(
            @Field("id") String id);



    @FormUrlEncoded
    @POST("edit_no_hp")
    Call<Response_login> edit_no_hp(@Field("phone") String no_hp);





}


