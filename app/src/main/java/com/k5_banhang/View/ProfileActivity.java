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

import com.k5_banhang.R;

public class ProfileActivity extends AppCompatActivity {
    TextView name, sdt, email;
    Button btn_DN, btn_CSHS, btn_DX,btnExit;
    LinearLayout ll_enable, ll_disable;
    ImageView imgv_profile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        anhxa();
        initView();
        getData();
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
    private void getData(){
        String nameValue = MainActivity.nameKH;
        String sdtValue = MainActivity.sdtKH;
        String emailValue = MainActivity.emailKH;

        // Đổ giá trị lên TextView
        name.setText(nameValue);
        sdt.setText(sdtValue);
        email.setText(emailValue);
    }
}
