package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Adapter.GioHangAdapter;
import com.k5_banhang.Model.DatHangResponse;
import com.k5_banhang.Model.GioHang;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DatHangActivity extends AppCompatActivity {

    private ListView lvSPDatHang;
    private TextView tv_tongtien;
    private Button btnXacNhanTT;
    private EditText txtTenKH, txtEmailKH, txtSdtKH, txtDiaChiKH;
    private APIService apiService;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dathang);

        anhxa();
        initData();
        btnXacNhanTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyThanhToan();
            }
        });
    }
    private void anhxa(){
        lvSPDatHang = findViewById(R.id.lv_sp_dathang);
        btnXacNhanTT = findViewById(R.id.btnXNTT);
        tv_tongtien = findViewById(R.id.tvtongtien);
        txtTenKH = findViewById(R.id.txt_tenKH);
        txtEmailKH = findViewById(R.id.txt_emailKH);
        txtSdtKH = findViewById(R.id.txt_sdtKH);
        txtDiaChiKH = findViewById(R.id.txt_diachi);
    }

    private void initData() {
        GioHangAdapter adapter = new GioHangAdapter(DatHangActivity.this, HomeActivity.mangGioHang);
        adapter.setUpdateTongTienListener(new GioHangAdapter.UpdateTongTienListener() {
            @Override
            public void onUpdateTongTien() {
                tv_tongtien.setText(loadTongCong(HomeActivity.mangGioHang));
            }
        });
        lvSPDatHang.setAdapter(adapter);
        tv_tongtien.setText(loadTongCong(HomeActivity.mangGioHang));
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    private String loadTongCong(List<GioHang> list) {
        long tongtien = 0;
        for (int i = 0; i < list.size(); i++){
            tongtien+=list.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        return decimalFormat.format(tongtien)+ " Đ ";
    }

    public void finish(){
        super.finish();
        setResult(RESULT_OK);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    private void xulyThanhToan(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ngaydathang = dateFormat.format(new Date());
        JSONArray jsonArray = new JSONArray();

        // Tạo mảng JSON từ danh sách giỏ hàng
        for (int i = 0; i < HomeActivity.mangGioHang.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("id_sanpham", HomeActivity.mangGioHang.get(i).getIdsp());
                jsonObject.put("soluong", HomeActivity.mangGioHang.get(i).getSoluong());
                jsonObject.put("gia",HomeActivity.mangGioHang.get(i).getGiasp());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        int id_user = MainActivity.idUser;
        String tenkh = txtTenKH.getText().toString();
        String emailkh = txtEmailKH.getText().toString();
        String sdtkh = txtSdtKH.getText().toString();
        String diachikh = txtDiaChiKH.getText().toString();

        // Tạo một đối tượng JSONObject để chứa các thông tin khách hàng
        JSONObject khachHangObject = new JSONObject();
        try {
            khachHangObject.put("id_user", id_user);
            khachHangObject.put("tenkh", tenkh);
            khachHangObject.put("emailkh", emailkh);
            khachHangObject.put("sdtkh", sdtkh);
            khachHangObject.put("diachikh", diachikh);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Tạo một đối tượng JSONObject chứa thông tin đơn hàng
        JSONObject donHangObject = new JSONObject();
        try {
            donHangObject.put("ngaydathang", ngaydathang);
            donHangObject.put("khachhang", khachHangObject);
            donHangObject.put("giohang", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Gửi đơn hàng lên server PHP
        apiService.insertDonHang(donHangObject.toString()).enqueue(new Callback<DatHangResponse>() {

            @Override
            public void onResponse(Call<DatHangResponse> call, Response<DatHangResponse> response) {
                DatHangResponse response1 = response.body();
                if(response1.getSuccess() == 1){
                    setResult(Contans.RESULT_CODE_DATHANG);
                    Toast.makeText(DatHangActivity.this, "Đặt hàng thành công!\n Mời bạn tiếp tục mua hàng", Toast.LENGTH_SHORT).show();
                    finish();
                    HomeActivity.mangGioHang.clear();
                    Intent intent = new Intent(DatHangActivity.this, HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.right_out);
                }else{
                    Toast.makeText(DatHangActivity.this,"Đặt hàng thất bại!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DatHangResponse> call, Throwable t) {
                Toast.makeText(DatHangActivity.this, "Lỗi đặt hàng", Toast.LENGTH_SHORT).show();

                finish();
                HomeActivity.mangGioHang.clear();
                Intent intent = new Intent(DatHangActivity.this,HomeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_in,R.anim.right_out);
            }
        });
    }
}
