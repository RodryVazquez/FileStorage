package com.example.rodrigovazquez.filestorage.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rodrigovazquez.filestorage.R;

/**
 *
 */
public class SQLStorage extends AppCompatActivity implements View.OnClickListener {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlstorage);
    }

    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
