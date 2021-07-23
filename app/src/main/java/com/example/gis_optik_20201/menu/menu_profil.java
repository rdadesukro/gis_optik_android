package com.example.gis_optik_20201.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.presenter.aksi;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jeevandeshmukh.glidetoastlib.GlideToast;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class menu_profil extends AppCompatActivity {
    ProgressDialog progressDialog;
    EditText pass_lama,pass_baru;

    BottomSheetDialog dialog;

    private ImageView btnKeluar;
    private ImageView btnPassword2;
    private ImageView btnPassword;
    String email;
    private TextView txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profil);
        initView();
        email = Guru.getString("email", "false");
        txtEmail.setText(email);
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new BottomSheetDialog(menu_profil.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_edit_password);
                dialog.setCancelable(true);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                dialog.getWindow().setAttributes(lp);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                dialog.getWindow().setDimAmount(0.5f);
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                pass_lama = (EditText) dialog.findViewById(R.id.edit_pw_lama);
                pass_baru = (EditText) dialog.findViewById(R.id.edit_pw_baru);
                final EditText pass_baru2 = (EditText) dialog.findViewById(R.id.edit_konfirmasi);
                pass_lama.requestFocus();
                Button btn_edit = (Button) dialog.findViewById(R.id.btn_edit_pw);
                ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });

                btn_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (pass_lama.getText().toString().equals("")) {
                            //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);

                            new GlideToast.makeToast(menu_profil.this, "Password lama tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                            pass_lama.requestFocus();
                        } else if (pass_baru.getText().toString().trim().equals("")) {
                            new GlideToast.makeToast(menu_profil.this, "Password baru tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                            // Toast.makeText(menu_profil_pejabat_pejabat.this, "Password baru tidak boleh kosong", Toast.LENGTH_SHORT);
                            pass_baru.requestFocus();
                        } else if (pass_baru2.getText().toString().trim().equals("")) {
                            new GlideToast.makeToast(menu_profil.this, "Password konfirmasi tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();

                            //Toast.makeText(menu_profil_pejabat_pejabat.this, "Password konfirmasi tidak boleh kosong", Toast.LENGTH_SHORT);
                            pass_baru2.requestFocus();
                        } else if (!pass_baru.getText().toString().equals(pass_baru2.getText().toString())) {
                            new GlideToast.makeToast(menu_profil.this, "pastikan password baru dan konfirmasi password sama !", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();


                            // Toast.makeText(menu_profil_pejabat_pejabat.this, "pastikan password baru dan konfirmasi password sama !", Toast.LENGTH_SHORT);
                            pass_baru2.requestFocus();
                        } else if (pass_baru.getText().toString().trim().length() < 6) {
                            //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Minimal Password Baru 6 Karketr", Toast.LENGTH_SHORT);
                            new GlideToast.makeToast(menu_profil.this, "Minimal Password Baru 6 Karketr", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();


                            pass_baru.requestFocus();
                        } else {

                            aksi countryPresenter = new aksi(null, menu_profil.this);
                            countryPresenter.update_password(pass_lama.getText().toString().trim(), pass_baru.getText().toString().trim(), progressDialog);



                        }


                    }
                });

                dialog.show();

            }
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(menu_profil.this, SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Apakah anda yakin ingin keluar ?");
                pDialog.setCancelable(false);
                pDialog.setConfirmText("Ya");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                        aksi countryPresenter = new aksi(null,menu_profil.this);
                        countryPresenter.logout(progressDialog);
                        Guru.putString("role", "1");
                        Log.i("isi_token", "onViewClicked: "+Guru.getString("token_login", "false"));
//                        countryPresenter.hapus_token(Guru.getString("token_login", "false"));


                    }
                });
                pDialog.setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                });
                pDialog.setCanceledOnTouchOutside(false);
                pDialog.show();


            }
        });


    }

    private void initView() {
        btnKeluar = (ImageView) findViewById(R.id.btn_keluar);
        btnPassword2 = (ImageView) findViewById(R.id.btn_password2);
        btnPassword = (ImageView) findViewById(R.id.btn_password);
        txtEmail = (TextView) findViewById(R.id.txt_email);
    }
}