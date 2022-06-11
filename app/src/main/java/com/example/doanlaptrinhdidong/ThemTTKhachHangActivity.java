package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.Retrofit2.APIUtils;
import com.example.doanlaptrinhdidong.Retrofit2.DataClient;
import com.example.doanlaptrinhdidong.model.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemTTKhachHangActivity extends AppCompatActivity {
    Button btnAddKH, btnCommbackAKH, btnTaoHD;
    EditText txtTen, txtGTinh, txtDiaChi, txtCMND, txtSDT, txtStatus;
    ImageView imgHInh;
    String realPath = "", image = "";
    private static final int requestCodeImg = 111;
    boolean checkAddCustomer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ttkhach_hang);
        anhXa();

        imgHInh.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //xin quyen
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 111);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, requestCodeImg);
                }
            }
        });
        btnAddKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCustomer();
            }
        });
        btnCommbackAKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ThemTTKhachHangActivity.this,BottomNavigationMainActivity.class);
                startActivity(intent);
            }
        });
        btnTaoHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAddCustomer) {
                    Intent intent = new Intent(getApplicationContext(), TaoHopDongActivity.class);
                    Room room = (Room) getIntent().getSerializableExtra("room");
                    intent.putExtra("room", room);
                    startActivity(intent);
                } else
                    Toast.makeText(ThemTTKhachHangActivity.this, "Bạn chưa thêm khách hàng.", Toast.LENGTH_SHORT).show();

            }

        });
    }

    public String getRealPathFromURI(Uri contentUri) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == requestCodeImg && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            realPath = getRealPathFromURI(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHInh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void insertCustomer() {
        String hoten = txtTen.getText().toString().trim();
        String gioitinh = txtGTinh.getText().toString().trim();
        String diachi = txtDiaChi.getText().toString().trim();
        String cmnd = txtCMND.getText().toString().trim();
        String sdt = txtSDT.getText().toString().trim();
       String status = txtStatus.getText().toString().trim();
        if (hoten.isEmpty() || gioitinh.isEmpty() || diachi.isEmpty() || cmnd.isEmpty() || sdt.isEmpty()) {
            Toast.makeText(ThemTTKhachHangActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if(cmnd.length()!=9){
            Toast.makeText(ThemTTKhachHangActivity.this, "Chứng minh nhân dân không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sdt.length()!=10){
            Toast.makeText(ThemTTKhachHangActivity.this, "Số điện thoại  không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlInsertCurtomer, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("success")) {
                    Toast.makeText(ThemTTKhachHangActivity.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    checkAddCustomer = true;
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("TENKHACHHANG", hoten);
                params.put("GIOITINH", gioitinh);
                params.put("DIACHI", diachi);
                params.put("CMND", cmnd);
                params.put("SDT", sdt);
                params.put("HINH", image);
                params.put("_status", status);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, requestCodeImg);
        }
    }

    private void addCustomer() {
        File file = new File(realPath);
        String file_path = file.getAbsolutePath();
        try {
            String[] arrayNameFile = file_path.split("\\.");
            file_path = arrayNameFile[0] + System.currentTimeMillis() + "." + arrayNameFile[1];
        } catch (Exception ex) {

        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", file_path, requestBody);
        DataClient dataClient = APIUtils.getData();
        retrofit2.Call<String> callback = dataClient.UploadImage(body);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response != null) {
                    image = response.body();
                    insertCustomer();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                insertCustomer();
            }
        });

    }

    private void anhXa() {
        txtTen = findViewById(R.id.txtTenKhachHang);
        txtGTinh = findViewById(R.id.txtGioiTinh);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtCMND = findViewById(R.id.txtCMND);
        txtSDT = findViewById(R.id.txtSDTKH);
        imgHInh = findViewById(R.id.imgHinhKhachHang);
        txtStatus = findViewById(R.id.txtStatus);
        btnAddKH = findViewById(R.id.btnAddTTKH);
        btnCommbackAKH = findViewById(R.id.btnComBacAddKH);
        btnTaoHD = findViewById(R.id.btnTaoHD);
    }
}