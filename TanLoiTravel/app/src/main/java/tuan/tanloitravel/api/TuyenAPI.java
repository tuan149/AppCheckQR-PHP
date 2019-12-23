package tuan.tanloitravel.api;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.TuyenModel;

public interface TuyenAPI {
    @GET("/tanloiapp/dstuyen.php")
    public void tuyen(Callback<List<TuyenModel>> callback);
}
