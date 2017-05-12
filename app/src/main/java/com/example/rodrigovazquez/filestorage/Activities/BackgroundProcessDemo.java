package com.example.rodrigovazquez.filestorage.Activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rodrigovazquez.filestorage.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class BackgroundProcessDemo extends AppCompatActivity {

    private Button btnStartProcess, btnSimulateProcess;
    private ImageView imageView;
    private ProgressDialog progressDialog;

    private static final String URL_IMAGE = "https://developer.android.com/images/android-7.0/mw-portrait.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background_process_demo);
        btnStartProcess = (Button) findViewById(R.id.btnStartProcess);
        btnSimulateProcess = (Button) findViewById(R.id.btnSimulateProcess);
        imageView = (ImageView) findViewById(R.id.imageView);

        btnStartProcess.setOnClickListener((view) -> {
            downloadImage(URL_IMAGE);
        });

        btnSimulateProcess.setOnClickListener((view) -> {

        });
    }

    /**
     * @param url
     */
    private void downloadImage(String url) {

        progressDialog = new ProgressDialog(BackgroundProcessDemo.this);
        progressDialog.setTitle("Downloading");
        progressDialog.setMessage("Download in progress...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();

        new Thread(() -> {
            Bitmap bitmap = getBitmapFromNetwork(url);
            if (bitmap != null) {
                imageView.post(() -> {
                    progressDialog.dismiss();
                    imageView.setImageBitmap(bitmap);
                });
            } else {
                runOnUiThread(() -> {
                    progressDialog.dismiss();
                    Toast.makeText(BackgroundProcessDemo.this, "Have a problem when download the image", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }

    /**
     * @param url
     * @return
     */
    private Bitmap getBitmapFromNetwork(String url) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TAG", "Error " + e.getMessage());
        }
        return null;
    }
}
