package com.example.gis_optik_20201.adapter;//package com.example.projek_kp_gis_badminton_2021.adapter;
//
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.WebView;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import androidx.annotation.Nullable;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.DataSource;
//import com.bumptech.glide.load.engine.GlideException;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.target.Target;
//import com.example.projek_kp_gis_badminton_2021.R;
//import com.example.projek_kp_gis_badminton_2021.model.foto_slider.IsiItem_slider;
//import com.github.squti.guru.Guru;
//import com.smarteist.autoimageslider.SliderViewAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//
//public class adapter_bener extends SliderViewAdapter<adapter_bener.HolderData> {
//
//        private Context context;
//        private String jenis;
//        private List<IsiItem_slider> mSliderItems = new ArrayList<>();
//
//    public adapter_bener(Context context, List <IsiItem_slider> mList, String jenis) {
//        this.mSliderItems = mList;
//        this.jenis = jenis;
//        this.context = context;
//    }
//
//        public void renewItems (List < IsiItem_slider > sliderItems) {
//        this.mSliderItems = sliderItems;
//        notifyDataSetChanged();
//    }
//
//        public void deleteItem ( int position){
//        this.mSliderItems.remove(position);
//        notifyDataSetChanged();
//    }
//
//        public void addItem (IsiItem_slider sliderItem){
//        this.mSliderItems.add(sliderItem);
//        notifyDataSetChanged();
//    }
//
//
//    @Override
//    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(HolderData viewHolder, int position) {
//
//    }
//
//    @Override
//    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
//        IsiItem_slider sliderItem = mSliderItems.get(position);
//
//
//
//
//            String server = Guru.getString("data_foto", "default value");
//            Glide.with(viewHolder.itemView)
//                    .load(server+"/foto_slider/"+sliderItem.getFoto())
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            viewHolder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//                    })
//                    .into(viewHolder.imageViewBackground);
//        }
//
//        @Override
//        public int getCount () {
//        //slider view count could be dynamic size
//        return mSliderItems.size();
//    }
//
//        class SliderAdapterVH extends ViewHolder {
//
//            View itemView;
//            ImageView imageViewBackground;
//            ImageView imageGifContainer;
//            WebView textViewDescription;
//            ProgressBar progressBar;
//
//            public SliderAdapterVH(View itemView) {
//                super(itemView);
//                try {
//                    textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
//                } catch (Exception e) {
//
//                    Log.i("cek_webview2", "SliderAdapterVH: " + e);
//
//                }
//                imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
//                progressBar = itemView.findViewById(R.id.progressBar);
//                imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
//
//                this.itemView = itemView;
//            }
//        }
//
//
//    public class HolderData extends ViewHolder {
//    }
//}
