package tuan.tanloitravel.api;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import tuan.tanloitravel.model.ApiReturnModel;

public interface ListAPI {
    @POST("/tanloiapp/dschuyenxe.php")
    @FormUrlEncoded
    public void chuyenxe(@Field("ngaykh") String ngaykh, @Field("giokh") String giokh, Callback<ApiReturnModel> callback);
}
