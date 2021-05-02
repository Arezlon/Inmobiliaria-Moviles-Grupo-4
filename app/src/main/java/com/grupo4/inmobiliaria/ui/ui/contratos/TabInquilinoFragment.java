package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inquilino;

public class TabInquilinoFragment extends Fragment {
    private Inquilino inquilino;
    private TextView tv1;

    public TabInquilinoFragment(Inquilino inquilino){
        this.inquilino = inquilino;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tab_inquilino, container, false);
        inicializarVista(root);

        return root;
    }

    private void inicializarVista(View root){
        tv1 = root.findViewById(R.id.tv1);
        tv1.setText(inquilino.getNombre() + " " + inquilino.getApellido());
    }
}