package com.example.doanlaptrinhdidong;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.Adapter.RoomAdapter;
import com.example.doanlaptrinhdidong.model.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentShowAll extends Fragment {
    Context context;
    RecyclerView recyclerView;
    ArrayList<Room> arrayListRoom = new ArrayList<>();
    RoomAdapter adapterRoom;
    int i=0;
    public FragmentShowAll(Context context,int i) {
        this.context=context;
        this.i=i;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.listRoomAll);
        adapterRoom = new RoomAdapter(context, arrayListRoom, R.layout.item_room);
        i++;
        if (i==1)
        loadRoom();
        recyclerView.setAdapter(adapterRoom);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    public void loadRoom() {
        String url = Server.urlRoom;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("fix", "onResponse: " + url);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d("fix", "onResponse: " + arrayListRoom.size());
                        JSONObject room = response.getJSONObject(i);
                        Room r = new Room(room.getString("ID_PHONG")
                                ,room.getString("ID_LOAIPHONG"),
                                room.getString("TENPHONG"),
                                room.getString("DIACHI"),
                                room.getString("HINH")
                                ,room.getString("_status"));
                        arrayListRoom.add(r);
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                adapterRoom.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(),"Loi LoadData"+error.toString(),Toast.LENGTH_LONG).show();
            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhcong,thatbai);
        requestQueue.add(jsonArrayRequest);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_show_all, container, false);
    }
}