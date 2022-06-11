package com.example.doanlaptrinhdidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.model.Room;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TaoHopDongActivity extends AppCompatActivity {
    Button btnthemHD, btnCaomBacAddHD;
    String idRoom, idCustomer;
    EditText txtTienCoc, txtNgayTra, txtNgayThue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_hop_dong);
        AnhXa();

        Room room = (Room) getIntent().getSerializableExtra("room");

        txtNgayThue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate();
            }
        });

        txtNgayTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDate1();
            }
        });

        loadCodeNewCustomer();


        idRoom = room.getID_PHONG();
        btnCaomBacAddHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        btnthemHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContract();
            }
        });
    }

    private void ChooseDate() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                txtNgayThue.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void ChooseDate1() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                calendar.set(i, i1, i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                txtNgayTra.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void addContract() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlInsert_HopDong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                if (response.equals("success")) {
                    Toast.makeText(getApplicationContext(), "Tạo hợp đồng thành công.", Toast.LENGTH_SHORT).show();
                    updateStatusRoom();
                    updateStatusCustomer();
                }else{
                    Toast.makeText(getApplicationContext(), "Tạo hợp đồng không  thành công.", Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Loi", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID_PHONG", idRoom);
                params.put("ID_KHACHHANG", idCustomer);
                params.put("TIENCOC", txtTienCoc.getText().toString());
                params.put("NGAYTHUE", txtNgayThue.getText().toString());
                params.put("NGAYTRA", txtNgayTra.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void updateStatusCustomer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlUpdateCustomer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID_KHACHHANG", idCustomer);
                params.put("_status", "1");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void updateStatusRoom() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlUpdateStatusPhong, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID_PHONG", idRoom);
                params.put("ID_LOAIPHONG", idRoom);
                params.put("_status", "1");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }

    private void loadCodeNewCustomer() {
        StringRequest stringRequest = new StringRequest(Server.urlGetCodeNewCustomer, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                idCustomer = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void AnhXa() {
        btnCaomBacAddHD = findViewById(R.id.btnComBacAddHDong);
        btnthemHD = findViewById(R.id.btnThemTTHDong);
        txtTienCoc = findViewById(R.id.txtTienCoc);
        txtNgayTra = findViewById(R.id.txtNgayTra);
        txtNgayThue = findViewById(R.id.txtNgayThue);
    }
}