package com.example.doanlaptrinhdidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.doanlaptrinhdidong.model.Bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Bill_False extends AppCompatActivity {
    TextView txtRoom,txtDate,txtMoney;
    Button btnPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_false);
        mapping();
        Intent intent = getIntent();
        Bill bill=(Bill) intent.getSerializableExtra("bill");
        //txtRoom.setText(bill.getID_PHONG());
        loadTypeRoom(bill.getID_PHONG(),txtRoom);
        txtDate.setText(bill.getThang_TToan());
        txtMoney.setText(bill.getTONGTIEN());
        Log.d("TAG"+bill.getTONGTIEN(), "onCreate: ");
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUpdate(bill.getID_HOADON());
                Intent intent=new Intent(getApplicationContext(),BottomNavigationMainActivity.class);
                intent.putExtra("checkPay",true);
                //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void loadUpdate(String ID_HoaDon) {
        String url = Server.urlUpdateInvoice;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG"+response.toString(), "onResponse: ");
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Thành Công",Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Thông Báo Cập Nhập Không Thành Công",Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                param.put("ID_HOADON", ID_HoaDon);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public void loadTypeRoom(String maphong, TextView txt) {
        String url = Server.urlRoomWithID + "?ID_PHONG=" + maphong;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String typeroom = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tr = response.getJSONObject(i);
                        typeroom += tr.getString("TENPHONG");
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                txt.setText(typeroom);
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
                param.put("ID_PHONG", maphong);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public  void mapping(){
        txtRoom=findViewById(R.id.textRoomNameBill);
        txtDate=findViewById(R.id.textDatetimeBill);
        txtMoney=findViewById(R.id.textTotalMoney);
        btnPay=findViewById(R.id.buttonPayBill);
    }
}