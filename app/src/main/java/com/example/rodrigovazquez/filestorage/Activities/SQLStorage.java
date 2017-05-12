package com.example.rodrigovazquez.filestorage.Activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    private EditText edtEnterData;
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

        edtEnterData = (EditText)findViewById(R.id.edtEnterData);
        showData = (TextView)findViewById(R.id.txtShowData);
        save = (Button)findViewById(R.id.btnSave);
        show = (Button)findViewById(R.id.btnShow);
        save.setOnClickListener(this);
        show.setOnClickListener(this);
        show.setEnabled(false);

        userData = new EventDataSQLHelper(this);
        Cursor cursor = getEvents();
        if(showData(cursor).length() > 0)
            show.setEnabled(true);
    }

    /**
     * Consulta los datos recorriendo el Cursor
     * @param cursor
     * @return
     */
    public String showData(Cursor cursor){
        StringBuilder builder = new StringBuilder("");
        while(cursor.moveToNext()){
            long id = cursor.getLong(0);
            String name = cursor.getString(1);
            builder.append(id + ": " + name + "\n");
        }
        return builder.toString();
    }

    /**
     * Habilitamos la lectura de la base de datos
     * @return cursor por defecto
     */
    public Cursor getEvents(){
        SQLiteDatabase database = userData.getReadableDatabase();
        Cursor cursor = database.query(EventDataSQLHelper.TABLE,null,null,null,null,null,null,null);
        startManagingCursor(cursor);
        return cursor;
    }

    /**
     * Inserta un registro en la base de datos
     * @param username
     */
    public void enterData(String username){
        SQLiteDatabase database = userData.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventDataSQLHelper.USERNAME, username);
        database.insert(EventDataSQLHelper.TABLE,null,contentValues);
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
     * Cerramos la conexion a la base de datos
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        userData.close();
    }

    /**
     * Escuchamos click de las views
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                //Guardamos el texto capturado en el EditText
                enterData(edtEnterData.getText().toString());
                show.setEnabled(true);
                break;
            case R.id.btnShow:
                //Mostramos los datos en el TextView
                Cursor cursor = getEvents();
                showData.setText(showData(cursor));
                break;
        }
    }
}
