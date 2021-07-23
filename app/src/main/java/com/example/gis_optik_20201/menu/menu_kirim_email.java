package com.example.gis_optik_20201.menu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.presenter.email;
import com.example.gis_optik_20201.view.email_view;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class menu_kirim_email extends AppCompatActivity implements Validator.ValidationListener, email_view {
    @BindView(R.id.btn_register)
    Button btnRegister;

    @NotEmpty(message = "Tidak Boleh Kosong")
    @BindView(R.id.edit_email)
    EditText editEmail;

    Validator validator;

    com.example.gis_optik_20201.presenter.email email;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kirim_email);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        validator = new Validator(this);
        validator.setValidationListener(this);
        editEmail.requestFocus();
        progressDialog = new ProgressDialog(this);
    }
    @OnClick(R.id.btn_register)
    public void onViewClicked() {

        validator.validate();
    }

    @Override
    public void send_email(String status, String pesan, ProgressDialog dialog) {
    }

    @Override
    public void onValidationSucceeded() {
        email = new email(this, menu_kirim_email.this);
        email.send_email(editEmail.getText().toString().trim(),progressDialog);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}