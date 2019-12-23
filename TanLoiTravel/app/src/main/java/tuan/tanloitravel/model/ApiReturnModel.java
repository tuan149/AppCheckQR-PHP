package tuan.tanloitravel.model;

import com.google.gson.annotations.SerializedName;

public class ApiReturnModel {
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public int success;
    @SerializedName("name")
    public String name;
}
