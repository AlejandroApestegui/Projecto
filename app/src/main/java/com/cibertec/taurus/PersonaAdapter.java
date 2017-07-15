package com.cibertec.taurus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import models.Persona;

/**
 * Created by apesteguia on 12/07/2017.
 */

public class PersonaAdapter extends ArrayAdapter<Persona> {

    public PersonaAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Persona>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvDescripcion;
        TextView tvId;
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.persona_unidad, parent, false);

        Persona persona = getItem(position);

        tvDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcion);
        tvId = (TextView) convertView.findViewById(R.id.tvId);

        tvDescripcion.setText(persona.getDescripcion());
        tvId.setText(persona.getId());

        return convertView;
    }
}
