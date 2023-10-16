package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.k5_banhang.Adapter.SanPhamADMINAdapter;
import com.k5_banhang.Adapter.SanphamAdapter;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.Model.SanphamResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuanLySPActivity extends AppCompatActivity {

    private APIService apiService;
    RecyclerView rc_loadMathang;
    ImageView imgv_addSP,imgv_nhapSP,imgv_profile;
    private List<SanPham> sanPhamList;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlysanpham);
        anhxa();
        LoadSanPham();

        imgv_addSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySPActivity.this, ThemSPActivity.class);
                startActivity(intent);
            }
        });
        imgv_nhapSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySPActivity.this, NhapSPActivity.class);
                startActivity(intent);
            }
        });
        imgv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLySPActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
    private void anhxa(){
        imgv_nhapSP = findViewById(R.id.imgv_nhapSP);
        rc_loadMathang = findViewById(R.id.rv_MatHangAdmin);
        imgv_addSP = findViewById(R.id.imgv_addSP);
        imgv_profile = findViewById(R.id.imgv_profile);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }
    private void LoadSanPham() {
        apiService.getAllsp().enqueue(new Callback<SanphamResponse>() {
            @Override
            public void onResponse(Call<SanphamResponse> call, Response<SanphamResponse> response) {
                if (response.isSuccessful()) {
                    SanphamResponse spResponse = response.body();
                    if (spResponse != null) {
                        sanPhamList = spResponse.getResult();
                        if (sanPhamList != null && !sanPhamList.isEmpty()) {
                            SanPhamADMINAdapter SanPhamADMINAdapter = new SanPhamADMINAdapter(QuanLySPActivity.this, sanPhamList);
                            rc_loadMathang.setLayoutManager(new GridLayoutManager(QuanLySPActivity.this,1));
                            rc_loadMathang.setAdapter(SanPhamADMINAdapter);
                        } else {
                            // Danh sách sản phẩm trống
                            Log.e("LoadSanPham", "Danh sách sản phẩm trống");
                        }
                    } else {
                        // Response body là null
                        Log.e("LoadSanPham", "Response body là null");
                    }
                } else {
                    // Response không thành công
                    Log.e("LoadSanPham", "Response không thành công");
                }
            }

            @Override
            public void onFailure(Call<SanphamResponse> call, Throwable t) {
                Log.e("LoadSanPham", "Lỗi khi gọi API: " + t.getMessage());
                Toast.makeText(QuanLySPActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
