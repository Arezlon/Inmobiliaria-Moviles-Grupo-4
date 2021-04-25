package com.grupo4.inmobiliaria.ui.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo4.inmobiliaria.R;


public class ContratosFragment extends Fragment {

    private ContratosViewModel contratosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contratosViewModel =
                new ViewModelProvider(this).get(ContratosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inicio, container, false);

        return root;
    }
}