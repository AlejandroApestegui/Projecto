package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class Utils {

    public static final String SELECCIONAR = "--Seleccionar--";

    public static void makeToast(Context context, String mensaje) {
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }
}
