package tuan.tanloitravel.api;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import tuan.tanloitravel.model.ApiReturnModel;

public interface VeXeAPI {
    @POST("/tanloiapp/kiemtrave.php")
    @FormUrlEncoded
    public void kiemtrave(@Field("NgayKhoiHanh") String NgayKhoiHanh, @Field("GioKhoiHanh") String GioKhoiHanh, @Field("idTuyen") String idTuyen, @Field("MaBiMat") String MaBiMat,Callback<ApiReturnModel> callback);
}
