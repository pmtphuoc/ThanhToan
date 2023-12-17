package com.example.bangiay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import Model.SanPham;

public class GioHang extends AppCompatActivity {

    EditText edtSoLuong;
    Button btnMua;
    ImageButton  btn1, btn2;
    TextView txtTen, txtGia;
    ImageView imgSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        addControls();
        Intent intent =  getIntent();
        SanPham sp = (SanPham) intent.getSerializableExtra("SANPHAM");
        String Ma = sp.getMa();
        String Ten = sp.getTen();
        String Gia =  Integer.toString(sp.getDonGia());
        String Anh = sp.getHinhAnh();
        addEvents(Ma,Ten,Gia,Anh);

    }

    private void addEvents(String ma, String ten, String gia, String anh) {
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edtSoLuong.getText().toString())){
                    edtSoLuong.setText("1");
                }
                btnMua.setVisibility(View.VISIBLE);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(GioHang.this, ma+"-"+ten+"-"+gia+"-"+anh, Toast.LENGTH_SHORT).show();
                moSP(ma,ten,gia,anh);

            }
        });
        edtSoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnMua.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  getIntent();
                SanPham sp = (SanPham) intent.getSerializableExtra("SANPHAM");
                Toast.makeText(GioHang.this, sp.getTen(), Toast.LENGTH_SHORT).show();
                SanPham putSanPham = new SanPham(sp.getMa(),sp.getTen(),sp.getDonGia(),sp.getHinhAnh());
                Intent intentput = new Intent(GioHang.this, ThanhToan.class);
                intentput.putExtra("SANPHAMPUT", putSanPham);
                intentput.putExtra("SOLUONG", edtSoLuong.getText().toString());
                startActivity(intentput);
            }
        });
    }
    private void   moSP(String Ma,String Ten,String Gia,String Anhs){
       // Toast.makeText(this, Anhs, Toast.LENGTH_SHORT).show();
        Intent abc = new Intent(GioHang.this, MainActivity.class);
        abc.putExtra("Ma",Ma);
        abc.putExtra("Ten",Ten);
        abc.putExtra("Gia",Gia);
        abc.putExtra("Anh","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");
        abc.putExtra("SOLUONG", edtSoLuong.getText().toString());
        startActivity(abc);

    }
    private void addControls() {

        edtSoLuong = (EditText) findViewById(R.id.edtSL);
        btn1 = (ImageButton) findViewById(R.id.imageButton);
        btn2 = (ImageButton) findViewById(R.id.imageButton2);
        btnMua = (Button) findViewById(R.id.btnXacNhanMua);
        txtTen = (TextView) findViewById(R.id.txtTen);
        txtGia = (TextView) findViewById(R.id.txtGia);
        imgSP = (ImageView) findViewById(R.id.imageView);
        Intent intent =  getIntent();
        SanPham sp = (SanPham) intent.getSerializableExtra("SANPHAM");
        txtTen.setText(sp.getTen());
        txtGia.setText(sp.getDonGia()+" VND");
        btnMua.setVisibility(View.GONE);
        //Toast.makeText(this, sp.getHinhAnh(), Toast.LENGTH_SHORT).show();
       byte[] decodedImageBytes = Base64.decode(sp.getHinhAnh(), Base64.DEFAULT);
       Bitmap bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.length);
       imgSP.setImageBitmap(bitmap);
    }
}