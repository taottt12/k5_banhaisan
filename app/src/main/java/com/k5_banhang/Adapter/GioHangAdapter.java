package com.k5_banhang.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.k5_banhang.Model.GioHang;
import com.k5_banhang.View.HomeActivity;
import com.k5_banhang.R;
import com.k5_banhang.remote.Contans;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class GioHangAdapter extends BaseAdapter {
    private Activity context;
    private List<GioHang> gioHangList;

    public interface UpdateTongTienListener {
        void onUpdateTongTien();
    }
    private UpdateTongTienListener updateTongTienListener;
    public void setUpdateTongTienListener(UpdateTongTienListener listener) {
        this.updateTongTienListener = listener;
    }

    public GioHangAdapter(Activity context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    class Holder{
        ImageView ivGioHang, btnDelete;
        TextView tvTengiohang, tvGiagiohang;
        Button btnCong, btnTru, btnSoluong;
    }

    @Override
    public int getCount() {
        return gioHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Holder holder;
        if(view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.item_giohang, null);
            holder.ivGioHang = view.findViewById(R.id.ivgiohang);
            holder.tvTengiohang = view.findViewById(R.id.tv_tengiohang);
            holder.tvGiagiohang = view.findViewById(R.id.tv_giagiohang);
            holder.btnCong = view.findViewById(R.id.btn_cong);
            holder.btnTru = view.findViewById(R.id.btn_tru);
            holder.btnSoluong = view.findViewById(R.id.btn_soluong);
            holder.btnDelete = view.findViewById(R.id.btn_delete);
            view.setTag(holder);
        }else holder = (Holder) view.getTag();
        GioHang gioHang = gioHangList.get(i);
        holder.tvTengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvGiagiohang.setText(decimalFormat.format(gioHang.getGiasp())+ "Đ");
        String hinh = HomeActivity.mangGioHang.get(i).getHinhsp();
        Picasso.get().load(Contans.API_URL +"image/"+hinh).into(holder.ivGioHang);
        holder.btnSoluong.setText(gioHang.getSoluong()+"");
        int sl = Integer.parseInt(holder.btnSoluong.getText().toString());
        if(sl >=10){
            holder.btnCong.setEnabled(false);
        }else if(sl == 1){
            holder.btnCong.setEnabled(true);
            holder.btnTru.setEnabled(false);
        }else if(sl>1){
            holder.btnCong.setEnabled(true);
            holder.btnTru.setEnabled(true);
        }
        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString())+ 1;
                int slhientai = HomeActivity.mangGioHang.get(i).getSoluong();
                long giahientai = HomeActivity.mangGioHang.get(i).getGiasp();
                HomeActivity.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai * slmoinhat / slhientai;
                HomeActivity.mangGioHang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");
                if(slmoinhat > 9){
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(false);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else{
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
                if (updateTongTienListener != null) {
                    updateTongTienListener.onUpdateTongTien();
                }
            }
        });
        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(holder.btnSoluong.getText().toString() )- 1;
                int slhientai = HomeActivity.mangGioHang.get(i).getSoluong();
                long giahientai = HomeActivity.mangGioHang.get(i).getGiasp();
                HomeActivity.mangGioHang.get(i).setSoluong(slmoinhat);
                long giamoinhat = giahientai * slmoinhat / slhientai;
                HomeActivity.mangGioHang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

                holder.tvGiagiohang.setText(decimalFormat.format(giamoinhat)+"Đ");

                if(slmoinhat < 2){
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(false);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }else{
                    holder.btnCong.setEnabled(true);
                    holder.btnTru.setEnabled(true);
                    holder.btnSoluong.setText(String.valueOf(slmoinhat));
                }
                if (updateTongTienListener != null) {
                    updateTongTienListener.onUpdateTongTien();
                }
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Xóa thành công sản phẩm" + HomeActivity.mangGioHang.get(i).getIdsp(), Toast.LENGTH_SHORT).show();
                HomeActivity.mangGioHang.remove(i);
                notifyDataSetChanged();
                if (updateTongTienListener != null) {
                    updateTongTienListener.onUpdateTongTien();
                }
                HomeActivity.setSoLuong(HomeActivity.mangGioHang.size());
            }
        });
        return view;
    }
}
