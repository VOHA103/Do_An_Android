package com.example.doanlaptrinhdidong.Retrofit2;

import com.example.doanlaptrinhdidong.Server;

public class APIUtils {
    public static DataClient getData()
    {
        return RetrofitClient.getClient(Server.url).create(DataClient.class);
    }
}
