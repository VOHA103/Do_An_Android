package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.ContractPageActivity;
import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.Server;
import com.example.doanlaptrinhdidong.model.Contract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ContractAdapter extends RecyclerView.Adapter<KHUNGNHINCONTRACT>{
    Context context;
    ArrayList<Contract> data;
    int layout;

    public ContractAdapter(Context context, ArrayList<Contract> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINCONTRACT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINCONTRACT(LayoutInflater.from(context).inflate(layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINCONTRACT holder, int position) {
        int index=position;
        Contract contract=data.get(index);
        holder.tvIDContract.setText(contract.getID_HOPDONG());
        loadNameRoom(contract.getID_PHONG(), holder.tvNameRoom);
        loadNameCustomer(contract.getID_KHACHHANG(), holder.tvNameCutomer);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ContractPageActivity.class);
                intent.putExtra("contract",contract);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    public void loadNameCustomer(String ID_KHACHHANG, TextView txt) {
        String url = Server.urlGetCustomerName + "?ID_KHACHHANG=" + ID_KHACHHANG;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String typeroom = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tr = response.getJSONObject(i);
                        typeroom += tr.getString("TENKHACHHANG");
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
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
                param.put("ID_KHACHHANG", ID_KHACHHANG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    public void loadNameRoom(String ID_PHONG, TextView txt) {
        String url = Server.urlRoomWithID + "?ID_PHONG=" + ID_PHONG;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String typeroom = "";
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tr = response.getJSONObject(i);
                        typeroom += tr.getString("TENPHONG");
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
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
                param.put("ID_PHONG", ID_PHONG);
                return param;
            }
        };
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHINCONTRACT extends RecyclerView.ViewHolder {
    TextView tvIDContract, tvNameCutomer, tvNameRoom;

    public KHUNGNHINCONTRACT(@NonNull View itemView) {
        super(itemView);
        tvIDContract = itemView.findViewById(R.id.textIDContract);
        tvNameCutomer = itemView.findViewById(R.id.textNameCustomerContract);
        tvNameRoom = itemView.findViewById(R.id.textNameRoomContract);
    }
}
