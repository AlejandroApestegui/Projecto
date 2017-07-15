package dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cibertec.taurus.ActivoMantenimiento;

import java.util.ArrayList;
import java.util.List;

import models.Persona;
import utils.DataBaseHelper;

/**
 * Created by apesteguia on 12/07/2017.
 */

public class PersonaDao {

    private final String PROVEEDOR_TABLA = "PROVEEDORES";
    private final String PROVEEDOR_COL_ID = "PROVEEDOR";
    private final String PROVEEDOR_COL_DES = "RAZONSOCIAL";

    private final String RESPONSABLE_TABLA = "RESPONSABLES";
    private final String RESPONSABLE_COL_ID = "RESPONSABLE";
    private final String RESPONSABLE_COL_DES = "NOMBRECOMPLETO";


    private Context context;
    private SQLiteDatabase db;


    public PersonaDao(Context context) {
        db = new DataBaseHelper(context).openDataBase();
        this.context = context;
    }

    public List<Persona> listarPersonas(int tipo, Persona persona) {

        Cursor cursor;
        List<Persona> lst = new ArrayList<>();

        if (persona == null) {
            cursor = db.query(tipo == ActivoMantenimiento.PROVEEDOR ? PROVEEDOR_TABLA : RESPONSABLE_TABLA, null, null, null, null, null, null);
        } else {

            String filtro = "%" + persona.getId() + "%";


            String query = "SELECT * FROM " + (tipo == ActivoMantenimiento.PROVEEDOR ? PROVEEDOR_TABLA : RESPONSABLE_TABLA)
                    + " where " + (tipo == ActivoMantenimiento.PROVEEDOR ? PROVEEDOR_COL_ID : RESPONSABLE_COL_ID) + " like '" + filtro + "' or "
                    + (tipo == ActivoMantenimiento.PROVEEDOR ? PROVEEDOR_COL_DES : RESPONSABLE_COL_DES) + " like '" + filtro + "'";
            cursor = db.rawQuery(query, null);
        }

        if (cursor.moveToFirst()) {
            do {
                lst.add(cursorToPersona(cursor));
            }
            while (cursor.moveToNext());

        }


        return lst;
    }

    private Persona cursorToPersona(Cursor cursor) {
        Persona persona = new Persona();
        persona.setId(cursor.isNull(0) ? "" : cursor.getString(0));
        persona.setDescripcion(cursor.isNull(1) ? "" : cursor.getString(1));
        return persona;
    }
}
