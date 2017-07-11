package com.cibertec.taurus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import dao.ActivoDao;
import dao.MiscelaneoDao;
import models.Activo;
import models.Miscelaneo;
import utils.Utils;

public class ActivoMantenimiento extends AppCompatActivity {

    private EditText etId;
    private EditText etDescripcion;
    private EditText etPeso, etUbicacion, etResponsable, etProveedor, etFecha;
    private Spinner spCCostos, spTipo, spMarca, spEstado;
    private MiscelaneoAdapter costosAdapter, tipoAdapter, marcaAdapter, estadoAdapter;

    private Activo activo;

    private View.OnClickListener btnSeleccionarResponsableOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnSeleccionarProveedorOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnSeleccionarFechaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener btnGuardarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (!validar()) {
                return;
            }
            activo.setCentroCostos(((Miscelaneo) (spCCostos.getSelectedItem())).getId());
            activo.setTipo(((Miscelaneo) (spTipo.getSelectedItem())).getId());
            activo.setMarca(((Miscelaneo) (spMarca.getSelectedItem())).getId());
            activo.setEstado(((Miscelaneo) (spEstado.getSelectedItem())).getId());
            activo.setDescripcion(etDescripcion.getText().toString().trim());
            activo.setUbicacion(etUbicacion.getText().toString().trim());
            activo.setPeso(Double.parseDouble(etPeso.getText().toString().trim()));

            new ActivoDao(ActivoMantenimiento.this).actualizar(activo);

        }
    };

    private boolean validar() {
        String mensaje = "Ingresar : ";
        boolean valida = true;
        if (TextUtils.isEmpty(etDescripcion.getText())) {
            mensaje += " descripcion,";
            valida = false;
        }
        if (((Miscelaneo) (spCCostos.getSelectedItem())).getId() == 0) {
            mensaje += " centro de costos,";
            valida = false;
        }
        if (((Miscelaneo) (spTipo.getSelectedItem())).getId() == 0) {
            mensaje += " tipo,";
            valida = false;
        }
        if (((Miscelaneo) (spMarca.getSelectedItem())).getId() == 0) {
            mensaje += " marca,";
            valida = false;
        }
        if (((Miscelaneo) (spEstado.getSelectedItem())).getId() == 0) {
            mensaje += " estado,";
            valida = false;
        }
        if (TextUtils.isEmpty(etPeso.getText())) {
            mensaje += " peso,";
            valida = false;
        }
        if (TextUtils.isEmpty(etUbicacion.getText())) {
            mensaje += " ubicación,";
            valida = false;
        }
        if (activo.getResponsable().trim().equals("")) {
            mensaje += " responsable,";
            valida = false;
        }
        if (activo.getProveedor().trim().equals("")) {
            mensaje += " proveedor,";
            valida = false;
        }
        if (TextUtils.isEmpty(etFecha.getText())) {
            mensaje += " fecha";
            valida = false;
        }

        if (!valida) {
            Utils.makeToast(this, mensaje.substring(0, mensaje.length() - 1));
        }
        return valida;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activo_mantenimiento);

        inflarVistas();
        asignarAdapter();
        cargarDatos();


    }

    private void cargarDatos() {

        activo = new ActivoDao(ActivoMantenimiento.this).buscarPorId(getIntent().getStringExtra("id"));

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
        for (int i = 0; i < sp.getAdapter().getCount(); i++) {
            if (((Miscelaneo) (sp.getAdapter().getItem(i))).getId().intValue() == centroCostos.intValue()) {
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
        findViewById(R.id.btnSeleccionarFecha).setOnClickListener(btnSeleccionarFechaOnClickListener);
        findViewById(R.id.btnSeleccionarProveedor).setOnClickListener(btnSeleccionarProveedorOnClickListener);
        findViewById(R.id.btnSeleccionarResponsable).setOnClickListener(btnSeleccionarResponsableOnClickListener);
    }
}
