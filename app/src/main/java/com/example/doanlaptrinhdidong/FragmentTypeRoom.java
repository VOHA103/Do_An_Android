package com.example.doanlaptrinhdidong;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.doanlaptrinhdidong.Adapter.TypeRoomAdapter;
import com.example.doanlaptrinhdidong.model.TypeRoom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentTypeRoom extends Fragment {
    Context context;
    RecyclerView recyclerTypeRoom;
    ArrayList<TypeRoom> typeRooms = new ArrayList<>();
    TypeRoomAdapter typeRoomAdapter;
int i=0;
    public FragmentTypeRoom(Context context,int i) {
        this.context = context;
        this.i=i;
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerTypeRoom = view.findViewById(R.id.roomTypeList);
        typeRoomAdapter = new TypeRoomAdapter(context, typeRooms, R.layout.item_typeroom);
        i++;
        if (i==1)
        loadTypeRoom();
        recyclerTypeRoom.setAdapter(typeRoomAdapter);
        recyclerTypeRoom.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void loadTypeRoom() {
        String url = Server.urlTypeRoom;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Response.Listener<JSONArray> thanhCong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length();i++){
                    try {
                        JSONObject typeRoom=response.getJSONObject(i);
TypeRoom typeRoom1=new TypeRoom(typeRoom.getString("ID_LOAIPHONG"),typeRoom.getString("TENLOAI"),
        typeRoom.getString("GIAPHONG"),typeRoom.getString("_status"));
                        typeRooms.add(typeRoom1);
                    } catch(JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                typeRoomAdapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatBai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context.getApplicationContext(),"Loi LoadData"+error.toString(),Toast.LENGTH_LONG).show();

            }
        };
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url,thanhCong,thatBai);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type_room, container, false);
    }
}