package com.example.gis_optik_20201.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.presenter.optik;
import com.example.gis_optik_20201.utils.FileUtils;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jeevandeshmukh.glidetoastlib.GlideToast;
import com.jpegkit.Jpeg;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class menu_tambah_optik extends AppCompatActivity implements  Validator.ValidationListener,  CameraCapture.OnInputListener {


    Validator validator;
    BottomSheetDialog bittom_dialog;

    @NotEmpty
    @BindView(R.id.edit_nama)
    EditText edit_nama;

    @NotEmpty
    @BindView(R.id.edit_alamat)
    EditText edit_alamat;

    @NotEmpty
    @BindView(R.id.edit_lat)
    EditText edit_lat;


    @BindView(R.id.radio_grup)
    RadioGroup radio_grup;

    @BindView(R.id.rd_buka)
    RadioButton rd_buka;


    @BindView(R.id.rd_tutup)
    RadioButton rd_tutup;

    @BindView(R.id.radio_grup_bpjs)
    RadioGroup radio_grup_bpjs;

    @BindView(R.id.rd_ada)
    RadioButton rd_ada;


    @BindView(R.id.rd_tidak)
    RadioButton rd_tidak;

    @NotEmpty
    @BindView(R.id.edit_lng)
    EditText edit_lng;

    @NotEmpty
    @BindView(R.id.edit_iformasi)
    EditText edit_iformasi;

    @NotEmpty
    @BindView(R.id.edit_jam)
    EditText edit_jam;

    @NotEmpty
    @BindView(R.id.edit_telpon)
    EditText edit_telpon;




    @BindView(R.id.img_foto)
    ImageView img_foto;

    @NotEmpty
    @BindView(R.id.btn_register)
    Button btn_register;
    SweetAlertDialog pd_new;
    Bitmap decoded;
    public final int SELECT_FILE = 1;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    Uri tempUri;
    String imageTempName;


    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // Bitmap sampling size
    public static final int BITMAP_SAMPLE_SIZE = 8;

    // Gallery directory name to store the images or videos
    public static final String GALLERY_DIRECTORY_NAME = "Hello Camera";

    // Image and Video file extensions
    public static final String IMAGE_EXTENSION = "jpg";
    public static final String VIDEO_EXTENSION = "mp4";
    public static  File file;

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static String imageStoragePath;
    Bitmap bitmap;
    String nama,alamat,lat,lng,phone,status,jenis,id_lapangan,informasi,jam,statu_bpjs;
    String ststus_optik,sts_bpjs;
    String foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tambah_optik);
        ButterKnife.bind(this);

        jenis = Guru.getString("edit", "");

        if (jenis.equals("new")){

        }else {


            id_lapangan = Guru.getString("id_optik", "");
            nama = Guru.getString("nama", "");
            alamat = Guru.getString("alamat", "");
            lat = Guru.getString("lat", "");
            lng = Guru.getString("lng", "");
            phone = Guru.getString("phone", "");
            status = Guru.getString("status", "");
            statu_bpjs = Guru.getString("status_bpjs", "");
            informasi = Guru.getString("informasi", "");
            jam = Guru.getString("jam", "");
            
            foto = Guru.getString("foto", "");

            Glide.with(this)
                    .load("http://192.168.43.48/gis_optik/public/foto_optik/"+foto)
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
                    .into(img_foto);


            if (status.equals("buka")){
                rd_buka.setChecked(true);
            }
            if (status.equals("tutup")){
                rd_tutup.setChecked(true);
            }
            if (statu_bpjs.equals("ada")){
                rd_ada.setChecked(true);
            }
            if (statu_bpjs.equals("tidak")){
                rd_tidak.setChecked(true);
            }

            edit_nama.setText(nama);
            edit_alamat.setText(alamat);
            edit_telpon.setText(phone);
            edit_lng.setText(lng);
            edit_lat.setText(lat);
            edit_nama.setText(nama);
            edit_iformasi.setText(informasi);
            edit_jam.setText(jam);
        }




        validator = new Validator(this);
        validator.setValidationListener(this);
        pd_new = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pd_new.getProgressHelper().setBarColor(Color.parseColor("#3395ff"));


        radio_grup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rd_buka) {
                    ststus_optik="buka";

                } else if(checkedId == R.id.rd_tutup) {
                    ststus_optik="tutup";


                } else {
                    Toast.makeText(getApplicationContext(), "choice: Vibration",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        radio_grup_bpjs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rd_ada) {
                    sts_bpjs="ada";

                } else if(checkedId == R.id.rd_tidak) {
                    sts_bpjs="tidak";


                } else {
                    Toast.makeText(getApplicationContext(), "choice: Vibration",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });

        img_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bittom_dialog = new BottomSheetDialog(menu_tambah_optik.this);
                bittom_dialog.setTitle("Login");
                bittom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                bittom_dialog.setContentView(R.layout.dialog_uabah_profil);
                bittom_dialog.setCancelable(true);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialog.getWindow().getAttributes());
                bittom_dialog.getWindow().setAttributes(lp);
                bittom_dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                bittom_dialog.getWindow().setDimAmount(0.5f);
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                ImageView btn_close = (ImageView) bittom_dialog.findViewById(R.id.btn_close);
                ImageView btn_camera = (ImageView) bittom_dialog.findViewById(R.id.btn_camera);
                ImageView btn_galeri = (ImageView) bittom_dialog.findViewById(R.id.btn_galeri);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bittom_dialog.dismiss();

                    }
                });

                btn_camera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FragmentManager fm = getSupportFragmentManager();
                        CameraCapture dialog_new = new CameraCapture();
                        dialog_new.show(fm, "fragment_camera");
                        bittom_dialog.dismiss();
                    }
                });

                btn_galeri.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_FILE);

                    }
                });


                bittom_dialog.show();
            }
        });
    }




    @Override
    public void onValidationSucceeded() {
        RequestBody nama = createPartFromString("" + edit_nama.getText().toString());
        RequestBody id_lap = createPartFromString("" + id_lapangan);
        RequestBody alamat = createPartFromString(""+edit_alamat.getText().toString());
        RequestBody lat = createPartFromString(""+edit_lat.getText().toString());
        RequestBody lng = createPartFromString(""+edit_lng.getText().toString());
        RequestBody sts = createPartFromString(""+ststus_optik);
        RequestBody s_bpjs = createPartFromString(""+sts_bpjs);
        RequestBody jam = createPartFromString(""+edit_jam.getText().toString());
        RequestBody info = createPartFromString(""+edit_iformasi.getText().toString());
        RequestBody phone = createPartFromString(""+edit_telpon.getText().toString());
        MultipartBody.Part body = null;
        RequestBody requestFile;
        if (file == null) {
            //Toast.makeText(this, "File Kosong", Toast.LENGTH_SHORT).show();
        } else {
            requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            body = MultipartBody.Part.createFormData("foto", file.getName(), requestFile);
        }

        optik countryPresenter = new optik(null, menu_tambah_optik.this);
        countryPresenter.simpan_optik(pd_new,
                id_lap,
                nama,
                alamat,
                phone,
                lat,
                lng,
                sts,
                jenis,
                s_bpjs,
                info,
                jam,
                body);
       // file=null;
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
    }
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("MissingSuperCall")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                try {


                    BitmapFactory.Options bounds = new BitmapFactory.Options();
                    bounds.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imageStoragePath, bounds);

                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    Bitmap bm = BitmapFactory.decodeFile(imageStoragePath, opts);
                    ExifInterface exif = new ExifInterface(imageStoragePath);
                    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                    int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;

                    int rotationAngle = 0;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
                    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

                    Matrix matrix = new Matrix();
                    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
                    Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);


                    setToImageView(getResizedBitmap(rotatedBitmap, max_resolution_image));
                    img_foto.setImageBitmap(rotatedBitmap);



                }
                catch (Exception e){
                    e.printStackTrace();
                }


            } else if (resultCode == RESULT_CANCELED) {

            }
            else {

            }
        }

        if (requestCode == SELECT_FILE && data != null && data.getData() != null) {
            try {


                // mengambil gambar dari Gallery
                bitmap = MediaStore.Images.Media.getBitmap(menu_tambah_optik.this.getContentResolver(), data.getData());


                Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                file = FileUtils.getFile(this, tempUri);
                int file_size = Integer.parseInt(String.valueOf(file.length()/1024));

                Log.i("ukuran_file", "onActivityResult: "+file_size);

                String val = ""+file;
                String extension = val.substring(val.lastIndexOf(".") + 1);


                if (file_size>5024){
                    new GlideToast.makeToast((Activity) this,"File Terlau Besar",GlideToast.LENGTHTOOLONG,GlideToast.WARNINGTOAST,GlideToast.CENTER).show();

                    file=null;
                }  else if (file_size==0){
                    file=null;
                    new GlideToast.makeToast((Activity) this,"File Rusak",GlideToast.LENGTHTOOLONG,GlideToast.WARNINGTOAST,GlideToast.CENTER).show();

                } else{
                    setToImageView(getResizedBitmap(bitmap, max_resolution_image));
                    getStringImage(decoded);
                }
//
                //  foto(tempUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Img"+ Calendar.getInstance().getTime(), null);
        return Uri.parse(path);
    }

    private void setToImageView(Bitmap bmp) {
        //compress image
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        //menampilkan gambar yang dipilih dari camera/gallery ke ImageView
        img_foto.setImageBitmap(decoded);

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //Log.i(TAG, "getStringImage: "+encodedImage);
        return encodedImage;
    }


    @Override
    public void onSimpanClick(Jpeg data, File file1) {
        try {
            file = file1;


            String filePath = file1.getPath();
            decoded = BitmapFactory.decodeFile(filePath);
            img_foto.setImageBitmap(decoded);
            int file_size = Integer.parseInt(String.valueOf(file1.length() / 1024));
            Log.i("isi_foto", "onSimpanClick: " + file1.getName() + " " + file_size);

        } catch (Exception e) {
            Log.i("eeee", "onSimpanClick: " + e);

        }

    }
}