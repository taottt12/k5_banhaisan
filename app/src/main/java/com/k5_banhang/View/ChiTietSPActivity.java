package com.k5_banhang.View;

import static com.squareup.picasso.Picasso.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.k5_banhang.Model.GioHang;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.R;
import com.k5_banhang.remote.Contans;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


public class ChiTietSPActivity extends AppCompatActivity {

    private ImageView ivGioHang, ivSanpham;
    private TextView tvTenSP, tvGiaSP, tvDVT, tvMoTa;
    private Spinner spSL;
    private Button btnExit, btnThem;

    int id = 0;
    String tenChiTiet = "", donvt ="";
    long giachitiet = 0;
    String hinhanhchitiet = "";
    int idloaisp = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        anhxa();
        catEventSpinner();
        getDataSP();
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Kết thúc Activity hiện tại
            }
        });
        ivGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietSPActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.idUser != 0){
                    xulyThemSPvaoGio();
                }else{
                    Toast.makeText(ChiTietSPActivity.this, "Bạn cần đăng nhập trước khi thực hiện thao tác mua", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChiTietSPActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    private void xulyThemSPvaoGio() {
        if (HomeActivity.mangGioHang.size() > 0){
            int sl = Integer.parseInt(spSL.getSelectedItem().toString());
            boolean exit = false;
            for (int i = 0; i < HomeActivity.mangGioHang.size(); i++){
                if(HomeActivity.mangGioHang.get(i).getIdsp() == id){
                    HomeActivity.mangGioHang.get(i).setSoluong(HomeActivity.mangGioHang.get(i).getSoluong() + sl);
                    if(HomeActivity.mangGioHang.get(i).getSoluong() >=10){
                        HomeActivity.mangGioHang.get(i).setSoluong(10);
                    }
                    HomeActivity.mangGioHang.get(i).setGiasp(giachitiet * HomeActivity.mangGioHang.get(i).getSoluong());
                    exit = true;
                }
            }
            if(!exit){
                int soluong = Integer.parseInt(spSL.getSelectedItem().toString());
                long giamoi = soluong * giachitiet;
                HomeActivity.mangGioHang.add(new GioHang(id, tenChiTiet, giamoi, hinhanhchitiet, soluong));
            }
        }else{
            int soluong = Integer.parseInt(spSL.getSelectedItem().toString());
            long giamoi = soluong * giachitiet;
            HomeActivity.mangGioHang.add(new GioHang(id, tenChiTiet, giamoi, hinhanhchitiet, soluong));
        }
        Toast.makeText(ChiTietSPActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        finish();
    }
    private void anhxa() {
        ivGioHang = findViewById(R.id.iv_giohang);
        ivSanpham = findViewById(R.id.imgCTSanPham);
        tvTenSP = findViewById(R.id.tv_tensp);
        tvGiaSP = findViewById(R.id.tv_giasp);
        tvDVT = findViewById(R.id.tv_dvt);
        spSL = findViewById(R.id.spinner);
        btnExit = findViewById(R.id.btnExit);
        btnThem = findViewById(R.id.btn_them);
    }
    private void catEventSpinner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        spSL.setAdapter(arrayAdapter);
        arrayAdapter.addAll(Arrays.asList(soluong));
    }
    private void getDataSP() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra(Contans.THONGTIN_SP);
        //lấy thông tin từ putextra
        id = Integer.parseInt(sanPham.getMa_mh());
        tenChiTiet = sanPham.getTensanpham();
        giachitiet = Long.parseLong(sanPham.getDongia());
        donvt = sanPham.getDonvitinh();
        hinhanhchitiet = sanPham.getHinhanh();
        //gán thông tin
        tvDVT.setText(donvt);
        tvTenSP.setText(tenChiTiet);
        Picasso.get().load(Contans.API_URL +"image/"+ sanPham.getHinhanh()).into(ivSanpham);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvGiaSP.setText("Giá: " + decimalFormat.format(giachitiet) + "Đ");
    }

    public void finish(){
        super.finish();
        setResult(RESULT_OK);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
