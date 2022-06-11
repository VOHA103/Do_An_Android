package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
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
import com.example.doanlaptrinhdidong.Adapter.TypeServiceAdapter;
import com.example.doanlaptrinhdidong.model.TypeRoom;
import com.example.doanlaptrinhdidong.model.TypeService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentShowTypeService extends Fragment {
Context context;
    RecyclerView recyclerTypeService;
    ArrayList<TypeService> listTypeServices = new ArrayList<>();
    TypeServiceAdapter typeServiceAdapter;

    public FragmentShowTypeService(Context context) {
        this.context = context;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerTypeService = view.findViewById(R.id.serviceTypeList);
         typeServiceAdapter= new TypeServiceAdapter(context,listTypeServices,R.layout.item_typeservice);
        loadTypeService();
        recyclerTypeService.setAdapter(typeServiceAdapter);
        recyclerTypeService.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

    }

    private void loadTypeService() {
        String url = Server.urlTypeService;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Response.Listener<JSONArray> thanhCong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length();i++){
                    try {
                        JSONObject typeService=response.getJSONObject(i);
                        TypeService typeservice=new TypeService(typeService.getString("ID_LOAIDICHVU"),typeService.getString("TENDICHVU"),
                                typeService.getString("DONGIA"),typeService.getString("_status"));
                        listTypeServices.add(typeservice);
                    } catch(JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                typeServiceAdapter.notifyDataSetChanged();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_show_type_service, container, false);

    }
}