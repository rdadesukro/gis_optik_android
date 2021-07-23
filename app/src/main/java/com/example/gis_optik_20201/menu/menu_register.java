//package com.example.gis_optik_20201.menu;
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.projek_kp_gis_badminton_2021.R;
//import com.example.projek_kp_gis_badminton_2021.model.user.IsiItem_user;
//import com.example.projek_kp_gis_badminton_2021.presenter.aksi;
//import com.example.projek_kp_gis_badminton_2021.view.coba_view;
//import com.github.squti.guru.Guru;
//import com.mobsandgeeks.saripaar.ValidationError;
//import com.mobsandgeeks.saripaar.Validator;
//import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
//import com.mobsandgeeks.saripaar.annotation.Email;
//import com.mobsandgeeks.saripaar.annotation.NotEmpty;
//import com.mobsandgeeks.saripaar.annotation.Password;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import cn.pedant.SweetAlert.SweetAlertDialog;
//
//public class menu_register extends AppCompatActivity implements Validator.ValidationListener, coba_view {
//
//    @BindView(R.id.btn_register)
//    Button btnRegister;
//
//    int role;
//
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @ConfirmPassword(message = "Konfiramsi Password Tidak Sama")
//    @BindView(R.id.edit_confirmasi)
//    EditText editConfirmasi;
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @Password(min = 6, message = "Minimal Password 6 Karakter")
//    @BindView(R.id.edit_password)
//    EditText editPassword;
//
//
//    Validator validator;
//    SweetAlertDialog pd_new;
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @BindView(R.id.edit_nama)
//    EditText editNik;
//
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @BindView(R.id.edit_username)
//    EditText editusername;
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @BindView(R.id.edit_alamat)
//    EditText edit_alamat;
//
//    @NotEmpty(message = "Tidak Boleh Kosong")
//    @BindView(R.id.edit_telpon)
//    EditText edit_telpon;
//
//
//    @Email
//    @BindView(R.id.edit_email)
//    EditText edit_email;
//
//
//
//    @BindView(R.id.rd_pemilik)
//    RadioButton rd_pemilik;
//
//
//    @BindView(R.id.rd_user)
//    RadioButton rd_user;
//
//    @BindView(R.id.radio_grup)
//    RadioGroup radio_grup;
//
//
//    String email;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu_register);
//        getSupportActionBar().hide();
//        ButterKnife.bind(this);
//        validator = new Validator(this);
//        validator.setValidationListener(this);
//
//        pd_new = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
//        pd_new.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));
//        Log.i("isi_server", "onCreate: "+ Guru.getString("server", "default value"));
//        radio_grup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // find which radio button is selected
//                if(checkedId == R.id.rd_user) {
//                    role=1;
//
//                    Toast.makeText(menu_register.this, "sads"+role, Toast.LENGTH_SHORT).show();
//                } else if(checkedId == R.id.rd_pemilik) {
//                    role=0;
//                    Toast.makeText(menu_register.this, "sdsa"+role, Toast.LENGTH_SHORT).show();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "choice: Vibration",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//
//        });
//
//    }
//
//    @OnClick(R.id.btn_register)
//    public void onViewClicked() {
//
//        validator.validate();
//    }
//
//    @Override
//    public void onValidationSucceeded() {
//
//        String password = editPassword.getText().toString().trim();
//        String nik = editNik.getText().toString().trim();
//        String alamat = edit_alamat.getText().toString().trim();
//        String telpon = edit_telpon.getText().toString().trim();
//        String username = editusername.getText().toString().trim();
//        email = edit_email.getText().toString().trim();
//
//        Guru.putString("password", password);
//        aksi countryPresenter = new aksi(null, menu_register.this);
//        countryPresenter.register(nik,username, password, pd_new,email,alamat,telpon,role);
//    }
//
//    @Override
//    public void onValidationFailed(List<ValidationError> errors) {
//        for (ValidationError error : errors) {
//            View view = error.getView();
//            String message = error.getCollatedErrorMessage(this);
//
//            // Display error messages ;)
//            if (view instanceof EditText) {
//                ((EditText) view).setError(message);
//            } else {
//                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
//            }
//        }
//    }
//
//
//    @Override
//    public void data_user(List<IsiItem_user> data_user) {
//
//    }
//}