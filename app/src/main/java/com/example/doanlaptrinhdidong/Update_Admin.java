
package com.example.doanlaptrinhdidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.model.Admin;

import java.util.HashMap;
import java.util.Map;

public class Update_Admin extends AppCompatActivity {
    EditText txtMatKhau;
    Button btnSua, btnHuy;

    String taikhoan = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);

        Intent intent = getIntent();
        Admin admin = (Admin) intent.getSerializableExtra("data");
        Toast.makeText(this,"Tai khoan : "+ admin.getTAIKHOAN(), Toast.LENGTH_SHORT).show();

        taikhoan = admin.getTAIKHOAN();

        txtMatKhau = findViewById(R.id.txtMatKhau);

        btnSua = findViewById(R.id.btnSuaAdmin);
        btnHuy = findViewById(R.id.btnHuy);

        txtMatKhau.setText(admin.getMATKHAU());


        //bat xu kien
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url=Server.urlUpdateAdmin;

                String pass = txtMatKhau.getText().toString().trim();

                if (pass.matches("") ) {
                    Toast.makeText(Update_Admin.this, "Vui lhong nhap day du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    CapNhatAdmin(url);
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void CapNhatAdmin(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")) {
                    Toast.makeText(Update_Admin.this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Update_Admin.this,AdminPage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Update_Admin.this, "Cap nhat khong  thanh cong", Toast.LENGTH_SHORT).show();

                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Update_Admin.this, "Loi", Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               params.put("TAIKHOAN",taikhoan);
                params.put("MATKHAU", txtMatKhau.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}