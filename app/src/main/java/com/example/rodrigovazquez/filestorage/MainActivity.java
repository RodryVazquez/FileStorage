package com.example.rodrigovazquez.filestorage;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rodrigovazquez.filestorage.Activities.ReadFileFromExternalStorage;
import com.example.rodrigovazquez.filestorage.Activities.ReadFileFromInternalStorage;
import com.example.rodrigovazquez.filestorage.Activities.SQLStorage;
import com.example.rodrigovazquez.filestorage.Activities.StoringDataInCache;
import com.example.rodrigovazquez.filestorage.Activities.WriteFileToExternalStorage;
import com.example.rodrigovazquez.filestorage.Activities.WriteFileToInternalStorage;

//Actividad base
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button writeFileToInternalStorage,
            readFileFromInternalStorage,
            writeFileToExternalStorage,
            readFileFromExternalStorage,
            saveAndReadInCache,
            saveAndReadSQLStorage;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeFileToInternalStorage = (Button)findViewById(R.id.btnWriteToInternalStorage);
        readFileFromInternalStorage = (Button)findViewById(R.id.btnReadFromInternalStorage);
        writeFileToExternalStorage = (Button)findViewById(R.id.btnWriteToExternalStorage);
        readFileFromExternalStorage = (Button)findViewById(R.id.btnReadFromExternalStorage);
        saveAndReadInCache = (Button)findViewById(R.id.btnStoreInCache);
        saveAndReadSQLStorage = (Button)findViewById(R.id.btnSqlStorage);

        writeFileToInternalStorage.setOnClickListener(this);
        readFileFromInternalStorage.setOnClickListener(this);
        writeFileToExternalStorage.setOnClickListener(this);
        readFileFromExternalStorage.setOnClickListener(this);
        saveAndReadInCache.setOnClickListener(this);
        saveAndReadSQLStorage.setOnClickListener(this);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnWriteToInternalStorage:
                intent = new Intent(this, WriteFileToInternalStorage.class);
                break;
            case R.id.btnReadFromInternalStorage:
                intent = new Intent(this, ReadFileFromInternalStorage.class);
                break;
            case R.id.btnWriteToExternalStorage:
                intent = new Intent(this, WriteFileToExternalStorage.class);
                break;
            case R.id.btnReadFromExternalStorage:
                intent = new Intent(this, ReadFileFromExternalStorage.class);
                break;
            case R.id.btnStoreInCache:
                intent = new Intent(this, StoringDataInCache.class);
                break;
            case R.id.btnSqlStorage:
                intent = new Intent(this, SQLStorage.class);
                break;
        }
        startActivity(intent);
    }
}
