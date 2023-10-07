package com.k5_banhang.remote;

import com.k5_banhang.Model.DangKyResponse;
import com.k5_banhang.Model.DatHangResponse;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.Model.SanphamResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {

    //login hệ thống
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(@Field("username") String username,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("dangky.php")
    Call<DangKyResponse> dangky(@Field("username") String username,
                                 @Field("password") String password,
                                @Field("name") String name,
                                @Field("sdt") String sdt,
                                @Field("email") String email);

    @GET("sanphamall.php")
    Call<SanphamResponse> getAllsp();

    @FormUrlEncoded
    @POST("dathang.php")
    Call<DatHangResponse>insertDonHang(@Field("json") String json);
}
