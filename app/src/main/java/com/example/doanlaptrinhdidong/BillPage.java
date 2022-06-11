package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

public class BillPage extends Fragment {
    Context context;
    TabLayout tablayout;
    ViewPager viewpager;
    public BillPage(Context context) {
        this.context = context;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //anh xa
        tablayout=view.findViewById(R.id.tabLayoutBill);
        viewpager=view.findViewById(R.id.viewPagerBill);

        tablayout.setupWithViewPager(viewpager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new FragmentShowBillAll(context,0),"All Bill");
        viewPagerAdapter.addFragment(new FragmentShowBillTrue(context,0),"Bill True");
        viewPagerAdapter.addFragment(new FragmentShowBillFalse(context,0),"Bill False");
        viewpager.setAdapter(viewPagerAdapter);
        tablayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tablayout.getTabAt(1).setIcon(R.drawable.ic_baseline_dashboard_24);
        tablayout.getTabAt(2).setIcon(R.drawable.ic_baseline_home_24);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_bill_page,container,false);
    }
}