package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.k5_banhang.Adapter.SanphamAdapter;
import com.k5_banhang.Model.GioHang;
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

public class HomeActivity extends AppCompatActivity {
    private APIService apiService;
    RecyclerView rc_loadMathang;
    private List<SanPham> sanPhamList;
    ImageView im_giohang,im_profile;

    public static List<GioHang> mangGioHang;
    LinearLayout ll_giohang;
    private static  TextView sl_tronggio;
    private boolean close = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mangGioHang = new ArrayList<>();
        Anhxa();
        initData();
        LoadSanPham();
        im_giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
        im_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        im_giohang = (ImageView) findViewById(R.id.iv_giohang);
        rc_loadMathang = (RecyclerView) findViewById(R.id.rv_MatHang);
        sl_tronggio = (TextView) findViewById(R.id.sl_giohang);
        im_profile = (ImageView) findViewById(R.id.iv_profile);
    }

    private void initData() {
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
                            SanphamAdapter SanphamAdapter = new SanphamAdapter(HomeActivity.this, sanPhamList);
                            rc_loadMathang.setLayoutManager(new GridLayoutManager(HomeActivity.this,1));
                            rc_loadMathang.setAdapter(SanphamAdapter);
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
                Toast.makeText(HomeActivity.this, "Lỗi khi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(mangGioHang != null) sl_tronggio.setText(mangGioHang.size() + "");
        close = false;
    }

    public static void setSoLuong(int sl){
        sl_tronggio.setText(sl + "");
    }
    private void initEvent(){
        ll_giohang.setOnClickListener((View.OnClickListener) this);
    }
    private void choseTv(TextView view){
        view.setTextColor(getResources().getColor(R.color.do_hong));
    }
}
