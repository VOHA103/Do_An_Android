package com.example.doanlaptrinhdidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.doanlaptrinhdidong.Adapter.CustomerAdapter;
import com.example.doanlaptrinhdidong.Adapter.RoomAdapter;
import com.example.doanlaptrinhdidong.model.Customer;
import com.example.doanlaptrinhdidong.model.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomerPage extends Fragment {
    Context context;

    RecyclerView recyclerView;
    ArrayList<Customer> arrayListCustomer = new ArrayList<>();
    CustomerAdapter customerAdapter;
    public CustomerPage(Context context) {
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewCustomerPage);
        customerAdapter = new CustomerAdapter(context, arrayListCustomer, R.layout.item_customer);
        loadCustomer();
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_customer_page,container,false);
    }
    public void loadCustomer() {
        String url = Server.urlGetCustomer;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("fix", "onResponse: " + url);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d("fix", "onResponse: " + arrayListCustomer.size());
                        JSONObject customer = response.getJSONObject(i);
                        Customer r = new Customer(customer.getString("ID_KHACHHANG")
                                ,customer.getString("TENKHACHHANG")
                                ,customer.getString("GIOITINH")
                                ,customer.getString("DIACHI")
                                ,customer.getString("CMND")
                                ,customer.getString("SDT")
                                ,customer.getString("HINH")
                                ,customer.getString("_status"));
                        arrayListCustomer.add(r);
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                customerAdapter.notifyDataSetChanged();
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
}