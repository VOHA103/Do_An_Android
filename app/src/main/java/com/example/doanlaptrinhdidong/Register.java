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

public class Register extends AppCompatActivity {
    Button btnCancel, btnSignIn;
    EditText txtUser, txtPhone, txtPassword, txtConfirmPassword;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mapping();
        clickButton();
    }
    public void mapping(){
    txtConfirmPassword=findViewById(R.id.inputPasswordConfirmRegister);
    txtPassword=findViewById(R.id.inputPasswordRegister);
    txtUser=findViewById(R.id.inputEmailRegister);
    txtPhone=findViewById(R.id.inputPhoneRegister);
    btnCancel=findViewById(R.id.buttonRegisterCanel);
    btnSignIn=findViewById(R.id.buttonRegister);
    }
    public void clickButton(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtPassword.getText().toString().equals(txtConfirmPassword.getText().toString()))
                InsertAdmin(txtUser.getText().toString(),txtConfirmPassword.getText().toString());
                else
                    Toast.makeText(getApplicationContext(),"Mật Khẩu Không Trùng Khớp",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InsertAdmin(String User,String Pass) {
        String url = Server.urlInsertAdmin;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.toString().equals("0"))
                    Toast.makeText(getApplicationContext(),"Thông Báo Tài Khoản Đã Tồn Tại",Toast.LENGTH_LONG).show();
                else if (response.toString().equals("1")){
                    Toast.makeText(getApplicationContext(),"Thông Báo Đăng Ký Thành Công",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Thông Báo Đăng Ky Không Thành Công",Toast.LENGTH_LONG).show();
                Log.d("TAG", "onErrorResponse: ");
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