package com.example.mk.proyectofinal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class Login_QR extends Activity {

    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private TextView barcodeInfo;
    public static int mesa=1;
    Intent i = null;
    static ProgressDialog mProgressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_qr);
        //getPreferences();

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        barcodeInfo = (TextView) findViewById(R.id.code_info);

        barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.QR_CODE)
                        .build();

        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();


        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(getApplication(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException ie) {
                    Log.e("CAMERA SOURCE", ie.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();



                if (barcodes.size() != 0) {

                    String qr;
                    qr = String.valueOf(barcodes.valueAt(0).displayValue).substring(0, 1);
                    if (qr.equals("1")) {
                       // cameraView.setVisibility(View.INVISIBLE);

                        mesa=Integer.parseInt(qr);


                     lanzarActivity();


                    }


                    barcodeInfo.post(new Runnable() {    // Use the post method of the TextView
                        public void run() {

                            barcodeInfo.setText(    // Update the TextView
                                    barcodes.valueAt(0).displayValue


                            );

                        }
                    });


                }
            }
        });

    }

    public void lanzarActivity() {
        // Toast.makeText(Login_QR.this, "Bienvenido." ,Toast.LENGTH_SHORT).show();

        if (i==null) {

            i = new Intent(this, MainActivity.class);
            startActivity(i);

            mProgressDialog.dismiss();
            Toast.makeText(Login_QR.this, "Bienvenido. Sentado en mesa: "+mesa, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
        barcodeDetector.release();
    }

    public void comprobarQR() {

        if (barcodeInfo.getText().toString().equals("1")) {
            Toast.makeText(Login_QR.this, "Bienvenido. Sentado en ", Toast.LENGTH_SHORT).show();
            lanzarActivity();

        }
    }


    public void getPreferences() {
        int name = 0;
        SharedPreferences prefs = getSharedPreferences("estado", MODE_PRIVATE);
        int restoredText = Integer.parseInt(prefs.getString("id_pedido", "0"));
        if (restoredText !=0) {
            name = prefs.getInt("id_pedido", 0);//"No name defined" is the default value.
            if (name>0){
                lanzarActivity();
                finish();
            }
            //0 is the default value.
        } else {

        }
        // Toast.makeText(MainActivity.this, "getPreferences: "+name, Toast.LENGTH_SHORT).show();
    }
}