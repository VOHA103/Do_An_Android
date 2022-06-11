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
import com.example.doanlaptrinhdidong.Adapter.BillAdapter;
import com.example.doanlaptrinhdidong.model.Bill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentShowBillTrue extends Fragment {
    Context context;
    int i;
    RecyclerView recyclerView; //recyclerViewBillAll
    ArrayList<Bill> arrayListBill = new ArrayList<>();
    BillAdapter adapterBill;
    public FragmentShowBillTrue(Context context,int i) {
        this.context=context;
        this.i=i;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewBillTrue);
        adapterBill = new BillAdapter(context, arrayListBill, R.layout.item_bill);
        i++;
        if (i==1)
            loadBillTrue();
        recyclerView.setAdapter(adapterBill);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    public void loadBillTrue() {
        String url = Server.urlBillTrue;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("fix", "onResponse: " + url);
        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        Log.d("fix", "onResponse: " + arrayListBill.size());
                        JSONObject bill = response.getJSONObject(i);
                        Bill r = new Bill(bill.getString("ID_HOADON")
                                ,bill.getString("ID_PHONG")
                                ,bill.getString("TONGTIEN")
                                ,bill.getString("Thang_TToan")
                                ,bill.getString("_status"));
                        arrayListBill.add(r);
                    } catch (JSONException e) {
                        Toast.makeText(context.getApplicationContext(), "Lỗi: " + e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                adapterBill.notifyDataSetChanged();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_bill_true, container, false);
    }
}