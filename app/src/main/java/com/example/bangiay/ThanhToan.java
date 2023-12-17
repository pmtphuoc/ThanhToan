package com.example.bangiay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Model.SanPham;

public class ThanhToan extends AppCompatActivity {
    TextView txtTen, txtGia, txtSoLuong, txtThanhTien;
    ImageView imgSP;
    Button btnTroVe, btnDoiSoLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        addControls();
        btnTroVe = (Button) findViewById(R.id.btnTroVe);
        btnDoiSoLuong = (Button) findViewById(R.id.btnDoiSoLuong);
        addEvents();
    }

    private void addEvents() {
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThanhToan.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnDoiSoLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addControls() {
        btnTroVe = (Button) findViewById(R.id.btnTroVe);
        btnDoiSoLuong = (Button) findViewById(R.id.btnDoiSoLuong);
        txtTen = (TextView) findViewById(R.id.txtTen);
        txtGia = (TextView) findViewById(R.id.txtGia);
        txtSoLuong = (TextView) findViewById(R.id.txtSL);
        txtThanhTien = (TextView) findViewById(R.id.txttt);
        imgSP = (ImageView) findViewById(R.id.imgSP);
        Intent intent = getIntent();
        SanPham sp = (SanPham) intent.getSerializableExtra("SANPHAMPUT");
        txtSoLuong.setText(intent.getStringExtra("SOLUONG"));
        txtTen.setText(sp.getTen());
        txtGia.setText(sp.getDonGia()+"");
        byte[] decodedImageBytes = Base64.decode(sp.getHinhAnh(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
        imgSP.setImageBitmap(bitmap);
        int SLuong = Integer.parseInt(intent.getStringExtra("SOLUONG").trim());
        int Gia = sp.getDonGia();
        int TTien = SLuong * Gia;
        txtThanhTien.setText(TTien+"");
    }
}