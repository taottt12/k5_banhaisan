package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.DangKyResponse;
import com.k5_banhang.Model.DatHangResponse;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKyActivity extends AppCompatActivity {

    EditText edit_UserName, edit_PassWord, edit_CofirmPass, edit_ten, edit_sdt, edit_email;
    Button btn_DangKy, btn_Thoat;
    private APIService apiService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        init();
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edit_UserName.getText().toString().trim();
                String password = edit_PassWord.getText().toString().trim();
                String cofirmPass = edit_CofirmPass.getText().toString().trim();
                System.out.println("Ps" + password + "-" + cofirmPass);
                String name = edit_ten.getText().toString().trim();
                String sdt = edit_sdt.getText().toString().trim();
                String email = edit_email.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty() || cofirmPass.isEmpty()){
                    Toast.makeText(DangKyActivity.this, "Bạn chưa nhập user hoặc pass", Toast.LENGTH_LONG).show();
                }else{
                    if(password.equals(cofirmPass)){
                        DangKy(username,password,name,sdt,email);

                    }else{
                        Toast.makeText(DangKyActivity.this, "Bạn nhập sai mật khẩu", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void init(){
        edit_UserName = (EditText) findViewById(R.id.edit_UsernameDK);
        edit_PassWord = (EditText) findViewById(R.id.edit_PassWordDK);
        edit_CofirmPass = (EditText) findViewById(R.id.edit_PassWord_cofirm);
        edit_ten = (EditText) findViewById(R.id.edit_name);
        edit_sdt = (EditText) findViewById(R.id.edit_sdt);
        edit_email = (EditText) findViewById(R.id.edit_email);
        btn_DangKy = (Button) findViewById(R.id.btn_DangKy);
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }
    private void DangKy(String username, String password, String name, String sdt, String email){
        apiService.dangky(username,password,name,sdt,email).enqueue(new Callback<DangKyResponse>(){

            @Override
            public void onResponse(Call<DangKyResponse> call, Response<DangKyResponse> response) {
                DangKyResponse dangKyResponse = response.body();
                if(dangKyResponse.getSuccess() == 1){
                    Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(DangKyActivity.this, "Lỗi khi đăng ký.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DangKyResponse> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
                Toast.makeText(DangKyActivity.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();
            }
        });
    }
}
