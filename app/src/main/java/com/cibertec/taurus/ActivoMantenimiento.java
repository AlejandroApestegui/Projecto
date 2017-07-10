package com.cibertec.taurus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import dao.ActivoDao;
import dao.MiscelaneoDao;
import models.Activo;
import models.Miscelaneo;
import utils.Utils;

public class ActivoMantenimiento extends AppCompatActivity {

    private EditText etId, etDescripcion, etPeso, etUbicacion, etResponsable, etProveedor, etFecha;
    private Spinner spCCostos, spTipo, spMarca, spEstado;
    private MiscelaneoAdapter costosAdapter, tipoAdapter, marcaAdapter, estadoAdapter;

    private View.OnClickListener btnGuardarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activo_mantenimiento);

        inflarVistas();
        asignarAdapter();
        cargarDatos();


    }

    private void cargarDatos() {

        Activo activo = new ActivoDao(ActivoMantenimiento.this).buscarPorId(getIntent().getStringExtra("id"));

        etId.setText(activo.getId());
        etDescripcion.setText(activo.getDescripcion());
        etPeso.setText("" + activo.getPeso());
        etUbicacion.setText(activo.getUbicacion());
        etProveedor.setText(activo.getProveedorNombre());
        etResponsable.setText(activo.getResponsableNombre());
        etFecha.setText(activo.getFechaCompra());

        seleccionarSpinner(spCCostos, activo.getCentroCostos());
        seleccionarSpinner(spEstado, activo.getEstado());
        seleccionarSpinner(spMarca, activo.getMarca());
        seleccionarSpinner(spTipo, activo.getTipo());

    }

    private void seleccionarSpinner(Spinner sp, Integer centroCostos) {
        for(int i = 0;i<sp.getAdapter().getCount();i++){
            if(((Miscelaneo)(sp.getAdapter().getItem(i))).getId().intValue()==centroCostos.intValue()){
                sp.setSelection(i);
            }
        }
    }

    private void asignarAdapter() {

        Miscelaneo miscelaneo = new Miscelaneo(0, Utils.SELECCIONAR);

        costosAdapter = new MiscelaneoAdapter(ActivoMantenimiento.this);
        costosAdapter.add(miscelaneo);
        tipoAdapter = new MiscelaneoAdapter(ActivoMantenimiento.this);
        tipoAdapter.add(miscelaneo);
        marcaAdapter = new MiscelaneoAdapter(ActivoMantenimiento.this);
        marcaAdapter.add(miscelaneo);
        estadoAdapter = new MiscelaneoAdapter(ActivoMantenimiento.this);
        estadoAdapter.add(miscelaneo);

        spCCostos.setAdapter(costosAdapter);
        spTipo.setAdapter(tipoAdapter);
        spMarca.setAdapter(marcaAdapter);
        spEstado.setAdapter(estadoAdapter);

        MiscelaneoDao dao = new MiscelaneoDao(ActivoMantenimiento.this);

        costosAdapter.addAll(dao.listar(MiscelaneoDao.CENTROCOSTOS));
        tipoAdapter.addAll(dao.listar(MiscelaneoDao.TIPO));
        marcaAdapter.addAll(dao.listar(MiscelaneoDao.MARCA));
        estadoAdapter.addAll(dao.listar(MiscelaneoDao.ESTADO));

    }

    private void inflarVistas() {
        etId = (EditText) findViewById(R.id.etId);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        etPeso = (EditText) findViewById(R.id.etPeso);
        etUbicacion = (EditText) findViewById(R.id.etUbicacion);
        etResponsable = (EditText) findViewById(R.id.etResponsable);
        etProveedor = (EditText) findViewById(R.id.etProveedor);
        etFecha = (EditText) findViewById(R.id.etFecha);

        spCCostos = (Spinner) findViewById(R.id.spCCostos);
        spTipo = (Spinner) findViewById(R.id.spTipo);
        spMarca = (Spinner) findViewById(R.id.spMarca);
        spEstado = (Spinner) findViewById(R.id.spEstado);

        findViewById(R.id.btnGuardar).setOnClickListener(btnGuardarOnClickListener);
    }
}
