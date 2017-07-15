package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import models.Miscelaneo;
import utils.DataBaseHelper;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class MiscelaneoDao {

    private SQLiteDatabase db;

    private final String TABLA = "MISCELANEOSDETALLE";
    private final String COL_MISCELANEO = "MISCELANEO";
    private final String COL_CODIGOELEMENTO = "CODIGOELEMENTO";
    private final String COL_DESCRIPCION = "DESCRIPCION";

    public static final Integer ESTADO = 1;
    public static final Integer MARCA = 2;
    public static final Integer TIPO = 3;
    public static final Integer CENTROCOSTOS = 4;


    private Context context;

    public MiscelaneoDao(Context context) {
        db = new DataBaseHelper(context).openDataBase();
        this.context = context;
    }

    public List<Miscelaneo> listar(Integer id) {

        List<Miscelaneo> lista = new ArrayList<Miscelaneo>();

        Cursor cursor = db.query(TABLA, null, COL_MISCELANEO + " = ? ", new String[]{String.valueOf(id.intValue())}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursorToMiscelaneo(cursor));
            }
            while (cursor.moveToNext());
        }

        return lista;
    }


    private Miscelaneo cursorToMiscelaneo(Cursor cursor) {
        Miscelaneo miscelaneo = new Miscelaneo();

        miscelaneo.setId(cursor.isNull(cursor.getColumnIndex(COL_CODIGOELEMENTO)) ? 0 : cursor.getInt(cursor.getColumnIndex(COL_CODIGOELEMENTO)));
        miscelaneo.setDescripcion(cursor.isNull(cursor.getColumnIndex(COL_DESCRIPCION)) ? "" : cursor.getString(cursor.getColumnIndex(COL_DESCRIPCION)));

        return miscelaneo;
    }
}
