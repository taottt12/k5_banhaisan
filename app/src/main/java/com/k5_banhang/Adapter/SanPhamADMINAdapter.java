package com.k5_banhang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.k5_banhang.Model.InsertSPResponse;
import com.k5_banhang.Model.LoginResponse;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.Model.SanphamResponse;
import com.k5_banhang.R;
import com.k5_banhang.View.ChiTietSPActivity;
import com.k5_banhang.View.SuaSPActivity;
import com.k5_banhang.remote.APIService;
import com.k5_banhang.remote.Contans;
import com.k5_banhang.remote.Retrofift;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SanPhamADMINAdapter extends RecyclerView.Adapter<SanPhamADMINAdapter.ViewHolder>{
    private Activity context;
    private List<SanPham> sanPhamList;
    private APIService apiService;
    public SanPhamADMINAdapter (Activity context, List<SanPham> sanPhamList ) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }


    @NonNull
    @Override
    public SanPhamADMINAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.item_sp_admin, null);

        return new SanPhamADMINAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamADMINAdapter.ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        Picasso.get().load(Contans.API_URL +"image/"+ sanPham.getHinhanh()).into(holder.imgSanpham);


        holder.txtten.setText("Tên: "+sanPham.getTensanpham());
        holder.txt_soLuong.setText("Còn: "+sanPham.getSoluong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPham.getDongia()))+"VNĐ");
        System.out.println(sanPham.getHinhanh());
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtgia, txtten, txt_soLuong;
        ImageView imgSanpham,imgv_update,imgv_delete;
        private String ma_mh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtgia = itemView.findViewById(R.id.txt_giasp);
            txtten = itemView.findViewById(R.id.txt_tensp);
            txt_soLuong = itemView.findViewById(R.id.txt_soLuong);
            imgSanpham = itemView.findViewById(R.id.iv_spadmin);
            imgv_update = itemView.findViewById(R.id.imgv_update);
            imgv_delete = itemView.findViewById(R.id.imgv_delete);
            itemView.setOnClickListener(this);
            apiService = Retrofift.getClient(Contans.API_URL).create(APIService.class);
        }
        @Override
        public void onClick(View v){
            imgv_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, SuaSPActivity.class);
                    intent.putExtra(Contans.THONGTIN_SP, sanPhamList.get(getLayoutPosition()));
                    context.startActivityForResult(intent, Contans.REQUEST_CODE_GIOHANG);
                }
            });
            imgv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SanPham sanPham = sanPhamList.get(getLayoutPosition());
                    ma_mh = sanPham.getMa_mh();

                    Delete(ma_mh);
                }
            });
        }
    }

    private void Delete(String maMh) {
        apiService.delete(maMh).enqueue(new Callback<InsertSPResponse>() {
            @Override
            public void onResponse(Call<InsertSPResponse> call, Response<InsertSPResponse> response) {
                InsertSPResponse deleteResponse = response.body();
                if(deleteResponse.getSuccess() == 1){
                    refreshData();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "lỗi response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertSPResponse> call, Throwable t) {

            }
        });

    }
    private void refreshData() {
        // Gọi API hoặc thực hiện bất kỳ hành động nào để tải lại danh sách sản phẩm
        // Sau khi tải lại danh sách, cập nhật sanPhamList và gọi notifyDataSetChanged()
        apiService.getAllsp().enqueue(new Callback<SanphamResponse>() {
            @Override
            public void onResponse(Call<SanphamResponse> call, Response<SanphamResponse> response) {
                if (response.isSuccessful()) {
                    SanphamResponse spResponse = response.body();
                    if (spResponse != null) {
                        sanPhamList = spResponse.getResult();
                    }
                }
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SanphamResponse> call, Throwable t) {

            }
        });
    }

}
