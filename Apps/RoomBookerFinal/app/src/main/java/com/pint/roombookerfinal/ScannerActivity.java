package com.pint.roombookerfinal;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.zxing.Result;
import com.pint.roombookerfinal.Sala.ReservasSalaActivity;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private static int cam = Camera.CameraInfo.CAMERA_FACING_BACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        int currentapiVersion = Build.VERSION.SDK_INT;
        if(currentapiVersion >= Build.VERSION_CODES.M){
            if (checkPermission()){
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
            }else{
                requestPermission();
            }
        }
    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(ScannerActivity.this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permissions[], int[] grantResult){
        switch (requestCode){
            case REQUEST_CAMERA:
                if (grantResult.length > 0){
                    boolean cameraAccept = grantResult[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccept){
                        Toast.makeText(getApplicationContext(), "Permission Granted By User", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Permission Not Granted By User", Toast.LENGTH_LONG).show();
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)){
                            showMessageOKCancel(
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                            {
                                                requestPermission();
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        int currentApiVersion = Build.VERSION.SDK_INT;
        if(currentApiVersion >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if (scannerView == null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
        scannerView = null;
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener oklistner){
        new AlertDialog.Builder(ScannerActivity.this)
                .setMessage("You need to grant permission")
                .setPositiveButton("OK", oklistner)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {
        final String rawresult = result.getText();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(rawresult);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //scannerView.resumeCameraPreview(ScanActivity.this);
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject) jsonParser.parse(rawresult);
                System.out.println(jsonObject);
                JsonPrimitive jsIdSala = jsonObject.getAsJsonPrimitive("idSala");
                JsonPrimitive jsNSala = jsonObject.getAsJsonPrimitive("nSala");
                int id_sala = Integer.parseInt(String.valueOf(jsIdSala));
                String nSala = String.valueOf(jsNSala);
                Intent intent = new Intent(ScannerActivity.this, ReservasSalaActivity.class);
                intent.putExtra("IdSala", id_sala);
                intent.putExtra("NSala", nSala);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}