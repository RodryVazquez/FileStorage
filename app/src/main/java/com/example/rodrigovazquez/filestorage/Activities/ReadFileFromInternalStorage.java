package com.example.rodrigovazquez.filestorage.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rodrigovazquez.filestorage.R;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFileFromInternalStorage extends AppCompatActivity {

    private TextView internalFileContent;
    private String fileName = "testFile.txt";

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file_from_internal_storage);
        internalFileContent = (TextView) findViewById(R.id.file_content);
        String fileContent = readFileInternalStorage(fileName, this);
        internalFileContent.setText(fileContent);
    }

    /**
     * Lee un archivo de internal storage
     *
     * @param fileName nombre del archivo
     * @param context  contexto actual
     * @return
     */
    public static String readFileInternalStorage(String fileName, Context context) {
        String stringToReturn = " ";
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream);
                BufferedReader bufferedReader =
                        new BufferedReader(inputStreamReader);

                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                stringToReturn = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("TAG", "Can not read file: " + e.toString());
        }
        return stringToReturn;
    }
}
