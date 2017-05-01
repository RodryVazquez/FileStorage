package com.example.rodrigovazquez.filestorage.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rodrigovazquez.filestorage.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteFileToInternalStorage extends AppCompatActivity {

    private EditText edFileName, edContent;
    Button btnSave;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_file_to_internal_storage);
        edFileName = (EditText) findViewById(R.id.enter_fileName);
        edContent = (EditText) findViewById(R.id.enter_file_content);

        btnSave = (Button) findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeFileInternalStorage(new Data(edFileName.getText().toString(),
                                edContent.getText().toString()),
                        v.getContext());
            }
        });
    }

    /**
     *
     * @param mData
     * @param mContext
     */
    private void writeFileInternalStorage(Data mData, Context mContext) {
        FileOutputStream fos = null;
        try {
            //Abrimos el archivo o lo creamos si no existe
            fos = mContext.openFileOutput(mData.getFileName(), Context.MODE_PRIVATE);
            byte[] ds = mData.getContent().getBytes();
            fos.write(ds);
            fos.flush();
        } catch (FileNotFoundException e) {
            Toast.makeText(mContext, R.string.file_not_found, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(mContext, R.string.error_writing_file, Toast.LENGTH_SHORT).show();
        } finally {
            //Cerramos el Stream y liberamos los recursos asociados a el
            if (fos != null) {
                try {
                    fos.close();
                    Toast.makeText(mContext, R.string.success_write_internal_memory, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(mContext, R.string.error_when_close_stream, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private class Data {

        public String fileName;
        public String content;

        public Data() {
        }

        public Data(String fileName, String content) {
            this.fileName = fileName;
            this.content = content;
        }

        public String getFileName() {
            return fileName;
        }
        public String getContent() {
            return content;
        }
    }
}
