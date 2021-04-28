package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Contrato;
import com.grupo4.inmobiliaria.modelo.Inmueble;
import com.grupo4.inmobiliaria.ui.ui.inmuebles.InmuebleViewModel;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private TextView tvFechaInicio, tvFechaFin, tvInquilino, tvInmueble, tvMonto;

    private Contrato contrato;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contratoViewModel =
                new ViewModelProvider(this).get(ContratoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contrato, container, false);

        InicializarVista(root);

        contratoViewModel.getContratoMutable().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged (Contrato contrato) {
                tvFechaInicio.setText(contrato.getFechaInicio());
                tvFechaFin.setText(contrato.getFechaFin());
                tvMonto.setText(contrato.getMontoAlquiler() + "");
                tvInquilino.setText(contrato.getInquilino().getNombre() + " "+ contrato.getInquilino().getApellido());
                tvInmueble.setText(contrato.getInmueble().getDireccion());
            }
        });
        contratoViewModel.LeerContrato(getArguments());
        return root;
    }

    private void InicializarVista(View root){
        tvFechaInicio = root.findViewById(R.id.tvFechaInicio);
        tvFechaFin = root.findViewById(R.id.tvFechaFin);
        tvMonto = root.findViewById(R.id.tvMontoAlquiler);
        tvInquilino = root.findViewById(R.id.tvInquilino);
        tvInmueble = root.findViewById(R.id.tvInmueble);
    }
}

