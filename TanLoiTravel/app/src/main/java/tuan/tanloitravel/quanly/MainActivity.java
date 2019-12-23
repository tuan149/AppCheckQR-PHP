package tuan.tanloitravel.quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import tuan.tanloitravel.api.API;
import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.Data;
import tuan.tanloitravel.model.LoginModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends AppCompatActivity {

    EditText editName;
    EditText editPassword;
    API api = new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
//        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        editName.setText(currentDate);
    }


    public void xuLyDangNhap(View view) {
        final String username = editName.getText().toString();
        String password = editPassword.getText().toString();
        final ProgressDialog Prodialog = new ProgressDialog(MainActivity.this);
        Prodialog.setMessage("Đang đăng nhập");
        Prodialog.show();
        api.getLoginAPI().login(username, password, new Callback<ApiReturnModel>() {
            @Override
            public void success(ApiReturnModel apiReturnModel, Response response) {
                if(apiReturnModel.success == 1) {
                    //Toast.makeText(MainActivity.this, apiReturnModel.message, Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle(apiReturnModel.message);
                    alertDialog.setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Prodialog.dismiss();
                            Data.taiKhoan = username;
                            OpenTrangChu();
                        }
                    });
                    alertDialog.show();
                }
                else {
                    //Toast.makeText(MainActivity.this, apiReturnModel.message, Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle(apiReturnModel.message);
                    alertDialog.setNegativeButton("OK!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Prodialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void OpenTrangChu(){
        Intent intent=new Intent(this,TrangChuActivity.class);
        startActivity(intent);
    }

}
