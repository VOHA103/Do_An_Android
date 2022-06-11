package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanlaptrinhdidong.model.Service;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<KHUNGNHINSERVICE>{
    Context context;
    int layout;
    ArrayList<Service> data;

    @NonNull
    @Override
    public KHUNGNHINSERVICE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINSERVICE(LayoutInflater.from(context).inflate(layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINSERVICE holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNGNHINSERVICE extends RecyclerView.ViewHolder{

    public KHUNGNHINSERVICE(@NonNull View itemView) {
        super(itemView);
    }
}
