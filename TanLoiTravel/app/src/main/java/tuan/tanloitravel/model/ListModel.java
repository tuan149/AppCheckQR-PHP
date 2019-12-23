package tuan.tanloitravel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListModel {
    @SerializedName("giokh")
    @Expose
    public String giokh;
    @SerializedName("ngaykh")
    @Expose
    public String ngaykh;
    @SerializedName("chuyenxe")
    @Expose
    public String chuyenxe;
}
