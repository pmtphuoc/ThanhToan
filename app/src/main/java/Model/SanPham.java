package Model;


import java.io.Serializable;

public class SanPham implements Serializable {
    private String Ma;
    private String Ten;
    private int DonGia;

    public SanPham(String ma, String ten, int donGia, String hinhAnh) {
        Ma = ma;
        Ten = ten;
        DonGia = donGia;
        HinhAnh = hinhAnh;
    }
    public SanPham() {
    }

    private String HinhAnh;

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int donGia) {
        DonGia = donGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return  Ma +"\n"+ Ten + "\n" + DonGia ;
    }
}
