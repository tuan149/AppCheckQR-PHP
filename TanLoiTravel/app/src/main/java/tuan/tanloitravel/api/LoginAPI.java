package tuan.tanloitravel.api;

import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.LoginModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


public interface LoginAPI {
    @POST("/tanloiapp/index.php")
    @FormUrlEncoded
    public void login(@Field("username") String username, @Field("password") String password, Callback<ApiReturnModel> callback);
}
