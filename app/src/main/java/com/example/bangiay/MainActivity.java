package com.example.bangiay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Adapter.SanPhamAdapter;
import Model.SanPham;

public class MainActivity extends AppCompatActivity {
Button btnMua;
    GridView gridView;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        btnMua=(Button) findViewById(R.id.btnMua);
        addControls();
        addEvents();
        Intent intent = getIntent();
        String data = intent.getStringExtra("SOLUONG");
        String Masp =intent.getStringExtra("Ma");
        String Tensp = intent.getStringExtra("Ten");
        String Giasp = intent.getStringExtra("Gia");
        String Anhsp = intent.getStringExtra("Anh");
        if(TextUtils.isEmpty(data)){
            btnMua.setVisibility(View.GONE);
        }
        else {
            btnMua.setVisibility(View.VISIBLE);
            btnMua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SanPham putSanPham = new SanPham(Masp,Tensp,Integer.parseInt(Giasp),Anhsp);
                    Intent intentput = new Intent(MainActivity.this, ThanhToan.class);
                    intentput.putExtra("SANPHAMPUT", putSanPham);
                    intentput.putExtra("SOLUONG", data);
                    startActivity(intentput);
                    Toast.makeText(MainActivity.this, Anhsp, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void addEvents() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                SanPham sp = sanPhamAdapter.getItem(i);
                moManHinhMuaSanPham(sp);
            }
        });
    }

    private void moManHinhMuaSanPham(SanPham sp) {
        Intent intent = new Intent(MainActivity.this, GioHang.class);
        intent.putExtra("SANPHAM",sp);
        startActivity(intent);
    }

    private void addControls() {
        gridView = (GridView) findViewById(R.id.gridView);

        DanhSachSanPhamTask task = new DanhSachSanPhamTask();
        task.execute();
    }
    class DanhSachSanPhamTask extends AsyncTask<Void, Void, ArrayList<SanPham>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPham> sanPham) {
            super.onPostExecute(sanPham);
            sanPhamAdapter = new SanPhamAdapter(MainActivity.this, R.layout.hinhanh, sanPham);
            gridView.setAdapter(sanPhamAdapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<SanPham> doInBackground(Void... voids) {
          ArrayList<SanPham> dsSP = new ArrayList<>();
            try{
                URL url = new URL("http://192.168.1.5/bangiay/api/SanPham");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type","application/json; charset=UTF-8");
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(),"UTF-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String line = null;
                while((line=br.readLine())!=null){
                    builder.append(line);
                }
                JSONArray jsonArray = new JSONArray(builder.toString());
                for(int i = 0; i< jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String masp = jsonObject.getString("MaSanPham");
                    String tensp = jsonObject.getString("TenSanPham");
                    int dongia = jsonObject.getInt("DonGia");
                    String hinhanh = jsonObject.getString("HinhAnh");

                    SanPham sp = new SanPham();
                    sp.setMa(masp);
                    sp.setTen(tensp);
                    sp.setDonGia(dongia);
                    sp.setHinhAnh(hinhanh);
                    dsSP.add(sp);
                }
                br.close();
            }
            catch (Exception ex){
                Log.e("Loi", ex.toString());
            }

           // Toast.makeText(MainActivity.this, (CharSequence) dsSP, Toast.LENGTH_SHORT).show();

            return dsSP;
        }
    }
}