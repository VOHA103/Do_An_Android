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
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.model.Room;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RoomDetail extends AppCompatActivity {
    TextView txtNameRoom,txtAddress,txtTypeRoom;
    ImageView imgRoom;
    Button btnCancel,btnTraPhong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        maping();
        Intent intent = getIntent();
        Room room=(Room) intent.getSerializableExtra("room");
        txtNameRoom.setText(room.getTENPHONG());
        txtAddress.setText(room.getDIACHI());
        txtTypeRoom.setText(room.getID_LOAIPHONG());
        Picasso.get().load(Server.urlHinhAnh+room.getHINH()).into(imgRoom);
        String maloai=room.getID_LOAIPHONG();
        loadTypeRoom(maloai);
        Log.d("TAG", "onCreate: "+maloai);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void maping(){
        txtNameRoom=findViewById(R.id.textDetailRoomName);
        txtTypeRoom=findViewById(R.id.textDetailTypeRoomName);
        imgRoom=findViewById(R.id.imageDetailRoom);
        txtAddress=findViewById(R.id.textDetailRoomAddress);
        btnTraPhong=findViewById(R.id.buttonTraPhong);
        btnCancel=findViewById(R.id.buttonHuy);
    }

    public void loadTypeRoom(String maloai){
        String url=Server.urlNameTypeRoom+"?mcd="+maloai;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        Log.d("fix", "onResponse: "+url);
        Response.Listener<JSONArray> thanhcong=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String typeroom="";
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject tr=response.getJSONObject(i);
                        typeroom+=tr.getString("TENLOAI");
                        Log.d(typeroom+"fix", "onResponse: ");
                    }catch (JSONException e){
                        Toast.makeText(getApplicationContext(),"Lỗi: "+e.toString(),Toast.LENGTH_LONG).show();
                    }
                }
                txtTypeRoom.setText(typeroom);
            }
        };
        Response.ErrorListener thatbai=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST,url,null,thanhcong,thatbai){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param=new HashMap<>();
                param.put("mcd",maloai);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }
}