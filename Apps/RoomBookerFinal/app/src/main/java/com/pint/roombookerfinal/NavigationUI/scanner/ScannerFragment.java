package com.pint.roombookerfinal.NavigationUI.scanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.Sala.ReservasSalaActivity;
import com.pint.roombookerfinal.databinding.FragmentScannerBinding;

import java.io.IOException;

public class ScannerFragment extends Fragment {

    private FragmentScannerBinding binding;
    private ScannerViewModel mViewModel;
    CameraSource cameraSource;
    BarcodeDetector qrCodeDetector;
    SurfaceView surfaceView;
    TextView txtQRCodeValue;
    String intentData;

    private static final int REQUEST_CAMERA_PERMISSION = 201;

    public static ScannerFragment newInstance() {
        return new ScannerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentScannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        surfaceView = root.findViewById(R.id.surfaceView);
        txtQRCodeValue = root.findViewById(R.id.txtQRcodeValue);

        qrCodeDetector = new BarcodeDetector.Builder(root.getContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        //you should add this feature
        cameraSource = new CameraSource.Builder(root.getContext(), qrCodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(root.getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        qrCodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getContext(), "To prevent memory leaks qrCode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(@NonNull Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if(barcodes.size()!=0){
                    txtQRCodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            if(barcodes.valueAt(0)!=null)
                            {
                                txtQRCodeValue.removeCallbacks(null);
                                intentData = barcodes.valueAt(0).rawValue;
                                JsonParser jsonParser = new JsonParser();
                                JsonObject jsonObject = (JsonObject) jsonParser.parse(intentData);
                                System.out.println(jsonObject);
                                JsonPrimitive jsIdSala = jsonObject.getAsJsonPrimitive("idSala");
                                JsonPrimitive jsNSala = jsonObject.getAsJsonPrimitive("nSala");
                                int id_sala = Integer.parseInt(String.valueOf(jsIdSala));
                                String nSala = String.valueOf(jsNSala);
                                Intent intent = new Intent(getContext(), ReservasSalaActivity.class);
                                intent.putExtra("IdSala", id_sala);
                                intent.putExtra("NSala", nSala);
                                getContext().startActivity(intent);
                            }
                        }
                    });
                }
            }
        });

        return root;
    }
}