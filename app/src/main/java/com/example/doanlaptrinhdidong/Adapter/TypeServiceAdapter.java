package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.model.TypeService;

import java.util.ArrayList;

public class TypeServiceAdapter extends RecyclerView.Adapter<KHUNGNHINTYPESERVICE>{
    Context context;
    ArrayList<TypeService> data;
    int layout;

    public TypeServiceAdapter(Context context, ArrayList<TypeService> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINTYPESERVICE onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINTYPESERVICE(LayoutInflater.from(context).inflate(layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINTYPESERVICE holder, int position) {
        int index=position;
        TypeService typeService=data.get(index);
        holder.txtNameService.setText(typeService.getTENDICHVU());
        holder.txtPriceService.setText(typeService.getDONGIA());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNGNHINTYPESERVICE extends RecyclerView.ViewHolder{
    TextView txtNameService,txtPriceService;
    public KHUNGNHINTYPESERVICE(@NonNull View itemView) {
        super(itemView);
        txtNameService=itemView.findViewById(R.id.textItemNameTypeService);
        txtPriceService=itemView.findViewById(R.id.textItemPriceTypeService);
    }
}
