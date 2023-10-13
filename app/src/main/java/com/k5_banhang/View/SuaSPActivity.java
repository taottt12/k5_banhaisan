package com.k5_banhang.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.LoaiHang;
import com.k5_banhang.Model.LoaihangResponse;
import com.k5_banhang.Model.NhaCungCap;
import com.k5_banhang.Model.NhaCungCapResponse;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaSPActivity extends AppCompatActivity {
    Spinner sp_ncc, sp_lh;
    EditText txt_tenSP, txt_giaSP, txt_DVT;
    ImageView imgv_upfile, imgv_chup, imgv_anh;
    Button btn_capnhat;
    private List<NhaCungCap> NhaCungCapList;
    private List<LoaiHang> LoaiHangList;
    public static List<String> mangncc;
    public static List<String> manglh;
    private APIService apiService;

    int id = 0;

    String tenChiTiet = "", donvt ="";
    long giachitiet = 0;
    String hinhanhchitiet = "";
    int idloaisp = 0, idncc = 0;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suasp);
        mangncc = new ArrayList<String>();
        manglh = new ArrayList<String>();
        anhxa();
        LoadNhaCC();
        LoadLoaiHang();
        sp_ncc.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mangncc));
        getDataSP();
    }

    private void anhxa() {
        txt_tenSP = findViewById(R.id.tv_tenSP);
        txt_giaSP = findViewById(R.id.tv_giaSP);
        txt_DVT = findViewById(R.id.tv_DVT);
        sp_ncc = findViewById(R.id.sp_ncc);
        sp_lh = findViewById(R.id.sp_lh);
        imgv_upfile = findViewById(R.id.imgv_upfile);
        imgv_chup = findViewById(R.id.imgv_chup);
        imgv_anh = findViewById(R.id.imgv_anh);
        btn_capnhat = findViewById(R.id.btn_update);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    private void getDataSP() {
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra(Contans.THONGTIN_SP);
        //lấy thông tin từ putextra
        id = Integer.parseInt(sanPham.getMa_mh());
        tenChiTiet = sanPham.getTensanpham();
        giachitiet = Long.parseLong(sanPham.getDongia());
        donvt = sanPham.getDonvitinh();
        hinhanhchitiet = sanPham.getHinhanh();
        idloaisp = Integer.parseInt(sanPham.getMalh());
        idncc = Integer.parseInt(sanPham.getMancc());
        //gán thông tin
        txt_DVT.setText(donvt);
        txt_tenSP.setText(tenChiTiet);
        Picasso.get().load(Contans.API_URL +"image/"+ sanPham.getHinhanh()).into(imgv_anh);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txt_giaSP.setText("Giá: " + decimalFormat.format(giachitiet) + "Đ");

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) sp_ncc.getAdapter();
        int position = adapter.getPosition(String.valueOf(idncc));
        sp_ncc.setSelection(position);

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
                            mangncc.add("" + NhaCungCapList.get(i).getTenncc());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SuaSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mangncc);
                        sp_ncc.setAdapter(adapter);
                        Log.e("aa",""+mangncc);
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
    private void LoadLoaiHang(){
        apiService.getLH().enqueue(new Callback<LoaihangResponse>() {
            @Override
            public void onResponse(Call<LoaihangResponse> call, Response<LoaihangResponse> response) {
                LoaihangResponse lhResponse = response.body();
                if(lhResponse != null){
                    LoaiHangList = lhResponse.getResult();
                    if (LoaiHangList != null) {
                        for (int i = 0; i < LoaiHangList.size(); i++) {
                            manglh.add("" + LoaiHangList.get(i).getTenloaihang());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SuaSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,manglh);
                        sp_lh.setAdapter(adapter);
                        Log.e("aa",""+manglh);
                    }else {
                        Log.e("LoadSanPham","Danh Sach Loai Hang Trong");
                    }
                }else {
                    Log.e("LoadSanPham","Null");
                }
            }

            @Override
            public void onFailure(Call<LoaihangResponse> call, Throwable t) {

            }
        });
    }
}
