package com.grupo4.inmobiliaria.ui.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Inmueble;

import java.util.ArrayList;


public class InmueblesFragment extends Fragment {

    private InmueblesViewModel inmueblesViewModel;
    private ListView lvInmuebles;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inmueblesViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inmuebles, container, false);

        InicializarVista(root);
        inmueblesViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                ArrayAdapter<Inmueble> adapter = new ListaInmueblesAdapter(getContext(), R.layout.list_item_inmueble, inmuebles, getLayoutInflater(), R.id.nav_inmueble);
                lvInmuebles.setAdapter(adapter);
            }
        });
        inmueblesViewModel.LeerInmuebles();

        return root;
    }

    private void InicializarVista(View root){
        lvInmuebles = root.findViewById(R.id.lvInmuebles);
    }
}