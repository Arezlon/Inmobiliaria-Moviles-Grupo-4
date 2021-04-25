package com.grupo4.inmobiliaria.ui.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    public TextView tvUserId;
    public TextView tvUserFullname;
    public TextView tvUserDni;
    public TextView tvUserEmail;
    public TextView tvUserPhone;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                new ViewModelProvider(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);

        InicializarVista(root);
        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                tvUserId.setText("Usuario #"+propietario.getId());
                tvUserFullname.setText(propietario.getApellido()+" "+propietario.getNombre());
                tvUserDni.setText("DNI: "+propietario.getDni());
                tvUserEmail.setText("E-Mail: "+propietario.getEmail());
                tvUserPhone.setText("Tel: "+propietario.getTelefono());
            }
        });
        perfilViewModel.LeerPropietario();

        return root;
    }

    private void InicializarVista(View v){
        tvUserId = v.findViewById(R.id.tvUserId);
        tvUserFullname = v.findViewById(R.id.tvUserFullname);
        tvUserDni = v.findViewById(R.id.tvUserDni);
        tvUserEmail = v.findViewById(R.id.tvUserEmail);
        tvUserPhone = v.findViewById(R.id.tvUserPhone);
    }
}