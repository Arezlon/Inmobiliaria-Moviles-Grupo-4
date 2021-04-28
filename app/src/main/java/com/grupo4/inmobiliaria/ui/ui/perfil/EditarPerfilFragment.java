package com.grupo4.inmobiliaria.ui.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.grupo4.inmobiliaria.MainActivity;
import com.grupo4.inmobiliaria.R;
import com.grupo4.inmobiliaria.modelo.Propietario;

public class EditarPerfilFragment extends Fragment {

    private EditarPerfilViewModel mViewModel;

    public static EditarPerfilFragment newInstance() {
        return new EditarPerfilFragment();
    }

    private EditarPerfilViewModel editarPerfilViewModel;
    public TextView tvUsuarioId;
    public EditText etEditarNombreUsuario;
    public EditText etEditarApellidoUsuario;
    public EditText etEditarDniUsuario;
    public EditText etEditarEmailUsuario;
    public EditText etEditarTelefonoUsuario;
    public Button btGuardar;
    private Propietario propietarioActual;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        editarPerfilViewModel = new ViewModelProvider(this).get(EditarPerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_editar_perfil, container, false);

        InicializarVista(root);
        editarPerfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                propietarioActual = propietario;
                tvUsuarioId.setText("Editando el usuario: #"+propietario.getId());
                etEditarNombreUsuario.setText(propietario.getNombre());
                etEditarApellidoUsuario.setText(propietario.getApellido());
                etEditarDniUsuario.setText(String.valueOf(propietario.getDni()));
                etEditarEmailUsuario.setText((propietario.getEmail()));
                etEditarTelefonoUsuario.setText(propietario.getTelefono());
            }
        });
        editarPerfilViewModel.ObtenerPropietario();

        return root;
    }

    private void InicializarVista(View v){
        tvUsuarioId = v.findViewById(R.id.tvUsuarioId);
        etEditarNombreUsuario = v.findViewById(R.id.etEditarNombreUsuario);
        etEditarApellidoUsuario = v.findViewById(R.id.etEditarApellidoUsuario);
        etEditarDniUsuario = v.findViewById(R.id.etEditarDniUsuario);
        etEditarEmailUsuario = v.findViewById(R.id.etEditarEmailUsuario);
        etEditarTelefonoUsuario = v.findViewById(R.id.etEditarTelefonoUsuario);
        btGuardar = v.findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                propietarioActual.setNombre(etEditarNombreUsuario.getText().toString());
                propietarioActual.setApellido(etEditarApellidoUsuario.getText().toString());
                propietarioActual.setDni(Long.parseLong(etEditarDniUsuario.getText().toString()));
                propietarioActual.setEmail(etEditarEmailUsuario.getText().toString());
                propietarioActual.setTelefono(etEditarTelefonoUsuario.getText().toString());

                editarPerfilViewModel.ModificarPropietario(propietarioActual);
                //Navigation.findNavController((Activity)getContext(), R.id.nav_host_fragment).navigate(R.id.nav_perfil);
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });
    }

}