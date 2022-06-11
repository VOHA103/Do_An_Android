package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doanlaptrinhdidong.R;
import com.example.doanlaptrinhdidong.model.TypeRoom;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TypeRoomAdapter extends RecyclerView.Adapter<KHUNGNHINTYPEROOM>{
    Context context;
    ArrayList<TypeRoom> data;
    int layout;

    public TypeRoomAdapter(Context context, ArrayList<TypeRoom> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINTYPEROOM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINTYPEROOM(LayoutInflater.from(context).inflate(layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINTYPEROOM holder, int position) {
        int index=position;
        TypeRoom typeRoom=data.get(index);
        holder.txtNameTypeRoom.setText(typeRoom.getTENLOAI());
        holder.txtPriceTypeRoom.setText(typeRoom.getGIAPHONG());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNGNHINTYPEROOM extends RecyclerView.ViewHolder {
TextView txtNameTypeRoom,txtPriceTypeRoom;
    public KHUNGNHINTYPEROOM(@NonNull View itemView) {
        super(itemView);
        txtNameTypeRoom=itemView.findViewById(R.id.textItemNameTypeRoom);
        txtPriceTypeRoom=itemView.findViewById(R.id.textItemPriceTypeRoom);;
    }
}
