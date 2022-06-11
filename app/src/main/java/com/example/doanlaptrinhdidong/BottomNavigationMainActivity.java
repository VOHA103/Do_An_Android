package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationMainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
SharedPreferences sharedPreferencesLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_main);
        sharedPreferencesLogin=getSharedPreferences("login",MODE_PRIVATE);
      Toast.makeText(this,sharedPreferencesLogin.getString("username","fail"),Toast.LENGTH_SHORT).show();
        anhXa();
        boolean checkPay=getIntent().getBooleanExtra("checkPay",false);
        if(checkPay)
            fragment=new BillPage(this);
        else
            fragment=new HomePage(this);
        boolean checkPayContract=getIntent().getBooleanExtra("checkPayContract",false);
        if(checkPayContract)
            fragment=new ContractPage(this);
        else
            fragment=new HomePage(this);
       loadFragment(fragment);

//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(isShowHomeAsUp());

      //  toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.START);
//            }
//        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.mnutrangchu:
                        fragment = new HomePage(getApplicationContext());
                        break;
                    case R.id.mnuhoadon:
                        fragment = new BillPage(getApplicationContext());
                        break;
                    case R.id.mnukhachthue:
                        fragment = new CustomerPage(getApplicationContext());
                        break;
                    case R.id.mnuhopdong:
                        fragment = new ContractPage(getApplicationContext());
                        break;
                }
                loadFragment(fragment);
                return true;
            }
        });

    }

    private boolean isShowHomeAsUp() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_xehang, menu);
        return true;
    }

    public void anhXa() {
        bottomNavigationView = findViewById(R.id.bottomnavigationview);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerlayoutmain);
    }

    public void loadFragment(Fragment f) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, f);
        fragmentTransaction.commit();
    }
}