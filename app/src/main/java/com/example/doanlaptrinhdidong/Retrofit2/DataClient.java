package com.example.doanlaptrinhdidong.Retrofit2;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploadHinhAnh.php")
    Call<String> UploadImage(@Part MultipartBody.Part image);

//    @FormUrlEncoded
//    @POST("getCustomer.php")
//    Call<String> InsertData(@Field("TENKHACHHANG") String TENKHACHHANG  ,
//                                        @Field("GIOITINH") String GIOITINH ,
//                                        @Field("DIACHI") String DIACHI ,
//                                        @Field("CMND") String CMND ,
//                                        @Field("SDT")  String SDT,
//                                        @Field("HINH") String HINH ,
//                                        @Field("_status") String _status
//                                          );
}
