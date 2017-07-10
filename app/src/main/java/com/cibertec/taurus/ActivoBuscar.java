package com.cibertec.taurus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import dao.ActivoDao;
import utils.DataBaseHelper;
import utils.Utils;

public class ActivoBuscar extends AppCompatActivity {

    private TextView tv;

    View.OnClickListener btnscannerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new IntentIntegrator(ActivoBuscar.this).initiateScan();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activo_buscar);

        findViewById(R.id.btnBuscar).setOnClickListener(btnscannerOnClickListener);

        try {
            new DataBaseHelper(ActivoBuscar.this).createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK) {
            return;
        }

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult == null) {
            return;
        }

        if (scanningResult.getContents() == null || scanningResult.getContents().trim().equals("")) {
            return;
        }

        String id = scanningResult.getContents();

        if (!new ActivoDao(ActivoBuscar.this).existe(id)) {
            Utils.makeToast(this, "El código escaneado no existe en la base de datos");
            return;
        }

        Intent intent = new Intent(ActivoBuscar.this, ActivoMantenimiento.class);
        intent.putExtra("id", id);
        startActivity(intent);

    }
}
