package tuan.tanloitravel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VeXeModel {
    @SerializedName("NgayKhoiHanh")
    @Expose
    public String NgayKhoiHanh;
    @SerializedName("GioKhoiHanh")
    @Expose
    public String GioKhoiHanh;
    @SerializedName("idTuyen")
    @Expose
    public String idTuyen;
    @SerializedName("MaBiMat")
    @Expose
    public String MaBiMat;
}
