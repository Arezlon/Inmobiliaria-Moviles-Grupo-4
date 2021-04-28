package com.grupo4.inmobiliaria.ui.ui.contratos;

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
import com.grupo4.inmobiliaria.ui.ui.inmuebles.InmueblesViewModel;
import com.grupo4.inmobiliaria.ui.ui.inmuebles.ListaInmueblesAdapter;

import java.util.ArrayList;


public class ContratosFragment extends Fragment {

    private ContratosViewModel contratosViewModel;
    private ListView lvContratos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contratosViewModel =
                new ViewModelProvider(this).get(ContratosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contratos, container, false);

        InicializarVista(root);
        contratosViewModel.getInmueblesMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {

                ArrayAdapter<Inmueble> adapter = new ListaInmueblesAdapter(getContext(),
                        R.layout.list_item_inmueble, inmuebles,
                        getLayoutInflater(), R.id.nav_contrato);

                lvContratos.setAdapter(adapter);
            }
        });
        contratosViewModel.LeerInmueblesAlquilados();

        return root;
    }

    private void InicializarVista(View root){
        lvContratos = root.findViewById(R.id.lvContratos);
    }
}