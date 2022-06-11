package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.example.doanlaptrinhdidong.Bill_Detail;
import com.example.doanlaptrinhdidong.Bill_False;
import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.RoomDetail;
import com.example.doanlaptrinhdidong.Server;
import com.example.doanlaptrinhdidong.model.Bill;
import com.example.doanlaptrinhdidong.model.Room;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BillAdapter extends RecyclerView.Adapter<KHUNGNHINBILL> {
    Context context;
    ArrayList<Bill> data;
    int layout;

    public BillAdapter(Context context, ArrayList<Bill> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINBILL onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINBILL(LayoutInflater.from(context).inflate(layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINBILL holder, int position) {
        int index = position;
        Bill bill = data.get(index);
        //holder.txtNameRoomBill.setText(bill.getID_PHONG());
        holder.txtMoneyBill.setText(bill.getTONGTIEN());
        loadNameRoom(bill.getID_PHONG(), holder.txtNameRoomBill);
        //1 : đã thanh toán
        //0 : chưa thanh toán
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;

                if (bill.get_status().equals("1")){
                    intent = new Intent(context, Bill_Detail.class);

                }else{
                    intent = new Intent(context, Bill_False.class);

                }
                intent.putExtra("bill", bill);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
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

class KHUNGNHINBILL extends RecyclerView.ViewHolder {
    TextView txtNameRoomBill, txtMoneyBill;

    public KHUNGNHINBILL(@NonNull View itemView) {
        super(itemView);
        txtNameRoomBill = itemView.findViewById(R.id.textNameRoomBill);
        txtMoneyBill = itemView.findViewById(R.id.textMoneyBill);
    }
}
