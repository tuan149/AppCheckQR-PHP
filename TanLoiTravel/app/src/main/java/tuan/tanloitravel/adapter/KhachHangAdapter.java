package tuan.tanloitravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Console;

import tuan.tanloitravel.model.KhachHang;
import tuan.tanloitravel.quanly.R;

public class KhachHangAdapter extends ArrayAdapter<KhachHang> {
    Activity context;
    int resource;
    public KhachHangAdapter(Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=inflater.inflate(this.resource,null);

        ImageView imgKH=(ImageView) customView.findViewById(R.id.imgKH);
        TextView tvHoTen=customView.findViewById(R.id.tvHoTen);
        TextView tvSDT=customView.findViewById(R.id.tvSDT);
        TextView tvEmail=customView.findViewById(R.id.tvEmail);
        TextView tvTenGhe=customView.findViewById(R.id.tvTenGhe);

        KhachHang kh=getItem(position);
        if (kh.getTrangThai().equals("0")) {
            imgKH.setImageResource(R.drawable.no);
        }
        else {
            imgKH.setImageResource(R.drawable.yes);
        }


        tvHoTen.setText(kh.getHoTen());
        tvSDT.setText(kh.getSDT());
        tvEmail.setText(kh.getEmail());
        tvTenGhe.setText(kh.getTenGhe());
        return customView;
    }
}
