package com.example.rodrigovazquez.filestorage.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigovazquez.filestorage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StoringDataInCache extends AppCompatActivity implements View.OnClickListener {

    private EditText enterData;
    private TextView loadData;
    private Button save, show;
    private final String TEMP_FILE_NAME = "cacheFile.txt";
    private File tempFile;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storing_data_in_cache);

        enterData = (EditText) findViewById(R.id.edtEnterData);
        save = (Button) findViewById(R.id.btnSaveData);
        show = (Button) findViewById(R.id.btnShowData);
        loadData = (TextView)findViewById(R.id.txtLoadData);

        save.setOnClickListener(this);
        show.setOnClickListener(this);
        show.setEnabled(false);

        //Obtenemos el directorio del cache interno
        File fileCache = getBaseContext().getCacheDir();
        //Obtenemos la referecia temporal del archivo
        tempFile = new File(fileCache.getPath() + "/" + TEMP_FILE_NAME);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveData:
                SaveData();
                show.setEnabled(true);
                break;
            case R.id.btnShowData:
                String content = ShowData();
                loadData.setText(content);
                break;
        }
    }

    /**
     * Guarda el archivo en cache
     */
    private void SaveData() {
        //Ideal para archivos de pocos caracteres
        FileWriter writer = null;
        try {
            writer = new FileWriter(tempFile);
            writer.write(enterData.getText().toString());
            //Cerramos el writer
            writer.close();
            Toast.makeText(this, R.string.file_saved_in_cache, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
    }

    /**
     * Lee la cadena del archivo almacenado en cache
     */
    private String ShowData() {
        String readContent = "";
        StringBuilder builder = new StringBuilder();
        /*Leemos el contenido del archivo temporal si ya existe*/
        try {
            FileReader reader = new FileReader(tempFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            /* Leemos el contenido del archivo linea por linea*/
            while ((readContent = bufferedReader.readLine()) != null) {
                builder.append(readContent + "\n");
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", e.getMessage());
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
        return builder.toString();
    }
}
