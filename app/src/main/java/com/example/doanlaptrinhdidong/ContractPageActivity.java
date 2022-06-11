package com.example.doanlaptrinhdidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.model.Contract;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContractPageActivity extends AppCompatActivity {
    TextView txtRoomName,txtCustomerName,txtAddressCustomer,txtFromContract,txtToContract;
    ImageView imgCustomer,imgRoom;
    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_page2);
        mapping();
        Intent intent = getIntent();
        Contract contract  =(Contract) intent.getSerializableExtra("contract");
        txtFromContract.setText(contract.getNGAYTHUE());
        txtToContract.setText(contract.getNGAYTRA());
        loadNameCustomer(contract.getID_KHACHHANG(),txtCustomerName,txtAddressCustomer,imgCustomer);
        loadNameRoom(contract.getID_PHONG(),txtRoomName,imgRoom);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUpdateContract(contract.getID_HOPDONG());
                loadUpdateRoom(contract.getID_PHONG());
                Intent intent=new Intent(getApplicationContext(),BottomNavigationMainActivity.class);
                intent.putExtra("checkPayContract",true);
                //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void loadUpdateContract(String ID_HOPDONG) {
        String url = Server.urlUpdateContract;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG"+response.toString(), "onResponse: ");
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Hợp Đồng  Thành Công",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Hợp Đồng  Không Thành Công",Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID_HOPDONG", ID_HOPDONG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void loadUpdateRoom(String ID_PHONG) {
        String url = Server.urlUpdateRoom;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG"+response.toString(), "onResponse: ");
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Phòng Thành Công",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Phòng  Không Thành Công",Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID_PHONG", ID_PHONG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void loadNameCustomer(String ID_KHACHHANG, TextView txtName,TextView txtAddress,ImageView img) {
        String url = Server.urlGetCustomerName + "?ID_KHACHHANG=" + ID_KHACHHANG;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String room = "";
                String address = "";
                String image = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tr = response.getJSONObject(i);
                        room += tr.getString("TENKHACHHANG");
                        address += tr.getString("DIACHI");
                        image += tr.getString("HINH");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                txtName.setText(room);
                txtAddress.setText(address);
                Picasso.get().load(Server.urlHinhAnh+image).into(img);
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, thanhcong, thatbai) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID_KHACHHANG", ID_KHACHHANG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void loadNameRoom(String ID_PHONG, TextView txt,ImageView img) {
        String url = Server.urlRoomWithID + "?ID_PHONG=" + ID_PHONG;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String typeroom = "";
                String image = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tr = response.getJSONObject(i);
                        typeroom += tr.getString("TENPHONG");
                        image += tr.getString("HINH");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                txt.setText(typeroom);
                Picasso.get().load(Server.urlHinhAnh+image).into(img);
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, null, thanhcong, thatbai) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID_PHONG", ID_PHONG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
    public void mapping(){
        txtRoomName=findViewById(R.id.textRoomContract);
        txtCustomerName=findViewById(R.id.textCustomerContract);
        txtAddressCustomer=findViewById(R.id.textAddressCustomerContract);
        txtFromContract=findViewById(R.id.textFrom);
        txtToContract=findViewById(R.id.textTo);
        btnPay=findViewById(R.id.buttonThanhToan);
        imgCustomer=findViewById(R.id.imageCustomerContract);
        imgRoom=findViewById(R.id.imageRoomContract);
    }
}