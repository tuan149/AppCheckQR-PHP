package tuan.tanloitravel.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.KhachHang;
import tuan.tanloitravel.model.TuyenModel;

public interface KhachHangAPI {


    @POST("/tanloiapp/dskhachhang.php")
    @FormUrlEncoded
    public void dskhachhang(@Field("NgayKhoiHanh") String NgayKhoiHanh, @Field("GioKhoiHanh") String GioKhoiHanh, @Field("idTuyen") String idTuyen, Callback<List<KhachHang>> callback);
}
