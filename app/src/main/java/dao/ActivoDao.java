package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import models.Activo;
import utils.DataBaseHelper;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class ActivoDao {

    private SQLiteDatabase db;

    private final String TABLA = "ACTIVOS";
    private final String COL_ID = "ACTIVO";
    private final String COL_DESCRIPCION = "DESCRIPCION";
    private final String COL_CENTROCOSTOS = "CENTROCOSTOS";
    private final String COL_TIPO = "TIPO";
    private final String COL_PESO = "PESO";
    private final String COL_MARCA = "MARCA";
    private final String COL_ESTADO = "ESTADO";
    private final String COL_UBICACION = "UBICACION";
    private final String COL_RESPONSABLE = "RESPONSABLE";
    private final String COL_FECHACOMPRA = "FECHACOMPRA";
    private final String COL_PROVEEDOR = "PROVEEDOR";

    private Context context;

    public ActivoDao(Context context) {
        db = new DataBaseHelper(context).openDataBase();
        this.context = context;
    }

    public boolean existe(String id) {

        Cursor cursor = db.query(TABLA, new String[]{COL_ID}, COL_ID + " = ?", new String[]{id}, null, null, null);

        if (cursor.moveToFirst()) {
            return true;
        }

        return false;
    }

    public Activo buscarPorId(String id) {

        Cursor cursor = db.rawQuery("SELECT * FROM ACTIVOS A JOIN PROVEEDORES B ON A.PROVEEDOR = B.PROVEEDOR JOIN RESPONSABLES C ON A.RESPONSABLE = C.RESPONSABLE where activo = " + id, null);

        if (cursor.moveToFirst()) {
            return cursorToActivo(cursor);
        }

        return new Activo();

    }


    public void actualizar(Activo activo) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_CENTROCOSTOS, activo.getCentroCostos());
        contentValues.put(COL_DESCRIPCION, activo.getDescripcion());
        contentValues.put(COL_ESTADO, activo.getEstado());
        contentValues.put(COL_FECHACOMPRA, activo.getFechaCompra());
        contentValues.put(COL_TIPO, activo.getTipo());
        contentValues.put(COL_MARCA, activo.getMarca());
        contentValues.put(COL_PESO, activo.getPeso());
        contentValues.put(COL_UBICACION, activo.getUbicacion());
        contentValues.put(COL_PROVEEDOR, activo.getProveedor());
        contentValues.put(COL_RESPONSABLE, activo.getResponsable());

        db.update(TABLA, contentValues, COL_ID + " = ?", new String[]{activo.getId()});
    }

    private Activo cursorToActivo(Cursor cursor) {
        Activo activo = new Activo();

        activo.setId(cursor.isNull(0) ? "" : cursor.getString(0));
        activo.setDescripcion(cursor.isNull(1) ? "" : cursor.getString(1));
        activo.setCentroCostos(cursor.isNull(2) ? 0 : cursor.getInt(2));
        activo.setTipo(cursor.isNull(3) ? 0 : cursor.getInt(3));
        activo.setPeso(cursor.isNull(4) ? 0.00 : cursor.getDouble(4));
        activo.setMarca(cursor.isNull(5) ? 0 : cursor.getInt(5));
        activo.setEstado(cursor.isNull(6) ? 0 : cursor.getInt(6));
        activo.setUbicacion(cursor.isNull(7) ? "" : cursor.getString(7));
        activo.setResponsableNombre(cursor.isNull(14) ? "" : cursor.getString(14));
        activo.setResponsable(cursor.isNull(13) ? "" : cursor.getString(13));
        activo.setProveedorNombre(cursor.isNull(12) ? "" : cursor.getString(12));
        activo.setProveedor(cursor.isNull(11) ? "" : cursor.getString(11));
        activo.setFechaCompra(cursor.isNull(9) ? "" : cursor.getString(9));

        return activo;
    }
}
