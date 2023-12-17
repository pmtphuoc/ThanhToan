package Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bangiay.MainActivity;
import com.example.bangiay.R;

import java.util.ArrayList;
import java.util.List;

import Model.SanPham;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {

    Activity context;
    int resource;
    List<SanPham> objects;
    public SanPhamAdapter( Activity context, int resource,List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtTen = (TextView) row.findViewById(R.id.txtTen);
        ImageView imgSP = (ImageView) row.findViewById(R.id.imgSP);
        SanPham sp = this.objects.get(position);
        txtTen.setText(sp.getTen());


        byte[] decodedImageBytes = Base64.decode(sp.getHinhAnh(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
        imgSP.setImageBitmap(bitmap);
        return row;
    }
}
