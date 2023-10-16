package com.k5_banhang.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Adapter.GioHangAdapter;
import com.k5_banhang.Model.GioHang;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangActivity extends AppCompatActivity {
    private TextView tvTongTien, btnThanhToan, tvTrong , btnExit;
    private LinearLayout llThongtin;
    private ListView lvSPGioHang;
    private List<SanPham> sanPhamList;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
        initData();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish(); // Kết thúc Activity hiện tại
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GioHangActivity.this, DatHangActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
    private void anhxa() {
        tvTongTien = findViewById(R.id.tv_tongtien);
        btnThanhToan = findViewById(R.id.btn_thanhtoan);
        llThongtin = findViewById(R.id.ll_thongtin);
        lvSPGioHang = findViewById(R.id.lv_sp_giohang);
        tvTrong = findViewById(R.id.tv_trong);
        btnExit = findViewById(R.id.btnExit);
    }

    private void initData() {
        if(HomeActivity.mangGioHang.size() < 1){
            tvTrong.setVisibility(View.VISIBLE);
            llThongtin.setVisibility(View.GONE);
            tvTongTien.setText("0 Đ");
        }else{
            tvTrong.setVisibility(View.GONE);
            llThongtin.setVisibility(View.VISIBLE);
            GioHangAdapter adapter = new GioHangAdapter(GioHangActivity.this, HomeActivity.mangGioHang);
            adapter.setUpdateTongTienListener(new GioHangAdapter.UpdateTongTienListener() {
                @Override
                public void onUpdateTongTien() {
                    tvTongTien.setText(loadTongCong(HomeActivity.mangGioHang));
                }
            });
            lvSPGioHang.setAdapter(adapter);
            tvTongTien.setText(loadTongCong(HomeActivity.mangGioHang));
        }
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
}
