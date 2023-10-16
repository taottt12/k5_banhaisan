package com.k5_banhang.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.k5_banhang.Model.DangKyResponse;
import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.Model.LoaiHang;
import com.k5_banhang.Model.LoaihangResponse;
import com.k5_banhang.Model.NhaCungCap;
import com.k5_banhang.Model.NhaCungCapResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Multipart;

public class ThemSPActivity extends AppCompatActivity {

    EditText tv_tenSPT, tv_giaSPT, tv_dvtSPT;
    Spinner sp_lhT, sp_nccT;
    ImageView img_folder, img_camera, img_anhSPT;
    Button btn_addSP;
    Bitmap bitmap;
    private int REQUEST_CODE_CAMERA = 123;
    private List<NhaCungCap> NhaCungCapList;
    private List<LoaiHang> LoaiHangList;
    public static List<String> mangncc;
    public static List<String> manglh;
    private APIService apiService;
    private ActivityResultLauncher<Intent> launcher;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themsp);
        mangncc = new ArrayList<String>();
        manglh = new ArrayList<String>();
        anhxa();
        LoadNhaCC();
        LoadLoaiHang();
        img_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            Uri uri = data.getData();
                            try {
                                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                img_anhSPT.setImageBitmap(bitmap);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
        img_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);

            }
        });


        btn_addSP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] bytes = byteArrayOutputStream.toByteArray();
                    final String base64Image = android.util.Base64.encodeToString(bytes, Base64.DEFAULT);
                    String tenSP = tv_tenSPT.getText().toString().trim();
                    String donGia = tv_giaSPT.getText().toString().trim();
                    String donViTinh = tv_dvtSPT.getText().toString().trim();

                    Long maNCC = sp_nccT.getSelectedItemId();
                    Long maLH = sp_lhT.getSelectedItemId();
                    String stringmaNCC = String.valueOf(maNCC + 1);
                    String stringmaLH = String.valueOf(maLH + 1);
                    if(tenSP.isEmpty() || donGia.isEmpty() || donViTinh.isEmpty()){
                        Toast.makeText(ThemSPActivity.this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                        Log.e("jgaergyfyugdfg",tenSP);
                    }else{
                        insertSP(tenSP,donGia,donViTinh,base64Image,stringmaNCC,stringmaLH);
                    }
                } else {
                    Toast.makeText(ThemSPActivity.this, "Hãy chọn file ảnh", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void insertSP(String tensanpham, String dongia, String donvitinh, String hinhanh,String mancc,String malh){
        apiService.insertSP(tensanpham,dongia,donvitinh,hinhanh,mancc,malh).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, retrofit2.Response<InsertSPResponse> response) {
                InsertSPResponse insertSPResponse = response.body();
                if(insertSPResponse.getSuccess() == 1){
                    Intent intent = new Intent(ThemSPActivity.this, QuanLySPActivity.class);
                    startActivity(intent);
                    Toast.makeText(ThemSPActivity.this, "Thêm mới thành công", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 2) {
                    Toast.makeText(ThemSPActivity.this, "Lỗi sql", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 3) {
                    Toast.makeText(ThemSPActivity.this, "Lỗi upload ảnh", Toast.LENGTH_LONG).show();
                }else if(insertSPResponse.getSuccess() == 4) {
                    Toast.makeText(ThemSPActivity.this, "Lỗi giá trị null", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ThemSPActivity.this, "mày sai rồi", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
                Toast.makeText(ThemSPActivity.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void anhxa() {
        tv_tenSPT = findViewById(R.id.ed_tenSPT);
        tv_giaSPT = findViewById(R.id.ed_giaSPT);
        tv_dvtSPT = findViewById(R.id.ed_DVTT);
        sp_lhT = findViewById(R.id.sp_lhT);
        sp_nccT = findViewById(R.id.sp_nccT);
        img_folder = findViewById(R.id.img_upfileT);
        img_camera = findViewById(R.id.img_chupT);
        img_anhSPT = findViewById(R.id.img_anhT);
        btn_addSP = findViewById(R.id.btn_ADD);

        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    private void LoadNhaCC(){
        apiService.getNCC().enqueue(new Callback<NhaCungCapResponse>() {
            @Override
            public void onResponse(Call<NhaCungCapResponse> call, retrofit2.Response<NhaCungCapResponse> response) {
                NhaCungCapResponse nccResponse = response.body();
                if(nccResponse != null){
                    NhaCungCapList = nccResponse.getResult();
                    if (NhaCungCapList != null) {
                        for (int i = 0; i < NhaCungCapList.size(); i++) {
                            mangncc.add("" + NhaCungCapList.get(i).getTenncc());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,mangncc);
                        sp_nccT.setAdapter(adapter);
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
            public void onResponse(Call<LoaihangResponse> call, retrofit2.Response<LoaihangResponse> response) {
                LoaihangResponse lhResponse = response.body();
                if(lhResponse != null){
                    LoaiHangList = lhResponse.getResult();
                    if (LoaiHangList != null) {
                        for (int i = 0; i < LoaiHangList.size(); i++) {
                            manglh.add("" + LoaiHangList.get(i).getTenloaihang());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThemSPActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,manglh);
                        sp_lhT.setAdapter(adapter);
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