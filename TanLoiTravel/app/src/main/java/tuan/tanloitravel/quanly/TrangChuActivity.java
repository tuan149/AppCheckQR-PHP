package tuan.tanloitravel.quanly;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tuan.tanloitravel.api.API;
import tuan.tanloitravel.model.ApiReturnModel;
import tuan.tanloitravel.model.Data;
import tuan.tanloitravel.model.TuyenModel;

public class TrangChuActivity extends AppCompatActivity {

    //Button btnScan;
    TextView tvName;
    Spinner spinGioKH;
    Spinner spinTuyen;
    CalendarView CalNgayKH ;
    API api = new API();
    ArrayAdapter<String> adapterTuyen;
    List<Integer> dsIDTuyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        spinGioKH = (Spinner) findViewById(R.id.spinGioKH);
        CalNgayKH=(CalendarView)findViewById(R.id.calNgayKH);
        spinTuyen=(Spinner) findViewById(R.id.spinTuyen);
        //String ngaykh=dayOfMonth + "-" + month + "-" + year;


        List<String> list = new ArrayList<>();
        list.add("6h00");
        list.add("7h00");
        list.add("8h00");
        list.add("9h00");
        list.add("10h00");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinGioKH.setAdapter(adapter);
        spinGioKH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(TrangChuActivity.this, spinGioKH.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Data.GioKH=spinGioKH.getSelectedItem().toString();
        }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //-------------------------------
        CalNgayKH=(CalendarView)findViewById(R.id.calNgayKH);
        CalNgayKH.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //display the selected date by using a toast
                month++;
                Toast.makeText(getApplicationContext(),  year+ "-" +(month < 10 ? "0" : "")+ month + "-" + (dayOfMonth < 10 ? "0" : "") + dayOfMonth, Toast.LENGTH_LONG).show();
                Data.NgayKH= year+ "-" +(month < 10 ? "0" : "")+ month + "-" + (dayOfMonth < 10 ? "0" : "") + dayOfMonth;
            }
        });
        //--------------------------------

        adapterTuyen = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
        adapterTuyen.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinTuyen.setAdapter(adapterTuyen);
        spinTuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(TrangChuActivity.this, spinTuyenư.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                Data.IdTuyenKH="1";
                Data.TuyenKH=spinTuyen.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

       /* btnScan=(Button)findViewById(R.id.btnScan);
        //final IntentIntegrator intentIntegrator=new IntentIntegrator(this);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intentIntegrator.initiateScan();
                //new IntentIntegrator(TrangChuActivity.this).setCaptureActivity(ScanActivity.class).initiateScan();
                Intent intent=new Intent(TrangChuActivity.this,ScanActivity.class);
                startActivity(intent);
            }
        });*/

    }

    @Override
    protected void onStart() {
        dsIDTuyen = new ArrayList<>();
        adapterTuyen.clear();
        dsIDTuyen.clear();
        api.getTuyenAPI().tuyen(new Callback<List<TuyenModel>>() {
            @Override
            public void success(List<TuyenModel> tuyenModels, Response response) {
                for (TuyenModel item: tuyenModels) {
                    adapterTuyen.add(item.TenTuyen);
                    dsIDTuyen.add(item.ID);
                }
                adapterTuyen.notifyDataSetChanged();

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(TrangChuActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        super.onStart();
    }

    public void kiemtralichchay(View view) {
        int indexSpinner = spinTuyen.getSelectedItemPosition();
        int idTuyen = dsIDTuyen.get(indexSpinner);
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy'T'HH:mm:ss");
        //Toast.makeText(TrangChuActivity.this,idTuyen+"" , Toast.LENGTH_LONG).show();
        //if (1 == 1) return;
        Data.IdTuyenKH=idTuyen+"";

        if(Data.NgayKH==null)
        {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            Data.NgayKH=currentDate;
        }
        //String giokh =Data.GioKH;
        //String ngaykh=Data.NgayKH.toString();
        OpenScan();
    }

    public void OpenScan(){
        Intent intent=new Intent(this,ScanActivity.class);
        startActivity(intent);
    }

}
