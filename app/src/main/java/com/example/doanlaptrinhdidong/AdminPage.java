package com.example.doanlaptrinhdidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.Adapter.AdminAdapter;
import com.example.doanlaptrinhdidong.model.Admin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Admin> data = new ArrayList<>();
    AdminAdapter adminAdapter;
    ImageView imageViewSua;
    Context context;
Button btnComBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        recyclerView = findViewById(R.id.recy_listAdmin);
        btnComBack=findViewById(R.id.comeback);
        btnComBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminPage.this,BottomNavigationMainActivity.class);
                startActivity(intent);
            }
        });
        adminAdapter = new AdminAdapter(this, data);
        recyclerView.setAdapter(adminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        loadAdmin();

//        imageViewSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,AdminPage.class);
//                context.startActivity(intent);
//            }
//        });
    }

    private void loadAdmin() {
        String url = Server.urlAdmin;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject admin = response.getJSONObject(i);
                        Admin cd = new Admin(admin.getString("TAIKHOAN"),
                                admin.getString("MATKHAU"), admin.getString("PHANQUYEN"));
                        data.add(cd);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Loi:" + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                adminAdapter.notifyDataSetChanged();//cap nhat lai du lieu len recycle view
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "loi roi" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, thanhcong, thatbai);
        requestQueue.add(jsonArrayRequest);
    }
}