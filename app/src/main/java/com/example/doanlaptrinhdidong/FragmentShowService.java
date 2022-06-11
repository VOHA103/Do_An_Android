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

import com.example.doanlaptrinhdidong.Adapter.RoomAdapter;
import com.example.doanlaptrinhdidong.Adapter.ServiceAdapter;
import com.example.doanlaptrinhdidong.model.Room;

import java.util.ArrayList;

public class FragmentShowService extends Fragment {
    Context context;
    RecyclerView recyclerView;
    ArrayList<Server> arrayListService = new ArrayList<>();
    ServiceAdapter serviceAdapter;

    public FragmentShowService(Context context) {
        this.context = context;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_show_service, container, false);

    }
}