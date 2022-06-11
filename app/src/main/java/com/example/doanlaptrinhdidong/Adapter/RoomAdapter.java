package com.example.doanlaptrinhdidong.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.doanlaptrinhdidong.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doanlaptrinhdidong.RoomDetail;
import com.example.doanlaptrinhdidong.RoomDetailFalse;
import com.example.doanlaptrinhdidong.Server;
import com.example.doanlaptrinhdidong.ThemTTKhachHangActivity;
import com.example.doanlaptrinhdidong.model.Room;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RoomAdapter extends RecyclerView.Adapter<KHUNGNHINROOM>{
    Context context;
    ArrayList<Room> data;
    int layout;
    public RoomAdapter(Context context, ArrayList<Room> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @NonNull
    @Override
    public KHUNGNHINROOM onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KHUNGNHINROOM(LayoutInflater.from(context).inflate(layout,null));
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHINROOM holder, int position) {
        int index=position;
        Room room=data.get(index);
        holder.txtNameTypeRoom.setText(room.getID_LOAIPHONG());
        holder.txtNameRoom.setText(room.getTENPHONG());
        Picasso.get().load(Server.urlHinhAnh+room.getHINH()).into(holder.imageRoom);
        Log.d(Server.urlHinhAnh+room.getHINH()+"FIX", "onBindViewHolder: ");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(room.get_status().equals("1"))
                     intent= new Intent(context, RoomDetail.class);
                else
                    intent= new Intent(context, ThemTTKhachHangActivity.class);
                intent.putExtra("room",room);
                context.startActivity(intent);

            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent= new Intent(context, ThemTTKhachHangActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
class KHUNGNHINROOM extends RecyclerView.ViewHolder{
    TextView txtNameRoom,txtNameTypeRoom;
    ImageView imageRoom;
    public KHUNGNHINROOM(@NonNull View itemView) {
        super(itemView);
        txtNameRoom=itemView.findViewById(R.id.textItemNameRoom);
        txtNameTypeRoom=itemView.findViewById(R.id.textItemRoomNameTypeRoom);
        imageRoom=itemView.findViewById(R.id.imageItemRoom);


    }
}
