package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.Model.LoaiHang;
import com.k5_banhang.Model.NhaCungCap;
import com.k5_banhang.Model.NhaCungCapResponse;
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


public class NhapSPActivity extends AppCompatActivity {

    ImageView imgv_exit;
    Spinner sp_nhaCC, sp_matHang;
    EditText ed_SL;
    Button btn_Nhap;
    private List<SanPham> sanPhamList;
    private List<NhaCungCap> NhaCungCapList;
    private List<SanPham> MatHangList;
    private List<String> mangNCC;
    private List<String> mangMH;
    private List<String> mangidMH;
    private APIService apiService;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhapsp);
        mangMH = new ArrayList<String>();
        mangNCC = new ArrayList<String>();
        mangidMH = new ArrayList<String>();
        anhxa();
        LoadMatHang();
        LoadNhaCC();

        imgv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NhapSPActivity.this, QuanLySPActivity.class);
                startActivity(intent);
            }
        });
        btn_Nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Long maMH = sp_matHang.getSelectedItemId();

                Long maNCC = sp_nhaCC.getSelectedItemId();
                String stringmaNCC = String.valueOf(maNCC + 1);
                String stringmaMH = mangidMH.get(Math.toIntExact(maMH));

                String SoLuong = ed_SL.getText().toString().trim();

                NhapSP(stringmaMH,SoLuong,stringmaNCC);
            }
        });
    }

    private void NhapSP(String MaMH, String SL, String MaNCC) {
        apiService.nhap(MaMH,SL,MaNCC).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, Response<InsertSPResponse> response) {
                InsertSPResponse insertSPResponse = response.body();

                if(insertSPResponse.getSuccess() == 1){
                    Intent intent = new Intent(NhapSPActivity.this, QuanLySPActivity.class);
                    startActivity(intent);
                    Toast.makeText(NhapSPActivity.this, "Nhập sản phẩm thành công", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 2){
                    Toast.makeText(NhapSPActivity.this, "lỗi sql nhập sản phẩm", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 3){
                    Toast.makeText(NhapSPActivity.this, "Lỗi sql update số lượng", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 4){
                    Toast.makeText(NhapSPActivity.this, "Lỗi post giá trioj null", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
                Toast.makeText(NhapSPActivity.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void anhxa() {
        sp_nhaCC = findViewById(R.id.sp_nhaCC);
        sp_matHang = findViewById(R.id.sp_matHang);
        ed_SL = findViewById(R.id.ed_soluong);
        btn_Nhap = findViewById(R.id.btn_nhap);
        imgv_exit = findViewById(R.id.imgv_exit);

        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    private void LoadNhaCC(){
        apiService.getNCC().enqueue(new Callback<NhaCungCapResponse>() {
            @Override
            public void onResponse(Call<NhaCungCapResponse> call, Response<NhaCungCapResponse> response) {
                NhaCungCapResponse nccResponse = response.body();
                if(nccResponse != null){
                    NhaCungCapList = nccResponse.getResult();
                    if (NhaCungCapList != null) {
                        for (int i = 0; i < NhaCungCapList.size(); i++) {
                            mangNCC.add("" + NhaCungCapList.get(i).getTenncc());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NhapSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mangNCC);
                        sp_nhaCC.setAdapter(adapter);
                        Log.e("aa",""+mangNCC);
                    }else {
                        Log.e("LoadSanPham","Danh Sach Loai Hang Trong");
                    }
                }else {
                    Log.e("LoadSanPham","Null");
                }
            }

            @Override
            public void onFailure(Call<NhaCungCapResponse> call, Throwable t) {

            }
        });
    }

    private void LoadMatHang(){
        apiService.getAllsp().enqueue(new Callback<SanphamResponse>() {
            @Override
            public void onResponse(Call<SanphamResponse> call, Response<SanphamResponse> response) {
                SanphamResponse MHResponse = response.body();
                if(MHResponse != null){
                    MatHangList = MHResponse.getResult();
                    if (MatHangList != null) {
                        for (int i = 0; i < MatHangList.size(); i++) {
                            mangMH.add("" + MatHangList.get(i).getTensanpham());
                            mangidMH.add(MatHangList.get(i).getMa_mh());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NhapSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mangMH);
                        sp_matHang.setAdapter(adapter);
                        Log.e("aa",""+mangMH);
                    }else {
                        Log.e("LoadSanPham","Danh Sach Loai Hang Trong");
                    }
                }else {
                    Log.e("LoadSanPham","Null");
                }
            }

            @Override
            public void onFailure(Call<SanphamResponse> call, Throwable t) {

            }
        });
    }
}
