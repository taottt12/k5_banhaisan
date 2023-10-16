package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    TextView name, sdt, email;
    Button btn_DN, btn_CSHS, btn_DX,btnExit, btn_DoiMK;
    LinearLayout ll_enable, ll_disable;
    ImageView imgv_profile;
    private APIService apiService;
    public static String nameKH, sdtKH, emailKH;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        anhxa();
        initView();

        String idUS = String.valueOf(MainActivity.idUser);
        if(idUS != null || !idUS.isEmpty()){
            getData(idUS);
        }else{
            Toast.makeText(this, "dell có id", Toast.LENGTH_SHORT).show();
        }

        btn_DN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_DX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.idUser = 0;
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        imgv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ADMINActivity.class);
                startActivity(intent);
            }
        });
        btn_CSHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, ChinhSuaHoSoActivity.class);
                startActivity(intent);
            }
        });

    }

    private  void anhxa(){
        name = findViewById(R.id.txt_Name);
        sdt = findViewById(R.id.txt_SDT);
        email = findViewById(R.id.txt_Email);
        btn_DN = findViewById(R.id.btn_DN);
        btn_CSHS = findViewById(R.id.btn_CSHS);
        btn_DX = findViewById(R.id.btn_DX);
        ll_enable = findViewById(R.id.ll_enable);
        ll_disable = findViewById(R.id.ll_disable);
        btnExit = findViewById(R.id.btnExit);
        imgv_profile = findViewById(R.id.imgv_profile);
        btn_DoiMK = findViewById(R.id.btn_DoiMK);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }
    private void initView(){
        int idUser = MainActivity.idUser;

        if(idUser != 0){
            ll_disable.setVisibility(View.VISIBLE);
            ll_enable.setVisibility(View.GONE);
            if(ADMINActivity.caidat == 1){
                imgv_profile.setVisibility(View.VISIBLE);
            }
        }else{
            ll_enable.setVisibility(View.VISIBLE);
            ll_disable.setVisibility(View.GONE);
        }
    }
    private void getData(String idUS){
        apiService.get_account(idUS).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, Response<InsertSPResponse> response) {
                InsertSPResponse insertSPResponse = response.body();
                if(insertSPResponse.getSuccess() == 1){

                    nameKH = insertSPResponse.getName();
                    sdtKH = insertSPResponse.getSdt();
                    emailKH = insertSPResponse.getEmail();

                    // Đổ giá trị lên TextView
                    name.setText(nameKH);
                    sdt.setText(sdtKH);
                    email.setText(emailKH);

                }
            }

            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Lỗi mẹ rồi", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
