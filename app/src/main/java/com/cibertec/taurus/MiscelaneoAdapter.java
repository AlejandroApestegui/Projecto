package com.cibertec.taurus;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import models.Miscelaneo;

/**
 * Created by apesteguia on 10/07/2017.
 */

public class MiscelaneoAdapter extends ArrayAdapter<Miscelaneo> {

    public MiscelaneoAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Miscelaneo>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView tvMiscelaneoDescripcion;
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.miscelaneo_unidad, parent, false);

        Miscelaneo miscelaneo = getItem(position);

        tvMiscelaneoDescripcion = (TextView) convertView.findViewById(R.id.tvMiscelaneoDescripcion);

        tvMiscelaneoDescripcion.setText(miscelaneo.getDescripcion());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
