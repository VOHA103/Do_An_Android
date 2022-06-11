package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.Server;
import com.example.doanlaptrinhdidong.model.Contract;
import com.example.doanlaptrinhdidong.model.Customer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<KHUNGNHINCUSTOMER>{
    Context context;
    ArrayList<Customer> data;
    int layout;

    public CustomerAdapter(Context context, ArrayList<Customer> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINCUSTOMER onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINCUSTOMER(LayoutInflater.from(context).inflate(layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINCUSTOMER holder, int position) {
        int index=position;
        Customer customer=data.get(index);
        holder.txtTenKhachHang.setText(customer.getTENKHACHHANG());
        holder.txtSoDienThoai.setText(customer.getSDT());
        Picasso.get().load(Server.urlHinhAnh+customer.getHINH()).into(holder.imgKhachHang);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNGNHINCUSTOMER extends RecyclerView.ViewHolder{
    TextView txtTenKhachHang,txtSoDienThoai;
    ImageView imgKhachHang;
    public KHUNGNHINCUSTOMER(@NonNull View itemView) {
        super(itemView);
        txtTenKhachHang = itemView.findViewById(R.id.textTenKhachHang);
        txtSoDienThoai = itemView.findViewById(R.id.textSoDienThoai);
        imgKhachHang = itemView.findViewById(R.id.imageKhachHang);
    }
}
