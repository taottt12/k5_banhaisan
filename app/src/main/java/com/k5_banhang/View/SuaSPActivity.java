package com.k5_banhang.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.InsertSPResponse;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private List<String> mangncc;
    private List<String> manglh;
    private APIService apiService;

    int id = 0;

    String tenChiTiet = "", donvt ="";
    long giachitiet = 0;
    String hinhanhchitiet = "";
    int idloaisp , idncc;
    Bitmap bitmap;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suasp);
        manglh = new ArrayList<String>();
        mangncc = new ArrayList<String>();

        anhxa();
        getDataSP();
        LoadNhaCC();
        LoadLoaiHang();
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                imgv_anh.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        imgv_upfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);

            }
        });

        imgv_chup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_tenSP.setText(txt_giaSP.getText());
            }
        });
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    final String base64Image = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);

                    String idSP = String.valueOf(id);
                    String idMH = idSP.toString().trim();
                    String tenSP = txt_tenSP.getText().toString().trim();

                    String input = txt_giaSP.getText().toString().trim();
                    input = input.replaceAll(",", ""); // Loại bỏ dấu phẩy
                    int donGia = Integer.parseInt(input);
                    String donViTinh = txt_DVT.getText().toString().trim();

                    Long maNCC = sp_ncc.getSelectedItemId();
                    Long maLH = sp_lh.getSelectedItemId();
                    String stringmaNCC = String.valueOf(maNCC + 1);
                    String stringmaLH = String.valueOf(maLH + 1);
//                    if(tenSP.isEmpty() || donGia != 0 || donViTinh.isEmpty()){
//                        Toast.makeText(SuaSPActivity.this, "Bạn chưa nhập", Toast.LENGTH_LONG).show();
//                        Log.e("jgaergyfyugdfg",tenSP);
//                        Log.e("jgaergyfyugdfg", String.valueOf(donGia));
//                        Log.e("jgaergyfyugdfg",donViTinh);
//                        Log.e("jgaergyfyugdfg",base64Image);
//                    }else{
                        Upadte(idMH,tenSP,donGia,donViTinh,base64Image,stringmaNCC,stringmaLH);
                    //}
                } else {
                    String img = "aa";
                    String idSP = String.valueOf(id);
                    String idMH = idSP.toString().trim();
                    String tenSP = txt_tenSP.getText().toString().trim();

                    String input = txt_giaSP.getText().toString().trim();
                    input = input.replaceAll(",", ""); // Loại bỏ dấu phẩy
                    int donGia = Integer.parseInt(input);
                    String donViTinh = txt_DVT.getText().toString().trim();

                    Long maNCC = sp_ncc.getSelectedItemId();
                    Long maLH = sp_lh.getSelectedItemId();
                    String stringmaNCC = String.valueOf(maNCC + 1);
                    String stringmaLH = String.valueOf(maLH + 1);
                    if(tenSP.isEmpty() || donGia <= 0 || donViTinh.isEmpty()){
                        Toast.makeText(SuaSPActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                        Log.e("jgaergyfyugdfg",tenSP);
                        Log.e("jgaergyfyugdfg", String.valueOf(donGia));
                        Log.e("jgaergyfyugdfg",donViTinh);
                    }else{
                        Upadte(idMH,tenSP,donGia,donViTinh,img,stringmaNCC,stringmaLH);
                    }
                    //Toast.makeText(SuaSPActivity.this, "Hãy chọn file ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Upadte(String idMH,String tensanpham, int dongia, String donvitinh, String hinhanh,String mancc,String malh){
        apiService.UpdateSP(idMH,tensanpham,dongia,donvitinh,hinhanh,mancc,malh).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, retrofit2.Response<InsertSPResponse> response) {
                InsertSPResponse insertSPResponse = response.body();
                if(insertSPResponse.getSuccess() == 1){
                    Intent intent = new Intent(SuaSPActivity.this, QuanLySPActivity.class);
                    startActivity(intent);
                    Toast.makeText(SuaSPActivity.this, "Sửa thành công", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 2) {
                    Toast.makeText(SuaSPActivity.this, "Lỗi sql", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 3) {
                    Toast.makeText(SuaSPActivity.this, "Lỗi upload ảnh", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 4) {
                    Toast.makeText(SuaSPActivity.this, "Lỗi giá trị null", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SuaSPActivity.this, "mày sai rồi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
                Toast.makeText(SuaSPActivity.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });
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
        txt_giaSP.setText(decimalFormat.format(giachitiet));
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
                        sp_ncc.setSelection(idncc-1);
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
                        sp_lh.setSelection(idloaisp-1);
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
