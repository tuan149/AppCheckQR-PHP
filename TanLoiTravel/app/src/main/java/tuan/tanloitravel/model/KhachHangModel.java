package tuan.tanloitravel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KhachHangModel {
    @SerializedName("NgayKhoiHanh")
    @Expose
    public String NgayKhoiHanh;
    @SerializedName("GioKhoiHanh")
    @Expose
    public String GioKhoiHanh;
    @SerializedName("idTuyen")
    @Expose
    public String idTuyen;

    public String HoTen;
    public String SDT;
    public String Email;
    //private int TrangThai;
}
