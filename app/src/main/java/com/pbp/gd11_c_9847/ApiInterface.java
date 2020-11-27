package com.pbp.gd11_c_9847;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("buku")
    Call<UserResponse> getAllUser(@Query("dataBuku") String data);


}
