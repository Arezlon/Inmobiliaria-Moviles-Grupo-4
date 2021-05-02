package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.modelo.Pago;

import java.util.List;

public class ListaPagosAdapter extends ArrayAdapter<Pago> {
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater inflater;

    public ListaPagosAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects, LayoutInflater layoutInflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.inflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = convertView != null ? convertView : inflater.inflate(R.layout.list_item_pago, parent, false);
        Pago pago = pagos.get(position);

        TextView tvPago = convertView.findViewById(R.id.tvPago);

        tvPago.setText(String.valueOf(pago.getImporte()));

        return convertView;
    }
}