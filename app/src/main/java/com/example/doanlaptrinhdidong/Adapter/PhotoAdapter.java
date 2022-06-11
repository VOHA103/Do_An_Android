package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    Context context;
    List<Photo> lstPhoto;

    public PhotoAdapter(Context context, List<Photo> lstPhoto) {
        this.context = context;
        this.lstPhoto = lstPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgPhoto=view.findViewById(R.id.imgBannerPhoto);
        Photo photo=lstPhoto.get(position);
        if (photo!=null)
            Glide.with(context).load(photo.getResourceID()).into(imgPhoto);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (lstPhoto.size()!=0)
            return lstPhoto.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
