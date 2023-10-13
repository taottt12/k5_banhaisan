package com.k5_banhang.remote;

import com.k5_banhang.Model.DangKyResponse;
import com.k5_banhang.Model.DatHangResponse;
import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.Model.LoaihangResponse;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.Model.NhaCungCapResponse;
import com.k5_banhang.Model.SanphamResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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


    @FormUrlEncoded
    @POST("insertSP.php")
    Call<InsertSPResponse> insertSP(@Field("tensanpham") String tensanpham,
                                  @Field("dongia") String dongia,
                                  @Field("donvitinh") String donvitinh,
                                  @Field("hinhanh") String hinhanh,
                                  @Field("mancc") String mancc,
                                    @Field("malh") String malh);


    @Multipart
    @POST("uploadimg.php")
    Call<String> Uploadimg(@Part MultipartBody.Part photo);

    @GET("sanphamall.php")
    Call<SanphamResponse> getAllsp();

    @GET("get_ncc.php")
    Call<NhaCungCapResponse> getNCC();

    @GET("get_lh.php")
    Call<LoaihangResponse> getLH();

    @FormUrlEncoded
    @POST("dathang.php")
    Call<DatHangResponse>insertDonHang(@Field("json") String json);
}
