package com.k5_banhang.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.k5_banhang.R;

public class ADMINActivity extends Activity {
    ImageView iv_profile;

    Button btn_QLSanPham, btn_QLDonHang;
    public static int caidat;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        anhxa();
        iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caidat = 1;
                Intent intent = new Intent(ADMINActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btn_QLSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ADMINActivity.this, QuanLySPActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhxa(){
        iv_profile = findViewById(R.id.img_profile);
        btn_QLSanPham = findViewById(R.id.btn_QLSP);
        btn_QLDonHang = findViewById(R.id.btn_QLDH);

    }
}
