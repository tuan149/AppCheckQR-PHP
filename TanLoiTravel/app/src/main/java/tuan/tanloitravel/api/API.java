package tuan.tanloitravel.api;

import tuan.tanloitravel.model.KhachHang;

//10.5.2.229255.255.255.0
public class API {
    //
    //private static final String URL = "http://tanloitravel.000webhostapp.com";
    private static final String URL = "http://192.168.43.169:8080";
    private retrofit.RestAdapter restAdapter;
    private LoginAPI loginAPI;
    private ListAPI listAPI;
    private TuyenAPI tuyenAPI;
    private VeXeAPI vexeAPI;
    private KhachHangAPI khachhangAPI;

    public API()
    {
        restAdapter = new retrofit.RestAdapter.Builder()
                .setEndpoint(URL)
                .setLogLevel(retrofit.RestAdapter.LogLevel.FULL)
                .build();

        loginAPI = restAdapter.create(LoginAPI.class);
        listAPI=restAdapter.create(ListAPI.class);
        tuyenAPI=restAdapter.create(TuyenAPI.class);
        vexeAPI =restAdapter.create(VeXeAPI.class);
        khachhangAPI=restAdapter.create(KhachHangAPI.class);
    }

    public LoginAPI getLoginAPI()
    {
        return loginAPI;
    }
    public TuyenAPI getTuyenAPI(){
        return tuyenAPI;
    }
    public ListAPI getListAPI(){
        return  listAPI;
    }
    public VeXeAPI getVeXeAPI(){
        return  vexeAPI;
    }
    public KhachHangAPI getKhachHangAPI(){
        return  khachhangAPI;
    }
}
