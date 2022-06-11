package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.doanlaptrinhdidong.Adapter.PhotoAdapter;
import com.example.doanlaptrinhdidong.model.Photo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomePage extends Fragment implements View.OnClickListener {
    ImageView imgDienNuoc, imgRoom, imgService, imgAdmin;
    Context context;
    Intent intent;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    PhotoAdapter photoAdapter;
    List<Photo> lstPhoto;
    Timer timer;

    public HomePage(Context context) {
        this.context = context;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgDienNuoc = view.findViewById(R.id.imgDienNuoc);
        imgRoom = view.findViewById(R.id.imgRoom);
        imgService = view.findViewById(R.id.imgService);
        imgAdmin = view.findViewById(R.id.imgAdmin);

        imgDienNuoc.setOnClickListener(this);
        imgRoom.setOnClickListener(this);
        imgService.setOnClickListener(this);
        imgAdmin.setOnClickListener(this);


        lstPhoto = getListPhoto();
        photoAdapter = new PhotoAdapter(context, lstPhoto);

        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.cicrleIndicator);

        photoAdapter = new PhotoAdapter(context, getListPhoto());
        viewPager.setAdapter(photoAdapter);

        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        autoSliderImage();
    }

    // load slider
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null)
            timer.cancel();
        timer = null;
    }

    public List<Photo> getListPhoto() {
        List<Photo> lst = new ArrayList<>();
        lst.add(new Photo(R.drawable.banner1));
        lst.add(new Photo(R.drawable.banner2));
        lst.add(new Photo(R.drawable.banner3));
        lst.add(new Photo(R.drawable.banner4));
        return lst;
    }

    public void autoSliderImage() {
        if (lstPhoto == null || lstPhoto.isEmpty() || viewPager == null)
            return;
        if (timer == null)
            timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = lstPhoto.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home_page, container, false);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imgAdmin:
                intent = new Intent(context, AdminPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.imgDienNuoc:
                intent = new Intent(context, QLDienNuocPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.imgRoom:
                intent = new Intent(context, RoomPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;
            case R.id.imgService:
                intent = new Intent(context, ServicePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }
}