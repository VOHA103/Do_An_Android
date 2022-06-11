package com.example.doanlaptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.Adapter.RoomAdapter;
import com.example.doanlaptrinhdidong.model.Room;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RoomPage extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_page);
        //anh xa
        tablayout=findViewById(R.id.tabLayout);
        viewpager=findViewById(R.id.viewPager);

        tablayout.setupWithViewPager(viewpager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new FragmentShowAll(this,0),"All Room");
        viewPagerAdapter.addFragment(new FragmentShowRoomTrue(this,0),"Room True");
        viewPagerAdapter.addFragment(new FragmentShowRoomFalse(this,0),"Room False");
        viewPagerAdapter.addFragment(new FragmentTypeRoom(this,0),"Type Room");
        viewpager.setAdapter(viewPagerAdapter);
        tablayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_baseline_dashboard_24);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_baseline_home_24);
        tablayout.getTabAt(3).setIcon(R.drawable.ic_baseline_dashboard_24);
    }

}