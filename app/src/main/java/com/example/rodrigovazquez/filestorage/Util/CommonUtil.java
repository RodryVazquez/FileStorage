package com.example.rodrigovazquez.filestorage.Util;

import android.os.Environment;
import android.util.Log;

public class CommonUtil {

    /**
     * Indica si la sd esta disponible para Escritura/Lectura
     *
     * @return
     */
    public static boolean isSdReadable() {
        boolean mExternalStorageAvailable = false;
        try {
            String state =
                    Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state))
                //Podemos leer y ecribir
                mExternalStorageAvailable = true;
            else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
                //Podemos leer unicamente
                mExternalStorageAvailable = true;
            else
                mExternalStorageAvailable = false;
        } catch (Exception ex) {
            Log.e("TAG", ex.getMessage());
        }
        return mExternalStorageAvailable;
    }
}
