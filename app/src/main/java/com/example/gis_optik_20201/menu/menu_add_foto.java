package com.example.gis_optik_20201.menu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.adapter.adapter_foto;
import com.example.gis_optik_20201.model.foto_slider.IsiItem_slider;
import com.example.gis_optik_20201.presenter.slider;
import com.example.gis_optik_20201.utils.FileUtils;
import com.example.gis_optik_20201.view.slider_view;
import com.github.squti.guru.Guru;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jeevandeshmukh.glidetoastlib.GlideToast;
import com.jpegkit.Jpeg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class menu_add_foto extends AppCompatActivity implements CameraCapture.OnInputListener , slider_view, adapter_foto.OnImageClickListener {

    private FloatingActionButton btnPanggil2;
    private TextView txtTdkAda;
    private ImageView imgData2;
    private SwipeRefreshLayout swifeRefresh;
    private RecyclerView rvAku;
    private ProgressBar progressBar;
    com.example.gis_optik_20201.adapter.adapter_foto adapter_foto;
    com.example.gis_optik_20201.presenter.slider slider;
    String id_jenis;
    AlertDialog.Builder acion;

    Bitmap decoded;
    public final int SELECT_FILE = 1;
    int bitmap_size = 40; // image quality 1 - 100;
    int max_resolution_image = 800;
    Uri tempUri;
    String imageTempName;

    BottomSheetDialog dialog;
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
    int staus_lapnagan;
    String foto;
    String id_optik;

    ProgressDialog progressDialog;
    ImageView img_foto;
    EditText nama_lapangan;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_add_foto);
        initView();
        id_optik = Guru.getString("id_optik", "false");
        slider = new slider(this, menu_add_foto.this);
        slider.get_slider(id_optik);
        btnPanggil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilaog_data("",foto,"new","nama");
            }
        });
        role = Guru.getString("role", "false");
        if (role.equals("user")){
            btnPanggil2.setVisibility(View.GONE);
        }else {
            btnPanggil2.setVisibility(View.VISIBLE);
        }
        swifeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                slider.get_slider(id_optik);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        slider.get_slider(id_optik);

    }
    private void initView() {
        btnPanggil2 = (FloatingActionButton) findViewById(R.id.btn_panggil2);
        txtTdkAda = (TextView) findViewById(R.id.txt_tdk_ada);
        imgData2 = (ImageView) findViewById(R.id.img_data2);
        swifeRefresh = (SwipeRefreshLayout) findViewById(R.id.swifeRefresh);
        rvAku = (RecyclerView) findViewById(R.id.rv_aku);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onImageClick(int id, String nama, String foto) {
        final CharSequence[] dialogitem = {"Edit", "Delete"};
        acion = new AlertDialog.Builder(this);
        acion.setCancelable(true);
        acion.setItems(dialogitem, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                switch (which) {
                    case 0:
//
                        dilaog_data(String.valueOf(id),foto,"edit",nama);
                        break;
                    case 1:
                        slider.hapus_foto(String.valueOf(id),progressDialog);

                        break;
                }
            }
        }).show();
    }


    @Override
    public void slider(List<IsiItem_slider> slider) {
        try {
            //  progKes.setVisibility(View.VISIBLE);
            Log.i("isi_event", "event: " + slider.size());
            adapter_foto = new adapter_foto(menu_add_foto.this, slider, 1, this::onImageClick);
            rvAku.setLayoutManager(new LinearLayoutManager(menu_add_foto.this, LinearLayoutManager.VERTICAL, false));
            rvAku.setAdapter(adapter_foto);
            swifeRefresh.setRefreshing(false);
            if (slider.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
                //  cardEvent.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                // cardEvent.setVisibility(View.VISIBLE);

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void status(String status) {
        if (status.equals("1")){
            slider.get_slider(id_optik);
        }else {

        }
    }

    void  dilaog_data(String id,String foto,String jenis,String nama) {
        dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_foto);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        nama_lapangan = (EditText) dialog.findViewById(R.id.edit_nama);
        img_foto = (ImageView) dialog.findViewById(R.id.imageView3);
        Button btn_edit = (Button) dialog.findViewById(R.id.btn_edit_pw);
        ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);
        if (jenis.equals("new")){

        }else{
           nama_lapangan.setText(nama);
            Glide.with(this)
                    .load("http://192.168.43.48/gis_optik/public/foto_slider/"+foto)
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
        }

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        img_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_foto();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestBody nama = createPartFromString("" + nama_lapangan.getText().toString());
                RequestBody id_jns = createPartFromString("" + id_optik);
                RequestBody id_foto = createPartFromString("" + id);
                MultipartBody.Part body = null;
                RequestBody requestFile;
                if (file == null) {
                    //Toast.makeText(this, "File Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                    body = MultipartBody.Part.createFormData("foto", file.getName(), requestFile);
                }
                if (nama_lapangan.getText().toString().equals("")) {
                    //  Toast.makeText(menu_profil_pejabat_pejabat.this, "Password lama tidak boleh kosong", Toast.LENGTH_SHORT);

                    new GlideToast.makeToast(menu_add_foto.this, "Password lama tidak boleh kosong", GlideToast.LENGTHLONG, GlideToast.WARNINGTOAST, GlideToast.CENTER).show();
                    nama_lapangan.requestFocus();
                } else {

                    slider.simpan_foto(id_foto,id_jns,nama,jenis,body,progressDialog);


                }


            }
        });

        dialog.show();
    }

    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(MediaType.parse(FileUtils.MIME_TYPE_TEXT), descriptionString);
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
                bitmap = MediaStore.Images.Media.getBitmap(menu_add_foto.this.getContentResolver(), data.getData());


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

    void add_foto(){
        dialog = new BottomSheetDialog(menu_add_foto.this);
        dialog.setTitle("Login");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_uabah_profil);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//                lp.copyFrom(dialog.getWindow().getAttributes());
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setDimAmount(0.5f);
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ImageView btn_close = (ImageView) dialog.findViewById(R.id.btn_close);
        ImageView btn_camera = (ImageView) dialog.findViewById(R.id.btn_camera);
        ImageView btn_galeri = (ImageView) dialog.findViewById(R.id.btn_galeri);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                CameraCapture dialog_new = new CameraCapture();
                dialog_new.show(fm, "fragment_camera");
                dialog.dismiss();
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


        dialog.show();
    }
    }
