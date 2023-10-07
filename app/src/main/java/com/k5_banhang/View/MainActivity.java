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

import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText edit_UserName, edit_PassWord;
    TextView tv_DangKy, tv_QuenPass;
    Button btn_DangNhap, btn_Thoat;

    public static int idUser;
    public static String nameKH, sdtKH, emailKH;
    private static int authorities;
    private APIService apiService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        tv_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_UserName.getText().toString().trim();
                String password = edit_PassWord.getText().toString().trim();
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Bạn chưa nhập user hoặc pass", Toast.LENGTH_LONG).show();
                }else {

                    DangNhap(username, password);
                }
            }
        });
        btn_Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
    //hàm tạo csdl
    private void initData(){
        edit_UserName = (EditText) findViewById(R.id.edit_Username);
        edit_PassWord = (EditText) findViewById(R.id.edit_PassWord);
        tv_DangKy = (TextView) findViewById(R.id.tv_Dangky);
        tv_QuenPass =(TextView) findViewById(R.id.tv_QuenPass);
        btn_DangNhap = (Button) findViewById(R.id.btn_DangNhap);
        btn_Thoat = (Button) findViewById(R.id.btn_Thoat);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    //hàm xử lý đăng nhập
    private void DangNhap(String username, String password){
        apiService.login(username, password).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if(loginResponse.getSuccess() == 1){
                    idUser = loginResponse.getId_user();
                    nameKH = loginResponse.getName();
                    sdtKH = loginResponse.getSdt();
                    emailKH = loginResponse.getEmail();
                    authorities = loginResponse.getAuthorities();
                    if(authorities == 1){
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(MainActivity.this, ADMINActivity.class);
                        startActivity(intent);
                    }

                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                    Log.d("Đăng nhập thành công", "AA");
                }else {
                    Toast.makeText(MainActivity.this, "UserName hoặc Mật khẩu không đúng.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("Lỗi", t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_LONG).show();

            }
        });
    }
}
