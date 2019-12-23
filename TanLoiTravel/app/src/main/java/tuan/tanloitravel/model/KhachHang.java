package tuan.tanloitravel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class KhachHang implements Serializable {
    public String HoTen;
    public String SDT;
    public String Email;
    public String TrangThai;
    public String TenGhe;

    public KhachHang() {
    }

    public KhachHang(String hoTen, String SDT, String email, String trangThai, String tenGhe) {
        HoTen = hoTen;
        this.SDT = SDT;
        Email = email;
        TrangThai = trangThai;
        TenGhe=tenGhe;

    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getTenGhe() {
        return TenGhe;
    }

    public void setTenGhe(String tenGhe) {
        TenGhe =tenGhe;
    }

    @SerializedName("NgayKhoiHanh")
    @Expose
    public String NgayKhoiHanh;
    @SerializedName("GioKhoiHanh")
    @Expose
    public String GioKhoiHanh;
    @SerializedName("idTuyen")
    @Expose
    public String idTuyen;

}
