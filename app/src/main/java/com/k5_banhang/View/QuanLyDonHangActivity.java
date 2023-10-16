package com.k5_banhang.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.k5_banhang.Adapter.AllDonhangAdapter;
import com.k5_banhang.Model.AllDonHang;
import com.k5_banhang.Model.AllDonHangResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLyDonHangActivity extends AppCompatActivity {

    ImageView imgv_exit;

    RecyclerView rv_DonHang;
    private APIService apiService;
    private List<AllDonHang> AllDonHangList;
    AllDonhangAdapter allDonhangAdapter;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlydonhang);
        anhxa();
        LoadDonhang();
    }

    private void LoadDonhang() {
        apiService.getDH().enqueue(new Callback<AllDonHangResponse>() {
            @Override
            public void onResponse(Call<AllDonHangResponse> call, Response<AllDonHangResponse> response) {
                AllDonHangResponse allDonHangResponse = response.body();
                if(allDonHangResponse.getSuccess() == 0){
                    AllDonHangList = allDonHangResponse.getResult();
                    if(AllDonHangList != null && !AllDonHangList.isEmpty()){
                        AllDonhangAdapter allDonhangAdapter = new AllDonhangAdapter(QuanLyDonHangActivity.this, AllDonHangList);
                        rv_DonHang.setLayoutManager(new GridLayoutManager(QuanLyDonHangActivity.this,1));
                        rv_DonHang.setAdapter(allDonhangAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<AllDonHangResponse> call, Throwable t) {
                Log.e("LoadSanPham", "Lỗi khi gọi API: " + t.getMessage());
                Toast.makeText(QuanLyDonHangActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void anhxa() {
        imgv_exit = findViewById(R.id.imgv_exit);
        rv_DonHang = findViewById(R.id.rv_DonHang);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);

        AllDonHangList = new ArrayList<>();
        allDonhangAdapter = new AllDonhangAdapter(QuanLyDonHangActivity.this, AllDonHangList);
    }

}
