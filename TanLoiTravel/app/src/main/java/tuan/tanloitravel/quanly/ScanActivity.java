package tuan.tanloitravel.quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.journeyapps.barcodescanner.camera.CameraSettings;

import java.util.List;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tuan.tanloitravel.adapter.KhachHangAdapter;
import tuan.tanloitravel.api.API;
import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.Data;
import tuan.tanloitravel.model.KhachHang;
import tuan.tanloitravel.model.KhachHangModel;
import tuan.tanloitravel.model.TuyenModel;


public class ScanActivity extends AppCompatActivity {

    API api = new API();
    private static final String TAG = ScanActivity.class.getSimpleName();
    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private String lastText="Quét mã QR để xác nhận";
    private String lastScan = "";
    TextView tvNgayKH,tvGioKH,tvTuyen,tvIDTuyen;

    String NgayKhoiHanh,GioKhoiHanh,idTuyen,MaBiMat;

    ListView lvKhachHang;
    KhachHangAdapter khachHangAdapter;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if(result.getText() == null || result.getText().equals(lastText)) {
                // Prevent duplicate scans
                return;
            }
            lastText = result.getText();
            //lastText = result.getText();
            //barcodeView.setStatusText(lastText);
            //Toast.makeText(getApplicationContext(),result.getText(), Toast.LENGTH_LONG).show();

            //Added preview of scanned barcode
            //ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
           // imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
            String NgayKhoiHanh = Data.NgayKH;
            String GioKhoiHanh = Data.GioKH;
            String idTuyen = Data.IdTuyenKH;
            String MaBiMat = result.getText();
            final ProgressDialog Prodialog = new ProgressDialog(ScanActivity.this);
            Prodialog.setMessage("Đang kiểm tra");

            Prodialog.show();
            api.getVeXeAPI().kiemtrave(NgayKhoiHanh,GioKhoiHanh,idTuyen,MaBiMat, new Callback<ApiReturnModel>() {
                @Override
                public void success(ApiReturnModel apiReturnModel, Response response) {
                    if(apiReturnModel.success == 1) {
                        //Toast.makeText(ScanActivity.this, "Thanh cpnmg", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(ScanActivity.this);
                        alertDialog.setTitle(apiReturnModel.message);
                        alertDialog.setNegativeButton("Tiếp tục", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                        Prodialog.dismiss();
                        addData();
                        return;
                    }
                    else {
                        //Toast.makeText(MainActivity.this, apiReturnModel.message, Toast.LENGTH_LONG).show();
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(ScanActivity.this);
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
                    Toast.makeText(ScanActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            beepManager.playBeepSoundAndVibrate();
            barcodeView.decodeContinuous(callback);

        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        barcodeView = (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
        barcodeView.decodeContinuous(callback);
        barcodeView.setStatusText(lastText);
        beepManager = new BeepManager(this);

        tvNgayKH=(TextView)findViewById(R.id.tvNgayKH);
        tvNgayKH.setText(Data.NgayKH);
        tvGioKH=(TextView)findViewById(R.id.tvGioKH);
        tvGioKH.setText(Data.GioKH);
        tvTuyen=(TextView)findViewById(R.id.tvTuyen);
        tvTuyen.setText(Data.TuyenKH);
        tvIDTuyen=(TextView)findViewById(R.id.tvIDTuyen);
        tvIDTuyen.setText(Data.IdTuyenKH);

        addControls();
        addEvents();
        addData();

    }

    private void addData() {
        khachHangAdapter.clear();
        khachHangAdapter.notifyDataSetChanged();
        //khachHangAdapter.add(new KhachHang("Tuan","018321","asdbasdk",R.drawable.yes));
        //khachHangAdapter.add(new KhachHang("sdas","sadas","fhgfh","0","A4"));
        String NgayKhoiHanh = Data.NgayKH;
        String GioKhoiHanh = Data.GioKH;
        String idTuyen = Data.IdTuyenKH;

        api.getKhachHangAPI().dskhachhang(NgayKhoiHanh,GioKhoiHanh,idTuyen, new Callback<List<KhachHang>>() {
            @Override
            public void success(List<KhachHang> khachhangModels, Response response) {
                for (KhachHang item: khachhangModels) {
                    khachHangAdapter.add(item);

                }
                khachHangAdapter.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(ScanActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void addEvents() {

    }

    private void addControls() {
        lvKhachHang=findViewById(R.id.lvKhachHang);
        khachHangAdapter=new KhachHangAdapter(ScanActivity.this,R.layout.listkhachhang);
        lvKhachHang.setAdapter(khachHangAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }
    public void pause(View view) {
        barcodeView.pause();
    }

    public void resume(View view) {
        barcodeView.resume();
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
