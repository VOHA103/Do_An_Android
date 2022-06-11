package com.example.doanlaptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class ServicePage extends AppCompatActivity {
    TabLayout tablayout;
    ViewPager viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_page);
        //anh xa
        tablayout=findViewById(R.id.tabServiceLayout);
        viewpager=findViewById(R.id.viewServicePager);

        tablayout.setupWithViewPager(viewpager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new FragmentShowTypeService(this),"Type of service");
        viewPagerAdapter.addFragment(new FragmentShowService(this),"Service");

        viewpager.setAdapter(viewPagerAdapter);
        tablayout.getTabAt(0).setIcon(R.drawable.ic_baseline_memory_24);

        tablayout.getTabAt(1).setIcon(R.drawable.ic_baseline_miscellaneous_services_24);
    }
}