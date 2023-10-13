package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.R;

public class ChinhSuaHoSoActivity extends AppCompatActivity {

    Button btn_exit, btn_update, btn_doiMK;

    EditText txtName, txtSdt, txtEmail;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actyvity_chinhsuahs);
        anhxa();
    }

    private void anhxa() {
        txtName = findViewById(R.id.txtTen);
        txtName = findViewById(R.id.txtTen);
    }
}
