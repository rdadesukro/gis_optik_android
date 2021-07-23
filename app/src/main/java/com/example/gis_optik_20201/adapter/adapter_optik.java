package com.example.gis_optik_20201.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gis_optik_20201.R;
import com.example.gis_optik_20201.menu.menu_detail;
import com.example.gis_optik_20201.model.optik.IsiItem_optik;
import com.github.squti.guru.Guru;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import maes.tech.intentanim.CustomIntent;


public class adapter_optik extends RecyclerView.Adapter<adapter_optik.HolderData> {
    private static CountDownTimer countDownTimer;
    String kriim;
    String lat_new,lng_new;
    String lat,lng;
    String jenis;
    private int animation_type = 0;
    private List<IsiItem_optik> mList ;
    private Context ctx;
    private OnImageClickListener onImageClickListener;
    public adapter_optik(Context ctx, List<IsiItem_optik> mList , int animation_type, OnImageClickListener onImageClickListener) {
        this.jenis = jenis;
        this.animation_type = animation_type;
        this.mList = mList;
        this.ctx = ctx;
        this.onImageClickListener = onImageClickListener;

    }
    public interface OnImageClickListener {
        void onImageClick(int id,
                          String nama,
                          String alamat,
                          String phone,
                          String foto,
                          double lat,
                          double lng,
                          String status,
                          String status_bpsjs,
                          String informasi,
                          String jam);
    }



    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout;
        HolderData holder;
            layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_optik,parent, false);
            holder = new HolderData(layout);

            return holder;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(final HolderData holder, int position) {
        final IsiItem_optik dm = mList.get(position);
        holder.txt_nama.setText(dm.getNamaOptik());
        holder.txt_alamat.setText(dm.getAlamat());
        holder.txt_status_bpjs.setText("BPJS "+dm.getStatusBpjs());
        holder.txt_status_bpjs.setBackgroundResource(R.drawable.bg_status_ready);


        if (dm.getStatus().equals("buka")){
            holder.txt_status.setBackgroundResource(R.drawable.bg_status_ready);
            holder.txt_status.setText("Buka");
        }else {
            holder.txt_status.setBackgroundResource(R.drawable.bg_status_penuh);
            holder.txt_status.setText("Tutup");
        }
        Glide.with(ctx)
                .load("http://192.168.43.48/gis_optik/public/foto_optik/"+dm.getFoto())
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
                .into(holder.img_foto);

        holder.dm = dm;
        setAnimation(holder.itemView,position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder {





        @BindView(R.id.txt_nama)
        TextView txt_nama;

        @BindView(R.id.txt_alamat)
        TextView txt_alamat;

        @BindView(R.id.txt_status)
        TextView txt_status;

        @BindView(R.id.txt_status_bpjs)
        TextView txt_status_bpjs;

        @BindView(R.id.img_foto)
        ImageView img_foto;





        IsiItem_optik dm;


        public HolderData(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                   String role = Guru.getString("role", "false");
                    if (role.equals("user")){

                    }else {
                        onImageClickListener.onImageClick(dm.getId(),
                                dm.getNamaOptik(),
                                dm.getAlamat(),dm.getPhone(),dm.getFoto(),dm.getLat(),dm.getLng(),dm.getStatus(),dm.getStatusBpjs(),dm.getInformasi(),dm.getJamOprasional());

                    }
                       return false;
                }
            });

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    onImageClickListener.onImageClick(String.valueOf(dm.getId()), String.valueOf(dm.getDataibuId()));
                        if (dm.getStatus().equals("buka")){
                            Intent goInput = new Intent(ctx, menu_detail.class);
                            Guru.putString("id_optik", String.valueOf(dm.getId()));
                            ctx.startActivity(goInput);
                            CustomIntent.customType(ctx, "bottom-to-up");
                        }else {
                            Toast.makeText(ctx, "MAAF OPTIK TUTUP", Toast.LENGTH_SHORT).show();
                        }


                }
            });

//
        }


    }

    private int lastPosition = -1;
    private boolean on_attach = true;

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
           // ItemAnimation.animate(view, on_attach ? position : -1, animation_type);
            lastPosition = position;
        }
    }
}
