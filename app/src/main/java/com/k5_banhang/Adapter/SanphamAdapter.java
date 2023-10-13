package com.k5_banhang.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.k5_banhang.Model.SanPham;
import com.k5_banhang.R;
import com.k5_banhang.View.ChiTietSPActivity;
import com.k5_banhang.remote.Contans;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;



public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ViewHolder>{
    private Activity context;
    private List<SanPham> sanPhamList;

    public SanphamAdapter(Activity context, List<SanPham> sanPhamList ) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.item_sanpham, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        Picasso.get().load(Contans.API_URL +"image/"+ sanPham.getHinhanh()).into(holder.imgSanpham);
        holder.txtten.setText(sanPham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: " + decimalFormat.format(Double.parseDouble(sanPham.getDongia()))+"VNĐ");
        holder.txtdvt.setText(sanPham.getDonvitinh());
        System.out.println(sanPham.getHinhanh());

//        int drawableResourceId = context.getResources().getIdentifier(sanPhamList.get(position).getHinhanh(), "drawable", context.getPackageName());
//        Glide.with(context).load(drawableResourceId).into(holder.imgSanpham);

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtgia, txtten, txtdvt;
        ImageView imgSanpham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtgia = itemView.findViewById(R.id.tv_giasp);
            txtten = itemView.findViewById(R.id.tv_tensp);
            txtdvt = itemView.findViewById(R.id.tv_dvt);
            imgSanpham = itemView.findViewById(R.id.imgSanPham);

            // Đăng ký sự kiện click cho itemView
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v){
            Intent intent = new Intent(context, ChiTietSPActivity.class);
            intent.putExtra(Contans.THONGTIN_SP, sanPhamList.get(getLayoutPosition()));
            context.startActivityForResult(intent, Contans.REQUEST_CODE_GIOHANG);
        }
    }
}
