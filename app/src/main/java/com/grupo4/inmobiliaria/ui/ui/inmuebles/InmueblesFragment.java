package com.grupo4.inmobiliaria.ui.ui.inmuebles;

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


public class InmueblesFragment extends Fragment {

    private InmueblesViewModel inmueblesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inmueblesViewModel =
                new ViewModelProvider(this).get(InmueblesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inicio, container, false);

        return root;
    }
}