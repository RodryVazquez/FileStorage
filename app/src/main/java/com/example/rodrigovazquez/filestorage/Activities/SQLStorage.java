package com.example.rodrigovazquez.filestorage.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rodrigovazquez.filestorage.R;
import com.example.rodrigovazquez.filestorage.Util.EventDataSQLHelper;

/**
 *
 */
public class SQLStorage extends AppCompatActivity implements View.OnClickListener {

    private EditText enterData;
    private TextView showData;
    private Button save, show;
    EventDataSQLHelper userData;

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
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     *
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Escuchamos click de las views
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                break;
            case R.id.btnShow:
                break;
        }
    }
}
