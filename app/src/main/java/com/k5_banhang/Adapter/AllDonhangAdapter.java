package com.k5_banhang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.k5_banhang.Model.AllDonHang;
import com.k5_banhang.R;

import java.util.List;

public class AllDonhangAdapter extends RecyclerView.Adapter<AllDonhangAdapter.ViewHolder>{
    private Activity context;
    private List<AllDonHang> allDonHangList;

    public AllDonhangAdapter(Context context, List<AllDonHang> allDonHangList) {
        this.context = (Activity) context;
        this.allDonHangList = allDonHangList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_alldonhang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllDonHang allDonHang = allDonHangList.get(position);

        holder.txt_TenKH.setText(allDonHang.getTenKH());
        holder.txt_SDTKH.setText(allDonHang.getSoDienThoai());
        holder.txt_diachiKH.setText(allDonHang.getDiaChi());
        holder.txt_ngayban.setText(allDonHang.getNgayDatHang());
    }

    @Override
    public int getItemCount() {
        return allDonHangList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_TenKH, txt_SDTKH, txt_diachiKH,txt_ngayban;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_TenKH = itemView.findViewById(R.id.txt_TenKH);
            txt_SDTKH = itemView.findViewById(R.id.txt_SDTKH);
            txt_diachiKH = itemView.findViewById(R.id.txt_diachiKH);
            txt_ngayban = itemView.findViewById(R.id.txt_ngayban);

            // Đăng ký sự kiện click cho itemView
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {

        }
    }
}
