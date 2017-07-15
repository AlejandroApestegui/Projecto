package com.cibertec.taurus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import dao.PersonaDao;
import models.Persona;

public class PersonaSelector extends AppCompatActivity {

    private ListView lvPersonas;
    private EditText etFiltro;
    private PersonaAdapter personaAdapter;

    private View.OnClickListener btnBuscarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Persona persona = new Persona();
            if (TextUtils.isEmpty(etFiltro.getText())) {
                persona = null;
            } else {
                persona.setId(etFiltro.getText().toString().trim());
            }
            personaAdapter.clear();
            personaAdapter.addAll(new PersonaDao(PersonaSelector.this).listarPersonas(getIntent().getIntExtra("tipo", 0),
                    persona));

        }
    };
    private AdapterView.OnItemLongClickListener lvPersonasOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            Intent intent = new Intent();
            intent.putExtra("id", ((Persona) (personaAdapter.getItem(position))).getId());
            intent.putExtra("descripcion", ((Persona) (personaAdapter.getItem(position))).getDescripcion());
            setResult(RESULT_OK, intent);
            finish();

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona_selector);

        lvPersonas = (ListView) findViewById(R.id.lvPersonas);
        etFiltro = (EditText) findViewById(R.id.etFiltro);

        int tipo = getIntent().getIntExtra("tipo", 0);

        if (tipo == 0) {
            finish();
        }

        findViewById(R.id.btnBuscar).setOnClickListener(btnBuscarOnClickListener);
        lvPersonas.setOnItemLongClickListener(lvPersonasOnItemLongClickListener);
        personaAdapter = new PersonaAdapter(PersonaSelector.this);
        lvPersonas.setAdapter(personaAdapter);
        personaAdapter.addAll(new PersonaDao(PersonaSelector.this).listarPersonas(tipo, null));
    }
}
