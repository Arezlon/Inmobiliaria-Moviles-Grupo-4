package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inmueble;

import java.util.List;

public class ListaInmueblesAdapter extends ArrayAdapter<Inmueble> {
    private Context context;
    private List<Inmueble> inmuebles;
    private LayoutInflater inflater;

    public ListaInmueblesAdapter(@NonNull Context context, int resource, @NonNull List<Inmueble> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.inmuebles = objects;
        this.inflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //super.getView(position, convertView, parent);
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.list_item_inmueble, parent, false);
        Inmueble inmueble = inmuebles.get(position);

        TextView tvDireccion = convertView.findViewById(R.id.tvDireccion);
        TextView tvPrecio = convertView.findViewById(R.id.tvPrecio);
        ImageView ivFoto = convertView.findViewById(R.id.ivFoto);

        tvDireccion.setText(inmueble.getDireccion());
        tvPrecio.setText("$"+String.valueOf(inmueble.getPrecio()));
        Glide.with(getContext()).load(inmueble.getImagen()).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivFoto);

        return convertView;
    }
}
