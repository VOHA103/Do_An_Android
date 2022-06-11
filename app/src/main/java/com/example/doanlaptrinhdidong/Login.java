package com.example.doanlaptrinhdidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btnRegister, btnLogin;
    TextView forgotPassword;
    Intent intent;
    EditText txtUser, txtPass;
    SharedPreferences sharedPreferencesLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferencesLogin = getSharedPreferences("login", MODE_PRIVATE);
        if (!sharedPreferencesLogin.getString("username", "fail").equals("fail")) {
            Intent intent = new Intent(getApplicationContext(), BottomNavigationMainActivity.class);
            startActivity(intent);
        }
        forgotPassword=findViewById(R.id.forgotPassword);
        btnRegister = findViewById(R.id.gotoRegister);
        btnLogin = findViewById(R.id.btnLogin);
        txtUser = findViewById(R.id.inputEmail);
        txtPass = findViewById(R.id.inputPassword);
//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent  intent = new Intent(Login.this, Update_Admin.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: ");
                CheckLogin(txtUser.getText().toString(), txtPass.getText().toString());
            }
        });
    }

    public void CheckLogin(String User, String Pass) {
        String url = Server.urlCheckLogin;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("FIX" + response.toString(), "onResponse: ");
                if (response.toString().equals("error"))
                    Toast.makeText(getApplicationContext(), "Thông Báo Sai Tài Khoản Mật Khẩu", Toast.LENGTH_LONG).show();
                else {
                    SharedPreferences.Editor editorLogin = sharedPreferencesLogin.edit();
                    editorLogin.putString("username", response.toString());
                    editorLogin.commit();
                    Toast.makeText(getApplicationContext(), "Thông Báo Đăng Nhập Thành Công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), BottomNavigationMainActivity.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Thông Báo Đăng Nhập Không Thành Công", Toast.LENGTH_LONG).show();
                Log.d("TAG loi" + error.toString(), "onErrorResponse: ");
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("TAIKHOAN", User);
                param.put("MATKHAU", Pass);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}