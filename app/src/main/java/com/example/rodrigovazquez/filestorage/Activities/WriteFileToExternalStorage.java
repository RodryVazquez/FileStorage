package com.example.rodrigovazquez.filestorage.Activities;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigovazquez.filestorage.R;
import com.example.rodrigovazquez.filestorage.Util.CommonUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteFileToExternalStorage extends AppCompatActivity {

    private EditText edFileName, edContent;
    Button btnSave;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_file_to_external_storage);
        edFileName = (EditText) findViewById(R.id.enter_fileName);
        edContent = (EditText) findViewById(R.id.enter_file_content);
        btnSave = (Button) findViewById(R.id.save);

        btnSave.setOnClickListener((v) -> writeFileExternalStorage(edFileName.getText().toString(),
                edContent.getText().toString(),
                v.getContext()));
    }

    /**
     * Crea un archivo en el external storage
     * @param fileName
     * @param fileContent
     * @param context
     */
    private void writeFileExternalStorage(String fileName, String fileContent, Context context) {
        try {
            if (CommonUtil.isSdReadable()) {

                String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File mFile = new File(fullPath + File.separator + "/" + fileName);

                FileOutputStream fileOutputStream = new FileOutputStream(mFile);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.append(fileContent);

                //Cerramos los streams
                outputStreamWriter.close();
                fileOutputStream.close();

                Toast.makeText(context, R.string.written_external_storage, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.error_writing_file, Toast.LENGTH_SHORT).show();
        }
    }
}
