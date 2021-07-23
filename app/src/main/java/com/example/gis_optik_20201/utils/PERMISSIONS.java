package com.example.gis_optik_20201.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PERMISSIONS {
    private Context ctx;
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    public PERMISSIONS(Context ctx) {
        this.ctx = ctx;
    }

    public void checkPermission(){
        if(ContextCompat.checkSelfPermission(ctx, Manifest.permission.CAMERA)
                + ContextCompat.checkSelfPermission(
                ctx,Manifest.permission.READ_CONTACTS)
                + ContextCompat.checkSelfPermission(
                ctx,Manifest.permission.CALL_PHONE)
                + ContextCompat.checkSelfPermission(
                ctx,Manifest.permission.ACCESS_FINE_LOCATION)
                + ContextCompat.checkSelfPermission(
                ctx,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){

            // Do something, when permissions not granted
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) ctx,Manifest.permission.CAMERA)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) ctx,Manifest.permission.READ_CONTACTS)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) ctx,Manifest.permission.CALL_PHONE)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) ctx,Manifest.permission.ACCESS_FINE_LOCATION)
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    (Activity) ctx,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                // If we should give explanation of requested permissions

                // Show an alert dialog here with request explanation
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setMessage("Camera, Read Contacts and Write External" +
                        " Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(
                                (Activity) ctx,
                                new String[]{
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_CONTACTS,
                                        Manifest.permission.CALL_PHONE,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                },
                                MY_PERMISSIONS_REQUEST_CODE
                        );
                    }
                });
                builder.setNeutralButton("Cancel",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                // Directly request for required permissions, without explanation
                ActivityCompat.requestPermissions(
                        (Activity) ctx,
                        new String[]{
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_CONTACTS,
                                Manifest.permission.CALL_PHONE,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_CODE
                );
            }
        }else {
            // Do something, when permissions are already granted
            Toast.makeText(ctx,"Permissions already granted",Toast.LENGTH_SHORT).show();
        }
    }

}
