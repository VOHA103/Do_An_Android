package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.Update_Admin;
import com.example.doanlaptrinhdidong.model.Admin;
import com.example.doanlaptrinhdidong.model.TypeRoom;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<KHUNGNHIN_ADMIN> {
    Context context;
    ArrayList<Admin> data;
    public AdminAdapter(Context context, ArrayList<Admin> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public KHUNGNHIN_ADMIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin, null);
        return new KHUNGNHIN_ADMIN(view);


    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN_ADMIN holder, int position) {
        int index = position;
      final   Admin admin = data.get(index);

        holder.txtTAIKHOAN.setText(admin.getTAIKHOAN());
        holder.txtMatKhau.setText(admin.getMATKHAU());
        holder.txtPhanQuyen.setText(admin.getPHANQUYEN());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update_Admin.class);
                intent.putExtra("data", admin);
                context.startActivity(intent);
            }
        });
        }




    @Override
    public int getItemCount() {
        return data.size();
    }
}

class KHUNGNHIN_ADMIN extends RecyclerView.ViewHolder {
    TextView txtTAIKHOAN, txtMatKhau, txtPhanQuyen;
    ImageView imgSua;

    public KHUNGNHIN_ADMIN(@NonNull View itemView) {
        super(itemView);
        txtTAIKHOAN = itemView.findViewById(R.id.txtTaiKhoan);
        txtMatKhau = itemView.findViewById(R.id.txtMatKhau);
        txtPhanQuyen = itemView.findViewById(R.id.txtPhanQuyen);
        imgSua = itemView.findViewById(R.id.imgAdmin);


    }
}