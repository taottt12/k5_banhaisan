package com.k5_banhang.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.R;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaHoSoActivity extends AppCompatActivity {

    Button btn_exit, btn_update, btn_doiMK;

    EditText txtName, txtSdt, txtEmail;
    private APIService apiService;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actyvity_chinhsuahs);
        anhxa();
        txtName.setText(ProfileActivity.nameKH);
        txtSdt.setText(ProfileActivity.sdtKH);
        txtEmail.setText(ProfileActivity.emailKH);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = txtName.getText().toString().trim();
                String sdt = txtSdt.getText().toString().trim();
                String email = txtEmail.getText().toString().trim();
                String idUser = String.valueOf(MainActivity.idUser);

                if(ten != null && !ten.isEmpty() && sdt != null && !sdt.isEmpty() && email != null && !email.isEmpty() && idUser != null && !idUser.isEmpty()){
                    updateHS(ten,sdt,email,idUser);
                }else{
                    Toast.makeText(ChinhSuaHoSoActivity.this, "Cần nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void updateHS(String ten, String sdt, String email, String idUser){
        apiService.updateHS(ten, sdt, email, idUser).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, Response<InsertSPResponse> response) {
                InsertSPResponse insertSPResponse = response.body();
                if(insertSPResponse.getSuccess() == 1){
                    Intent intent = new Intent(ChinhSuaHoSoActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    Toast.makeText(ChinhSuaHoSoActivity.this, "Cập nhật thành công", Toast.LENGTH_LONG).show();
                }

            }
            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {
                Toast.makeText(ChinhSuaHoSoActivity.this, "Dell respone lun", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void anhxa() {
        txtName = findViewById(R.id.txtTen);
        txtSdt = findViewById(R.id.txtSdt);
        txtEmail = findViewById(R.id.txtEmail);
        btn_exit = findViewById(R.id.btn_Exit);
        btn_update = findViewById(R.id.btn_CapNhat);
        apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
    }

    public void finish(){
        super.finish();
        setResult(RESULT_OK);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
