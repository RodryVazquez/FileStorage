package com.example.rodrigovazquez.filestorage.Activities;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.rodrigovazquez.filestorage.R;
import com.example.rodrigovazquez.filestorage.Util.CommonUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileFromExternalStorage extends AppCompatActivity {

    private TextView externalStorageContent;
    private String fileName = "testFile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file_from_external_storage);
        externalStorageContent = (TextView) findViewById(R.id.file_content);

        String fileContent = readFileExternalStorage(fileName);
        externalStorageContent.setText(fileContent);
    }

    /**
     * Lee un archivo del external storage
     *
     * @param fileName
     * @return
     */
    public static String readFileExternalStorage(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        if (CommonUtil.isSdReadable()) {

            try {
                File sdCard = Environment.getExternalStorageDirectory();
                File file = new File(sdCard, fileName);

                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    System.out.println("text : " + stringBuilder + " end");
                    stringBuilder.append("\n");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
