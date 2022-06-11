package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
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
import com.example.doanlaptrinhdidong.Adapter.ContractAdapter;
import com.example.doanlaptrinhdidong.Adapter.RoomAdapter;
import com.example.doanlaptrinhdidong.model.Contract;
import com.example.doanlaptrinhdidong.model.Room;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContractPage extends Fragment {
    Context context;
    RecyclerView recyclerView;
    ArrayList<Contract> arrayListContract = new ArrayList<>();
    ContractAdapter adapterContract;
    public ContractPage(Context context) {
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //anh xa
        recyclerView=view.findViewById(R.id.recyclerViewContract);
        adapterContract = new ContractAdapter(context, arrayListContract, R.layout.item_contract);
        loadContract();
        recyclerView.setAdapter(adapterContract);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void loadContract() {
        String url = Server.urlContract;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("fix", "onResponse: " + url);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d("fix", "onResponse: " + arrayListContract.size());
                        JSONObject contract = response.getJSONObject(i);
                        Contract r = new Contract(contract.getString("ID_HOPDONG")
                                ,contract.getString("ID_PHONG")
                                ,contract.getString("ID_KHACHHANG")
                                ,contract.getString("TIENCOC")
                                ,contract.getString("NGAYTHUE")
                                ,contract.getString("NGAYTRA")
                                ,contract.getString("_status"));
                        arrayListContract.add(r);
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                adapterContract.notifyDataSetChanged();
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contract_page,container,false);
    }
}